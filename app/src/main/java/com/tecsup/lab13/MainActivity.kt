package com.tecsup.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tecsup.lab13.ui.theme.Lab13Theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab13Theme {
                AnimatedContentExample()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentExample() {
    // Estado que representa el estado actual (Cargando, Contenido, Error)
    val currentState = remember { mutableStateOf(State.Loading) }

    // Efectos de entrada y salida personalizados con fadeIn y fadeOut
    val enterTransition = fadeIn(animationSpec = tween(durationMillis = 500))
    val exitTransition = fadeOut(animationSpec = tween(durationMillis = 500))

    // UI con un botón para cambiar de estado
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedContent(
            targetState = currentState.value,
            transitionSpec = {
                enterTransition with exitTransition
            }
        ) { state ->
            when (state) {
                State.Loading -> {
                    Text("Cargando...", modifier = Modifier.fillMaxSize())
                }
                State.Content -> {
                    Text("Contenido cargado correctamente", modifier = Modifier.fillMaxSize())
                }
                State.Error -> {
                    Text("Ocurrió un error", modifier = Modifier.fillMaxSize())
                }
            }
        }

        // Botón para alternar entre los estados
        Button(onClick = {
            currentState.value = when (currentState.value) {
                State.Loading -> State.Content
                State.Content -> State.Error
                State.Error -> State.Loading
            }
        }) {
            Text("Cambiar Estado")
        }
    }
}

// Enum para representar los tres estados
enum class State {
    Loading, Content, Error
}