package com.ssmmhh.pixeleet.presentation.show_image

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.PixelMap
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShowImageViewModel
@Inject
constructor(

) : ViewModel() {
    private val TAG = "ShowImageViewModel"

    private var pixelMap: MutableStateFlow<PixelMap?> = MutableStateFlow(null)

    var textViewResult by mutableStateOf(AnnotatedString("Loading..."))

    fun setPixelMapTo(value: PixelMap) {
        Log.d(TAG, "setPixelMapTo: called with x${value.height}y${value.width}")
        if (pixelMap.value != null) return
        pixelMap.value = value
    }

    init {
        viewModelScope.launch {
            pixelMap.collect {
                it?.let { loadTheImage(it) }
            }
        }
    }

    private fun loadTheImage(pixelMap: PixelMap) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = AnnotatedString.Builder()
            for (i in 0 until pixelMap.height) {
                for (j in 0 until pixelMap.width) {
                    val color = pixelMap[j, i]
                    result.pushStyle(
                        SpanStyle(
                            color = color,
                        )
                    )
                    result.append("@")
                    result.pop()
                }
                result.append("\n")
            }
            withContext(
                Dispatchers.Main
            ) {
                textViewResult = result.toAnnotatedString()
            }

        }
    }


}