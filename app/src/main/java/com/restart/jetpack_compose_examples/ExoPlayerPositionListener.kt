package com.restart.jetpack_compose_examples

import android.os.Handler
import android.os.Looper
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

class ExoPlayerPositionListener(private val exoPlayer: ExoPlayer) {

    private val updateInterval = 100L // ms (update 10 times per second)
    private val handler = Handler(Looper.getMainLooper())
    private var isUpdating = false

    init {
        exoPlayer.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                if (isPlaying) startProgressUpdates()
                else stopProgressUpdates()
            }
        })
    }

    private var onProgressChanged: (currentPosition: Long, inPercentage: Double) -> Unit =
        { _, _ -> }

    fun setOnPositionChange(changingHandler: (Long, Double) -> Unit) {
        this.onProgressChanged = changingHandler
    }

    private val updateRunnable = object : Runnable {
        override fun run() {
            if (exoPlayer.isPlaying) {
                val duration = exoPlayer.duration
                val position = exoPlayer.currentPosition

                if (duration > 0) {
                    val percentage = (position.toDouble() / duration.toDouble()) * 100.0
                    onProgressChanged.invoke(exoPlayer.currentPosition, percentage)
                }
            }
            handler.postDelayed(this, updateInterval)
        }
    }

    private fun startProgressUpdates() {
        if (!isUpdating) {
            isUpdating = true
            handler.post(updateRunnable)
        }
    }

    private fun stopProgressUpdates() {
        isUpdating = false
        handler.removeCallbacks(updateRunnable)
    }

    companion object {

        fun ExoPlayer.setOnPositionChange(changingHandler: (Long, Double) -> Unit) {
            val listener = ExoPlayerPositionListener(this)
            listener.setOnPositionChange(changingHandler)
        }

    }
}