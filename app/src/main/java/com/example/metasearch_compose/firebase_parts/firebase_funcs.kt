package com.example.metasearch_compose.firebase_parts

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import com.example.metasearch_compose.parts.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


val storage = FirebaseStorage.getInstance()
val db = FirebaseFirestore.getInstance()

val storagePath = "gs://metasearch-compose.appspot.com/profile_pics/${FirebaseAuth.getInstance().currentUser?.uid}"

internal fun createFirebaseAccount(
    email: String,
    password: String
) {
    FirebaseAuth
        .getInstance()
        .createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener{
            Log.d(TAG,"Inside_OnCompleteListener")
            Log.d(TAG,"Is succesful ${it.isSuccessful}")
        }
        .addOnFailureListener {
            Log.d(TAG, "Inside_OnFailureListene")
            Log.d(TAG, "Exception: ${it.message}")
            Log.d(TAG, "Exception: ${it.localizedMessage}")
        }
}

internal fun login(
    email: String,
    password: String
){
    FirebaseAuth
        .getInstance()
        .signInWithEmailAndPassword(email, password)
        .addOnCompleteListener{
            Log.d(TAG,"Inside_OnCompleteListener")
            Log.d(TAG,"Is succesful ${it.isSuccessful}")
        }
        .addOnFailureListener{
            Log.d(TAG,"Inside_OnFailureListene")
            Log.d(TAG,"Exception: ${it.message}")
            Log.d(TAG,"Exception: ${it.localizedMessage}")
        }
}

internal fun sendRecoveryEmail(
    email: String
){
    FirebaseAuth.getInstance()
        .sendPasswordResetEmail(email)
        .addOnCompleteListener{
            Log.d(TAG,"Inside_OnCompleteListener")
            Log.d(TAG,"Is succesful ${it.isSuccessful}")
        }
        .addOnFailureListener{
            Log.d(TAG,"Inside_OnFailureListene")
            Log.d(TAG,"Exception: ${it.message}")
            Log.d(TAG,"Exception: ${it.localizedMessage}")
        }
}

internal suspend fun addUserData(user: Users) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val userId = currentUser?.uid
    val userRef = db.collection("users").document(userId!!)

    // Загружаем изображение в хранилище Firebase и получаем URL
    val imageUrl = uploadImageAndGetUrl(user.pictureUri)

    // Обновляем данные пользователя в Firestore
    try {
        userRef.set(
            mapOf(
                "name" to user.name,
                "phone" to user.phone,
                "birthdate" to user.birthDate,
                "profileImageURL" to imageUrl
            )
        ).await()
        Log.d(TAG, "User data updated successfully")
    } catch (e: Exception) {
        Log.w(TAG, "Error updating user data", e)
    }
}

internal suspend fun uploadImageAndGetUrl(imageUri: String): String {
    return suspendCoroutine { continuation ->
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid
        val storageRef = storage.reference
        val imageRef = storageRef.child("profile_pics/${FirebaseAuth.getInstance().currentUser?.uid}_profile_pic")

        val uploadTask = imageRef.putFile(Uri.parse(imageUri))

        uploadTask.addOnSuccessListener { _ ->
            Log.d(TAG, "Profile image uploaded successfully")
            imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                val imageUrl = downloadUri.toString()
                Log.d(TAG, "Profile image URL: $imageUrl")
                continuation.resume(imageUrl)
            }.addOnFailureListener { e ->
                Log.w(TAG, "Error getting download URL", e)
                continuation.resume("")
            }
        }.addOnFailureListener { e ->
            Log.w(TAG, "Error uploading profile image", e)
            continuation.resume("")
        }
    }
}



fun getDataFromDB(callback: (Users) -> Unit) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    db.collection("users").document(userId!!).get()
        .addOnSuccessListener { result ->
            val name = result.getString("name") ?: ""
            val phone = result.getString("phone") ?: ""
            val birthdate = result.getString("birthdate") ?: ""
            val pictureURI = result.getString("profileImageURL")?: ""

            val userData = Users(name, phone, birthdate, pictureURI)
            callback(userData)
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error getting user data", e)
        }
}

//private fun codeGenerator() : Int{
//    val random = Random.Default
//    return (100000..999999).random(random)
//}

