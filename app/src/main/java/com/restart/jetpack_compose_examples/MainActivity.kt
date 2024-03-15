package com.restart.jetpack_compose_examples

import android.os.Bundle
import android.util.Log
import android.view.WindowInsets.Side
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_compose_examplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TimerScreen()
                }
            }
        }
    }
}

const val TAG = "GlobalTag"


@Composable
fun Counter() {
    // Define a state variable for the count
    val count = remember { mutableStateOf(0) }

    // Use SideEffect to log the current value of count
    SideEffect {
        // Called on every recomposition
        Log.d(TAG, "Counter: Trigger SideEffect ${count.value}")
    }

    Column {
        Button(onClick = { count.value++ }) {
            // This recomposition doesn't trigger the outer side effect
            // every time button has been tapped
            Text("Increase Count ${count.value}")
        }
    }
}

@Composable
fun TestSideEffect()
{
    var count by remember { mutableStateOf(0) }

    val some by remember { mutableStateOf(0) }
    // this side effect not recomposed until the TestSideEffect recomposed


    key(some){
        SideEffect {
            Log.d(TAG, "TestSideEffect: Trigger SideEffect")
        }
    }

    Column(
    ){

        Button(onClick = { count++ }) {
            Text(text = "count: ${count}")
        }

        Text(text = "Count: $count")

    }

}

@Composable
fun MyCounter()
{
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        var count by remember { mutableStateOf(0) }

        Button(onClick = { count++ }) {
            Text(text = "Click me")
        }

        Text(text = "Count: $count")

    }
}


suspend fun doSomething()
{
    delay(1000)
    Log.d(TAG, "doSomething: Call")
}

@Composable
fun TimerScreen() {
    val elapsedTime = remember { mutableStateOf(0) }

    DisposableEffect(Unit) {
        val scope = CoroutineScope(Dispatchers.Default)
        val job = scope.launch {
            while (true) {
                delay(1000)
                elapsedTime.value += 1
                Log.d(TAG, "TimerScreen: \"Timer is still working ${elapsedTime.value}\"")
            }
        }

        onDispose {
            //job.cancel()
        }
    }

    Text(
        text = "Elapsed Time: ${elapsedTime.value}",
        modifier = Modifier.padding(16.dp),
        fontSize = 24.sp
    )
}
