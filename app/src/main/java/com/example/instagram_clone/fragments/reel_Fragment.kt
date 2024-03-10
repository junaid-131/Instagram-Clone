package com.example.instagram_clone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instagram_clone.Adapters.ReelAdapter
import com.example.instagram_clone.Models.Reel
import com.example.instagram_clone.R
import com.example.instagram_clone.Utills.REEL
import com.example.instagram_clone.databinding.FragmentReelBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject


class reel_Fragment : Fragment() {
private lateinit var binding:FragmentReelBinding
lateinit var adapter:ReelAdapter
var reellist=ArrayList<Reel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentReelBinding.inflate(inflater, container, false)
        adapter=ReelAdapter(requireContext(),reellist)
        binding.viewpager.adapter=adapter
        Firebase.firestore.collection(REEL).get().addOnSuccessListener {

            var templist=ArrayList<Reel>()
            reellist.clear()
                for (i in it.documents){
                    var reel=i.toObject<Reel>()!!
                    templist.add(reel)
                }

            reellist.addAll(templist)
            reellist.reverse()
            adapter.notifyDataSetChanged()
        }


        return binding.root
    }

    companion object {

    }
}