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
//        val binding = PostRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val binding = PostRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        Log.d("holder", binding.root::class.java.toString())
        val holder = Holder(binding)
//        Log.d("post create holder", holder.hashCode().toString())
        return holder
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentPost = posts[position]
//        Log.d("bind post", "${currentPost.index}")
//        Log.d("bind post", "===========")
//        Log.d("holder", "top = ${holder.binding.root.top}")
//        Log.d("holder", "bottom = ${holder.binding.root.bottom}")
//        Log.d("holder", "left = ${holder.binding.root.left}")
//        Log.d("holder", "right = ${holder.binding.root.right}")
//        Log.d("holder", "${holder.binding.root.verticalScrollbarPosition}")
//        Log.d("holder", "===================")
        holder.binding.root.setOnClickListener {
            postClick?.onClick(it, position)
        }

        holder.binding.root.setOnLongClickListener {
//            Log.d("remove post", "${currentPost.index}")
            postClick?.onLongClick(it, currentPost.index)
//            postClick?.onLongClick(it, position)
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
        holder.address.text = currentPost.user.address
        holder.price.text = AppleMarketUtils.makePriceFormat(currentPost.price)
        holder.postId.text = currentPost.index.toString()
        holder.numChatting.text = currentPost.numChatting.toString()
        holder.numLike.text = currentPost.likes.size.toString()
        holder.binding.btnLike.setBackgroundResource(R.drawable.ic_like)
        holder.binding.btnLike.backgroundTintList = postClick?.getColorStateList(R.color.likeUnChecked)

        for (like in currentPost.likes) {
            if(like.user == loginedUser) {
//                Log.d("like", "post index = ${currentPost.index}")
                holder.binding.btnLike.setBackgroundResource(R.drawable.ic_like_full)
                holder.binding.btnLike.backgroundTintList = postClick?.getColorStateList(R.color.likeChecked)
//                holder.binding.btnLike.backgroundTintList = getColorStateList(R.color.colorLikeChecked, null)
                break
            }
        }
//        Log.d("like", "===============")
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