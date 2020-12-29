package com.gracemyanmar.myapplication.network.number

import android.annotation.SuppressLint
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.gracemyanmar.myapplication.R

class NumberListItemViewHolder(itemView: View, delegate: NumberViewHolderActionDelegate) : BaseViewHolder<NumberVO>(itemView) {

    private lateinit var mData: NumberVO

    var no_txt: TextView
    var number_txt: TextView
    var amount_txt: TextView
    var minus_iv: ImageView
    var plus_iv: ImageView

    init {

        no_txt = itemView.findViewById(R.id.no_tv)
        number_txt = itemView.findViewById(R.id.number_tv)
        amount_txt = itemView.findViewById(R.id.amount_et)
        minus_iv = itemView.findViewById(R.id.minus_iv)
        plus_iv = itemView.findViewById(R.id.plus_iv)

        plus_iv.setOnClickListener {
            mData.let {
                delegate.updateAmount(it.amount + 50, it.id)
            }
        }

        minus_iv.setOnClickListener {
            mData.let {
                if (it.amount > 1 ) {
                   // delegate.updateQuantity(it.quantity - 1, it.plantId)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun bindData(data: NumberVO) {

        mData = data

        no_txt.text = data.id
        number_txt.text = data.number.toString()
        amount_txt.text = data.amount.toString()


        //itemView.card_plant_price.text = "${getCommaSeparatedAmount(data.plant_price.toInt())} ${data.plantCurrencySign}"

    }
}
