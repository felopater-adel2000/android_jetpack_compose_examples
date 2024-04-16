package com.restart.jetpack_compose_examples

import android.os.Bundle
import android.util.Log
import android.view.WindowInsets.Side
import android.widget.RemoteViews.RemoteView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.restart.jetpack_compose_examples.databinding.ActivityMainBinding
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.math.log

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.composeView.setContent {

        }
        super.onCreate(savedInstanceState)
        setContent {

            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
            ) {
                TestAnimation()
            }
        }

    }
}



@Composable
fun TestScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .size(300.dp)
                .background(Color.Black)
                .align(Alignment.Center)
        ){
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red)
                    .align(Alignment.Center)
            ){
                Icon(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(20.dp)
                        .background(Color.Blue),
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = null
                )
            }
        }


    }
}



@Preview
@Composable()
fun TestAnimation()
{
    var bool by remember {
        mutableStateOf(false)
    }
    val value = animateIntAsState(
        targetValue = if(bool) 100 else 0,
        label = "",
        animationSpec = TweenSpec(durationMillis = 5000)
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            modifier = Modifier.align(Alignment.Center)
                .clickable { bool = !bool },
            text = "Text ${value.value}"
        )


    }
}

