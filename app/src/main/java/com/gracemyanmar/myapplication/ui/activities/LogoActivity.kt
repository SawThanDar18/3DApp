package com.gracemyanmar.myapplication.ui.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.gracemyanmar.myapplication.R
import com.gracemyanmar.myapplication.constant.TIME_OUT

class LogoActivity: AppCompatActivity() {

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, LogoActivity::class.java)
        }
    }

    lateinit var constraintLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)

        constraintLayout = findViewById(R.id.constraintLayout)

        if (isNetworkConnected()) {
            Handler().postDelayed(Runnable {
                startActivity(SplashActivity.newIntent(this))
                finish()
            }, TIME_OUT.toLong())
        } else {
            Snackbar.make(constraintLayout, R.string.internet_connection_error_msg, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}