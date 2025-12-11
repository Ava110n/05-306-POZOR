package org.example.project

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.RangeSliderState
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.ui.tooling.preview.Preview
import java.lang.Math.pow
import kotlin.math.cos
import kotlin.math.log2
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.tan
import kotlin.math.tanh

val max_n = 10_000

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun App() {
    var info by remember {
        mutableStateOf(
            SizeInfo(
                0f,
                0f,
                -2f,
                1f,
                -1f,
                1f
            )
        )
    }
    Row {
        Canvas(Modifier.fillMaxSize().background(Color.LightGray)) {
            info.width = size.width
            info.height = size.height
            drawMandelbrot(info)
        }
    }
}

suspend fun mandelbrot(cartesian: Cartesian): Int {
    var zn = Complex(0f, 0f)
    var c = Complex(cartesian)
    var n = 0
    val R = 2f
    while (n < max_n) {
        zn = zn * zn + c
        if (zn.abs() >= R) {
            return n
        }
        n++
    }
    return n
}

fun mandelbrotWithColor(cartesian: Cartesian): Color {
    var zn = Complex(0f, 0f)
    var c = Complex(cartesian)
    var n = 0
    val max_n = 10_000
    val R = 2f
    while (n < max_n) {
        zn = zn * zn + c
        if (zn.abs() >= R) {
            break
        }
        n++
    }

    val h = (n.toDouble() / max_n.toDouble() * 360.0).pow(1.5).mod(360f)
    return Color.hsv(
        h.toFloat(),
        0.5f,
        (n.toFloat() / max_n.toFloat())
    )
}


fun color(n: Int) = Color(
    sin(n.toFloat() * 100 + 50) * 255,
    sin(n.toFloat() * 100 + 150) * 255,
    sin(n.toFloat() * 100 + 250) * 255
)

fun color2(n: Int) = Color(
    sin(n.toFloat() * 100 + 5) * 255,
    sin(n.toFloat() * 100 + 15) * 255,
    sin(n.toFloat() * 100 + 25) * 255
)


fun DrawScope.drawMandelbrot(size: SizeInfo) {
    val cpus = Runtime.getRuntime().availableProcessors()
    val c = Channel<Pair<Screen, Int>>()
    val myFlow: Flow<Pair<Screen, Int>> = channelFlow {
        for (i in 0 until size.width.toInt()) {
            launch(Dispatchers.Default) {
            for (j in 0 until size.height.toInt()) {
                    val screen = Screen(i, j)
                    val coord = Cartesian(screen, size)
                    val n = mandelbrot(coord)
                    send(Pair(screen, n))
                }
            }
        }
    }
    runBlocking<Unit> {
        myFlow.collect {
            drawCircle(color(it.second), 1.0f, it.first.toOffset())
        }
    }
}