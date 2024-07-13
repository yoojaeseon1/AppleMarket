package com.android.applemarket

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.applemarket.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

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

        val post = intent.getParcelableExtra("post", Post::class.java)?:Post()

        binding.ivDetail.setImageResource(post.imageResource)
        binding.tvDetailTitle.text = post.title
        binding.tvDetailContent.text = post.content
        binding.userId.text = post.seller
        binding.userAddress.text = post.address
        binding.tvDetailPrice.text = AppleMarketUtils.makePriceFormat(post.price)

        binding.btnBackToMain.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

    }
}