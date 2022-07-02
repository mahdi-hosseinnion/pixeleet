package com.ssmmhh.pixeleet.presentation.show_image

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily.Companion.Monospace
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

private const val TAG = "ShowImageScreen"

@Composable
@Destination
fun ShowImageScreen(
    imageUri: Uri,
    viewModel: ShowImageViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    remember {
        val bitmap = if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, imageUri)
            ImageDecoder.decodeBitmap(source)
        }
        viewModel.setPixelMapTo(bitmap.asImageBitmap().toPixelMap())
        bitmap
    }

    Text(
        text = viewModel.textViewResult,
        color = Color.Black,
        fontSize = 1.5.sp,
        fontFamily = Monospace
    )

}




