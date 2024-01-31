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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.afollestad.materialdialogs.MaterialDialog
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