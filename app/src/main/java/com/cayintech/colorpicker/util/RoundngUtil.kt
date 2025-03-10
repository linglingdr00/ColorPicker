package com.cayintech.colorpicker.util

/**
 * Converts alpha, red, green or blue values from range of [0f-1f] to [0-255].
 */
fun Float.fractionToRGBRange() = (this * 255.0f).toInt()