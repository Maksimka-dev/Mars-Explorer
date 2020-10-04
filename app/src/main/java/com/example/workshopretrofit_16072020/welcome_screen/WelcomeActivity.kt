package com.example.workshopretrofit_16072020.welcome_screen


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workshopretrofit_16072020.MainActivity
import com.example.workshopretrofit_16072020.R
import java.util.*


class WelcomeActivity : AppCompatActivity() {

    private val timer = Timer()
    private val myTimerTask = MyTimerTask()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)
        timer.schedule(myTimerTask, 1000)
    }

    inner class MyTimerTask : TimerTask() {
        override fun run() {
            val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}