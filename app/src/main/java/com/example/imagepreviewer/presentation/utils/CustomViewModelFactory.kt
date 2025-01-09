package com.example.imagepreviewer.presentation.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imagepreviewer.domain.repository.ImageRepository
import com.example.imagepreviewer.presentation.previewer.viewModel.ImagePreviewViewModel

class CustomViewModelFactory(private val repository: ImageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImagePreviewViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImagePreviewViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}