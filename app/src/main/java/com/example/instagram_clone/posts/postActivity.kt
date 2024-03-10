package com.example.instagram_clone.posts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instagram_clone.HomeActivity
import com.example.instagram_clone.Models.User
import com.example.instagram_clone.Models.post
import com.example.instagram_clone.R
import com.example.instagram_clone.Utills.POST
import com.example.instagram_clone.Utills.POST_FOLDER
import com.example.instagram_clone.Utills.USER_NODE
import com.example.instagram_clone.Utills.USER_PROFILE_FOLDER
import com.example.instagram_clone.Utills.uploadImage
import com.example.instagram_clone.databinding.ActivityPostBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlin.concurrent.timer

class postActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }
    var imageurl: String? = null
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { Uri ->
        Uri?.let {

            uploadImage(Uri, POST_FOLDER) { url ->
                if (url != null) {

                    binding.selectImg.setImageURI(Uri)
                    imageurl = url
                }

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            startActivity(Intent(this@postActivity, HomeActivity::class.java))
            finish()
        }

        binding.selectImg.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.cancelBtn.setOnClickListener {
            startActivity(Intent(this@postActivity, HomeActivity::class.java))
            finish()
        }
        binding.postBtn.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
                .addOnSuccessListener {

                    val user = it.toObject<User>()

                    val post: post = post(
                            posturl = imageurl!!,
                            caption = binding.caption.editText?.text.toString(),
                           uid = Firebase.auth.currentUser!!.uid,
                            time = System.currentTimeMillis().toString()
                        )
                    Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document()
                            .set(post).addOnSuccessListener {
                                startActivity(Intent(this@postActivity, HomeActivity::class.java))
                                finish()
                            }
                    }
                }
        }

    }
}