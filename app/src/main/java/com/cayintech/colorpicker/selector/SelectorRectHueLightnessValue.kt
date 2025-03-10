package com.cayintech.colorpicker.selector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.cayintech.colorpicker.ui.brush.transparentToGrayVerticalGradient
import com.cayintech.colorpicker.util.gradientColorScaleHSL
import com.cayintech.colorpicker.util.detectMotionEvents

@Composable
private fun SelectorRect(
    modifier: Modifier = Modifier,
    hue: Float,
    property: Float,
    selectionRadius: Dp = Dp.Unspecified,
    brushHue: Brush,
    brushProperty: Brush,
    onChange: (Float, Float) -> Unit
) {
    BoxWithConstraints(modifier) {

        val density = LocalDensity.current.density

        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()

        // Center position of color picker
        val center = Offset(width / 2, height / 2)

        var currentPosition by remember {
            mutableStateOf(center)
        }

        val posX = hue / 360 * width
        val posY = (1 - property) * height
        currentPosition = Offset(posX, posY)

        val selectorRadius =
            if (selectionRadius != Dp.Unspecified) selectionRadius.value * density
            else width.coerceAtMost(height) * .04f

        val canvasModifier = Modifier
            .pointerInput(Unit) {
                detectMotionEvents(
                    onDown = {
                        val position = it.position
                        val hueChange = (position.x / width).coerceIn(0f, 1f) * 360f
                        val propertyChange = (1 - (position.y / height)).coerceIn(0f, 1f)
                        onChange(hueChange, propertyChange)
                        it.consume()

                    },
                    onMove = {
                        val position = it.position
                        val hueChange = (position.x / width).coerceIn(0f, 1f) * 360f
                        val propertyChange = (1 - (position.y / height)).coerceIn(0f, 1f)
                        onChange(hueChange, propertyChange)
                        it.consume()
                    },
                    delayAfterDownInMillis = 20
                )
            }

        SelectorRectImpl(
            modifier = canvasModifier,
            selectorPosition = currentPosition,
            brushHue = brushHue,
            brushProperty = brushProperty,
            selectorRadius = selectorRadius
        )

    }
}

@Composable
private fun SelectorRectImpl(
    modifier: Modifier,
    selectorPosition: Offset,
    brushHue: Brush,
    brushProperty: Brush,
    selectorRadius: Float
) {
    Canvas(
        modifier = modifier.fillMaxSize()
    ) {
        // Draw hue and saturation selection gradients
        drawRect(brush = brushHue)
        drawRect(brush = brushProperty)

        drawHueSelectionCircle(
            center = selectorPosition,
            radius = selectorRadius
        )
    }
}

@Composable
fun SelectorRectHueSaturationHSL(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float,
    selectionRadius: Dp = Dp.Unspecified,
    onChange: (Float, Float) -> Unit
) {
    //  Red, Magenta, Blue, Cyan, Green, Yellow, Red
    val colorScaleHSLGradient = remember {
        Brush.linearGradient(
            colors = gradientColorScaleHSL,
            start = Offset.Zero,
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        )
    }
    val transparentToGrayGradient = remember {
        transparentToGrayVerticalGradient()
    }

    SelectorRect(
        modifier = modifier,
        hue = hue,
        property = saturation,
        selectionRadius = selectionRadius,
        brushHue = colorScaleHSLGradient,
        brushProperty = transparentToGrayGradient,
        onChange = onChange
    )
}