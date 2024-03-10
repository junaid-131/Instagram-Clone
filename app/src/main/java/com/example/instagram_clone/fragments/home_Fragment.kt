package com.example.instagram_clone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagram_clone.Adapters.followAdapter
import com.example.instagram_clone.Adapters.postAdapter
import com.example.instagram_clone.Models.User
import com.example.instagram_clone.Models.post
import com.example.instagram_clone.R
import com.example.instagram_clone.Utills.FOLLOW
import com.example.instagram_clone.Utills.POST
import com.example.instagram_clone.databinding.FragmentHomeBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject


class home_Fragment : Fragment() {

private lateinit var binding:FragmentHomeBinding
private var postlist=ArrayList<post>()
    private lateinit var adapter: postAdapter
    private var followlist=ArrayList<User>()
    private lateinit var followAdapter: followAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentHomeBinding.inflate(inflater, container, false)
        adapter= postAdapter(requireContext(),postlist)
        binding.postRv.layoutManager=LinearLayoutManager(requireContext())
        binding.postRv.adapter=adapter

        followAdapter= followAdapter(requireContext(),followlist)
        binding.followRv.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.followRv.adapter=followAdapter

        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.materialToolbar)

        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FOLLOW).get().addOnSuccessListener {
            var templist=ArrayList<User>()
            followlist.clear()
            for (i in it.documents){
                var user:User=i.toObject<User>()!!
                templist.add(user)
            }
            followlist.addAll(templist)
            followAdapter.notifyDataSetChanged()
        }

        Firebase.firestore.collection(POST).get().addOnSuccessListener {
            var templist = ArrayList<post>()
            postlist.clear()
            for(i in it.documents){

                var post:post=i.toObject<post>()!!
                templist.add(post)
            }
            postlist.addAll(templist)
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }

    companion object {
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}