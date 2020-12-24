package com.gracemyanmar.myapplication.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import com.gracemyanmar.myapplication.R
import com.gracemyanmar.myapplication.adapters.CustomAdapter
import com.gracemyanmar.myapplication.constant.STATUSBAR
import com.gracemyanmar.myapplication.adapters.SpinnerAdapter

class PaymentActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    companion object {
        fun newIntent (context: Context) : Intent {
            return Intent(context, PaymentActivity::class.java)
        }
    }

    var paymentNames = arrayOf("Select your payment", "Wave Money", "KBZ Bank", "CB Bank", "OK$")
    var paymentImages = intArrayOf(0, R.drawable.wave, R.drawable.kbz, R.drawable.cb_bank, R.drawable.ok)

    lateinit var spinner: Spinner
    lateinit var payment_txt: TextView
    lateinit var send_btn: Button

    lateinit var spinnerAdapter: SpinnerAdapter
    lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = STATUSBAR.toColorInt()
        setContentView(R.layout.activity_payment)

        spinner = findViewById(R.id.paymentName_spinner)
        payment_txt = findViewById(R.id.payment_txt)
        send_btn = findViewById(R.id.send_btn)

        spinner.onItemSelectedListener = this

        //customAdapter = CustomAdapter(this, paymentImages, paymentNames)
        spinner.adapter = customAdapter

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this, paymentNames[position], Toast.LENGTH_SHORT).show()
    }
}