package com.android.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.applemarket.databinding.PostRecyclerViewBinding
import java.lang.StringBuilder

class PostAdapter(val posts: MutableList<Post>) : RecyclerView.Adapter<PostAdapter.Holder>(){

    interface PostClick{
        fun onClick(view: View, position: Int)
    }

    var postClick: PostClick? = null

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = PostRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.binding.root.setOnClickListener {
            postClick?.onClick(it, position)
        }

        val currentPost = posts[position]

        holder.image.setImageResource(currentPost.imageResource)
        holder.title.text = currentPost.title
        holder.address.text = currentPost.address
        holder.price.text = AppleMarketUtils.makePriceFormat(currentPost.price)
    }




    inner class Holder(val binding: PostRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.postTitle
        val image = binding.postImage
        val address = binding.postAddress
        val price = binding.postPrice
    }

}