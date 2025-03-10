package com.cayintech.colorpicker.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HEXAInputView(
    hexa: String,
    onHEXAChange: (String) -> Unit,
    onDone: (String) -> Unit
) {
    BasicFilledTextField(
        value = hexa,
        onValueChange = onHEXAChange,
        onDone = onDone,
        singleLine = true
    )
}

@Composable
fun RGBAInputView(
    red: String,
    green: String,
    blue: String,
    alpha: String,
    onRedChange: (String) -> Unit,
    onGreenChange: (String) -> Unit,
    onBlueChange: (String) -> Unit,
    onAlphaChange: (String) -> Unit,
    onRedDone: (String) -> Unit,
    onGreenDone: (String) -> Unit,
    onBlueDone: (String) -> Unit,
    onAlphaDone: (String) -> Unit
) {
    Row {
        // R
        BasicFilledTextField(
            value = red,
            onValueChange = onRedChange,
            onDone = onRedDone,
            singleLine = true,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        // G
        BasicFilledTextField(
            value = green,
            onValueChange = onGreenChange,
            onDone = onGreenDone,
            singleLine = true,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        // B
        BasicFilledTextField(
            value = blue,
            onValueChange = onBlueChange,
            onDone = onBlueDone,
            singleLine = true,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        // A
        BasicFilledTextField(
            value = alpha,
            onValueChange = onAlphaChange,
            onDone = onAlphaDone,
            singleLine = true,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun HSLAInputView(
    hue: String,
    saturation: String,
    lightness: String,
    alpha: String,
    onHueChange: (String) -> Unit,
    onSaturationChange: (String) -> Unit,
    onLightnessChange: (String) -> Unit,
    onAlphaChange: (String) -> Unit,
    onHueDone: (String) -> Unit,
    onSaturationDone: (String) -> Unit,
    onLightnessDone: (String) -> Unit,
    onAlphaDone: (String) -> Unit
) {
    Row {
        // H
        BasicFilledTextField(
            value = hue,
            onValueChange = onHueChange,
            onDone = onHueDone,
            singleLine = true,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        // S
        BasicFilledTextField(
            value = saturation,
            onValueChange = onSaturationChange,
            onDone = onSaturationDone,
            singleLine = true,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        // L
        BasicFilledTextField(
            value = lightness,
            onValueChange = onLightnessChange,
            onDone = onLightnessDone,
            singleLine = true,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        // A
        BasicFilledTextField(
            value = alpha,
            onValueChange = onAlphaChange,
            onDone = onAlphaDone,
            singleLine = true,
            modifier = Modifier.weight(1f)
        )
    }
}