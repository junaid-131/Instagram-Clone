package com.example.instagram_clone.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.instagram_clone.Adapters.MyPostrvAdapter
import com.example.instagram_clone.Models.post
import com.example.instagram_clone.Models.Reel
import com.example.instagram_clone.databinding.FragmentMypostBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject


class mypostFragment : Fragment() {
   private lateinit var binding:FragmentMypostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =FragmentMypostBinding.inflate(inflater, container, false)
        val postlist=ArrayList<post>()
        val adapter=MyPostrvAdapter(requireContext(),postlist)
        binding.rv.layoutManager=StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter=adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
            val templist= arrayListOf<post>()
            for (i in it.documents){
                val post:post=i.toObject<post>()!!
                templist.add(post)
            }
            postlist.addAll(templist)
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {

    }
}