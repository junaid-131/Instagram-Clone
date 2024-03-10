package com.example.instagram_clone.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagram_clone.Models.User
import com.example.instagram_clone.Models.post
import com.example.instagram_clone.R
import com.example.instagram_clone.Utills.USER_NODE
import com.example.instagram_clone.databinding.PostRvBinding
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject


class postAdapter(private val context: Context, private val postlist: ArrayList<post>) :
    RecyclerView.Adapter<postAdapter.MyHolder>() {

    inner class MyHolder(var binding: PostRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = PostRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        try {
            Firebase.firestore.collection(USER_NODE).document(postlist.get(position).uid).get().addOnSuccessListener {
                val user = it.toObject<User>()
                Glide.with(context).load(user!!.image).placeholder(R.drawable.profile_upload).into(holder.binding.profileImage)
                holder.binding.nameProfile.text=user.name
            }

        }
        catch (e:Exception){

        }

        try {

            val text = TimeAgo.using(postlist.get(position).time.toLong() )
            holder.binding.time.text=text
        }
        catch (e:Exception){
            holder.binding.time.text=""

        }
        Glide.with(context).load(postlist.get(position).posturl).placeholder(R.drawable.profile_upload).into(holder.binding.profileImage)

        holder.binding.share.setOnClickListener{
            var  i = Intent(Intent.ACTION_SEND)
            i.type="text/plain"
            i.putExtra(Intent.EXTRA_TEXT,postlist.get(position).posturl)
            context.startActivity(i)
        }
        holder.binding.caption.text=postlist.get(position).caption
        holder.binding.like.setOnClickListener{
            holder.binding.like.setImageResource(R.drawable.heart_red)
        }

    }

    override fun getItemCount(): Int {
        return postlist.size
    }


}
