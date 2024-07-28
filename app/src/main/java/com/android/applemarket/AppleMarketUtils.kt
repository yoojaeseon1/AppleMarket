package com.android.applemarket

import java.text.DecimalFormat

object AppleMarketUtils {

    val format = DecimalFormat("#,###원")

    fun makePriceFormat(price: Int): String {
        return format.format(price)
    }

}