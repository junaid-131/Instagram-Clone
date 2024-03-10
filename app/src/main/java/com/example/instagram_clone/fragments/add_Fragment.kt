package com.example.instagram_clone.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instagram_clone.R
import com.example.instagram_clone.databinding.FragmentAddBinding
import com.example.instagram_clone.posts.postActivity
import com.example.instagram_clone.posts.reelsActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class add_Fragment : BottomSheetDialogFragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        binding.post.setOnClickListener {
            activity?.startActivity(Intent(requireContext(),postActivity::class.java))
            activity?.finish()
        }
        binding.reel.setOnClickListener {

            activity?.startActivity(Intent(requireContext(),reelsActivity::class.java))
        }


        return binding.root
    }

    companion object {

    }
}