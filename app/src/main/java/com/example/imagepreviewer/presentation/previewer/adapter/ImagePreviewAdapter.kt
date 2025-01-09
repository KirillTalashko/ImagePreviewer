package com.example.imagepreviewer.presentation.previewer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.imagepreviewer.databinding.ItemImagePreviewBinding
import com.example.imagepreviewer.presentation.previewer.viewHolder.ImagePreviewViewHolder
import com.example.imagepreviewer.presentation.utils.OnClickGetImage

class ImagePreviewAdapter(private val onClickGetImage: OnClickGetImage) :
    ListAdapter<String, ImagePreviewViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePreviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemImagePreviewBinding.inflate(layoutInflater, parent, false)
        return ImagePreviewViewHolder(view, onClickGetImage)
    }

    override fun onBindViewHolder(holder: ImagePreviewViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}