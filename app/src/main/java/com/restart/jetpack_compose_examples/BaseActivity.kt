package com.restart.jetpack_compose_examples

import android.os.Bundle
import kotlinx.coroutines.flow.flow
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityRetainedScope
import org.koin.core.scope.Scope

abstract class BaseActivity : androidx.activity.ComponentActivity() , AndroidScopeComponent
{
    override val scope: Scope by activityRetainedScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        flow<Int> {  }



    }
}