package pe.edu.idat.rondanurbano_ec1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.idat.rondanurbano_ec1.ui.theme.RondanUrbano_EC1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RondanUrbano_EC1Theme {
                MainScreen()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RondanUrbano_EC1Theme {
        MainScreen()
    }
}

@Composable
fun MainScreen() {
    var pantallaActual by remember { mutableStateOf("main") }

    when (pantallaActual) {
        "main" -> MainMenu { pantallaActual = it }
        "salario" -> CalcularSalario { pantallaActual = "main" }
        "promedio" -> CalcularPromedio { pantallaActual = "main" }
        "tiempo" -> CalcularMinutos { pantallaActual = "main" }
        "series" -> CalcularSumaSerie { pantallaActual = "main" }
    }
}

@Composable
fun MainMenu(navigateTo: (String) -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navigateTo("salario") }) {
            Text("Calcular Salario")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navigateTo("promedio") }) {
            Text("Calcular Promedio")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navigateTo("tiempo") }) {
            Text("Calcular Minutos")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navigateTo("series") }) {
            Text("Calcular Suma de Serie")
        }
    }

}