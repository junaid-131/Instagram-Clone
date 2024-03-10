package com.example.instagram_clone.posts

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram_clone.HomeActivity
import com.example.instagram_clone.Models.Reel
import com.example.instagram_clone.Models.User
import com.example.instagram_clone.Utills.POST
import com.example.instagram_clone.Utills.REEL
import com.example.instagram_clone.Utills.REEL_FOLDER
import com.example.instagram_clone.Utills.USER_NODE
import com.example.instagram_clone.Utills.uploadvideo
import com.example.instagram_clone.databinding.ActivityReelsBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class reelsActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityReelsBinding.inflate(layoutInflater)
    }
    private lateinit var videourl:String
    lateinit var progressDialog:ProgressDialog
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { Uri ->
        Uri?.let {
            uploadvideo(Uri, REEL_FOLDER,progressDialog) {
                    url->
                if (url != null) {
                    videourl=url
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        progressDialog=ProgressDialog(this)
        binding.selectReel.setOnClickListener{
            launcher.launch("video/*")
        }
        binding.cancelBtn.setOnClickListener {
            startActivity(Intent(this@reelsActivity, HomeActivity::class.java))
            finish()
        }
        binding.postBtn.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                var user:User = it.toObject<User>()!!
                val reel:Reel= Reel(videourl,binding.caption.editText?.text.toString(),user.image!!)
                Firebase.firestore.collection(REEL).document().set(reel).addOnSuccessListener {
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ REEL).document().set(reel).addOnSuccessListener {
                        startActivity(Intent(this@reelsActivity,HomeActivity::class.java))
                        finish()
                    }
                }
            }

        }
    }
}