package com.example.instagram_clone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instagram_clone.Utills.USER_NODE
import com.example.instagram_clone.Utills.USER_PROFILE_FOLDER
import com.example.instagram_clone.Utills.uploadImage
import com.example.instagram_clone.databinding.ActivitySignupBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.squareup.picasso.Picasso
import com.example.instagram_clone.Models.User as User

class SignupActivity : AppCompatActivity() {
    val binding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }
    lateinit var user: User
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { Uri ->
        Uri?.let {

            uploadImage(Uri, USER_PROFILE_FOLDER) {
                if (it == null) {

                } else {
                    user.image = it
                    binding.profileImage.setImageURI(Uri)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setContentView(binding.root)

        user = User()
        if (intent.hasExtra("MODE")) {
            if (intent.getIntExtra("MODE", -1) == 1) {
                binding.signUpBtn.text = "Update Profile"
                Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid)
                    .get()
                    .addOnSuccessListener {

                        user = it.toObject<User>()!!
                        if (!user.image.isNullOrEmpty()) {
                            Picasso.get().load(user.image).into(binding.profileImage)
                        }
                        binding.name.editText?.setText(user.name)
                        binding.email.editText?.setText(user.email)
                        binding.password.editText?.setText(user.password)


                    }
            }
        }

        binding.signUpBtn.setOnClickListener {
            if (intent.hasExtra("MODE")) {
                if (intent.getIntExtra("MODE", -1) == 1) {
                    Firebase.firestore.collection(USER_NODE)
                        .document(Firebase.auth.currentUser!!.uid).set(user)
                        .addOnSuccessListener {
                            startActivity(
                                Intent(
                                    this@SignupActivity,
                                    HomeActivity::class.java
                                )
                            )
                            finish()
                        }

                }
            } else {
                if (binding.name?.editText?.text.toString().equals("") or
                    binding.password?.editText?.text.toString().equals("") or
                    binding.email?.editText?.text.toString().equals("")
                ) {
                    Toast.makeText(
                        this@SignupActivity,
                        "Please fill the detail",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        binding.email?.editText?.text.toString(),
                        binding.password?.editText?.text.toString()
                    ).addOnCompleteListener { result ->

                        if (result.isSuccessful) {
                            user.name = binding.name?.editText?.text.toString()
                            user.password = binding.password?.editText?.text.toString()
                            user.email = binding.email?.editText?.text.toString()
                            Firebase.firestore.collection(USER_NODE)
                                .document(Firebase.auth.currentUser!!.uid).set(user)
                                .addOnSuccessListener {
                                    startActivity(
                                        Intent(
                                            this@SignupActivity,
                                            HomeActivity::class.java
                                        )
                                    )
                                    finish()
                                }

                        } else {
                            Toast.makeText(
                                this@SignupActivity,
                                result.exception?.localizedMessage, Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
        binding.addimage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.login.setOnClickListener {
            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            finish()
        }
    }
}