package com.cayintech.colorpicker.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.cayintech.colorpicker.util.RGBUtil.argbToHex
import com.cayintech.colorpicker.util.RGBUtil.rgbToHSL

private const val TAG = "ColorUtil"

object ColorUtil {

    fun parseHexToColor(hex: String): Color? {
        return try {
            Color(android.graphics.Color.parseColor(hex))
        } catch (e: Exception) {
            null
        }
    }

    fun rgbFloatToString(rgb: Float): String {
        return "%.0f".format(rgb * 255)
    }

    fun floatToDcsString(float: Float): String {
        return "%.0f".format(float)
    }

    fun floatToTwoDcsString(float: Float): String {
        return "%.2f".format(float)
    }

    fun colorToHSL(color: Color): FloatArray {
        val rgbArray: IntArray = colorIntToRGBArray(color.toArgb())
        val red = rgbArray[0]
        val green = rgbArray[1]
        val blue = rgbArray[2]
        return rgbToHSL(red, green, blue)
    }

    fun colorToHexAlpha(color: Color): String {
        return argbToHex(color.alpha, color.red, color.green, color.blue)
    }

    private fun colorIntToRGBArray(color: Int): IntArray {
        val red = android.graphics.Color.red(color)
        val green = android.graphics.Color.green(color)
        val blue = android.graphics.Color.blue(color)
        return intArrayOf(red, green, blue)
    }

}