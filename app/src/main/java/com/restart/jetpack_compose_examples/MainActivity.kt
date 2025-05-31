package com.restart.jetpack_compose_examples

import android.os.Bundle
import android.util.Log
import android.view.WindowInsets.Side
import android.widget.RemoteViews.RemoteView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.restart.jetpack_compose_examples.databinding.ActivityMainBinding
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme
import com.restart.jetpack_compose_examples.ui.theme.MyEnum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import java.util.UUID
import kotlin.math.log

class MainActivity : ComponentActivity() {

    @OptIn(InternalSerializationApi::class)
    var person by mutableStateOf(Person("Felopater Adel"))

    var stateInt = 0

    @OptIn(InternalSerializationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_compose_examplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        stateInt++
                        Text(
                            text = person.name,
                        )

                        Text(
                            text = stateInt.toString(),
                        )

                        Button(
                            onClick = {
                                val personJson = Json.encodeToString(this@MainActivity.person)

                                val newPerson = Json.decodeFromString<Person>(personJson)
                                this@MainActivity.person = newPerson.copy(name = "New Name")
                            }
                        ) { Text("Click Me") }

                    }

                }
            }
        }

        encodingAndDecoding()
//        encodingAndDecodingEnumClass()
//        basicTypeSerializer()
    }


    @OptIn(InternalSerializationApi::class)
    fun encodingAndDecoding() {
        val person = Person("Felopater Adel")

        val json = Json.encodeToString(person)

        Log.d("TAG", "onCreate: $json")

        val newPerson = Json.decodeFromString<Person>(json)
        Log.d("TAG", "onCreate: $newPerson")
    }

    fun encodingAndDecodingEnumClass() {
        val myEnum = MyEnum.ONE

        val jsonEnum = Json.encodeToString(myEnum)

        Log.d("TAG", "onCreate: $jsonEnum")
    }

    fun basicTypeSerializer() {
        val intSerializer: KSerializer<Int> = Int.serializer()
        Log.d("TAG", "onCreate: ${intSerializer.descriptor}")
    }
}


