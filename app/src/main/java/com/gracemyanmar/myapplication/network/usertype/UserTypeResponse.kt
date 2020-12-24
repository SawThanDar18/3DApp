package com.gracemyanmar.myapplication.network.usertype

import com.google.gson.annotations.SerializedName

class UserTypeResponse (
        @SerializedName("code")
        var statusCode: Int? = null,

        @SerializedName("message")
        var message: String? = null,

        @SerializedName("allow user role")
        var userRoleName: List<String>
)