package com.example.instagram_clone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.instagram_clone.Models.User
import com.example.instagram_clone.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.createAcc.setOnClickListener {
           startActivity( Intent(this@LoginActivity,SignupActivity::class.java))
            finish()
        }
        binding.loginBtn.setOnClickListener {
            if (binding.mail.editText?.text.toString().isEmpty() ||
                binding.pass.editText?.text.toString().isEmpty()
            ) {
                Toast.makeText(
                    this@LoginActivity,
                    "Please fill in both email and password",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                var user = User(
                    binding.mail.editText?.text.toString(),
                    binding.pass.editText?.text.toString()
                )

                Firebase.auth.signInWithEmailAndPassword(user.password!!,user.email!!)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                            finish()
                    }else{
                            Toast.makeText(this@LoginActivity, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                        }

                }
            }
        }
    }
}
