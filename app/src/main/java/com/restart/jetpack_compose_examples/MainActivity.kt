package com.restart.jetpack_compose_examples

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.restart.jetpack_compose_examples.databinding.ActivityMainBinding
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val TAG = "Felo"
    private lateinit var binding: ActivityMainBinding

    private val mSharedFlow = MutableStateFlow<String?>(null)
    private val message = "Hello, World"
    private var x= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editText.doAfterTextChanged {
            lifecycleScope.launch { mSharedFlow.emit(it.toString()) }
        }


        lifecycleScope.launch {
            mSharedFlow
                .debounce(1000)
                .collect {
                    Toast.makeText(this@MainActivity, it ?: "", Toast.LENGTH_SHORT).show()
                }
        }

    }
}
