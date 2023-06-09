package com.example.caltracker.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caltracker.models.PaymentData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class ServiceDataViewModel : ViewModel (){

    val state = mutableStateOf(PaymentData())

    init {
        getServiceData()
    }

    private fun getServiceData(){
        viewModelScope.launch {
            state.value = getServiceDataFromFireStore()

        }
    }


}


//retrieve data
suspend fun getServiceDataFromFireStore(): PaymentData {

    val db = FirebaseFirestore.getInstance()
    var data = PaymentData()


    try {

        db.collection("RoadHelp ServicePayment").get().await().map {
            val result = it.toObject(PaymentData::class.java)
            data = result

        }
    } catch (e: FirebaseFirestoreException) {
        Log.d("error", "getDataFromFireStore: $e")
    }
    return data
}
