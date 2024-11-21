package eu.tutorials.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
    var inputExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember { mutableStateOf(false) }
    val inputConversionFactor = remember { mutableStateOf(1.0) }
    val outputConversionFactor = remember { mutableStateOf(1.0) }

    val customTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 24.sp,
        color = Color.Blue
        )

    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * inputConversionFactor.value * 100.0 / outputConversionFactor.value)
            .roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //UI elements will be stacked below each other
            Text("Unit Converter",   style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = inputValue ,
                onValueChange = {
                    inputValue = it
                    convertUnits()
                },
                label = { Text(text = "Enter value")},
                modifier = Modifier.fillMaxWidth()
            )

        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            UnitDropdownMenu(
                label = "From",
                selectedUnit = inputUnit,
                expanded = inputExpanded,
                onExpandChange = { inputExpanded = it },
                onUnitSelected = { unit, factor ->
                    inputUnit = unit
                    inputConversionFactor.value = factor
                    convertUnits()
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            UnitDropdownMenu(
                label = "To",
                selectedUnit = outputUnit,
                expanded = outputExpanded,
                onExpandChange = { outputExpanded = it },
                onUnitSelected = { unit, factor ->
                    outputUnit = unit
                    outputConversionFactor.value = factor
                    convertUnits()
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Result: $outputValue $outputUnit",
            style = customTextStyle
        )
    }
}

@Composable
fun UnitDropdownMenu(
    label: String,
    selectedUnit: String,
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    onUnitSelected: (String, Double) -> Unit
) {
    Box {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("$label ")
            Button(onClick = { onExpandChange(true) }) {
                Text(selectedUnit)
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandChange(false) }
        ) {
            UnitDropdownMenuItem("Centimetres", 0.01, onUnitSelected, onExpandChange)
            UnitDropdownMenuItem("Metres", 1.0, onUnitSelected, onExpandChange)
            UnitDropdownMenuItem("Feet", 0.3048, onUnitSelected, onExpandChange)
            UnitDropdownMenuItem("Millimetres", 0.001, onUnitSelected, onExpandChange)
        }
    }
}

@Composable
fun UnitDropdownMenuItem(
    unit: String,
    conversionFactor: Double,
    onUnitSelected: (String, Double) -> Unit,
    onExpandChange: (Boolean) -> Unit
) {
    DropdownMenuItem(
        text = { Text(unit) },
        onClick = {
            onExpandChange(false)
            onUnitSelected(unit, conversionFactor)
        }
    )
}
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}