package com.cayintech.colorpicker.util

import androidx.core.graphics.ColorUtils
import java.util.Locale

object RGBUtil {

    fun rgbToHSL(
        red: Int,
        green: Int,
        blue: Int
    ): FloatArray {
        val outHsl = floatArrayOf(0f, 0f, 0f)
        ColorUtils.RGBToHSL(red, green, blue, outHsl)
        return outHsl
    }

    fun argbToHex(
        alpha: Float,
        red: Float,
        green: Float,
        blue: Float
    ): String {
        return "#" +
                floatToHexString(alpha) +
                floatToHexString(red) +
                floatToHexString(green) +
                floatToHexString(blue)
    }

    private fun floatToHexString(value: Float): String {
        return Integer.toHexString(value.fractionToRGBRange())
            .toStringComponent().uppercase(Locale.ROOT)
    }

    private fun String.toStringComponent() =
        this.let { if (it.length == 1) "0${it}" else it }

}