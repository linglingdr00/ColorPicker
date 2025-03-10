package com.cayintech.colorpicker.selector

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

fun DrawScope.drawHueSelectionCircle(
    center: Offset,
    radius: Float
) {
    drawCircle(
        Color.White,
        radius = radius,
        center = center,
        style = Stroke(width = radius / 4)
    )

    /*drawCircle(
        Color.Black,
        radius = radius + radius / 8,
        center = center,
        style = Stroke(width = radius / 8)
    )*/
}