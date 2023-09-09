package com.yakasoftware.zerotech.classes

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserDatabase {
    class FirestoreDatabase {

        val auth = Firebase.auth
        val user = auth.currentUser!!
        private val userEmail = user!!.email
        val db = Firebase.firestore
        fun addUserData(key: String, value: Any?){
            val currentUser = FirebaseAuth.getInstance().currentUser
            currentUser?.let {
                val user = hashMapOf<String, Any?>(key to value)
                db.collection("users").document(userEmail!!)
                    .set(user, SetOptions.merge()) // SetOptions.merge() sadece key değerlerini günceller
                    .addOnSuccessListener {
                        println("DocumentSnapshot added")
                    }
                    .addOnFailureListener { e ->
                        println("Error adding document ${e.message}")
                    }
            }

        }

    }
}