package com.catnip.rizkyilmann_challange4

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class MainViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }
}
