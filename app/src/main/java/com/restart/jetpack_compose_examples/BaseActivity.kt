package com.restart.jetpack_compose_examples

import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityRetainedScope
import org.koin.core.scope.Scope

abstract class BaseActivity : androidx.activity.ComponentActivity() , AndroidScopeComponent
{
    override val scope: Scope by activityRetainedScope()
}