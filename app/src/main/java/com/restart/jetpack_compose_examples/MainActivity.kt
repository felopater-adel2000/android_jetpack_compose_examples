package com.restart.jetpack_compose_examples

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.restart.jetpack_compose_examples.annotation.FakeResponse
import com.restart.jetpack_compose_examples.databinding.ActivityMainBinding
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme
import org.reflections.Reflections

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

                }
            }
        }

        test()
    }

    private fun test() {
        val reflections = Reflections("com.restart.jetpack_compose_examples.annotation")
        val annotatedClasses = reflections.getTypesAnnotatedWith(FakeResponse::class.java)
        Log.d("FakeResponse", "Annotated classes found: ${annotatedClasses.size}")
        for (clazz in annotatedClasses) {
            Log.d("FakeResponse", "Found annotated class: ${clazz.simpleName}")
        }

    }

}


