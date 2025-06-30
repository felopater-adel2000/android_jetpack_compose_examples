package com.restart.jetpack_compose_examples

import android.Manifest
import android.util.Log
import androidx.camera.compose.CameraXViewfinder
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(viewModel: CameraViewModel) {

    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    if (cameraPermissionState.status.isGranted) {

        CameraPreviewContent(viewModel)

    } else {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {

            Text("Need Camera Permission")

            Button(
                onClick = { cameraPermissionState.launchPermissionRequest() },
            ) {
                Text("Request Permission")
            }

        }
    }
}


@Composable
private fun CameraPreviewContent(viewModel: CameraViewModel) {
    val lifecycle = LocalLifecycleOwner.current
    val context = LocalContext.current
    val surfaceRequest by viewModel.surfaceRequest.collectAsState()
    LaunchedEffect(lifecycle) {
        viewModel.bindToCamera(context, lifecycle)
    }

    surfaceRequest?.let { request ->
        Log.d("FeloTAG", "CameraPreviewContent: SurfaceRequest is not null")
        CameraXViewfinder(
            modifier = Modifier.fillMaxSize(),
            surfaceRequest = request,
        )
    }
}