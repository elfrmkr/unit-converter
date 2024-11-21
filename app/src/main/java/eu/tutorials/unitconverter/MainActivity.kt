package eu.tutorials.unitconverter

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}


@Composable
fun UnitConverter() {

    //setting up states
    var inputValue by remember{mutableStateOf(value = "") }
    var outputValue by remember{mutableStateOf(value = "") }
    var inputUnit by remember{mutableStateOf(value = "Metres") }
    var outputUnit  by remember{mutableStateOf(value = "Metres") }
    var iExpanded by remember { mutableStateOf(value=false) }
    var oExpanded by remember { mutableStateOf(value=false) }
    val iConversionFactor = remember {mutableStateOf(value = 1.0) }
    val oConversionFactor = remember {mutableStateOf(value = 1.0) }

    val customTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 24.sp,
        color = Color.Blue
        )

    fun converUnits() {
        // ?: elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * iConversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() /100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //UI elements will be stacked below each other
            Text("Unit Converter",   style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = inputValue ,
                onValueChange = {
                inputValue = it
                converUnits() },
                label = { Text(text = "Enter value")}
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Box{
                Row {
                Text(text = "From ")
                // Input button
                Button(onClick = {
                    iExpanded = true
                }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimetres") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimetres"
                            iConversionFactor.value = 0.01
                            converUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Metres") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Metres"
                            iConversionFactor.value = 1.0
                            converUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            iConversionFactor.value = 0.3048
                            converUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimetres") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimetres"
                            iConversionFactor.value = 0.001
                            converUnits()
                        }
                    )
                }
              }
            }

            Spacer(modifier = Modifier.width(16.dp))
            Box{
                Row{
                Text(text = "To ")
                Button(onClick = {
                    oExpanded = true
                }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimetres") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimetres"
                            oConversionFactor.value = 0.01
                            converUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Metres") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Metres"
                            oConversionFactor.value = 1.00
                            converUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            converUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimetres") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Millimetres"
                            oConversionFactor.value = 0.001
                            converUnits()
                        }
                    )
                }
            }
          }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result:$outputValue $outputUnit",
                    style = customTextStyle)
    }
}



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}