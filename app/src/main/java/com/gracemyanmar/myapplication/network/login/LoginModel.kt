package com.gracemyanmar.myapplication.network.login

import com.google.gson.annotations.SerializedName

data class LoginModel(

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("phone")
    var phone: String? = null,

    @SerializedName("status")
    var status: Int? = null,

    @SerializedName("role")
    var role: Int? = null,

    var password: String? = null
)