package com.example.caltracker.viewModel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.caltracker.models.ServicePayment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class ServiceViewModel : ViewModel(){
    fun addServicePaymentToFirebase(
        service:String,
        mechanic:String,
        payment:String,
        context: android.content.Context
    ) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid;

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        val dbUsers: CollectionReference = db.collection("RoadHelp ServicePayment")

        val serviceDetails = ServicePayment(service,mechanic, payment, uid.toString())

        if (uid != null) {
            dbUsers.document(uid).set(serviceDetails).addOnSuccessListener {

                Toast.makeText(
                    context,
                    "Payment has been received!",
                    Toast.LENGTH_SHORT
                ).show()

            }.addOnFailureListener { e ->

                Toast.makeText(context, "Fail to add data \n$e", Toast.LENGTH_SHORT).show()
            }
        }

    }
}