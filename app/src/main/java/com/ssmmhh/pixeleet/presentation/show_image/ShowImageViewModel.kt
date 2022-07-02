package com.ssmmhh.pixeleet.presentation.show_image

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PixelMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder
import javax.inject.Inject

//Important source
@HiltViewModel
class ShowImageViewModel
@Inject
constructor(

) : ViewModel() {
    private val TAG = "ShowImageViewModel"

    private var pixelMap: MutableStateFlow<PixelMap?> = MutableStateFlow(null)

    var textViewResult by mutableStateOf("")

    fun setPixelMapTo(value: PixelMap) {
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
            val timeInSeconds = System.currentTimeMillis().div(1000.toDouble())
            val result = StringBuilder(pixelMap.height + (pixelMap.height * pixelMap.width))
            for (i in 0 until pixelMap.height) {
                for (j in 0 until pixelMap.width) {
                    val color = pixelMap[j, i]
                    val grayScaleColor = color.toGrayScale()
                    val charPosition = grayScaleColor.times(ASCII_CHARACTERS.size.minus(1)).toInt()
                    result.append(ASCII_CHARACTERS[charPosition])
                }
                Log.d(
                    TAG,
                    "loadTheImage: it took ${
                        ((System.currentTimeMillis()
                            .div(1000.toDouble())) - timeInSeconds).format(2)
                    } seconds to load the $i line!"
                )
                result.append('\n')
                withContext(
                    Dispatchers.Main
                ) {
                    textViewResult = result.toString()
                }
            }


        }
    }

    private fun Double.format(digits: Int): String = "%5.${digits}f".format(this)

    /**
     * Convert color to gray scale.
     *  source: https://stackoverflow.com/q/596216/10362460
     */
    private fun Color.toGrayScale(): Float {
        return ((0.3f * this.red) + (0.59f * this.green) + (0.11f * this.blue))
    }

    companion object {
        private val ASCII_CHARACTERS =
            ( "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ").toCharArray()
    }


}