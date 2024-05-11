package com.example.metasearch_compose.firebase_parts

import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
//import kotlin.random.Random

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
        .addOnFailureListener{
            Log.d(ContentValues.TAG,"Inside_OnFailureListene")
            Log.d(ContentValues.TAG,"Exception: ${it.message}")
            Log.d(ContentValues.TAG,"Exception: ${it.localizedMessage}")
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

//private fun codeGenerator() : Int{
//    val random = Random.Default
//    return (100000..999999).random(random)
//}

