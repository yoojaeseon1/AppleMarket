package com.android.applemarket

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private val loginedUser = Dummy.loginedUser

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var post = intent.getParcelableExtra("post", Post::class.java)?:Post()

        for (dummyPost in Dummy.posts) {
            if(dummyPost.index == post.index) {
                post = dummyPost
            }
        }

        binding.ivDetail.setImageResource(post.imageResource)
        binding.tvDetailTitle.text = post.title
        binding.tvDetailContent.text = post.content
//        binding.userId.text = post.user.seller
//        binding.userAddress.text = post.address
        binding.userId.text = post.user.nickname
        binding.userAddress.text = post.user.address
        binding.tvDetailPrice.text = AppleMarketUtils.makePriceFormat(post.price)

        for (like in post.likes) {
            if(like.user == loginedUser) {
                binding.ivLike.isChecked = true
                binding.ivLike.backgroundTintList = resources.getColorStateList(R.color.likeChecked, null)
                break
            }
        }

        binding.ivLike.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked) {
                binding.ivLike.backgroundTintList = resources.getColorStateList(R.color.likeChecked, null)
                Snackbar.make(buttonView, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                val like = Like(loginedUser)
//                Log.d("like", "like.id = ${like.index}")
                post.likes.add(like)
            } else {
                binding.ivLike.backgroundTintList = resources.getColorStateList(R.color.likeUnChecked, null)
//                for (post in Dummy.posts) {
//                    if(post.index == currentPost.index) {
//                        post.likes.add(Like(loginedUser))
//                    }
//                }
                for ((likeIndex, like) in post.likes.withIndex()) {
                    if(like.user == loginedUser) {
                        post.likes.removeAt(likeIndex)
                        break
                    }
                }
            }

//            Log.d("like", "likes.size = ${post.likes.size}")

        }

        binding.btnBackToMain.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

    }
}