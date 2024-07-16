package com.android.applemarket

import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView
import com.android.applemarket.databinding.PostRecyclerViewBinding

class PostAdapter(val posts: MutableList<Post>) : RecyclerView.Adapter<PostAdapter.Holder>(){



    interface PostClick{
        fun onClick(view: View, position: Int)
        fun onLongClick(view: View, index: Int)
        fun getColorStateList(@ColorRes resId: Int) : ColorStateList
    }

    var postClick: PostClick? = null
    var loginedUser = Dummy.loginedUser

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = PostRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = Holder(binding)
        return holder
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentPost = posts[position]
        holder.binding.root.setOnClickListener {
            postClick?.onClick(it, position)
        }

        holder.binding.root.setOnLongClickListener {
            postClick?.onLongClick(it, currentPost.index)
            true
        }

        holder.image.setImageResource(currentPost.imageResource)
        holder.title.text = currentPost.title
        holder.address.text = currentPost.user.address
        holder.price.text = AppleMarketUtils.makePriceFormat(currentPost.price)
        holder.postId.text = currentPost.index.toString()
        holder.numChatting.text = currentPost.numChatting.toString()
        holder.numLike.text = currentPost.likes.size.toString()
        holder.binding.btnLike.setBackgroundResource(R.drawable.ic_like)
        holder.binding.btnLike.backgroundTintList = postClick?.getColorStateList(R.color.likeUnChecked)

        for (like in currentPost.likes) {
            if(like.user == loginedUser) {
                holder.binding.btnLike.setBackgroundResource(R.drawable.ic_like_full)
                holder.binding.btnLike.backgroundTintList = postClick?.getColorStateList(R.color.likeChecked)
                break
            }
        }
    }




    inner class Holder(val binding: PostRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.postTitle
        val image = binding.postImage
        val address = binding.postAddress
        val price = binding.postPrice
        val postId = binding.tvPostId
        val numChatting = binding.tvNumChatting
        val numLike = binding.tvNumLike
    }

}