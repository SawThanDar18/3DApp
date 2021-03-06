package com.gracemyanmar.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.gracemyanmar.myapplication.R
import com.gracemyanmar.myapplication.adapters.CustomAdapter
import com.gracemyanmar.myapplication.constant.SAME_PAYMENT_CODE
import com.gracemyanmar.myapplication.constant.SUCCESS_CODE
import com.gracemyanmar.myapplication.network.DataImpl
import com.gracemyanmar.myapplication.network.balance.BalanceResponse
import com.gracemyanmar.myapplication.network.payment.PaymentResponse
import com.gracemyanmar.myapplication.network.payment.PaymentVO
import com.gracemyanmar.myapplication.ui.activities.Activity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    var paymentImages = intArrayOf(R.drawable.kpay, R.drawable.wave, R.drawable.cb_bank, R.drawable.ok, R.drawable.mytel)
    var phImages = intArrayOf(0, 0, 0, 0, 0, 0)

    lateinit var paymentName_spinner: Spinner
    lateinit var phoneNumber_spinner: Spinner
    lateinit var description_txt: TextView
    lateinit var send_btn: Button
    lateinit var text_id_et: EditText
    lateinit var constraintLayout: ConstraintLayout

    lateinit var paymentNameAdapter: CustomAdapter
    lateinit var phoneNumberAdapter: CustomAdapter

    lateinit var paymentList: List<PaymentVO>
    lateinit var phoneNumberList: List<String>
    lateinit var currentString: String
    lateinit var separated : Array<String>

    lateinit var paymentResponse: Call<PaymentResponse>

    lateinit var balanceResponse: Call<BalanceResponse>

    lateinit var text_id: String
    var payment_id: Int? = null
    var user_id: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        description_txt = root.findViewById(R.id.description_txt)
        paymentName_spinner = root.findViewById(R.id.paymentName_spinner)
        phoneNumber_spinner = root.findViewById(R.id.phoneNumber_spinner)
        send_btn = root.findViewById(R.id.send_btn)
        text_id_et = root.findViewById(R.id.text_id_et)
        constraintLayout = root.findViewById(R.id.constraintLayout)

        text_id = text_id_et.text.toString()
        user_id = arguments?.getInt("id")

        addPayment()

        return root
    }

    private fun addPayment() {
        paymentResponse = DataImpl.userApi.addPayment()
        paymentResponse.enqueue(object : Callback<PaymentResponse> {
            override fun onFailure(call: Call<PaymentResponse>?, t: Throwable?) {
                Snackbar.make(constraintLayout, R.string.error_msg, Snackbar.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<PaymentResponse>?, response: Response<PaymentResponse>?) {
                val response = response!!.body()
                if (response.statusCode == SUCCESS_CODE && response != null) {
                    paymentList = response.paymentVO
                    val items = arrayOfNulls<String>(paymentList.size)
                    for (i in paymentList.indices) {
                        items[i] = paymentList[i].paymentName!!
                    }
                    paymentNameAdapter = CustomAdapter(context, paymentImages, items)
                    paymentName_spinner.prompt = "Select Payment...."
                    paymentName_spinner.adapter = paymentNameAdapter
                } else {
                    Snackbar.make(constraintLayout, R.string.error_msg, Snackbar.LENGTH_LONG).show()
                }

                paymentName_spinner.onItemSelectedListener = object : OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        for (i in paymentList.indices) {
                            if (i == position) {
                                when(position) {
                                    i -> {
                                        payment_id = response.paymentVO[position].id!!
                                        description_txt.text = response.paymentVO[i].description
                                        phoneNumberList = paymentList[i].phone!!
                                        currentString = phoneNumberList[0]
                                        separated = currentString.split(",".toRegex()).toTypedArray()
                                        var item = arrayOfNulls<String>(separated.size)
                                        for (j in separated.indices) {
                                            item[j] = separated[j]
                                        }
                                        phoneNumberAdapter = CustomAdapter (context, phImages, item)
                                        phoneNumber_spinner.prompt = "Select Payment Number...."
                                        phoneNumber_spinner.adapter = phoneNumberAdapter
                                        text_id_et.text = null
                                    }
                                }
                            }
                        }
                    }
                }
                phoneNumber_spinner.onItemSelectedListener = object : OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        for (i in separated.indices)
                            if(i == position){
                                when(i) {
                                    position -> {
                                        send_btn.visibility = View.VISIBLE
                                        text_id_et.visibility = View.VISIBLE
                                        send_btn.setOnClickListener {
                                            requestPayment(user_id!!, payment_id!!, text_id_et.text.toString(), separated[position])
                                        }
                                    }
                                }
                            }
                    }
                }
            }
        })
    }

    private fun requestPayment(userId: Int, payment_id: Int, text_id: String, phone: String) {
        balanceResponse = DataImpl.userApi.requestBalance(userId, payment_id, text_id, phone)
        balanceResponse.enqueue(object : Callback<BalanceResponse> {
            override fun onFailure(call: Call<BalanceResponse>?, t: Throwable?) {
                Snackbar.make(constraintLayout, t?.localizedMessage.toString(), Snackbar.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<BalanceResponse>?, response: Response<BalanceResponse>?) {
                val response = response!!.body()
                if (response.statusCode == SUCCESS_CODE && response != null) {
                    Snackbar.make(constraintLayout, "Successful", Snackbar.LENGTH_LONG).show()
                } else if (response.statusCode == SAME_PAYMENT_CODE){
                    Snackbar.make(constraintLayout, R.string.sane_data_error, Snackbar.LENGTH_LONG).show()
                }
            }

        })

    }
}