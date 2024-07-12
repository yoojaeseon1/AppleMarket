package com.android.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Post (val id: Int,
                 val imageResource: Int,
                 var title: String,
                 var content: String,
                 val seller: String,
                 var price: Int,
                 var address: String,
                 var numLike: Int,
                 var numChatting: Int) : Parcelable{
}