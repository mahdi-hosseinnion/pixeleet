package com.ssmmhh.pixeleet.presentation.select_image

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
@Destination(start = true)
fun SelectImageScreen(
    navigator: DestinationsNavigator,
    viewModel: SelectImageViewModel = hiltViewModel()
) {
    Text(
        "Hey it is SelectImageScreen",
        modifier = Modifier.fillMaxSize(),
        fontSize = 24.sp,
        color = MaterialTheme.colors.onBackground
    )
}