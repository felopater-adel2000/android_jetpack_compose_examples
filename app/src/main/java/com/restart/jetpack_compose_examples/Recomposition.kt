package com.restart.jetpack_compose_examples

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val TAG = "Recomposition"
@Composable
fun UnderstandRecomposition(
    state: MainState,
    onValueChange: (Float) -> Unit,
    onButtonClick: () -> Unit
) {
    Log.d(TAG, "UnderstandRecomposition: Start")
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            Log.d(TAG, "UnderstandRecomposition: build Slider")
            Slider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                onValueChange = onValueChange,
                value = state.sliderValue.toFloat(),
                valueRange = 0f..100f,
                steps = 1
            )

            key(state.counter) {
                CounterRow(counter = state.counter, onButtonClick = onButtonClick)
            }

        }
    }
}

@Composable
fun CounterRow(counter: Int, onButtonClick: () -> Unit) {
    Log.d(TAG, "CounterRow: build CounterRow")
    Log.d(TAG, "CounterRow: onButtonClick = ${onButtonClick.hashCode()}")
    /** SHOULD NOT BE CALLED ON SLIDER CHANGE **/
    Row(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = onButtonClick) {
            Text(text = "Click me!")
        }
        Spacer(modifier = Modifier.width(24.dp))
        Text(text = counter.toString())
    }
}