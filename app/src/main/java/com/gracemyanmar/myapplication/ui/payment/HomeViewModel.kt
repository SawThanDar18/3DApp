package com.gracemyanmar.myapplication.ui.home

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        val intent = Intent()
        value = intent.getIntExtra("id", 0).toString()
    }
    val text: LiveData<String> = _text
}