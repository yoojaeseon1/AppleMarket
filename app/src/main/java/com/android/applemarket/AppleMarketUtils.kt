package com.android.applemarket

import java.lang.StringBuilder

class AppleMarketUtils {

    companion object {
        fun makePriceFormat(price: Int): String {
            val priceToSB = StringBuilder(price.toString())
            var priceIndex = priceToSB.length-1
            var numberCount = 0
            while (priceIndex > 0) {
                numberCount++
                if(numberCount == 3) {
                    priceToSB.insert(priceIndex, ',')
                    numberCount = 0
                }
                priceIndex--
            }

            priceToSB.append("원")
            return priceToSB.toString()
        }
    }

}