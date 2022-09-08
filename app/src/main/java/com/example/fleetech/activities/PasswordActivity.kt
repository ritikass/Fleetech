package com.example.fleetech.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fleetech.databinding.ActivityPasswordBinding
import com.example.fleetech.retrofit.Apiuitils
import com.example.fleetech.retrofit.RetrofitClient
import com.example.fleetech.retrofit.model.UpdateModel
import com.example.fleetech.retrofit.response.VerifyNewRespone
import com.example.fleetech.util.Session
import com.example.fleetech.viewModel.UpdatePWDViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class PasswordActivity : AppCompatActivity() {
    private var mobileNo: String? = ""
    private lateinit var mBinding: ActivityPasswordBinding
    lateinit var viewModel: UpdatePWDViewModel
    lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPasswordBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(UpdatePWDViewModel::class.java)
        mobileNo = intent.getStringExtra("mobileNo");
        session = Session(applicationContext)
        setContentView(mBinding.root)
        mBinding.submitBtn.setOnClickListener {
            if (mBinding.textView4.length() < 4 && mBinding.textView6.length() < 4) {
                Toast.makeText(this, "Please enter 4 digit Pass!!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.getUserPWDData(
                    mobileNo.toString(),
                    mBinding.textView4.text.toString(),

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
        viewModel.updatePWDList.observe(this, Observer {
            if (it.success) {
                session.createLoginSession(mobileNo, it.jUserProfile.get(0).Pwd)
                session.mobile= mobileNo
                if (it.jUserProfile.get(0).UserType.equals("A")) {
                    val intent = Intent(this, AdminActivity::class.java)
                    intent.putExtra("DriverList", it.jData as Serializable)
                    intent.putExtra("comingFrom","Register")
                    startActivity(intent)
                } else if(it.jUserProfile.get(0).UserType.equals("F")){
                    val intent = Intent(this, AdminActivity::class.java)
                    intent.putExtra("DriverList", it.jData as Serializable)
                    intent.putExtra("comingFrom","Register")
                    startActivity(intent)
                }else{
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
                    val intent = Intent(this@PasswordActivity, MainActivity::class.java)
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