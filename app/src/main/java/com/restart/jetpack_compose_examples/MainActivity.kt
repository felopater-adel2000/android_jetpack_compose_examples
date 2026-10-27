package com.restart.jetpack_compose_examples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.restart.jetpack_compose_examples.databinding.ActivityMainBinding
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme

class MainActivity : ComponentActivity() {

    val url =
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfv-1HbVT7Rk0SXLexFsTjNHijDRYldwxALeQJp-CKezjOpzhvIY-kaEs&s=10"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.composeView.setContent {

        }
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_compose_examplesTheme {

                var outerHeight by remember { mutableIntStateOf(0) }
                var innerHeight by remember { mutableIntStateOf(0) }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = androidx.compose.ui.graphics.Color.White)
                        .onSizeChanged { coordinates ->
                            outerHeight = coordinates.height
                        }
                ) {

                    AsyncImage(
                        model = url,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                            .drawWithContent {
                                drawContent()
                                drawRect(
                                    brush = Brush.verticalGradient(
                                        0.5f to Color.Black,
                                        1f to Color.Transparent
                                    ),
                                    blendMode = BlendMode.DstIn
                                )
                            }
                    )

                }


            }
        }
    }
}


