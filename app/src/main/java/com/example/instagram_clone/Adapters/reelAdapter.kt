package com.example.instagram_clone.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram_clone.Models.Reel
import com.example.instagram_clone.R
import com.example.instagram_clone.databinding.ReelDgBinding
import com.squareup.picasso.Picasso

class ReelAdapter(var context: Context, var reelList: ArrayList<Reel>) :
    RecyclerView.Adapter<ReelAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ReelDgBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ReelDgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(reelList.get(position).profilelink).placeholder(R.drawable.profile_upload).into(holder.binding.profileImage)
        holder.binding.caption.setText(reelList.get(position).caption)
        holder.binding.videoView.setVideoPath(reelList.get(position).reelurl)
        holder.binding.videoView.setOnPreparedListener {
            holder.binding.progressBar.visibility=View.GONE
            holder.binding.videoView.start()
        }

    }
}
