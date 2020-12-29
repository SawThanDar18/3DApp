package com.gracemyanmar.myapplication.network.balance

import com.google.gson.annotations.SerializedName

class BalanceResponse {
    @SerializedName("code")
    var statusCode: Int? = null

    @SerializedName("message")
    var message: String? = null
}