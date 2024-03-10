package com.example.instagram_clone.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagram_clone.Models.User
import com.example.instagram_clone.R
import com.example.instagram_clone.databinding.FollowRvBinding


class followAdapter(var context: Context,var followlist:ArrayList<User>):RecyclerView.Adapter<followAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: FollowRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = FollowRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return followlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(followlist.get(position).image).placeholder(R.drawable.profile_upload).into(holder.binding.profileImage)
        holder.binding.name.text=followlist.get(position).name
    }
}