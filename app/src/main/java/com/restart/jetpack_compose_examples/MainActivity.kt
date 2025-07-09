package com.restart.jetpack_compose_examples

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qamar.composescanner.trackRecompositionsIf
import com.restart.jetpack_compose_examples.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.composeView.setContent {

        }
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ComposeTracker()
            }
        }
    }
}


@Composable
fun ComposeTracker() {
    val context = androidx.compose.ui.platform.LocalContext.current

    val num = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AppName()

        Spacer(Modifier.height(14.dp))

        Name(num.value)

        Spacer(Modifier.height(14.dp))

        key("button") {
            Button(
                modifier = Modifier.trackRecompositionsIf(BuildConfig.DEBUG),
                onClick = {
                    num.value += 1
                }
            ) {
                Text(
                    modifier = Modifier.trackRecompositionsIf(BuildConfig.DEBUG),
                    text = "Click Me"
                )
            }
        }


        Button(
            modifier = Modifier.trackRecompositionsIf(BuildConfig.DEBUG),
            onClick = {
                Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(
                text = "Show Toast",
            )
        }

    }

}

@Composable
fun Name(index: Int) {
    Text(
        modifier = Modifier.trackRecompositionsIf(BuildConfig.DEBUG),
        text = "Number $index",
    )
}

@Composable
fun AppName() {
    Text(
        text = "Compose Tracker",
        modifier = Modifier.trackRecompositionsIf(BuildConfig.DEBUG),
    )
}


