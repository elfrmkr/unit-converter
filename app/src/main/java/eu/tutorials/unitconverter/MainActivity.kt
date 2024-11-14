package eu.tutorials.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tutorials.unitconverter.ui.theme.UnitConverterTheme

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
    var inputUnit by remember{mutableStateOf(value = "Centimetres") }
    var outputUnit  by remember{mutableStateOf(value = "Metres") }
    var iExpanded by remember { mutableStateOf(value=false) }
    var oExpanded by remember { mutableStateOf(value=false) }
    var conversionFactor = remember {mutableStateOf(value = 0.01) }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //UI elements will be stacked below each other
            Text("Unit Converter")
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = inputValue , onValueChange = {
                inputValue = it
        })

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Box{
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Select")
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = false, onDismissRequest = { /*TODO*/ }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimetres") },
                        onClick = { /*TODO*/ }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Metres") },
                        onClick = { /*TODO*/ }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = { /*TODO*/ }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimetres") },
                        onClick = { /*TODO*/ }
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))
            Box{
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Select")
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = false, onDismissRequest = { /*TODO*/ }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimetres") },
                        onClick = { /*TODO*/ }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Metres") },
                        onClick = { /*TODO*/ }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = { /*TODO*/ }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimetres") },
                        onClick = { /*TODO*/ }
                    )
                }
            }
       
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result:")
    }
}



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}