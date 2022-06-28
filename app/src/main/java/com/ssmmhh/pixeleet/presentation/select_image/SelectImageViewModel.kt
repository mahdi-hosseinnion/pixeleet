package com.ssmmhh.pixeleet.presentation.select_image

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectImageViewModel
@Inject
constructor(

) : ViewModel() {

    var imageUri by mutableStateOf<Uri?>(null)

    fun setImageUriTo(uri: Uri?) {
        imageUri = uri
    }

}