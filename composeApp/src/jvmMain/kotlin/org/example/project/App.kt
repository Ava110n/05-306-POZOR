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
    var show by remember { mutableStateOf(false) }
    var isX by remember { mutableStateOf(true) }
    var info by remember { mutableStateOf(SizeInfo(0f, 0f, -5f, 5f, -5f, 5f)) }
    Row {
        Column(modifier = Modifier.weight(3f)) {
            Canvas(Modifier.fillMaxSize().background(Color.LightGray)) {
                info.width = size.width
                info.height = size.height
                drawLine(
                    Color.Black,
                    Screen(Cartesian(info.xMin, 0f), info).toOffset(),
                    Screen(Cartesian(info.xMax, 0f), info).toOffset()
                )
                drawCircle(Color.Black, radius = 10f, Screen(Cartesian(1f, 0f), info).toOffset())
                drawCircle(Color.Black, radius = 10f, Screen(Cartesian(0f, 1f), info).toOffset())

                drawLine(
                    Color.Black,
                    Screen(Cartesian(0f, info.yMin), info).toOffset(),
                    Screen(Cartesian(0f, info.yMax), info).toOffset()
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
                    if (isX) info.xMin.toString() else info.yMin.toString(),
                    {
                        if (isX) {
                            info.xMin = it.toFloatOrNull() ?: info.xMin
                        } else info.yMin = it.toFloatOrNull() ?: info.yMin
                    },
                )
                TextField(if (isX) info.xMax.toString() else info.yMax.toString(), {
                    if (isX) info.xMax = it.toFloatOrNull() ?: info.xMax
                    else info.yMax = it.toFloatOrNull() ?: info.yMax
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