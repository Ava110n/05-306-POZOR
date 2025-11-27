package org.example.project

import androidx.compose.ui.geometry.Offset

class Screen {
    var x: Int
    var y: Int

    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    constructor(cartesian: Cartesian, sizeInfo: SizeInfo) {
        this.x = (
                (cartesian.x - sizeInfo.xMin)
                        / (sizeInfo.xMax - sizeInfo.xMin)
                        * sizeInfo.width
                ).toInt()
        this.y = (
                (sizeInfo.yMax - cartesian.y)
                        / (sizeInfo.yMax - sizeInfo.yMin)
                        * sizeInfo.height
                ).toInt()
    }

    fun toOffset(): Offset {
        return Offset(
            x.toFloat(),
            y.toFloat()
        )
    }
}

