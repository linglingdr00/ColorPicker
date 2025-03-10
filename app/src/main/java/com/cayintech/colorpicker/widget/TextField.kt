package com.cayintech.colorpicker.widget

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.cayintech.colorpicker.ui.theme.ColorPickerTheme

private const val TAG = "TextField"

@Composable
fun BasicFilledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onDone: ((String) -> Unit) = {},
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    prefix: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = false,
    modifier: Modifier = Modifier,
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.surface,
        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        focusedIndicatorColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        focusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        focusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        cursorColor = MaterialTheme.colorScheme.onSurfaceVariant
    )
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        readOnly = readOnly,
        enabled = enabled,
        prefix = prefix,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone(value)
                // 設定狀態為 done，關閉鍵盤
                defaultKeyboardAction(ImeAction.Done)
            }
        ),
        singleLine = singleLine,
        modifier = modifier,
        colors = colors
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewBasicFilledTextField() {
    ColorPickerTheme {
        BasicFilledTextField(
            value = "123",
            onValueChange = {}
        )
    }
}