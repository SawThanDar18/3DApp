package com.gracemyanmar.myapplication.ui.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColorInt
import androidx.viewpager.widget.ViewPager
import com.gracemyanmar.myapplication.R
import com.gracemyanmar.myapplication.adapters.ViewPagerAdapter
import com.gracemyanmar.myapplication.constant.BASE_URL
import com.gracemyanmar.myapplication.constant.STATUSBAR

class SplashActivity: AppCompatActivity() {

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    lateinit var viewPager: ViewPager

    lateinit var dots_1: ImageView
    lateinit var dots_2: ImageView
    lateinit var dots_3: ImageView

    lateinit var register_layout: ConstraintLayout
    lateinit var login_layout: ConstraintLayout

    companion object{
        fun newIntent (context: Context) : Intent {
            return Intent(context, SplashActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = STATUSBAR.toColorInt()
        setContentView(R.layout.activity_splash)

        viewPager = findViewById(R.id.viewPager)
        register_layout = findViewById(R.id.register_layout)
        login_layout = findViewById(R.id.login_layout)

        dots_1 = findViewById(R.id.dots_1)
        dots_2 = findViewById(R.id.dots_2)
        dots_3 = findViewById(R.id.dots_3)

        viewPagerAdapter = ViewPagerAdapter()
        viewPager.adapter = viewPagerAdapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {

                when (position) {
                    0 -> {
                        dots_1.setBackgroundResource(R.drawable.dots_select_shape)
                        dots_2.setBackgroundResource(R.drawable.dots_shape)
                        dots_3.setBackgroundResource(R.drawable.dots_shape)
                    }

                    1 -> {
                        dots_1.setBackgroundResource(R.drawable.dots_shape)
                        dots_2.setBackgroundResource(R.drawable.dots_select_shape)
                        dots_3.setBackgroundResource(R.drawable.dots_shape)
                    }

                    2 -> {
                        dots_1.setBackgroundResource(R.drawable.dots_shape)
                        dots_2.setBackgroundResource(R.drawable.dots_shape)
                        dots_3.setBackgroundResource(R.drawable.dots_select_shape)
                    }
                }
            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(arg0: Int) {

            }
        })

        setUpListener()
    }

    private fun setUpListener() {
        register_layout.setOnClickListener {
            startActivity(RegisterActivity.newIntent(this))
            finish()
        }
        login_layout.setOnClickListener {
            startActivity(LoginActivity.newIntent(this))
            finish()
        }
    }
}
