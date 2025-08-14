package com.restart.jetpack_compose_examples.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListScreen(
    token: String,
    viewState: ListViewState,
    onAction: (ListAction) -> Unit
) {
    ListContent(
        token = token,
        viewState = viewState,
        onAction = onAction
    )
}


@Composable
private fun ListContent(token: String, viewState: ListViewState, onAction: (ListAction) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Button(
            modifier = Modifier.testTag("load_data_button"),
            onClick = { onAction(ListAction.LoadData) },
        ) {
            Text(
                text = "Load Data"
            )
        }

        Button(
            modifier = Modifier
                .padding(10.dp)
                .testTag("load_data_button"),
            onClick = { onAction(ListAction.SetToken) },
        ) {
            Text(
                text = "setToken"
            )
        }

        Text(
            text = "Token: $token",
            color = Color.White
        )

        LazyColumn(
            modifier = Modifier
                .testTag("list")
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)
                .padding(8.dp)
        ) {

            itemsIndexed(viewState.products) { index, item ->
                Text(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .clickable {
                            onAction(ListAction.OnProductClick(item))
                        },
                    text = item.name,
                    fontSize = 18.sp
                )
            }

        }
    }
}