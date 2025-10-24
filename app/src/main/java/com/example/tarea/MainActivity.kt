package com.example.tarea

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Curso(
    val id: Int,
    val nombre: String,
    val descripcion: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ListSample4()
            }
        }
    }
}

@Composable
fun ListSample4() {
    val context = LocalContext.current

    var idCurso by remember { mutableStateOf("") }
    var nombreCurso by remember { mutableStateOf("") }

    // Usamos mutableStateListOf para que Compose observe los cambios
    val cursos = remember {
        mutableStateListOf<Curso>().apply {
            for (i in 1..10) {
                add(Curso(i, "Nombre $i", "Descripción del curso $i"))
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Input ID
        OutlinedTextField(
            label = { Text("ID") },
            value = idCurso,
            onValueChange = { idCurso = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        // Input nombre
        OutlinedTextField(
            label = { Text("Nombre del curso") },
            value = nombreCurso,
            onValueChange = { nombreCurso = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        // Botón Modificar
        Button(
            onClick = {
                val id = idCurso.toIntOrNull()
                if (id != null && id in 1..cursos.size) {
                    val index = cursos.indexOfFirst { it.id == id }
                    if (index != -1) {
                        cursos[index] = cursos[index].copy(nombre = nombreCurso)
                        Toast.makeText(context, "Curso modificado correctamente", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "ID no válido", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Modificar")
        }

        Spacer(Modifier.height(12.dp))

        // Botón Ver Lista
        Button(
            onClick = {
                Toast.makeText(context, "Lista actualizada", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Ver Lista")
        }

        Spacer(Modifier.height(20.dp))

        // Lista LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(cursos) { curso ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE0E0E0))
                        .padding(10.dp)
                ) {
                    Text(
                        text = "ID: ${curso.id} | ${curso.nombre} | ${curso.descripcion}",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}