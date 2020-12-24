package com.gracemyanmar.myapplication.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import com.google.android.material.snackbar.Snackbar
import com.gracemyanmar.myapplication.R
import com.gracemyanmar.myapplication.constant.PASSWORD_ERROR_CODE
import com.gracemyanmar.myapplication.constant.RECORD_ERROR_CODE
import com.gracemyanmar.myapplication.constant.STATUSBAR
import com.gracemyanmar.myapplication.constant.SUCCESS_CODE
import com.gracemyanmar.myapplication.network.DataImpl
import com.gracemyanmar.myapplication.network.login.LoginVO
import com.gracemyanmar.myapplication.network.login.LoginVOResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    var loginVO: LoginVO = LoginVO()
    lateinit var loginVOResponse: Call<LoginVOResponse>

    lateinit var login_email_et: TextView
    lateinit var login_password_et: EditText
    lateinit var login_btn: Button
    lateinit var register_txt: TextView
    lateinit var linear_layout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = STATUSBAR.toColorInt()
        setContentView(R.layout.activity_login)

        login_email_et = findViewById(R.id.login_email_et)
        login_password_et = findViewById(R.id.login_password_et)
        login_btn = findViewById(R.id.login_btn)
        register_txt = findViewById(R.id.register_txt)
        linear_layout = findViewById(R.id.linearLayout)

        setUpListener()
    }

    private fun setUpListener() {
        register_txt.setOnClickListener {
            startActivity(RegisterActivity.newIntent(this))
        }

        login_btn.setOnClickListener {
            loginVO.username = login_email_et.text.toString()
            loginVO.password = login_password_et.text.toString()
            loginUsers(loginVO)
        }
    }

    private fun loginUsers(loginVO: LoginVO) {
        loginVOResponse = DataImpl.userApi.login(loginVO)
        loginVOResponse.enqueue(object : Callback<LoginVOResponse> {
            override fun onFailure(call: Call<LoginVOResponse>?, t: Throwable?) {
                Snackbar.make(linear_layout, R.string.error_msg, Snackbar.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<LoginVOResponse>?, response: Response<LoginVOResponse>?) {
                val response = response!!.body()
                if (response.statusCode == 200 && response != null && response.loginModel?.status == 1) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("name", response.loginModel?.name)
                    intent.putExtra("phone", response.loginModel?.phone)
                    startActivity(intent)
                    finish()
                } else if (response.statusCode == 200 && response != null || response.loginModel?.status == 0) {
                    Snackbar.make(linear_layout, R.string.status_error, Snackbar.LENGTH_LONG).show()
                } else if (response!!.statusCode == PASSWORD_ERROR_CODE) {
                    Snackbar.make(linear_layout, R.string.password_error, Snackbar.LENGTH_LONG)
                        .show()
                } else if (response.statusCode == RECORD_ERROR_CODE) {
                    Snackbar.make(linear_layout, R.string.record_error, Snackbar.LENGTH_LONG).show()
                } else if (response.loginModel?.email == null || response.loginModel?.password == null) {
                    Snackbar.make(linear_layout, R.string.empty_data_error, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}


/*private fun loginUsers(loginVO: LoginVO) {
        loginResponse = DataImpl.userApi.loginUsers(loginVO)
        loginResponse.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                Snackbar.make(linear_layout, R.string.error_msg, Snackbar.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<LoginResponse>?,
                response: Response<LoginResponse>?
            ) {
                val response = response!!.body()
                if (response.statusCode == 200 && response != null) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("name", response.)
                    startActivity(intent)
                    finish()
                } else if (response!!.statusCode == PASSWORD_ERROR_CODE) {
                    Snackbar.make(linear_layout, R.string.password_error, Snackbar.LENGTH_LONG)
                        .show()
                } else if (response.statusCode == RECORD_ERROR_CODE) {
                    Snackbar.make(linear_layout, R.string.record_error, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }*/


//var userRoleVO: UserRoleVO = UserRoleVO()
//lateinit var userTypeResponse: Call<UserTypeResponse>
//intent.putExtra("role name", roleName)
/*private fun showUserRole(role: Int) {
        userTypeResponse = DataImpl.userApi.showUserType(role)
        userTypeResponse.enqueue(object : Callback<UserTypeResponse> {
            override fun onFailure(call: Call<UserTypeResponse>?, t: Throwable?) {
                Snackbar.make(linear_layout, t?.localizedMessage.toString(), Snackbar.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<UserTypeResponse>?, response: Response<UserTypeResponse>?) {
                val response = response!!.body()
                if (response.statusCode == SUCCESS_CODE && response != null) {
                    roleName = response.userRoleName.toString()
                }
            }
        })

    }*/

/*private fun userRole(role: Int, userTypeVO: UserTypeVO) {
    userTypeResponse = DataImpl.userApi.showUserType(userTypeVO)
    userTypeResponse.enqueue(object : Callback<UserTypeResponse> {
        override fun onFailure(call: Call<UserTypeResponse>?, t: Throwable?) {
            Snackbar.make(linear_layout, R.string.error_msg, Snackbar.LENGTH_LONG).show()
        }

        override fun onResponse(call: Call<UserTypeResponse>?, response: Response<UserTypeResponse>?) {
            val response = response!!.body()
            if (response.statusCode == 200 && response != null && role == 4) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("userRole", response.username)
                startActivity(intent)
                finish()
            } else if (response!!.statusCode == PASSWORD_ERROR_CODE) {
                Snackbar.make(linear_layout, R.string.password_error, Snackbar.LENGTH_LONG).show()
            } else if (response.statusCode == RECORD_ERROR_CODE) {
                Snackbar.make(linear_layout, R.string.record_error, Snackbar.LENGTH_LONG).show()
            }
        }
    })
}*/