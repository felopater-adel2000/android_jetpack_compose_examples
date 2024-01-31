package com.restart.jetpack_compose_examples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme
import kotlinx.coroutines.flow.asStateFlow

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModels by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_compose_examplesTheme {
                // A surface container using the 'background' color from the theme

                val state: MainState by viewModel.state.collectAsState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnderstandRecomposition(
                        state = state,
                        onValueChange = { viewModel.updateSlider(it.toInt()) },
                        onButtonClick = viewModel::updateCounter
                    )
                }
            }
        }
    }
}


/**
 * Structure :
 * (SliderValue + Counter)  ==> MainState  ==>  MainViewModel  ==>  Activity  ==>  MainScaffold
 *
 * Very Important Note : Lambda Expression has Custom Code you can get it by using hasCode, in the Re-Composition this Code is Change
**/
