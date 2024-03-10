package com.example.instagram_clone.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instagram_clone.Adapters.viewpagerAdapter
import com.example.instagram_clone.Models.User
import com.example.instagram_clone.SignupActivity
import com.example.instagram_clone.Utills.USER_NODE
import com.example.instagram_clone.databinding.FragmentProfileBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.squareup.picasso.Picasso


class profile_Fragment : Fragment() {
    private lateinit var binder: FragmentProfileBinding
    private lateinit var viewpagerAdapter:viewpagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binder = FragmentProfileBinding.inflate(inflater, container, false)
        binder.edtProfile.setOnClickListener {
            val intent = Intent(activity,SignupActivity::class.java)
            intent.putExtra("MODE" ,1)
            activity?.startActivity(intent)
            activity?.finish()

        }
        viewpagerAdapter= viewpagerAdapter(requireActivity().supportFragmentManager)
        viewpagerAdapter.addFragments(mypostFragment(),"My post")
        viewpagerAdapter.addFragments(myreelsFragment(),"My reels")
        binder.viewpager.adapter=viewpagerAdapter
        binder.tabLayout.setupWithViewPager(binder.viewpager)


        return binder.root


    }

    companion object {

    }

    override fun onStart() {
        super.onStart()
        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
            .addOnSuccessListener {

                val user: User? = it.toObject<User>()!!
                if (user != null) {
                    binder.nameProfile.text = user.name

                    binder.bio.text = user.email
                }
                if (user != null) {
                    if(!user.image.isNullOrEmpty()){
                        Picasso.get().load(user.image).into(binder.profileImage)

                    }
                }


            }
    }
}

