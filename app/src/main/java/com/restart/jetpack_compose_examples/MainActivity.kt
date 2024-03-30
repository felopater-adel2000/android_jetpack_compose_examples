package com.restart.jetpack_compose_examples

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme
import java.util.Locale

val LocalMainActivity = staticCompositionLocalOf<MainActivity> { noLocalProvidedFor("LocalActivity") }

private fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}
class MainActivity : ComponentActivity() {

    private val localLayoutDirection = mutableStateOf(LayoutDirection.Ltr)
    var currentLang = "en"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            CompositionLocalProvider(
                LocalLayoutDirection provides localLayoutDirection.value,
                LocalMainActivity provides this
            ) {
                Jetpack_compose_examplesTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        TimerScreen()
                    }
                }
            }
        }
    }

    fun displayToast()
    {
        Toast.makeText(this, "Hello, World", Toast.LENGTH_LONG).show()
    }

    fun setCurrentLanguage(lang: String){
        currentLang = lang
        val config = resources.configuration
        val locale = Locale(lang, if (lang == "en") "US" else "EG")
        Locale.setDefault(locale)
        config.setLocale(locale)
        createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)
        localLayoutDirection.value = if(lang == "en") LayoutDirection.Ltr else LayoutDirection.Rtl
    }
}

private const val TAG = "MainActivity"





@Preview(showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=480")
@Composable
fun TimerScreen() {
    val name = "Felopater Adel"

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        val activity = LocalMainActivity.current
        Text(
            text = stringResource(id = R.string.my_name, name),
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = { if(activity.currentLang == "en")activity.setCurrentLanguage("ar") else activity.setCurrentLanguage("en") }) {
            Text(
                text = stringResource(id = R.string.chnage_language)
            )
        }
    }
}
