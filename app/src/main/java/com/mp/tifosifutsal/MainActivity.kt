package com.mp.tifosifutsal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mp.tifosifutsal.sign.signin.SignInActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            var handler= Handler()
        handler.postDelayed({
            val intent = Intent(this@MainActivity,
                SignInActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}
