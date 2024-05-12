package com.example.metasearch_compose.firebase_parts

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.metasearch_compose.parts.Users
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.storage
import com.google.firebase.storage.storageMetadata

//import kotlin.random.Random


val storage = FirebaseStorage.getInstance()
val db = FirebaseFirestore.getInstance()

val storagePath = "gs://metasearch-compose.appspot.com/"
internal fun createFirebaseAccount(
    email: String,
    password: String
) {
    FirebaseAuth
        .getInstance()
        .createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener{
            Log.d(ContentValues.TAG,"Inside_OnCompleteListener")
            Log.d(ContentValues.TAG,"Is succesful ${it.isSuccessful}")
        }
        .addOnFailureListener {
            Log.d(ContentValues.TAG, "Inside_OnFailureListene")
            Log.d(ContentValues.TAG, "Exception: ${it.message}")
            Log.d(ContentValues.TAG, "Exception: ${it.localizedMessage}")
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
            Log.d(ContentValues.TAG,"Inside_OnCompleteListener")
            Log.d(ContentValues.TAG,"Is succesful ${it.isSuccessful}")
        }
        .addOnFailureListener{
            Log.d(ContentValues.TAG,"Inside_OnFailureListene")
            Log.d(ContentValues.TAG,"Exception: ${it.message}")
            Log.d(ContentValues.TAG,"Exception: ${it.localizedMessage}")
        }
}

internal fun sendRecoveryEmail(
    email: String
){
    FirebaseAuth.getInstance()
        .sendPasswordResetEmail(email)
        .addOnCompleteListener{
            Log.d(ContentValues.TAG,"Inside_OnCompleteListener")
            Log.d(ContentValues.TAG,"Is succesful ${it.isSuccessful}")
        }
        .addOnFailureListener{
            Log.d(ContentValues.TAG,"Inside_OnFailureListene")
            Log.d(ContentValues.TAG,"Exception: ${it.message}")
            Log.d(ContentValues.TAG,"Exception: ${it.localizedMessage}")
        }
}

internal fun addUserData(user: Users){
    val currentUser = FirebaseAuth.getInstance().currentUser
    val userId = currentUser?.uid
    val userRef = db.collection("users").document(userId!!)

    userRef.set(
        mapOf(
            "name" to user.name,
            "phone" to user.phone,
            "birthdate" to user.birthDate
        )
    ).addOnSuccessListener {
        Log.d(TAG, "User data updated successfully")
    }.addOnFailureListener { e ->
        Log.w(TAG, "Error updating user data", e)
    }

    addImageToStorage(user.pictureUri)
    val imageURL = getProfileImageURL()

    userRef.update(
        mapOf(
            "profileImageURL" to imageURL.toString()
        )
    ).addOnSuccessListener {
        Log.d(TAG, "Profile image added to user successfully")
    }.addOnFailureListener { e ->
        Log.w(TAG, "Error adding profile image to user", e)
    }

}

internal fun addImageToStorage(
    image: Uri,

){
    val storageRef = storage.reference
    val imageRef = storageRef.child(storagePath)

    val uploadTask: UploadTask = imageRef.putFile(image)

    uploadTask.addOnSuccessListener {
        Log.d(TAG, "Profile image uploaded successfully")
    }.addOnFailureListener { e ->
        Log.w(TAG, "Error uploading profile image", e)
    }
}

fun getProfileImageURL(): Task<Uri> {
    val storageRef = storage.reference
    val imageRef = storageRef.child(storagePath)

    return imageRef.getDownloadUrl()
}

//private fun codeGenerator() : Int{
//    val random = Random.Default
//    return (100000..999999).random(random)
//}

