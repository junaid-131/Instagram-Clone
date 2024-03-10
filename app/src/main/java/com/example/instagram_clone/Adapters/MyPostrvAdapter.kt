package com.example.instagram_clone.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram_clone.Models.post
import com.example.instagram_clone.databinding.MyPostRvDesignBinding
import com.squareup.picasso.Picasso

class MyPostrvAdapter(var context:Context,var postlist:ArrayList<post>):RecyclerView.Adapter<MyPostrvAdapter.Viewholder>() {
    inner class Viewholder(var binding: MyPostRvDesignBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        var binding= MyPostRvDesignBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int {
       return postlist.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
       Picasso.get().load(postlist.get(position).posturl).into(holder.binding.postImg)
    }
}