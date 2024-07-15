package com.android.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Like(val user: User,
                val index: Int = indexCount++,
                val createdDate: LocalDateTime = LocalDateTime.now()) : Parcelable {

    companion object{
        private var indexCount = 0
    }

}