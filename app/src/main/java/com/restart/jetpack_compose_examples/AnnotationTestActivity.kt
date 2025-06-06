package com.restart.jetpack_compose_examples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme

class AnnotationTestActivity : ComponentActivity() {
    

    @OptIn(InternalComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        val userSettings = UserSettings()
        val profile = Profile(name = "John Doe", age = 30)
        setContent {
            Jetpack_compose_examplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        UserProfile(profile = profile)
                        SettingsScreen(userSettings = userSettings)
                    }
                }
            }
        }

        ReadOnlyComposable()
    }

    
    
    @InternalComposeApi
    @Composable
    fun HelloWorld(name: MutableState<String>) {

//        val bnm = remember { this.ReadOnlyComposable() }
//

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(name.value)

            Button(
                onClick = {
                    name.value = "Hello Felopater ${System.currentTimeMillis()}"
                }
            ) {
                Text(text = "Click Me")
            }
        }


    }


    @ReadOnlyComposable
    fun ReadOnlyComposable() {
    }


    @Composable
    fun ScreenRoot() {
        AnnotationExample(
            x = {
                //Text(text = "Hello World")
            },
            y = {
                //Text("Hello World")
            }
        )
        Text("Felo")
    }

    @Composable
    inline fun AnnotationExample(
        x: @DisallowComposableCalls () -> Unit,
        noinline y: () -> Unit
    ) {
        x()
        y()
        Text(text = "Hello World")

        LazyColumn(

        ) {
            item {  }
        }
    }
}

@Immutable
data class Profile(
    val name: String,
    val age: Int
)

@Stable
class UserSettings {
    var theme: String = "Light"
        private set

    fun changeTheme(newTheme: String) {
        theme = newTheme
    }
}

@Composable
fun UserProfile(profile: Profile) {
    Column {
        Text(text = "Name: ${profile.name}")
        Text(text = "Age: ${profile.age}")
    }
}

@Composable
fun SettingsScreen(userSettings: UserSettings) {
    Column {
        Text(text = "Current Theme: ${userSettings.theme}")
        Button(onClick = { userSettings.changeTheme("Dark") }) {
            Text("Change Theme")
        }
    }
}