package com.example.metasearch_compose

import android.app.Application
import com.google.firebase.FirebaseApp

class LoginThengy : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}