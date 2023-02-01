package com.route.todoappc37.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.route.todoappc37.R
import com.route.todoappc37.ui.database.MyDataBase
import com.route.todoappc37.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startHomeScreen()
                              }, 2000)
    }
    fun startHomeScreen(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}