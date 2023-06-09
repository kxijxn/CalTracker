package com.example.caltracker.viewModel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.caltracker.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class UserRegistrationViewModel() : ViewModel() {

    fun addDataToFirebase(
        name: String,
        email: String,
        phone: String,
        context: android.content.Context
    ) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid;

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()


        val dbUsers: CollectionReference = db.collection("RoadHelp Users")

        val userDetails = Users(name,email,phone, uid.toString())

        if (uid != null) {
            dbUsers.document(uid).set(userDetails).addOnSuccessListener {

                Toast.makeText(
                    context,
                    "Your data has been added to Firebase Firestore",
                    Toast.LENGTH_SHORT
                ).show()

            }.addOnFailureListener { e ->
                Toast.makeText(context, "Fail to add data \n$e", Toast.LENGTH_SHORT).show()
            }
        }

    }

}