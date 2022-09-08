package com.example.fleetech.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fleetech.R
import com.example.fleetech.databinding.ActivityLogin2Binding
import com.example.fleetech.databinding.ActivityOtpactivityBinding
import com.example.fleetech.retrofit.Apiuitils
import com.example.fleetech.retrofit.RetrofitClient
import com.example.fleetech.retrofit.model.UpdateModel
import com.example.fleetech.retrofit.response.VerifyNewRespone
import com.example.fleetech.util.Session
import com.example.fleetech.viewModel.LoginViewModel
import com.example.fleetech.viewModel.UserRegisterViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class LoginActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityLogin2Binding
    lateinit var viewModel: LoginViewModel
    lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLogin2Binding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        session = Session(applicationContext)
        setContentView(mBinding.root)

        mBinding.btnOTP.setOnClickListener {
            if (mBinding.editTextNumberPassword.length() < 4) {
                Toast.makeText(this, "Please enter 4 digit Pass!!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.getUserLoginData(
                    session.mobile,
                    mBinding.editTextNumberPassword.text.toString(),

                    )
            }
        }
        observeData()
    }

    fun observeData() {
        viewModel.loading.observe(this, Observer {
            if (it) {
                mBinding.progressBar2.visibility = View.VISIBLE
            } else {
                mBinding.progressBar2.visibility = View.GONE

            }
        })
        viewModel.verifyList.observe(this, Observer {
            Log.d("Json data", "Json data" + it.jUserProfile.get(0).UserType)
            if (it.success) {
                if (it.jUserProfile.get(0).UserType.equals("A")) {
                    val intent = Intent(this, AdminActivity::class.java)
                    intent.putExtra("DriverList", it.jData as Serializable)
                    intent.putExtra("comingFrom", "Login")
                    startActivity(intent)
                    finish()
                } else if (it.jUserProfile.get(0).UserType.equals("F")) {
                    val intent = Intent(this, AdminActivity::class.java)
                    intent.putExtra("DriverList", it.jData as Serializable)
                    intent.putExtra("comingFrom", "Login")
                    startActivity(intent)
                    finish()
                } else {
                    getDriverData(it.jUserProfile.get(0).MobileNo, it.jUserProfile.get(0).Pwd)
                }

            } else {
                Toast.makeText(this, "Something went wrong!!", Toast.LENGTH_SHORT).show()

            }

        })
    }

    fun getDriverData(mobileNo: String, otp: String) {
        RetrofitClient.retrofit = null
        val userAPI = Apiuitils.testUrl
        val call = userAPI.verifyPWD(UpdateModel(mobileNo, otp))
        call.enqueue(object : Callback<VerifyNewRespone> {
            override fun onResponse(
                call: Call<VerifyNewRespone>,
                response: Response<VerifyNewRespone>
            ) {
                if (response.code() == 200) {
                    session.keyToken = response.body()!!.jToken
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(
                call: Call<VerifyNewRespone>,
                t: Throwable
            ) {
//                loading.value = false

            }
        })
    }

}