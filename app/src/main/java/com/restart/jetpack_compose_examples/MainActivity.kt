package com.restart.jetpack_compose_examples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.restart.jetpack_compose_examples.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity2222"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}



