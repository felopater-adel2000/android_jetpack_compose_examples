package com.restart.jetpack_compose_examples

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets.Side
import android.widget.RemoteViews.RemoteView
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
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentComposer
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
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.lifecycle.lifecycleScope
import com.restart.jetpack_compose_examples.databinding.ActivityMainBinding
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.math.log
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.composeView.setContent {

        }
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_compose_examplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val sessionManager by dataStore.data.collectAsState(SessionManager())

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "${sessionManager.token} /// ${sessionManager.id}"
                        )

                        Button(
                            onClick = {
                                lifecycleScope.launch {
                                    dataStore.updateData {
                                        it.copy(
                                            token = UUID.randomUUID().toString(),
                                            id = Random.nextInt(1000)
                                        )
                                    }
                                }
                            }
                        ) { Text("Click Me")}
                    }


                }
            }
        }
    }
}


val Context.dataStore: DataStore<SessionManager> by dataStore(
    fileName = "session_manager",
    serializer = SessionManagerSerializer()
)

