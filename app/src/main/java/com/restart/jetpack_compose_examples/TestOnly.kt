package com.restart.jetpack_compose_examples

import androidx.annotation.RestrictTo
import androidx.annotation.RestrictTo.Scope

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.CLASS
)
@Retention(AnnotationRetention.SOURCE)
@RestrictTo(Scope.TESTS)
annotation class TestOnly