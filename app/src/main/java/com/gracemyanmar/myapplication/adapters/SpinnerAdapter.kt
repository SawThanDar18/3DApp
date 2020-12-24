package com.gracemyanmar.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.gracemyanmar.myapplication.R

class SpinnerAdapter(var context: Context, var images: IntArray, var paymentNames: Array<String>) : BaseAdapter() {

    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(i: Int): Any? {
        return paymentNames[i]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View, viewGroup: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(viewGroup.context).inflate(R.layout.custom_spinner_item, viewGroup, false)
        val payment_iv = view.findViewById<View>(R.id.payment_iv) as ImageView
        val payment_name = view.findViewById<View>(R.id.payment_txt) as TextView
        payment_iv.setImageResource(images[i])
        payment_name.text = paymentNames[i]
        return layoutInflater
    }
}