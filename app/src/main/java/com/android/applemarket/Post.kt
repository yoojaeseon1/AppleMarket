package com.android.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime


@Parcelize
data class Post (val user: User = User(),
                 val imageResource: Int = R.drawable.no_image,
                 var title: String = "empty title",
                 var content: String = "empty content",
                 var price: Int = 0,
                 var numChatting: Int = 0,
                 val index: Int = indexCount++,
                 val likes: MutableList<Like> = mutableListOf(),
                 val createdDate: LocalDateTime = LocalDateTime.now(),
                 var updatedDate: LocalDateTime? = null) : Parcelable {

     companion object {
         private var indexCount = 0
     }

 }