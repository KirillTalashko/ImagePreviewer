package com.example.imagepreviewer.presentation.fullImage.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.imagepreviewer.databinding.FragmentFullImageBinding
import com.example.imagepreviewer.presentation.utils.extension.loadPhoto

class FullImageFragment : Fragment() {

    private val args: FullImageFragmentArgs by navArgs()

    private var _binding: FragmentFullImageBinding? = null
    val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFullImageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageViewFullImage.loadPhoto(url = args.image)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}