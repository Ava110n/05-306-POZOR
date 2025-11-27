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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    var xMin by remember { mutableStateOf(-5.0f) }
    var xMax by remember { mutableStateOf(5.0f) }
    var yMin by remember { mutableStateOf(-5.0f) }
    var yMax by remember { mutableStateOf(5.0f) }
    var show by remember { mutableStateOf(false) }
    var isX by remember { mutableStateOf(true) }


    Row {
        Column(modifier = Modifier.weight(3f)) {
            Canvas(Modifier.fillMaxSize().background(Color.LightGray)) {
                var info = SizeInfo(size.width, size.height, xMin, xMax, yMin, yMax)
                drawLine(
                    Color.Black,
                    Screen(Cartesian(xMin, 0f), info).toOffset(),
                    Screen(Cartesian(xMax, 0f), info).toOffset()
                )
                drawCircle(Color.Black, radius = 10f, Screen(Cartesian(1f, 0f), info).toOffset())
                drawCircle(Color.Black, radius = 10f, Screen(Cartesian(0f, 1f), info).toOffset())

                drawLine(
                    Color.Black,
                    Screen(Cartesian(0f, yMin), info).toOffset(),
                    Screen(Cartesian(0f, yMax), info).toOffset()
                )

            }
        }
        if (!show) {
            Column(modifier = Modifier.weight(1f)) {
                Row {
                    RadioButton(isX, { isX = true })
                    Text("X", modifier = Modifier.padding(0.dp, 12.5.dp))
                }
                Row {
                    RadioButton(!isX, { isX = false })
                    Text("Y", modifier = Modifier.padding(0.dp, 12.5.dp))
                }
                TextField(
                    if (isX) xMin.toString() else yMin.toString(),
                    {
                        if (isX) {
                            xMin = it.toFloat()
                        } else yMin = it.toFloat()
                    },
                )
                TextField(if (isX) xMax.toString() else yMax.toString(), {
                    if (isX) xMax = it.toFloat()
                    else yMax = it.toFloat()
                })
            }
        }
    }
    Switch(show, { b ->
        show = b
    })

}

fun newValuesX() {

}