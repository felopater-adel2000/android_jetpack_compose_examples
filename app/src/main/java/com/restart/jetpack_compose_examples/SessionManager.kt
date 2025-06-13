package com.restart.jetpack_compose_examples

import android.annotation.SuppressLint
import androidx.datastore.core.Serializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class SessionManager(
    val userName: String = "",

    val token: String = "",

    val id: Int = 90,

    val isLoggedIn: Boolean = false
)


class SessionManagerSerializer : Serializer<SessionManager> {
    override val defaultValue: SessionManager
        get() = SessionManager()

    override suspend fun readFrom(input: InputStream): SessionManager {
        return try {
            Json.decodeFromString<SessionManager>(
                input.readBytes().decodeToString()
            )
        } catch (exception: SerializationException) {
            defaultValue
        }
    }

    override suspend fun writeTo(
        t: SessionManager,
        output: OutputStream
    ) {
        output.write(
            Json.encodeToString(t).encodeToByteArray()
        )
    }

}