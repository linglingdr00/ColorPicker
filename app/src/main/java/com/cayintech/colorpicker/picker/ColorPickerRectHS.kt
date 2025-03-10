package com.cayintech.colorpicker.picker

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cayintech.colorpicker.util.ColorUtil
import com.cayintech.colorpicker.selector.SelectorRectHueSaturationHSL
import com.cayintech.colorpicker.slider.AlphaSlider
import com.cayintech.colorpicker.slider.LightnessSlider
import com.cayintech.colorpicker.ui.theme.ColorPickerTheme
import com.cayintech.colorpicker.widget.DropdownTextField
import com.cayintech.colorpicker.widget.HEXAInputView
import com.cayintech.colorpicker.widget.HSLAInputView
import com.cayintech.colorpicker.widget.RGBAInputView

private const val TAG = "ColorPickerRectHS"

@Composable
fun ColorPickerRectHS(
    modifier: Modifier = Modifier,
    selectionRadius: Dp = 8.dp,
    initialColor: Color,
    onColorChange: (Color, String) -> Unit
) {
    // 顏色的色相(hue)、飽和度(saturation)、亮度(lightness)、透明度(alpha)
    val hslArray = ColorUtil.colorToHSL(initialColor)
    var hue by remember { mutableFloatStateOf(hslArray[0]) }
    var saturation by remember { mutableFloatStateOf(hslArray[1]) }
    var lightness by remember { mutableFloatStateOf(hslArray[2]) }
    var alpha by remember { mutableFloatStateOf(initialColor.alpha) }

    // 當 h,s,l,a 改變時，以下顏色會隨之更新
    val hslaColor = Color.hsl(hue = hue, saturation = saturation, lightness = lightness, alpha = alpha)
    val hsColor = Color.hsl(hue = hue, saturation = saturation, lightness = 0.5f)
    val hslColor = Color.hsl(hue = hue, saturation = saturation, lightness = lightness)

    // 亮度(l)和透明度(a)的 slider 位置
    var lightnessPosition by remember { mutableFloatStateOf(lightness) }
    var alphaPosition by remember { mutableFloatStateOf(alpha) }

    // 切換 hexa, rgba, hsla 的 dropdown menu
    val colorOptions = listOf("HEXA", "RGBA", "HSLA")
    var colorText by remember { mutableStateOf(colorOptions[0]) }

    // hexa text field 的文字
    var hexaText by remember { mutableStateOf(ColorUtil.colorToHexAlpha(hslaColor)) }
    // rgba text field 的文字
    var redString by remember { mutableStateOf(ColorUtil.rgbFloatToString(hslaColor.red)) }
    var greenString by remember { mutableStateOf(ColorUtil.rgbFloatToString(hslaColor.green)) }
    var blueString by remember { mutableStateOf(ColorUtil.rgbFloatToString(hslaColor.blue)) }
    // hsla text field 的文字
    var hueString by remember { mutableStateOf(ColorUtil.floatToDcsString(hue)) }
    var saturationString by remember { mutableStateOf(ColorUtil.floatToTwoDcsString(saturation)) }
    var lightnessString by remember { mutableStateOf(ColorUtil.floatToTwoDcsString(lightness)) }
    var alphaString by remember { mutableStateOf(ColorUtil.floatToTwoDcsString(alpha)) }

    // 如果有點選色條中的位置或調整 slider 或修改 text field，就將 isTouchColor 改為 true
    var isTouchColor by remember { mutableStateOf(false) }

    // 當選擇的顏色改變時，要更新以下
    LaunchedEffect(hslaColor) {
        // 更新 hexa text
        hexaText = ColorUtil.colorToHexAlpha(hslaColor)
        // 更新 rgba text
        redString = ColorUtil.rgbFloatToString(hslaColor.red)
        greenString = ColorUtil.rgbFloatToString(hslaColor.green)
        blueString = ColorUtil.rgbFloatToString(hslaColor.blue)
        // 更新 hsla text
        hueString = ColorUtil.floatToDcsString(hue)
        saturationString = ColorUtil.floatToTwoDcsString(saturation)
        lightnessString = ColorUtil.floatToTwoDcsString(lightness)
        alphaString = ColorUtil.floatToTwoDcsString(alpha)
        // 更新亮度和透明度 slider 位置
        lightnessPosition = lightness
        alphaPosition = alpha
    }

    onColorChange(hslaColor, ColorUtil.colorToHexAlpha(hslaColor))

    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            SelectorRectHueSaturationHSL(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2 / 1f),
                hue = hue,
                saturation = saturation,
                selectionRadius = selectionRadius,
                onChange = { h, s ->
                    hue = h
                    saturation = s
                    isTouchColor = true
                }
            )
        }
        Row {
            // lightness slider
            Row(
                modifier = Modifier.weight(0.8f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Lightness",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.width(60.dp)
                )
                Box(modifier = Modifier.weight(1f)) {
                    LightnessSlider(
                        lightness = lightnessPosition,
                        onLightnessChange = {
                            lightnessPosition = it
                            lightness = it
                            isTouchColor = true
                        },
                        hsColor = hsColor
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            // alpha slider
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Alpha",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.width(60.dp)
                )
                Box(modifier = Modifier.weight(1f)) {
                    AlphaSlider(
                        alpha = alphaPosition,
                        onAlphaChange = {
                            alphaPosition = it
                            alpha = it
                            isTouchColor = true
                        },
                        hslColor = hslColor
                    )
                }
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            DrawCircle(
                modifier = Modifier.weight(0.2f),
                color = hslaColor
            )
            Spacer(modifier = Modifier.width(12.dp))
            // HEXA, RGBA, HSLA Input
            Column(modifier = Modifier.weight(1f)) {
                DropdownTextField(
                    selectedOption = colorText,
                    options = colorOptions,
                    onValueChange = {
                        colorText = it
                    }
                )
                when (colorText) {
                    // HEXA
                    colorOptions[0] -> {
                        HEXAInputView(
                            hexa = hexaText,
                            onHEXAChange = { hexaText = it },
                            onDone = {
                                // 將 hexa string 轉換成 color
                                val newColor = ColorUtil.parseHexToColor(it)
                                // 如果 newColor 不等於 null，就將 color 轉換成 hsl array
                                if (newColor != null) {
                                    val newHSLArray = ColorUtil.colorToHSL(newColor)
                                    hue = newHSLArray[0]
                                    saturation = newHSLArray[1]
                                    lightness = newHSLArray[2]
                                    alpha = newColor.alpha
                                    isTouchColor = true
                                } else {
                                    hexaText = ColorUtil.colorToHexAlpha(hslaColor)
                                }
                            }
                        )
                    }
                    // RGBA
                    colorOptions[1] -> {
                        RGBAInputView(
                            red = redString,
                            green = greenString,
                            blue = blueString,
                            alpha = alphaString,
                            onRedChange = { redString = it },
                            onGreenChange = { greenString = it },
                            onBlueChange = { blueString = it },
                            onAlphaChange = { alphaString = it },
                            onRedDone = {
                                if (it.toIntOrNull() in 0..255) {
                                    val newColor = hslaColor.copy(
                                        red = (it.toFloat() / 255f)
                                    )
                                    Log.d(TAG, "red newColor: $newColor")
                                    val newHSLArray = ColorUtil.colorToHSL(newColor)
                                    hue = newHSLArray[0]
                                    saturation = newHSLArray[1]
                                    lightness = newHSLArray[2]
                                    alpha = newColor.alpha
                                    isTouchColor = true
                                } else {
                                    redString = ColorUtil.rgbFloatToString(hslaColor.red)
                                }
                            },
                            onGreenDone = {
                                if (it.toIntOrNull() in 0..255) {
                                    val newColor = hslaColor.copy(
                                        green = (it.toFloat() / 255f)
                                    )
                                    Log.d(TAG, "green newColor: $newColor")
                                    val newHSLArray = ColorUtil.colorToHSL(newColor)
                                    hue = newHSLArray[0]
                                    saturation = newHSLArray[1]
                                    lightness = newHSLArray[2]
                                    alpha = newColor.alpha
                                    isTouchColor = true
                                } else {
                                    greenString = ColorUtil.rgbFloatToString(hslaColor.green)
                                }
                            },
                            onBlueDone = {
                                if (it.toIntOrNull() in 0..255) {
                                    val newColor = hslaColor.copy(
                                        blue = (it.toFloat() / 255f)
                                    )
                                    Log.d(TAG, "blue newColor: $newColor")
                                    val newHSLArray = ColorUtil.colorToHSL(newColor)
                                    hue = newHSLArray[0]
                                    saturation = newHSLArray[1]
                                    lightness = newHSLArray[2]
                                    alpha = newColor.alpha
                                    isTouchColor = true
                                } else {
                                    blueString = ColorUtil.rgbFloatToString(hslaColor.blue)
                                }
                            },
                            onAlphaDone = {
                                if (it.isNotEmpty() && it.toFloat() in 0f..1f) {
                                    alpha = it.toFloat()
                                    isTouchColor = true
                                } else {
                                    alphaString = ColorUtil.floatToTwoDcsString(hslaColor.alpha)
                                }
                            }
                        )
                    }
                    colorOptions[2] -> {
                        HSLAInputView(
                            hue = hueString,
                            saturation = saturationString,
                            lightness = lightnessString,
                            alpha = alphaString,
                            onHueChange = { hueString = it },
                            onSaturationChange = { saturationString = it },
                            onLightnessChange = { lightnessString = it },
                            onAlphaChange = { alphaString = it },
                            onHueDone = {
                                if (it.isNotEmpty() && it.toFloat() in 0f..360f) {
                                    hue = it.toFloat()
                                    isTouchColor = true
                                } else {
                                    hueString = ColorUtil.floatToDcsString(hue)
                                }
                            },
                            onSaturationDone = {
                                if (it.isNotEmpty() && it.toFloat() in 0f..1f) {
                                    saturation = it.toFloat()
                                    isTouchColor = true
                                } else {
                                    saturationString = ColorUtil.floatToTwoDcsString(saturation)
                                }
                            },
                            onLightnessDone = {
                                if (it.isNotEmpty() && it.toFloat() in 0f..1f) {
                                    lightness = it.toFloat()
                                    isTouchColor = true
                                } else {
                                    lightnessString = ColorUtil.floatToTwoDcsString(lightness)
                                }
                            },
                            onAlphaDone = {
                                if (it.isNotEmpty() && it.toFloat() in 0f..1f) {
                                    alpha = it.toFloat()
                                    isTouchColor = true
                                } else {
                                    alphaString = ColorUtil.floatToTwoDcsString(alpha)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DrawCircle(
    modifier: Modifier = Modifier,
    color: Color
) {
    Box(
        modifier = modifier
            .aspectRatio(1f) // 保持寬高相等，確保是圓形
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val radius = size.minDimension / 2
            drawCircle(
                color = color,
                radius = radius
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHSLAColorPicker() {
    ColorPickerTheme {
        ColorPickerRectHS(
            initialColor = Color.Red,
            onColorChange = { color, colorHEXA ->
                println("Color: $color, $colorHEXA")
            }
        )
    }
}