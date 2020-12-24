package com.gracemyanmar.myapplication.network.payment

import com.google.gson.annotations.SerializedName

class PaymentResponse (
    @SerializedName("code")
    var statusCode: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    var paymentVO: List<PaymentVO>
)