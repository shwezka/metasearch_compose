package com.example.metasearch_compose.firebase_parts

import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

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