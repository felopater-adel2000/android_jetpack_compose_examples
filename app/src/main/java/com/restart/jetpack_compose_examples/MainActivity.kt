package com.restart.jetpack_compose_examples

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.restart.jetpack_compose_examples.databinding.ActivityMainBinding
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme
import com.restart.jetpack_compose_examples.ui.theme.TAG
import kotlinx.coroutines.launch
import java.util.UUID

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.composeView.setContent {

        }
        val uuid = "123e4567-e89b-12d3-a456-426614174000"
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_compose_examplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Button(
                            onClick = {
                                val uuid = UUID.fromString(uuid)
                                val worker = OneTimeWorkRequestBuilder<CoroutineWork>()
                                    .addTag("CoroutineWork")
                                    .setId(uuid)
                                    .build()

                                WorkManager.getInstance(this@MainActivity)
                                    .enqueueUniqueWork(
                                        uniqueWorkName = "MyWork",
                                        existingWorkPolicy = androidx.work.ExistingWorkPolicy.REPLACE,
                                        request = worker
                                    )

                                Log.d(TAG, "onCreate: uuid ${uuid.toString()}")
                                Log.d(TAG, "onCreate: workerId ${worker.id.toString()}")

                            }
                        ) { Text("Start Worker") }

                        Button(
                            onClick = {

                                WorkManager.getInstance(this@MainActivity)
                                    .cancelWorkById(UUID.fromString(uuid))
                            }
                        ) { Text("Stop Worker") }

                        Button(
                            onClick = {
                                lifecycleScope.launch {
                                    WorkManager.getInstance(this@MainActivity)
                                        .getWorkInfoByIdFlow(UUID.fromString(uuid))
                                        .collect { info ->
                                            info?.let { workInfo ->
                                                val currentIndex =
                                                    workInfo.progress.getInt("index", -1)
                                                Log.d(
                                                    TAG,
                                                    "Worker State: ${workInfo.state}, Index: $currentIndex"
                                                )
                                            }
                                        }
                                }
                            }
                        ) { Text(" Worker State ") }

                    }

                }
            }
        }
    }
}
