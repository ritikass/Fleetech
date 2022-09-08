package com.example.fleetech.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fleetech.retrofit.Apiuitils
import com.example.fleetech.retrofit.RetrofitClient
import com.example.fleetech.retrofit.model.OTPModel
import com.example.fleetech.retrofit.model.UpdateModel
import com.example.fleetech.retrofit.response.ResendOtpResponse
import com.example.fleetech.retrofit.response.UpdatePWDResponse
import com.example.fleetech.retrofit.response.VerifyNewRespone
import com.example.fleetech.retrofit.response.VerifyPWDResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminViewModel:ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val verifyList = MutableLiveData<VerifyNewRespone>()
    val resendOtpList = MutableLiveData<ResendOtpResponse>()

    val loading = MutableLiveData<Boolean>()
    val requestFriendList = ObservableField<OTPModel>()
    private val progressbarObservable: MutableLiveData<Boolean>? = null

    fun getDriverData(mobileNo: String, otp: String) {
        RetrofitClient.retrofit = null
        val userAPI = Apiuitils.testUrl
        println("MObile No Admin $mobileNo $otp")
        loading.value = true
        val call = userAPI.verifyPWD(UpdateModel( mobileNo, otp))
        call.enqueue(object : Callback<VerifyNewRespone> {
            override fun onResponse(
                call: Call<VerifyNewRespone>,
                response: Response<VerifyNewRespone>
            ) {
                if (response.code() == 200) {
                    verifyList.postValue(response.body())
                    loading.value = false
                    println("data check Admin ${verifyList}" + response.body())
                }
            }

            override fun onFailure(
                call: Call<VerifyNewRespone>,
                t: Throwable
            ) {
                loading.value = false

            }
        })
    }
}