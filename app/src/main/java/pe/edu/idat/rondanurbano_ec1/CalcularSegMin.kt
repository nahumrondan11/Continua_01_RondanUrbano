package pe.edu.idat.rondanurbano_ec1

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun CalcularMinutos(onBack: () -> Unit) {
    var segundos = remember { mutableStateOf("") }
    var minutos = remember { mutableStateOf("") }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (input, button, result) = createRefs()

        TextField(
            value = segundos.value,
            onValueChange = { segundos.value = it },
            label = { Text("Segundos") },
            modifier = Modifier
                .padding(top = 60.dp)
                .constrainAs(input) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Spacer(modifier = Modifier.height(16.dp).constrainAs(createRef()) {
            top.linkTo(input.bottom)
        })

        Button(onClick = {
            val seg = segundos.value.toIntOrNull() ?: 0
            minutos.value = (seg / 60).toString()
        }, modifier = Modifier.constrainAs(button) {
            top.linkTo(input.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Text("Calcular Minutos")
        }

        Text("Minutos: ${minutos.value}", modifier = Modifier.constrainAs(result) {
            top.linkTo(button.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        Button(onClick = onBack) {
            Text(text = "Volver")
        }
    }
}
