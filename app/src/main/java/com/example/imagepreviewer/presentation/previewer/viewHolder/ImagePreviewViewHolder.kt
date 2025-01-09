package com.example.imagepreviewer.presentation.previewer.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.imagepreviewer.databinding.ItemImagePreviewBinding
import com.example.imagepreviewer.presentation.utils.OnClickGetImage
import com.example.imagepreviewer.presentation.utils.extension.loadPhoto

class ImagePreviewViewHolder(
    private val binding: ItemImagePreviewBinding,
    private val onClickGetImage: OnClickGetImage
) : RecyclerView.ViewHolder(binding.root) {

    private var url: String? = null

    init {
        binding.imageViewPreview.setOnClickListener {
            url?.let { it1 -> onClickGetImage.getImage(it1) }
        }
    }

    fun bind(item: String) {
        url = item
        binding.imageViewPreview.loadPhoto(item)
    }
}