package com.example.imagepreviewer.presentation.fullImage.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.imagepreviewer.databinding.FragmentFullImageBinding
import com.example.imagepreviewer.presentation.utils.extension.loadPhoto

class FullImageFragment : Fragment() {

    private val args: FullImageFragmentArgs by navArgs()

    private var _binding: FragmentFullImageBinding? = null
    val binding
        get() = _binding!!

    private var isVisibleNavBar = true

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
        binding.photoViewFullImage.loadPhoto(url = args.image)
        binding.photoViewFullImage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                toggleNavBar()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun toggleNavBar() {
        val controller = requireActivity().window.insetsController
        controller?.let {
            if (isVisibleNavBar) {
                it.hide(WindowInsets.Type.systemBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            } else {
                it.show(WindowInsets.Type.systemBars())
            }

            isVisibleNavBar = !isVisibleNavBar
        }
    }

}
