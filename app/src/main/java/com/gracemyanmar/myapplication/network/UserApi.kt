package com.gracemyanmar.myapplication.network

import com.gracemyanmar.myapplication.constant.*
import com.gracemyanmar.myapplication.network.login.LoginResponse
import com.gracemyanmar.myapplication.network.login.LoginVO
import com.gracemyanmar.myapplication.network.login.LoginVOResponse
import com.gracemyanmar.myapplication.network.payment.PaymentResponse
import com.gracemyanmar.myapplication.network.payment.PaymentVO
import com.gracemyanmar.myapplication.network.register.RegisterResponse
import com.gracemyanmar.myapplication.network.register.UserVO
import com.gracemyanmar.myapplication.network.usertype.UserRoleVO
import com.gracemyanmar.myapplication.network.usertype.UserTypeResponse
import com.gracemyanmar.myapplication.network.usertype.UserTypeVO
import retrofit2.Call
import retrofit2.http.*

interface UserApi {
    @Headers("ApiKey: $API_KEY")
    @POST(ADD_USER_URL)
    fun registerUsers(@Body userVO: UserVO?): Call<RegisterResponse>

    @Headers("ApiKey: $API_KEY")
    @POST(LOGIN_URL)
    fun loginUsers(@Body loginVO: LoginVO?): Call<LoginResponse>

    @Headers("ApiKey: $API_KEY")
    @FormUrlEncoded
    @POST(USERTYPE_URL)
    fun showUserType(@Field("role") role: Int): Call<UserTypeResponse>

    @Headers("ApiKey: $API_KEY")
    @GET(PAYMENT_URL)
    fun addPayment() : Call<PaymentResponse>

    @Headers("ApiKey: $API_KEY")
    @POST(LOGIN_URL)
    fun login(@Body loginVO: LoginVO?): Call<LoginVOResponse>

}