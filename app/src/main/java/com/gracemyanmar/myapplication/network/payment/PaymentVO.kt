package com.gracemyanmar.myapplication.network.payment

import com.google.gson.annotations.SerializedName

class PaymentVO {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("name")
    var paymentName: String? = null

    @SerializedName("phone")
    var phone: List<String>? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("status")
    var status: Int? = null
}