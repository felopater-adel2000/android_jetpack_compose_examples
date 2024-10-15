package com.restart.jetpack_compose_examples

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DataActivity : ComponentActivity() {
    private val TAG = "DataActivity"

    val state = MutableStateFlow(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jetpack_compose_examplesTheme {
                TestView()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TestView()
{
    Jetpack_compose_examplesTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            Column(
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() } // This is mandatory
                    ) {

                    }
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                val composer = Recomposer.runningRecomposers.collectAsState()

                Button(
                    onClick = { },
                    border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {



                }

            }

        }
    }
}

fun Modifier.clickableWithoutEffect(onClick: () -> Unit): Modifier = this.clickable(
    indication = null,
    interactionSource =  MutableInteractionSource() , // This is mandatory,
    onClick = onClick
)


