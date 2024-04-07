package com.restart.jetpack_compose_examples

import android.app.Application
import android.util.Log
import com.theapache64.rebugger.RebuggerConfig
import kotlin.math.log

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RebuggerConfig.init(
            tag = "AppRebugger",
            logger = {tag, message -> Log.d(tag, "$message")}
        )
    }
}