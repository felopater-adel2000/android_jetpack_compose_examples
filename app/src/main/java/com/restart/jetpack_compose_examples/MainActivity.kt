package com.restart.jetpack_compose_examples

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.exoplayer.util.EventLogger
import com.restart.jetpack_compose_examples.ExoPlayerPositionListener.Companion.setOnPositionChange
import com.restart.jetpack_compose_examples.databinding.ActivityMainBinding
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme

class MainActivity : ComponentActivity() {

    val soundUri =
        "https://seha-w-afeya.fra1.digitaloceanspaces.com/stg/attachment/679e3b715d402f192b8f6db9-ab3c5a26-f45a-4b84-83ff-e490cd8d5517.mp3?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=DO00C72MW8CKEZ7FHFWB%2F20250820%2Ffra1%2Fs3%2Faws4_request&X-Amz-Date=20250820T093453Z&X-Amz-Expires=518400&X-Amz-Signature=7a04c95c4a4b6f6ef95be3a774ad14ca22bd532c1abbfa3b9e9cff789fbc0a0c&X-Amz-SignedHeaders=host&x-amz-checksum-mode=ENABLED&x-id=GetObject"

    val exoPlayer by lazy { ExoPlayer.Builder(this).build() }
    private lateinit var binding: ActivityMainBinding

    var progressInPercentage by mutableStateOf("0.0")

    val playerListener = object : Player.Listener {

        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)

            // Update progress when playing or buffering
            if (playbackState == Player.STATE_READY || playbackState == Player.STATE_BUFFERING) {
                val duration = exoPlayer.duration.takeIf { it > 0 } ?: return
                val position = exoPlayer.currentPosition
                val progress = (position.toDouble() / duration.toDouble()) * 100
                progressInPercentage = String.format("%.2f", progress)
            }
        }

        override fun onPositionDiscontinuity(
            oldPosition: Player.PositionInfo,
            newPosition: Player.PositionInfo,
            reason: Int
        ) {
            super.onPositionDiscontinuity(oldPosition, newPosition, reason)
            Log.d("FeloTag", "onPositionDiscontinuity: ")
        }

        override fun onTimelineChanged(timeline: Timeline, reason: Int) {
            super.onTimelineChanged(timeline, reason)
            Log.d("FeloTag", "onTimelineChanged: ")
        }

    }

    @OptIn(UnstableApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exoPlayer.addAnalyticsListener(EventLogger())
        exoPlayer.addListener(playerListener)
        setContent {
            Jetpack_compose_examplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Progrss: $progressInPercentage",
                            color = Color.White
                        )

                        Button(
                            onClick = {
                                val mediaItem = MediaItem.Builder().setUri(soundUri.toUri()).build()

                                val mediaSource =
                                    ProgressiveMediaSource.Factory(DefaultDataSource.Factory(this@MainActivity))
                                        .createMediaSource(mediaItem)

                                exoPlayer.apply {
                                    setMediaSource(mediaSource)
                                    playWhenReady = true
                                    prepare()
                                    setOnPositionChange { currentPosition, inPercentage ->
                                        progressInPercentage = String.format("%.2f", inPercentage)
                                    }
                                }
                            }
                        ) { Text("Play") }

                    }
                }
            }
        }
    }
}


