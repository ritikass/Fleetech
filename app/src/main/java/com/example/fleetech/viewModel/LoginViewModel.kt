package com.example.fleetech.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fleetech.retrofit.Apiuitils
import com.example.fleetech.retrofit.RetrofitClient
import com.example.fleetech.retrofit.model.OTPModel
import com.example.fleetech.retrofit.model.UpdateModel
import com.example.fleetech.retrofit.response.OTPResponse
import com.example.fleetech.retrofit.response.ResendOtpResponse
import com.example.fleetech.retrofit.response.UpdatePWDResponse
import com.example.fleetech.retrofit.response.VerifyPWDResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel:ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val verifyList = MutableLiveData<UpdatePWDResponse>()
    val resendOtpList = MutableLiveData<ResendOtpResponse>()

    val loading = MutableLiveData<Boolean>()
    val requestFriendList = ObservableField<OTPModel>()
    private val progressbarObservable: MutableLiveData<Boolean>? = null

    fun getUserLoginData(mobileNo: String, otp: String) {
        RetrofitClient.retrofit = null
        val userAPI = Apiuitils.testUrl
        println("MObile No $mobileNo")
        loading.value = true
        val call = userAPI.userUpdatePWd(UpdateModel( mobileNo, otp))
        call.enqueue(object : Callback<UpdatePWDResponse> {
            override fun onResponse(
                call: Call<UpdatePWDResponse>,
                response: Response<UpdatePWDResponse>
            ) {
                loading.value = false

                if (response.code() == 200) {
                    verifyList.postValue(response.body())
                    println("data check Login ${verifyList}" + response.body())
                }
            }

            override fun onFailure(
                call: Call<UpdatePWDResponse>,
                t: Throwable
            ) {
                loading.value = false

            }
        })
    }

}