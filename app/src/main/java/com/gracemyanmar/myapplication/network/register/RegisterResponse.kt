package com.gracemyanmar.myapplication.network.register

import com.google.gson.annotations.SerializedName

class RegisterResponse {

    @SerializedName("code")
    var statusCode: Int? = null

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var phone: String? = null
    var reg_id: Int? = null
    var role: Int? = null
}