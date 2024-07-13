package com.android.applemarket

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.applemarket.databinding.PostRecyclerViewBinding

class PostAdapter(val posts: MutableList<Post>) : RecyclerView.Adapter<PostAdapter.Holder>(){

    interface PostClick{
        fun onClick(view: View, position: Int)
        fun onLongClick(view: View, index: Int)
    }

    var postClick: PostClick? = null

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = PostRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = Holder(binding)
        Log.d("create holder", holder.hashCode().toString())
        return holder
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentPost = posts[position]
        Log.d("bind holder", position.toString())
        holder.binding.root.setOnClickListener {
            postClick?.onClick(it, position)
        }

        holder.binding.root.setOnLongClickListener {
            postClick?.onLongClick(it, currentPost.index)
            true
        }

//        holder.postId = holder.binding.tvPostId.toString()
//        val postId = holder.binding.tvPostId

//        for (post in posts) {
//            if(post.id.toString() == postId.text.toString())
//                currentPost = post
//        }

        holder.image.setImageResource(currentPost.imageResource)
        holder.title.text = currentPost.title
        holder.address.text = currentPost.address
        holder.price.text = AppleMarketUtils.makePriceFormat(currentPost.price)
        holder.postId.text = currentPost.index.toString()
    }




    inner class Holder(val binding: PostRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.postTitle
        val image = binding.postImage
        val address = binding.postAddress
        val price = binding.postPrice
        val postId = binding.tvPostId
    }

}