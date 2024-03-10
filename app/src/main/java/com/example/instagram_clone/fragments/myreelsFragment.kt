package com.example.instagram_clone.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.instagram_clone.Adapters.MyPostrvAdapter
import com.example.instagram_clone.Adapters.myreelAdapter
import com.example.instagram_clone.Models.Reel
import com.example.instagram_clone.Models.post
import com.example.instagram_clone.R
import com.example.instagram_clone.Utills.REEL
import com.example.instagram_clone.databinding.FragmentMyreelsBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject


class myreelsFragment : Fragment() {
private lateinit var binding:FragmentMyreelsBinding

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
        binding = FragmentMyreelsBinding.inflate(inflater, container, false)
        val reellist = ArrayList<Reel>()
        val adapter = myreelAdapter(requireContext(), reellist)
        binding.rv.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter = adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL).get()
            .addOnSuccessListener {
                val templist = arrayListOf<Reel>()
                for (i in it.documents) {
                    val reel: Reel = i.toObject<Reel>()!!
                    templist.add(reel)
                }
                reellist.addAll(templist)
                adapter.notifyDataSetChanged()
            }
        return binding.root

    }
    companion object {


    }
}