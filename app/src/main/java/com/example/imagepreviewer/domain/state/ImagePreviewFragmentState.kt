package com.example.imagepreviewer.domain.state

sealed class ImagePreviewFragmentState {
    data class Error(val error: String) : ImagePreviewFragmentState()
    data class SuccessImagePreview(val imageList: List<String>) : ImagePreviewFragmentState()
    data object LoadingImagePreview : ImagePreviewFragmentState()
}