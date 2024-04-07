package com.restart.jetpack_compose_examples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.theapache64.rebugger.Rebugger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun PlayWithRecomposition(viewModel: CompositionViewModel) {


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        TextFieldOne(
            modifier = Modifier.fillMaxWidth(),
            viewModel = viewModel,
            state = viewModel.oneTextField
        )

        Spacer(modifier = Modifier.height(17.dp))


        TextFieldTwo(
            modifier = Modifier.fillMaxWidth(),
            state = viewModel.twoTextField
        )
    }

}


@Composable
fun TextFieldOne(
    viewModel: CompositionViewModel,
    state: StateFlow<String>,
    modifier: Modifier = Modifier
) {

    Rebugger(
        composableName = "TextFieldOne",
        trackMap = mapOf()
    )

    val uiState by state.collectAsState()
    TextField(
        modifier = modifier,
        value = uiState,
        onValueChange = viewModel::updateOneTextField
    )
}


@Composable
fun TextFieldTwo(
    state: StateFlow<String>,
    modifier: Modifier = Modifier
) {

    Rebugger(
        composableName = "TextFieldTwo",
        trackMap = mapOf()
    )

    val uiState by state.collectAsState()
    TextField(
        modifier = modifier,
        value = uiState,
        onValueChange = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewTextFieldTwo() {
    TextFieldTwo( state = MutableStateFlow("Hello"))
}

