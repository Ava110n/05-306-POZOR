package org.example.project

class Cartesian {
    var x: Float
    var y: Float

    constructor(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    constructor(screen: Screen, sizeInfo: SizeInfo) {
        this.x =
            sizeInfo.xMin +
                    screen.x * (sizeInfo.xMax - sizeInfo.xMin) /
                    sizeInfo.width
        this.y =
            sizeInfo.yMax -
                    screen.y * (sizeInfo.yMax - sizeInfo.yMin) /
                    sizeInfo.height
    }
}