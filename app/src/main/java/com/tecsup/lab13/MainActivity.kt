package com.tecsup.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
                AnimatedSizeAndPositionBox()
            }
        }
    }
}

@Composable
fun AnimatedSizeAndPositionBox() {
    // Estados para la posición y el tamaño
    val isExpanded = remember { mutableStateOf(false) }

    // Tamaño animado
    val boxSize = animateDpAsState(
        targetValue = if (isExpanded.value) 150.dp else 100.dp,
        animationSpec = tween(durationMillis = 500)
    )

    // Offset animado
    val boxOffset = animateDpAsState(
        targetValue = if (isExpanded.value) 100.dp else 0.dp,
        animationSpec = tween(durationMillis = 500)
    )

    // UI
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(boxSize.value)
                .offset(x = boxOffset.value, y = boxOffset.value) // Posición animada
                .background(Color.Blue)
        )
        Button(
            onClick = { isExpanded.value = !isExpanded.value },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Animate Box")
        }
    }
}