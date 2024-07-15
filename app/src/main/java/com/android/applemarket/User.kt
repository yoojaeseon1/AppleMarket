package com.android.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val id: String = "empty id",
           var password: String = "empty password",
           var nickname: String = "empty nickname",
           var address: String = "empty address") : Parcelable