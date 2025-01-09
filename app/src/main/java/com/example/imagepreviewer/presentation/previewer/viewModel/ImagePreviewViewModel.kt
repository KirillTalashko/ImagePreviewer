package com.example.imagepreviewer.presentation.previewer.viewModel

import android.webkit.URLUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagepreviewer.domain.repository.ImageRepository
import com.example.imagepreviewer.domain.state.ImagePreviewFragmentState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImagePreviewViewModel(private val repository: ImageRepository) : ViewModel() {

    private val _stateImagePreview = MutableLiveData<ImagePreviewFragmentState>()
    val stateImagePreview: LiveData<ImagePreviewFragmentState>
        get() = _stateImagePreview

    init {
        downloadImageFile()
    }

    private fun downloadImageFile() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _stateImagePreview.postValue(ImagePreviewFragmentState.LoadingImagePreview)
                val response = repository.downloadImageFile()
                if (response.isSuccessful) {
                    response.body()?.let { body ->

                        val imageList = body.string().lines().filter { URLUtil.isValidUrl(it) }
                        _stateImagePreview.postValue(
                            ImagePreviewFragmentState.SuccessImagePreview(imageList)
                        )
                    }
                        ?: _stateImagePreview.postValue(ImagePreviewFragmentState.Error("Failed to download image links"))
                }
            } catch (e: Exception) {
                _stateImagePreview.postValue(e.localizedMessage?.let { massage ->
                    ImagePreviewFragmentState.Error(massage)
                })
            }
        }
    }
}