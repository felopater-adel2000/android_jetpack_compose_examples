package com.restart.jetpack_compose_examples.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListScreen(
    viewState: ListViewState,
    onAction: (ListAction) -> Unit
) {

    ListContent(
        viewState = viewState,
        onAction = onAction
    )

    LaunchedEffect(Unit) { onAction(ListAction.LoadData) }


}


@Composable
private fun ListContent(viewState: ListViewState, onAction: (ListAction) -> Unit) {

    LazyColumn(
        modifier = Modifier
            .testTag("list")
            .fillMaxSize()
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