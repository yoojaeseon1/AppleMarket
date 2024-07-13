package com.android.applemarket

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.applemarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            Toast.makeText(this@MainActivity, "push back button", Toast.LENGTH_SHORT).show()
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("종료")
            builder.setMessage("정말 종료하시겠습니까?")
            builder.setIcon(R.drawable.ic_comment)

            val listener = object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when(which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            finish()
                        }
                    }
                }
            }
            builder.setPositiveButton("확인", listener)
            builder.setNegativeButton("취소", null)
            builder.show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("lifeCycle", "onCreate()")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        Dummy()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val posts = Dummy.posts
        val adapter = PostAdapter(posts)

        binding.postRecyclerView.adapter = adapter
        binding.postRecyclerView.layoutManager = LinearLayoutManager(this)
//        binding.postRecyclerView.layoutManager = ConstraintLayoutManager(this)

        adapter.postClick = object : PostAdapter.PostClick {
            override fun onClick(view: View, position: Int) {
//                val title = posts[position].title
//                Toast.makeText(this@MainActivity, title, Toast.LENGTH_SHORT).show()
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("post", posts[position])
//                Log.d("position", position.toString())
                startActivity(intentToDetail)
            }

            override fun onLongClick(view: View, index: Int) {

//                Log.d("click", "longCLick")
//                posts.removeAt(position)
                for ((postsIndex, post) in posts.withIndex()) {
                    if(post.index == index) {
                        posts.removeAt(postsIndex)
                        break
                    }
                }
                (binding.postRecyclerView.layoutManager as LinearLayoutManager).removeAllViews()
            }
        }

        binding.btnPostNotification.setOnClickListener {
            notification()
        }

//        val fadeInAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
//        fadeInAnimation.duration = 1000
//        val fadeOutAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_out)
//        fadeOutAnimation.duration = 1000
//        binding.btnMainFloating.animation = fadeOutAnimation

        binding.btnMainFloating.visibility = View.VISIBLE
        binding.postRecyclerView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            val firstVisiblePosition =
                (binding.postRecyclerView.layoutManager as LinearLayoutManager)
                    .findFirstVisibleItemPosition()
//            Log.d("firstVisiblePosition", firstVisiblePosition.toString())
            if(firstVisiblePosition == 0) {
//                Log.d("firstVisiblePosition", "into 0")
                binding.btnMainFloating.animate().alpha(0.0f).setDuration(1000)
//                binding.btnMainFloating.animation = fadeOutAnimation
//                binding.btnMainFloating.visibility = View.INVISIBLE
            } else{
//                Log.d("firstVisiblePosition", "into more 0")
                binding.btnMainFloating.animate().alpha(1.0f).setDuration(1000)
//                binding.btnMainFloating.animation = fadeInAnimation
            }
//            Log.d("scroll", "(${scrollX}, ${scrollY})")
//            Log.d("scroll", "old (${oldScrollX}, ${oldScrollY})")
//            if(oldScrollY == 0) {
//                binding.btnMainFloating.animation = fadeOutAnimation
//                binding.btnMainFloating.visibility = View.GONE
//            } else {
//                binding.btnMainFloating.animation = fadeInAnimation
//                binding.btnMainFloating.visibility = View.VISIBLE
//
//            }
        }

        binding.btnMainFloating.setOnClickListener {
//            binding.btnMainFloating.animation = fadeOutAnimation
            binding.postRecyclerView.scrollToPosition(0)
//            binding.btnMainFloating.animate().alpha(0.0f).setDuration(1000)
//            binding.btnMainFloating.visibility = View.INVISIBLE
//            binding.btnMainFloating.visibility = View.GONE
//            binding.btnMainFloating.visibility = View.VISIBLE
        }

//        binding.btnMainFloating.isScrollbarFadingEnabled = true
//
////        binding.btnMainFloating.scrollBarFadeDuration = 3000
//        binding.btnMainFloating.setOnClickListener {
//        binding.btnMainFloating.visibility = View.INVISIBLE
//
////            binding.btnMainFloating.
//
//        }

        this.onBackPressedDispatcher.addCallback(this, backPressedCallback)
    }

    private fun notification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if(!NotificationManagerCompat.from(this).areNotificationsEnabled()) {
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                    putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                }
                startActivity(intent)
            }
        }

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one-channel"
            val channelName = "my Channel one"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "My Cahnnel One Description"
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }
            manager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, channelId)
        } else {
            builder = NotificationCompat.Builder(this)
        }

        builder.run {
            setSmallIcon(R.mipmap.ic_launcher)
            setWhen(System.currentTimeMillis())
            setContentTitle("키워드 알림")
            setContentText("설정한 키워드에 대한 알림이 도착했습니다!!")
        }
        manager.notify(11, builder.build())
    }


    override fun onPause() {
        Log.d("lifeCycle", "onPause()")
        super.onPause()
//        Log.d("lifeCycle", "start onPause()")
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("종료")
//        builder.setMessage("정말 종료하시겠습니까?")
//        builder.setIcon(R.drawable.ic_comment)
//
//        val listener = object : DialogInterface.OnClickListener {
//            override fun onClick(dialog: DialogInterface?, which: Int) {
//                when(which) {
//                    DialogInterface.BUTTON_POSITIVE -> {
//                        onStop()
//                    }
////                    DialogInterface.BUTTON_NEGATIVE -> {
////
////                    }
////                    DialogInterface.BUTTON_NEUTRAL -> {
////
////                    }
//                }
//            }
//        }
//
//        builder.setPositiveButton("확인", listener)
//        builder.setNegativeButton("취소", null)
////        builder.setNeutralButton("중립", null)
//
//        builder.show()
//        Log.d("lifeCycle", "end onPause()")

//        var builder = AlertDialog.Builder(this)
//        builder.setTitle("기본 다이얼로그 타이틀")
//        builder.setMessage("기본 다이얼로그 메세지")
//        builder.setIcon(R.mipmap.ic_launcher)
//
//        val listener = object : DialogInterface.OnClickListener {
//            override fun onClick(dialog: DialogInterface?, which: Int) {
//                when(which) {
//                    DialogInterface.BUTTON_POSITIVE -> binding.button.text = "BUTTON_POSITIVE"
//                    DialogInterface.BUTTON_NEUTRAL -> binding.button.text = "BUTTON_NEUTRAL"
//                    DialogInterface.BUTTON_NEGATIVE -> binding.button.text = "BUTTON_NEGATIVE"
//                }
//            }
//        }
//
//        builder.setPositiveButton("Positive", listener)
//        builder.setNegativeButton("Negative", listener)
//        builder.setNeutralButton("Neutral", listener)
//
//        builder.show()

    }

    override fun onResume() {
        Log.d("lifeCycle", "onResume()")
        super.onResume()
    }


    override fun onRestart() {
        Log.d("lifeCycle", "onRestart()")
        super.onRestart()
    }

    override fun finish() {
        Log.d("lifeCycle", "onfinish()")
        super.finish()
    }
    override fun onStop() {
        super.onStop()
        Log.d("lifeCycle", "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifeCycle", "onDestroy()")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d("lifeCycle", "onDetachedFromWindow()")
    }
}