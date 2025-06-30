package com.restart.jetpack_compose_examples

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Locale

class CameraViewModel : ViewModel() {

    private val _surfaceRequest = MutableStateFlow<SurfaceRequest?>(null)
    val surfaceRequest = _surfaceRequest.asStateFlow()

    private val cameraPreviewUseCase = Preview.Builder().build().apply {
        setSurfaceProvider { newRequest -> _surfaceRequest.update { newRequest } }
    }

    private val captureImageUseCase = ImageCapture.Builder().build()

    suspend fun bindToCamera(appContext: Context, lifecycleOwner: LifecycleOwner) {

        val cameraProvider = ProcessCameraProvider.awaitInstance(appContext)


        cameraProvider.bindToLifecycle(
            lifecycleOwner = lifecycleOwner,
            cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
            useCases = listOf(cameraPreviewUseCase, captureImageUseCase).toTypedArray()
        )

        try {
            awaitCancellation()
        } finally {
            cameraProvider.unbindAll()
        }

    }

    fun test() {
    }


    fun takePhoto(appContext: Context) {

        // Get a stable reference of the modifiable image capture use case
        val imageCapture = captureImageUseCase

        // Create time stamped name and MediaStore entry.
        val name = SimpleDateFormat("dd/MM/YY", Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                appContext.contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        // Set up image capture listener, which is triggered after photo has
        // been taken
        //imageCapture.takePicture {  }
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(appContext),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e("FeloTAG", "Photo capture failed: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val msg = "Photo capture succeeded: ${output.savedUri}"

                    val uriFilePath = output.savedUri?.path
                    val path: String = uriFilePath.toString()


                    val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        ImageDecoder.decodeBitmap(
                            ImageDecoder.createSource(
                                appContext.contentResolver,
                                output.savedUri!!
                            )
                        )
                    } else {
                        MediaStore.Images.Media.getBitmap(
                            appContext.contentResolver,
                            output.savedUri
                        )
                    }

                    Log.e("FeloTAG", "data" + BitMapToString(bitmap))


                    val bundle = Bundle()
                    bundle.putString("image", path)
                    Toast.makeText(appContext, msg, Toast.LENGTH_SHORT).show()
                    Log.d("FeloTAG", msg)
                }
            }
        )
// if you don't want to store captured photo in  external storage, just need a image
        /*  imageCapture.takePicture(cameraExecutor, object :
               ImageCapture.OnImageCapturedCallback() {
               override fun onCaptureSuccess(image: ImageProxy) {
                   //get bitmap from image
                   Handler(Looper.getMainLooper()).post(Runnable {

                       val bitmap = imageProxyToBitmap(image)
                       viewBinding.image.setImageBitmap(bitmap)
                       viewBinding.image.visibility = View.VISIBLE
                    })
                   super.onCaptureSuccess(image)
               }

               override fun onError(exception: ImageCaptureException) {
                   super.onError(exception)
               }

           })*/


    }

    fun BitMapToString(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }


}