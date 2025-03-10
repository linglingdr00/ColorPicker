package com.cayintech.colorpicker.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Colorize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cayintech.colorpicker.ui.theme.lightGray

@Composable
fun ColorPickerBar(
    colors: List<Color>,
    colorIndex: Int,
    onColorIndexChange: (Int) -> Unit
) {
    Box(
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        MultipleColorItem(
            colors = colors,
            selectedIndex = colorIndex,
            onColorSelected = {
                onColorIndexChange(it)
            }
        )
    }

}

@Composable
fun SingleColorItem(
    index: Int,
    color: Color,
    focus: Boolean = true,
    onClick: (Int) -> Unit
) {
    // 定義 focus 和 un focus 的樣式
    val modifier = if (focus) {
        Modifier.border(
            width = 2.dp,
            color = MaterialTheme.colorScheme.primary,
            shape = CircleShape
        )
    } else {
        Modifier.border(
            width = 1.dp,
            color = lightGray,
            shape = CircleShape
        )
    }
    Box(
        modifier = modifier
            .background(
                color = color,
                shape = CircleShape
            )
            .size(24.dp)
            .clickable { onClick(index) }
    ) {
        // if index == 0
        if (index == 0) {
            Icon(
                imageVector = Icons.Outlined.Colorize,
                contentDescription = null,
                tint = lightGray,
                modifier = Modifier.padding(3.dp)
            )
        }
    }
}

@Composable
fun MultipleColorItem(
    colors: List<Color>,
    selectedIndex: Int,
    onColorSelected: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
    ) {
        itemsIndexed(colors) {  index, color ->
            SingleColorItem(
                index = index,
                color = color,
                focus = index == selectedIndex,
                onClick = { i ->
                    onColorSelected(i)
                }
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}