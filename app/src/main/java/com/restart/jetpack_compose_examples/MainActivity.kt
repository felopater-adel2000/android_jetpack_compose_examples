package com.restart.jetpack_compose_examples

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.restart.jetpack_compose_examples.databinding.ActivityMainBinding
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme
import java.io.File


class MainActivity : ComponentActivity() {
    private val TAG = "Felo"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_compose_examplesTheme {
                Content()
            }
        }
    }



    private fun manageAllFilesAccessPermission()
    {
        Log.d(TAG, "manageAllFilesAccessPermission: ${Environment.isExternalStorageManager()}")
        val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
        intent.data = Uri.parse("package:${applicationContext.packageName}")
        startActivity(intent)
        /*val dir = Environment.getExternalStorageDirectory()
        Log.d(TAG, "manageAllFilesAccessPermission: ${dir?.path}")
        Log.d(TAG, "manageAllFilesAccessPermission: ${dir?.absolutePath}")

        val newFile = File(dir, "newFile.txt")
        if(!newFile.exists()) {
            newFile.createNewFile()
        }*/
    }

    @RequiresApi(33)
    private fun requestPermissionForAllFilesAccessAndCreatFileInRootAPI33()
    {
        if(Environment.isExternalStorageManager())
        {
            val rootDirectory = Environment.getExternalStorageDirectory()
            val newFile = File(rootDirectory, "newFile.txt")
            if(!newFile.exists()) {
                newFile.createNewFile()
            }
        }
        else
        {
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            intent.data = Uri.parse("package:${packageName}")
            startActivity(intent)
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun Content() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.clickable { requestPermissionForAllFilesAccessAndCreatFileInRootAPI33() },
                    text = "Hello, World!",
                    fontSize = 30.sp
                )
            }
        }
    }
}


