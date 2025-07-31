package com.restart.jetpack_compose_examples.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DetailsScreen(viewState: DetailsViewState, onAction: (DetailsAction) -> Unit) {

    DetailsContent(
        viewState = viewState,
        onAction = onAction
    )
}

@Composable
private fun DetailsContent(
    viewState: DetailsViewState,
    onAction: (DetailsAction) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Product Id: ${viewState.product?.id}"
        )


        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Product Id: ${viewState.product?.name}"
        )

    }

}