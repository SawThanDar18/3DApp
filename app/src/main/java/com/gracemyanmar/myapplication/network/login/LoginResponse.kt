package com.gracemyanmar.myapplication.network.login

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("code")
    var statusCode: Int? = null

    @SerializedName("name")
    var username: String? = null
    var password: String? = null

}