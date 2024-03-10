package com.example.instagram_clone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagram_clone.Adapters.SearchAdapter
import com.example.instagram_clone.Models.User
import com.example.instagram_clone.R
import com.example.instagram_clone.Utills.USER_NODE
import com.example.instagram_clone.databinding.FragmentSearchBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject


class search_Fragment : Fragment() {
 lateinit var binding:FragmentSearchBinding
 lateinit var adapter: SearchAdapter
 var userlist=ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSearchBinding.inflate(inflater, container, false)
        binding.rv.layoutManager=LinearLayoutManager(requireContext())
        adapter= SearchAdapter(requireContext(),userlist)
        binding.rv.adapter=adapter

        Firebase.firestore.collection(USER_NODE).get().addOnSuccessListener {
            var templist=ArrayList<User>()
            userlist.clear()
            for (i in it.documents){
                if (i.id.toString().equals(Firebase.auth.currentUser!!.uid.toString())){

                }else{

                    var user:User=i.toObject<User>()!!
                    templist.add(user)
                }

            }
            userlist.addAll(templist)
            adapter.notifyDataSetChanged()

        }





        binding.searchBtn.setOnClickListener {

            var text =binding.searchView.text.toString()
            Firebase.firestore.collection(USER_NODE).whereEqualTo("name",text).get().addOnSuccessListener {
                var templist=ArrayList<User>()
                userlist.clear()
                if (it.isEmpty){

                }else{
                    for (i in it.documents){
                        if (i.id.toString().equals(Firebase.auth.currentUser!!.uid.toString())){

                        }else{

                            var user:User=i.toObject<User>()!!
                            templist.add(user)
                        }

                    }
                    userlist.addAll(templist)
                    adapter.notifyDataSetChanged()
                }


            }

        }
        return binding.root
    }

    companion object {

    }
}