package com.gracemyanmar.myapplication.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.google.android.material.snackbar.Snackbar
import com.gracemyanmar.myapplication.R
import com.gracemyanmar.myapplication.constant.ALERADY_REGISTER_CODE
import com.gracemyanmar.myapplication.constant.STATUSBAR
import com.gracemyanmar.myapplication.constant.SUCCESS_CODE
import com.gracemyanmar.myapplication.network.DataImpl
import com.gracemyanmar.myapplication.network.register.RegisterResponse
import com.gracemyanmar.myapplication.network.register.UserVO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    var userVO: UserVO = UserVO()
    lateinit var registerResponse: Call<RegisterResponse>

    lateinit var name_et: EditText
    lateinit var email_et: EditText
    lateinit var phone_et: EditText
    lateinit var password_et: EditText
    lateinit var signup_btn: Button
    lateinit var already_user_txt: TextView
    lateinit var linear_layout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = STATUSBAR.toColorInt()
        setContentView(R.layout.activity_register)

        name_et = findViewById(R.id.name_et)
        email_et = findViewById(R.id.email_et)
        phone_et = findViewById(R.id.phone_et)
        password_et = findViewById(R.id.password_et)
        signup_btn = findViewById(R.id.singup_btn)
        already_user_txt = findViewById(R.id.already_user_txt)

        linear_layout = findViewById(R.id.linearLayout)

        setUpListener()
    }

    private fun setUpListener() {

        signup_btn.setOnClickListener {
            userVO.name = name_et.text.toString()
            userVO.email = email_et.text.toString()
            userVO.password = password_et.text.toString()
            userVO.phone = phone_et.text.toString()
            userVO.reg_id = 1
            userVO.role = 1
            registerUsers(userVO)
        }

        already_user_txt.setOnClickListener {
            startActivity(LoginActivity.newIntent(this))
            finish()
        }
    }

    private fun registerUsers(userVO: UserVO) {
        registerResponse = DataImpl.userApi.registerUsers(userVO)
        registerResponse.enqueue(object : Callback<RegisterResponse> {
            override fun onFailure(call: Call<RegisterResponse>?, t: Throwable?) {
                Snackbar.make(linear_layout, R.string.error_msg, Snackbar.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<RegisterResponse>?,
                response: Response<RegisterResponse>?
            ) {
                val response = response!!.body()
                if (response.statusCode == SUCCESS_CODE && response != null) {
                    startActivity(LoginActivity.newIntent(this@RegisterActivity))
                    finish()
                } else if (response.statusCode == ALERADY_REGISTER_CODE) {
                    Snackbar.make(linear_layout, R.string.already_exit_error, Snackbar.LENGTH_LONG)
                        .show()
                } else if (response.name == null || response.email == null || response.phone == null || response.password == null) {
                    Snackbar.make(linear_layout, R.string.empty_data_error, Snackbar.LENGTH_LONG)
                        .show()
                }
            }

        })
    }
}