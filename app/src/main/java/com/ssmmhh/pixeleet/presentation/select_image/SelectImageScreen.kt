package com.ssmmhh.pixeleet.presentation.select_image

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

private const val TAG = "SelectImageScreen"

@Composable
@Destination(start = true)
fun SelectImageScreen(
    navigator: DestinationsNavigator,
    viewModel: SelectImageViewModel = hiltViewModel()
) {
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        viewModel.setImageUriTo(uri)
    }
    Column() {
        Button(onClick = {
            launcher.launch("image/*")
        }) {
            Text(text = "Pick image")
        }

        viewModel.imageUri.let {
            //TODO("Navigate to next screen")
            Log.d(TAG, "SelectImageScreen: called with $it")
        }

    }

}