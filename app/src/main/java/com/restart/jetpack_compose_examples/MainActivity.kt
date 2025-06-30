package com.restart.jetpack_compose_examples

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.restart.jetpack_compose_examples.databinding.ActivityMainBinding
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity2222"
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

        var user1 = User("f", "A")

        val user2 = User("c", "b")


        Log.d(TAG, "onCreate: User1 Hash = ${user1.hashCode()}")
        Log.d(TAG, "onCreate: User1 Hash = ${user2.hashCode()}")

        user1 = user1.copy(name = "Felopater Adel", email = "")

        Log.d(TAG, "onCreate: User1 Hash = ${user1.hashCode()}")

        user1 = user1.copy(name = user2.name, email = user2.email.co)

        Log.d(TAG, "onCreate: User1 Hash = ${user1.hashCode()}")
    }
}

data class User(
    val name: String,
    val email: String
) {


}


