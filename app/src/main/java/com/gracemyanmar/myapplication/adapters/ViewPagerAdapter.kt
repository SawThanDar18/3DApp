package com.gracemyanmar.myapplication.adapters

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import com.gracemyanmar.myapplication.R

class ViewPagerAdapter: PagerAdapter() {

    var screens: IntArray? = null

    init {
        screens = intArrayOf(R.layout.activity_logo, R.layout.activity_logo, R.layout.activity_logo)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(container.context)
        val view = layoutInflater.inflate(screens!![position], container, false)
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return screens!!.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val v = `object` as View
        container.removeView(v)
    }
}