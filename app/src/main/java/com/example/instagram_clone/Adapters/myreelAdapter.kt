package com.example.instagram_clone.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.instagram_clone.Models.Reel

import com.example.instagram_clone.databinding.MyPostRvDesignBinding


class myreelAdapter(var context: Context, private var reellist: ArrayList<Reel>) :
    RecyclerView.Adapter<myreelAdapter.Viewholder>() {
    inner class Viewholder(var binding: MyPostRvDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = MyPostRvDesignBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int {
        return reellist.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        Glide.with(context).load(reellist.get(position).reelurl)
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.binding.postImg)
    }
}