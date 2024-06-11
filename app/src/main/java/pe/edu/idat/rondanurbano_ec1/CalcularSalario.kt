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
fun CalcularSalario(onBack: () -> Unit){
    var horasTrabajadas = remember { mutableStateOf("") }
    var salario = remember { mutableStateOf("")}
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        val (input, button, result) = createRefs()

        TextField(
            value = horasTrabajadas.value,
            onValueChange = { horasTrabajadas.value = it },
            label = { Text("Horas trabajadas") },
            modifier = Modifier
                .padding(top = 60.dp)
                .constrainAs(input) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Spacer(modifier = Modifier
            .height(16.dp)
            .constrainAs(createRef()) {
                top.linkTo(input.bottom)
            })

        Button(onClick = {
            val horas = horasTrabajadas.value.toIntOrNull() ?: 0
            salario.value = if (horas <= 40) {
                (horas * 16).toString()
            } else {
                (40 * 16 + (horas - 40) * 20).toString()
            }
        }, modifier = Modifier.constrainAs(button) {
            top.linkTo(input.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Text("Calcular Salario")
        }

        Text("Salario Semanal: ${salario.value}", modifier = Modifier.constrainAs(result) {
            top.linkTo(button.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        Button(onClick = onBack) {
            Text(text = "Volver")
        }
    }

}