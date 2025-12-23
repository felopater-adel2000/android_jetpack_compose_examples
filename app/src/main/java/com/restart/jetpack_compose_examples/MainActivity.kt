package com.restart.jetpack_compose_examples

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
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
import androidx.lifecycle.lifecycleScope
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.UUID
import kotlin.coroutines.resume

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
                            onClick = {
                                lifecycleScope.launch {
                                    getBoundedDevices()
                                }
                            }
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

    private suspend fun getBoundedDevices() = bluetoothAdapter?.let {
        val boundedDevices = it.bondedDevices
        Log.d(TAG, "fetBoundedDevices: ${boundedDevices.size}")
        boundedDevices.forEach { bluetoothDevice ->
            Log.d(TAG, "fetBoundedDevices: ${bluetoothDevice.name}")
            Log.d(TAG, "fetBoundedDevices: ${bluetoothDevice.address}")
            Log.d(TAG, "fetBoundedDevices: ${bluetoothDevice.bondState}")

            val profiles = getDeviceProfiles(bluetoothDevice)

            profiles.forEach { pt ->
                val isConeccted = isDeviceConnectedByProfile(bluetoothDevice, pt)

                Log.d(TAG, "fetBoundedDevices: at Profile Type $pt isConeccted: $isConeccted")
            }


            Log.d(
                TAG,
                "============================================================================="
            )
        }
    }

    /** By Reflection **/
    fun isDeviceConnectedByReflection(device: BluetoothDevice): Boolean {
        val method = device.javaClass.getMethod("isConnected")
        return method.invoke(device) as Boolean
    }


    fun getDeviceProfiles(device: BluetoothDevice): List<Int> {
        val profiles = mutableListOf<Int>()
        val uuids = device.uuids ?: return emptyList()

        uuids.forEach { parcelUuid ->
            Log.d(TAG, "UUID: ${parcelUuid.uuid}")

            when (parcelUuid.uuid) {
                // Audio Profiles
                BluetoothUuids.A2DP_SINK -> profiles.add(BluetoothProfile.A2DP)
                BluetoothUuids.A2DP_SOURCE -> profiles.add(BluetoothProfile.A2DP)
                BluetoothUuids.ADV_AUDIO_DIST -> profiles.add(BluetoothProfile.A2DP)

                // Headset Profiles
                BluetoothUuids.HSP -> profiles.add(BluetoothProfile.HEADSET)
                BluetoothUuids.HFP -> profiles.add(BluetoothProfile.HEADSET)
                BluetoothUuids.HSP_AG -> profiles.add(BluetoothProfile.HEADSET)
                BluetoothUuids.HFP_AG -> profiles.add(BluetoothProfile.HEADSET)

                // HID Profile
                BluetoothUuids.HID -> profiles.add(BluetoothProfile.HID_DEVICE)
                BluetoothUuids.HOGP -> profiles.add(BluetoothProfile.HID_DEVICE)

                // GATT (BLE)
                else -> {
                    // Most BLE devices will have custom UUIDs
                    if (device.type == BluetoothDevice.DEVICE_TYPE_LE ||
                        device.type == BluetoothDevice.DEVICE_TYPE_DUAL
                    ) {
                        profiles.add(BluetoothProfile.GATT)
                    }
                }
            }
        }

        return profiles.distinct()
    }


    suspend fun isDeviceConnectedByProfile(
        bluetoothDEvice: BluetoothDevice,
        profileType: Int
    ): Boolean =
        suspendCancellableCoroutine { continuation ->

            if (bluetoothAdapter == null) continuation.resumeWith(Result.success(false))

            val profileProxyREsult = bluetoothAdapter!!.getProfileProxy(
                this@MainActivity,
                object : BluetoothProfile.ServiceListener {
                    override fun onServiceConnected(
                        profile: Int,
                        proxy: BluetoothProfile?
                    ) {
                        Log.d(TAG, "onServiceConnected: ")
                        val connectedDevices = proxy?.connectedDevices
                        Log.d(
                            TAG,
                            "onServiceConnected: connectedDevices size ${connectedDevices?.size}"
                        )
                        connectedDevices?.forEach { device ->
                            Log.d(
                                TAG,
                                "onServiceConnected: device.address:${device.address} :::: bluetoothDEvice.address:${bluetoothDEvice.address}"
                            )
                            if (bluetoothDEvice.address == device.address) {
                                bluetoothAdapter?.closeProfileProxy(profile, proxy)
                                return continuation.resume(true)

                            }
                        }
                        bluetoothAdapter?.closeProfileProxy(profile, proxy)
                        return continuation.resume(false)
                    }

                    override fun onServiceDisconnected(profile: Int) {
                        Log.d(TAG, "onServiceDisconnected: Profile: $profile")
                    }

                },
                profileType,
            )
            Log.d(TAG, "isDeviceConnectedByProfile: profileProxyREsult: $profileProxyREsult")
        }
}


object BluetoothUuids {
    // Audio Profiles
    val A2DP_SINK = UUID.fromString("0000110B-0000-1000-8000-00805F9B34FB")
    val A2DP_SOURCE = UUID.fromString("0000110A-0000-1000-8000-00805F9B34FB")
    val ADV_AUDIO_DIST = UUID.fromString("0000110D-0000-1000-8000-00805F9B34FB")

    // Headset Profiles
    val HSP = UUID.fromString("00001108-0000-1000-8000-00805F9B34FB")
    val HSP_AG = UUID.fromString("00001112-0000-1000-8000-00805F9B34FB")
    val HFP = UUID.fromString("0000111E-0000-1000-8000-00805F9B34FB")
    val HFP_AG = UUID.fromString("0000111F-0000-1000-8000-00805F9B34FB")

    // HID Profile
    val HID = UUID.fromString("00001124-0000-1000-8000-00805F9B34FB")
    val HOGP = UUID.fromString("00001812-0000-1000-8000-00805F9B34FB")

    // Other Common Profiles
    val HEALTH_DEVICE = UUID.fromString("00001400-0000-1000-8000-00805F9B34FB")
    val PANU = UUID.fromString("00001115-0000-1000-8000-00805F9B34FB") // PAN User
}


