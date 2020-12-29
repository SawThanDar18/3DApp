package com.gracemyanmar.myapplication.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.toColorInt
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.gracemyanmar.myapplication.R
import com.gracemyanmar.myapplication.constant.TOOLBAR_BG
import com.gracemyanmar.myapplication.constant.TOOLBAR_TXT
import com.gracemyanmar.myapplication.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    companion object {
        fun newIntent (context: Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.setBackgroundColor(TOOLBAR_BG.toColorInt())
        toolbar.setTitleTextColor(TOOLBAR_TXT.toColorInt())
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        val hView = navigationView.getHeaderView(0)
        val username = hView.findViewById(R.id.username) as TextView
        val userPhone = hView.findViewById<TextView>(R.id.userPhone)
        val id : Int

        username.text = intent.getStringExtra("name")
        userPhone.text = intent.getStringExtra("phone")
        id = intent.getIntExtra("id", 0)

        var bundle=Bundle()
        bundle.putInt("id", id)
        var fragment=HomeFragment()
        fragment.arguments=bundle

        //intent.putExtra("id", id)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}