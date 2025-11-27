package org.example.project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SizeInfo(
    width: Float,
    height: Float,
    xMin: Float,
    xMax: Float,
    yMin: Float,
    yMax: Float,
) {
    var width by mutableStateOf(width)
    var height by mutableStateOf(height)
    var xMin by mutableStateOf(xMin)
    var xMax by mutableStateOf(xMax)
    var yMin by mutableStateOf(yMin)
    var yMax by mutableStateOf(yMax)
}
