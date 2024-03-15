package com.restart.jetpack_compose_examples


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.launch


@Composable
@Preview(
    showSystemUi = true,
    showBackground = true,
)
fun TestSideEffectScreen()
{
    val coroutineScope = rememberCoroutineScope()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        var snakBarHostState = remember { SnackbarHostState() }

        LaunchedEffect(key1 = snakBarHostState){
            snakBarHostState.showSnackbar(
                message = "Snakbar Message"
            )
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = {
                SnackbarHost(hostState = snakBarHostState)
            }
        ) {_ ->

        }
    }
}

@Composable
fun AttachViewsLifecycleCallback(
    onCreate: () -> Unit,
    onStart: () -> Unit,
    onResume: () -> Unit,
    onPause: () -> Unit,
    onStop: () -> Unit,
    onDestroy: () -> Unit,
    viewLifecycle : LifecycleOwner = LocalLifecycleOwner.current
)
{
    DisposableEffect(viewLifecycle) {
        val lifecycleEventObserver = LifecycleEventObserver{ _, event ->
            when(event){
                Lifecycle.Event.ON_CREATE -> onCreate()
                Lifecycle.Event.ON_START -> onStart()
                Lifecycle.Event.ON_RESUME -> onResume()
                Lifecycle.Event.ON_PAUSE -> onPause()
                Lifecycle.Event.ON_STOP -> onStop()
                Lifecycle.Event.ON_DESTROY -> onDestroy()
                else -> {}
            }
        }


        viewLifecycle.lifecycle.addObserver(lifecycleEventObserver)
        onDispose{
            viewLifecycle.lifecycle.removeObserver(lifecycleEventObserver)
        }
    }
}