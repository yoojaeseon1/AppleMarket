package com.android.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Post (val id: Int = -1,
                 val imageResource: Int = R.drawable.no_image,
                 var title: String = "no title",
                 var content: String = "no content",
                 val seller: String = "no seller",
                 var price: Int = 0,
                 var address: String = "no address",
                 var numLike: Int = -1,
                 var numChatting: Int = -1) : Parcelable{
}