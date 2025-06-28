package com.restart.jetpack_compose_examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun ProfileScreen() {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            modifier = Modifier.testTag("text_profile_name"),
            text = "Name: Felopater Adel"
        )

        Text(
            text = "Email: fab712000@gmail.com"
        )

        Button(
            onClick = {}
        ) {

            Text(text = "Logout")
        }


    }

}