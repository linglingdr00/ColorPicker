package com.cayintech.colorpicker.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cayintech.colorpicker.ui.theme.ColorPickerTheme

@Composable
fun DropdownTextField(
    selectedOption: String,
    options: List<String>,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    // 捕捉 Row 點擊事件，來顯示或隱藏下拉選單
    Box(
        modifier = modifier
            .wrapContentSize(Alignment.TopStart)
            .clickable {
                isExpanded = !isExpanded
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedOption,
                style = MaterialTheme.typography.bodySmall
            )
            Icon(
                imageVector = if (isExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                contentDescription = null
            )
        }

        // 控制下拉選單的顯示
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier
                .widthIn(min = 80.dp)
                .background(Color.White)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        isExpanded = false
                        onValueChange(option)
                    },
                    modifier = Modifier
                        .background(Color.White)
                        .widthIn(min = 80.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDropdownTextField() {
    ColorPickerTheme {
        DropdownTextField(
            selectedOption = "HEXA",
            options = listOf("HEXA", "RGBA", "HSLA"),
            onValueChange = {}
        )
    }
}