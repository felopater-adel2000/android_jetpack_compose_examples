package com.restart.jetpack_compose_examples.datastore

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Serializable
data class SessionManager(
    val token: String = ""
) {

    object SessionManagerSerialization : Serializer<SessionManager> {
        override val defaultValue: SessionManager
            get() = SessionManager()

        override suspend fun readFrom(input: InputStream): SessionManager {
            return try {
                val byteArray = withContext(Dispatchers.IO) {
                    input.use { it.readBytes() }
                }
                Json.decodeFromString(SessionManager.serializer(), byteArray.decodeToString())
            } catch (e: Exception) {
                e.printStackTrace()
                SessionManager()
            }
        }

        override suspend fun writeTo(
            t: SessionManager,
            output: OutputStream
        ) {
            val json = Json.encodeToString(SessionManager.serializer(), t)
            output.write(json.toByteArray())
        }

    }
}