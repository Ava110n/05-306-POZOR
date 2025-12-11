package org.example.project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import kotlin.math.max
import kotlin.math.min

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

    fun ifChangedX() {
        val pix = width / (xMax - xMin)
        val y = height / pix
        val yMean = (yMax + yMin) / 2
        yMin = yMean - y / 2
        yMax = yMean + y / 2
    }

    fun ifChangedY() {
        val pix = width / (yMax - yMin)
        val x = height / pix
        val xMean = (xMax + xMin) / 2
        xMin = xMean - x / 2
        xMax = xMean + x / 2
    }

    fun zoom(pos: Offset) {
        val deltaX = width * 0.5f
        val deltaY = height * 0.5f
        val leftX = max(pos.x-deltaX, 0f)
        val rightX = min(pos.x+deltaX, width)
        val topY = max(pos.y-deltaY, 0f)
        val bottomY = min(pos.y+deltaY, height)
        val topLeft = Cartesian(Screen(leftX.toInt(), topY.toInt()), this)
        val bottomRight = Cartesian(Screen(rightX.toInt(), bottomY.toInt()), this)
        this.xMin = topLeft.x
        this.yMax = topLeft.y
        this.xMax = bottomRight.x
        this.yMin = bottomRight.y
    }
}
