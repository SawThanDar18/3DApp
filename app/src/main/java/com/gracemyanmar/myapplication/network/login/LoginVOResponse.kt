package com.gracemyanmar.myapplication.network.login

import com.google.gson.annotations.SerializedName
import com.gracemyanmar.myapplication.network.login.LoginModel

class LoginVOResponse  {
    @SerializedName("data")
    var loginModel: LoginModel? = null

    @SerializedName("code")
    var statusCode: Int? = null
}