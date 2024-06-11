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
fun CalcularPromedio(onBack: () -> Unit) {
    var nota1 = remember { mutableStateOf("") }
    var nota2 = remember { mutableStateOf("") }
    var nota3 = remember { mutableStateOf("") }
    var nota4 = remember { mutableStateOf("") }
    var resultado = remember { mutableStateOf("") }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (input1, input2, input3, input4, button, result) = createRefs()

        TextField(
            value = nota1.value,
            onValueChange = { nota1.value = it },
            label = { Text("Nota 1") },
            modifier = Modifier
                .padding(top = 60.dp)
                .constrainAs(input1) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Spacer(modifier = Modifier.height(8.dp).constrainAs(createRef()) {
            top.linkTo(input1.bottom)
        })

        TextField(
            value = nota2.value,
            onValueChange = { nota2.value = it },
            label = { Text("Nota 2") },
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(input2) {
                    top.linkTo(input1.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Spacer(modifier = Modifier.height(8.dp).constrainAs(createRef()) {
            top.linkTo(input2.bottom)
        })

        TextField(
            value = nota3.value,
            onValueChange = { nota3.value = it },
            label = { Text("Nota 3") },
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(input3) {
                    top.linkTo(input2.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Spacer(modifier = Modifier.height(8.dp).constrainAs(createRef()) {
            top.linkTo(input3.bottom)
        })

        TextField(
            value = nota4.value,
            onValueChange = { nota4.value = it },
            label = { Text("Nota 4") },
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(input4) {
                    top.linkTo(input3.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Spacer(modifier = Modifier.height(16.dp).constrainAs(createRef()) {
            top.linkTo(input4.bottom)
        })

        Button(onClick = {
            val notas = listOfNotNull(
                nota1.value.toDoubleOrNull(),
                nota2.value.toDoubleOrNull(),
                nota3.value.toDoubleOrNull(),
                nota4.value.toDoubleOrNull()
            )
            if (notas.size == 4) {
                val notasOrdenadas = notas.sorted()
                val notaEliminada = notasOrdenadas.first()
                val promedio = notasOrdenadas.drop(1).average()
                resultado.value = "Nota Eliminada: $notaEliminada, Promedio: $promedio"
            } else {
                resultado.value = "Por favor, ingrese todas las notas."
            }
        }, modifier = Modifier.constrainAs(button) {
            top.linkTo(input4.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Text("Calcular Promedio")
        }

        Text(resultado.value, modifier = Modifier.constrainAs(result) {
            top.linkTo(button.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        Button(onClick = onBack) {
            Text(text = "Volver")
        }
    }
}
