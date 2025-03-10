package com.cayintech.colorpicker.slider

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.cayintech.colorpicker.ui.theme.ColorPickerTheme

@Composable
fun LightnessSlider(
    lightness: Float,
    onLightnessChange: (Float) -> Unit,
    hsColor: Color
) {
    CustomSlider(
        value = lightness,
        onValueChange = onLightnessChange,
        sliderBackground = Brush.linearGradient(
            colors = listOf(
                Color.Black,
                hsColor,
                Color.White
            )
        )
    )
}

@Composable
fun AlphaSlider(
    alpha: Float,
    onAlphaChange: (Float) -> Unit,
    hslColor: Color
) {
    CustomSlider(
        value = alpha,
        onValueChange = onAlphaChange,
        sliderBackground = Brush.linearGradient(
            colors = listOf(
                hslColor.copy(alpha = 0f),
                hslColor.copy(alpha = 1f)
            )
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    sliderBackground: Brush,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    colors: SliderColors = SliderDefaults.colors(
        activeTrackColor = Color.Transparent,
        activeTickColor = Color.Transparent,
        inactiveTrackColor = Color.Transparent,
        inactiveTickColor = Color.Transparent
    ),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    Slider(
        value = value,
        modifier = modifier,
        onValueChange = onValueChange,
        colors = colors,
        valueRange = valueRange,
        interactionSource = interactionSource,
        thumb = {
            Label(
                label = {
                    PlainTooltip(
                        modifier = Modifier
                            .size(width = 48.dp, height = 44.dp),
                        shape = RoundedCornerShape(CornerSize(100.dp))
                    ) {
                        Box(modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center) {
                            Text(
                                text = "%.2f".format(value),
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                },
                interactionSource = interactionSource
            ) {
                SliderDefaults.Thumb(
                    interactionSource = interactionSource,
                    colors = SliderDefaults.colors(),
                    thumbSize = DpSize(width = 4.dp, height = 30.dp)
                )
            }
        },
        track = { sliderState ->
            Box(
                modifier = Modifier
                    .background(
                        brush = sliderBackground,
                        shape = CircleShape
                    )
            ) {
                SliderDefaults.Track(
                    colors = colors,
                    sliderState = sliderState
                )
            }

        }
    )
}

@Preview
@Composable
fun PreviewLightnessSlider() {
    ColorPickerTheme {
        LightnessSlider(
            lightness = 0.5f,
            hsColor = Color.Red,
            onLightnessChange = {}
        )
    }
}

@Preview
@Composable
fun PreviewAlphaSlider() {
    ColorPickerTheme {
        AlphaSlider(
            alpha = 0.5f,
            hslColor = Color.Red,
            onAlphaChange = {}
        )
    }
}