package com.example.metasearch_compose.firebase_parts

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import com.example.metasearch_compose.parts.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
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

internal fun addUserData(user: Users){
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid
        val userRef = db.collection("users").document(userId!!)
        val storageRef = storage.reference
        val imageRef = storageRef.child("profile_pics/${FirebaseAuth.getInstance().currentUser?.uid}_profile_pic")

        val uploadTask: UploadTask = imageRef.putFile(Uri.parse(user.pictureUri))

        uploadTask.addOnSuccessListener { uploadTaskSnapshot ->
            Log.d(TAG, "Profile image uploaded successfully")

            // Получаем ссылку на загруженное изображение
            imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                val profileImageURL = downloadUri.toString()

                // Обновляем данные пользователя и ссылку на профильное изображение в базе данных Firestore
                userRef.set(
                    mapOf(
                        "name" to user.name,
                        "phone" to user.phone,
                        "birthdate" to user.birthDate,
                        "profileImageURL" to profileImageURL
                    )
                ).addOnSuccessListener {
                    Log.d(TAG, "User data updated successfully")
                }.addOnFailureListener { e ->
                    Log.w(TAG, "Error updating user data", e)
                }

            }.addOnFailureListener { e ->
                Log.w(TAG, "Error getting download URL", e)
            }
        }.addOnFailureListener { e ->
            Log.w(TAG, "Error uploading profile image", e)
        }
}

internal fun addImageToStorage(
    image: String,
) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val userId = currentUser?.uid
    val userRef = db.collection("users").document(userId!!)
    val storageRef = storage.reference
    val imageRef = storageRef.child("profile_pics/${FirebaseAuth.getInstance().currentUser?.uid}_profile_pic")

    val uploadTask: UploadTask = imageRef.putFile(Uri.parse(image))

    uploadTask.addOnSuccessListener { uploadTaskSnapshot ->
        Log.d(TAG, "Profile image uploaded successfully")

// Получаем ссылку на загруженное изображение
        imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
            val profileImageURL = downloadUri.toString()

// Обновляем ссылку на профильное изображение в базе данных Firestore
            userRef.update("profileImageURL", profileImageURL)
                .addOnSuccessListener {
                    Log.d(TAG, "Profile image URL updated successfully in Firestore")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error updating profile image URL in Firestore", e)
                }
        }.addOnFailureListener { e ->
            Log.w(TAG, "Error getting download URL", e)
        }
    }.addOnFailureListener { e ->
        Log.w(TAG, "Error uploading profile image", e)
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

