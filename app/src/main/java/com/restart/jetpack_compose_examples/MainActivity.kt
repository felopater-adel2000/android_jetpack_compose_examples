package com.restart.jetpack_compose_examples

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme

class MainActivity : ComponentActivity() {

    private val TAG = "FELOTAG"

    private var bluetoothAdapter: BluetoothAdapter? = null

    private val enableBluetoothResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                Log.d(TAG, "Result Launcher: RESULT_OK")
            } else {
                Log.d(TAG, "Result Launcher: RESULT_CANCELED: ")
            }
            Log.d(TAG, "Bluetooth Enable ${bluetoothAdapter?.isEnabled}: ")
        }

    private val bluetoothIntentFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
    private val bluetoothStateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action
            Log.d(TAG, "onReceive: $action")

            when (action) {
                BluetoothAdapter.ACTION_STATE_CHANGED -> {
                    intent?.extras?.keySet()?.forEach {
                        Log.d(TAG, "Key: $it, Value: ${intent.extras?.get(it)}")
                    }
                }

                BluetoothAdapter.ACTION_DISCOVERY_STARTED -> {
                    Log.d(TAG, "onReceive: ACTION_DISCOVERY_STARTED")
                }

                BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                    Log.d(TAG, "onReceive: ACTION_DISCOVERY_FINISHED")
                }

                BluetoothDevice.ACTION_FOUND -> {
                    val device: BluetoothDevice? = intent.getParcelableExtra(
                        BluetoothDevice.EXTRA_DEVICE,
                        BluetoothDevice::class.java
                    )
                    val deviceName = device?.name
                    val deviceHardwareAddress = device?.address
                    Log.d(
                        TAG,
                        "onReceive: New Bluetooth Device Found: $deviceName, $deviceHardwareAddress"
                    )
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_compose_examplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Button(
                            onClick = { create() }
                        ) { Text(text = "create") }

                        Button(
                            onClick = { openBluetooth() }
                        ) { Text(text = "openBluetooth") }

                        Button(
                            onClick = { getAllParedDevices() }
                        ) { Text(text = "getAllParedDevices") }

                        Button(
                            onClick = { findDevices() }
                        ) { Text(text = "findDevices") }

                        Button(
                            onClick = { getBoundedDevices() }
                        ) { Text(text = "getBoundedDevice") }

                    }
                }
            }
        }

        create()
        listenToBluetoothState()
    }

    private fun create() {
        val bluetoothManager = getSystemService(BluetoothManager::class.java)
        bluetoothAdapter = bluetoothManager.adapter

        if (bluetoothAdapter != null) {
            Log.d(TAG, "create: bluetooth is available")
            Log.d(TAG, "create: bluetooth name: ${bluetoothAdapter?.name}")
            Log.d(TAG, "create: bluetooth address: ${bluetoothAdapter?.address}")
            Log.d(TAG, "create: bluetooth isEnabled: ${bluetoothAdapter?.isEnabled}")
            Log.d(TAG, "create: bluetooth isDiscovering: ${bluetoothAdapter?.isDiscovering}")
        } else {
            Log.d(TAG, "create: bluetooth is not available")
        }
    }


    private fun openBluetooth() {
        if (bluetoothAdapter?.isEnabled == false) {
            Log.d(TAG, "openBluetooth: the bluetooth is closed")


            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            enableBluetoothResultLauncher.launch(intent)


        } else {
            Log.d(TAG, "openBluetooth: the bluetooth is opened")
        }
    }

    private fun listenToBluetoothState() {
        Log.d(TAG, "listenToBluetoothState: Start listining")
        registerReceiver(bluetoothStateReceiver, bluetoothIntentFilter)
    }

    private fun getAllParedDevices() = bluetoothAdapter?.let {
        it.bondedDevices.forEach { bluetoothDevice ->
            Log.d(TAG, "create: bluetooth name: ${bluetoothDevice?.name}")
            Log.d(TAG, "create: bluetooth address: ${bluetoothDevice?.address}")
            Log.d(TAG, "create: bluetooth bondState: ${bluetoothDevice?.bondState}")
            Log.d(TAG, "create: bluetooth uuids: ${bluetoothDevice?.uuids}")
//            Log.d(TAG, "create: bluetooth addressType: ${bluetoothDevice?.addressType}")  // Throw Exception
            Log.d(TAG, "create: bluetooth alias: ${bluetoothDevice?.alias}")
        }
    }

    private fun findDevices() = bluetoothAdapter?.let {
        val result = it.startDiscovery()
        Log.d(TAG, "findDevices: $result")
    }

    private fun getBoundedDevices() = bluetoothAdapter?.let {
        val boundedDevices = it.bondedDevices
        Log.d(TAG, "fetBoundedDevices: ${boundedDevices.size}")
        boundedDevices.forEach {
            Log.d(TAG, "fetBoundedDevices: ${it.name}")
            Log.d(TAG, "fetBoundedDevices: ${it.address}")
            Log.d(TAG, "fetBoundedDevices: ${it.bondState}")
        }
    }
}


