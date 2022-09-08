package com.example.fleetech.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fleetech.retrofit.Apiuitils
import com.example.fleetech.retrofit.RetrofitClient
import com.example.fleetech.retrofit.model.OTPModel
import com.example.fleetech.retrofit.response.DriverDocuments
import com.example.fleetech.retrofit.response.ProfileResponse
import com.example.fleetech.retrofit.response.ResendOtpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DocumentViewModel : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val docList = MutableLiveData<DriverDocuments>()

    val resendOtpList = MutableLiveData<ResendOtpResponse>()

    val loading = MutableLiveData<Boolean>()
    val requestFriendList = ObservableField<OTPModel>()
    private val progressbarObservable: MutableLiveData<Boolean>? = null

    fun getDriverDoc(token: String) {
        RetrofitClient.retrofit = null
        val userAPI = Apiuitils.testUrl
        println("MObile No $token")
        loading.value = true
        val call = userAPI.driverDocuments("Bearer " + token)
        call.enqueue(object : Callback<DriverDocuments> {
            override fun onResponse(
                call: Call<DriverDocuments>,
                response: Response<DriverDocuments>
            ) {
                if (response.code() == 200) {
                    docList.postValue(response.body())
                    loading.value = false
                }
            }

            override fun onFailure(
                call: Call<DriverDocuments>,
                t: Throwable
            ) {
                loading.value = false

            }
        })
    }


}