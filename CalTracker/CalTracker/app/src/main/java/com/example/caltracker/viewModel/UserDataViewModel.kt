package com.example.caltracker.viewModel
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caltracker.models.UsersData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class UserDataViewModel ():  ViewModel() {

val state = mutableStateOf(UsersData())

    init {
        getData()
    }

    private fun getData(){
        viewModelScope.launch {
            state.value = getDataFromFireStore()

        }
    }

}


//retrieve data
suspend fun getDataFromFireStore(): UsersData {

    val db = FirebaseFirestore.getInstance()
    var data = UsersData()


    try {

        db.collection("RoadHelp Users").get().await().map {
            val result = it.toObject(UsersData::class.java)
            data = result

        }
    } catch (e: FirebaseFirestoreException) {
        Log.d("error", "getDataFromFireStore: $e")
    }
    return data
}




