package com.example.instagram_clone

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import com.example.instagram_clone.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = Color.TRANSPARENT

        android.os.Handler(Looper.getMainLooper()).postDelayed({
           if (FirebaseAuth.getInstance().currentUser==null) {
               startActivity(Intent(this, SignupActivity::class.java))
           }
            else{
                startActivity(Intent(this@MainActivity,HomeActivity::class.java))
           }
            finish()
        },3000)

    }
}