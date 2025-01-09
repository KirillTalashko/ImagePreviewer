package com.example.imagepreviewer.presentation.previewer.fragment

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagepreviewer.R
import com.example.imagepreviewer.common.Settings
import com.example.imagepreviewer.databinding.FragmentImagePreviewBinding
import com.example.imagepreviewer.domain.repository.ImageRepositoryImpl
import com.example.imagepreviewer.domain.state.ImagePreviewFragmentState
import com.example.imagepreviewer.presentation.previewer.adapter.ImagePreviewAdapter
import com.example.imagepreviewer.presentation.previewer.viewModel.ImagePreviewViewModel
import com.example.imagepreviewer.presentation.utils.CustomViewModelFactory
import com.example.imagepreviewer.presentation.utils.OnClickGetImage
import kotlin.math.max

class ImagePreviewFragment : Fragment(), OnClickGetImage {

    private var _binding: FragmentImagePreviewBinding? = null
    val binding
        get() = _binding!!

    private var customLayoutManager: GridLayoutManager? = null

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(
            this,
            factory = CustomViewModelFactory(repository = ImageRepositoryImpl())
        )[ImagePreviewViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImagePreviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customLayoutManager = GridLayoutManager(
            requireContext(),
            calculateSpanCount(),
            GridLayoutManager.VERTICAL,
            false
        )
        binding.recyclerViewImagePreview.layoutManager = customLayoutManager
        val adapterImagePreview = ImagePreviewAdapter(this)
        binding.recyclerViewImagePreview.adapter = adapterImagePreview
        observerViewModel(adapterImagePreview)
        changeTheme()
    }

    private fun observerViewModel(adapter: ImagePreviewAdapter) {
        viewModel.stateImagePreview.observe(viewLifecycleOwner) { stateImage ->
            when (stateImage) {
                is ImagePreviewFragmentState.Error -> {
                    binding.apply {
                        textViewError.visibility = View.VISIBLE
                        imageViewLoading.visibility = View.GONE
                        recyclerViewImagePreview.visibility = View.GONE
                    }
                }

                ImagePreviewFragmentState.LoadingImagePreview -> {
                    binding.apply {
                        imageViewLoading.visibility = View.VISIBLE
                        recyclerViewImagePreview.visibility = View.GONE
                        textViewError.visibility = View.GONE
                        imageViewLoading.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.ic_loading
                            )
                        )
                    }
                }

                is ImagePreviewFragmentState.SuccessImagePreview -> {
                    binding.apply {
                        imageViewLoading.visibility = View.GONE
                        textViewError.visibility = View.GONE
                        recyclerViewImagePreview.visibility = View.VISIBLE
                    }
                    adapter.submitList(stateImage.imageList)
                }
            }

        }
    }

    private fun calculateSpanCount(): Int {
        val screenWidthPx = Resources.getSystem().displayMetrics.widthPixels
        val density = Resources.getSystem().displayMetrics.density
        val cellSizePx = (100 * density).toInt()
        return max(1, screenWidthPx / cellSizePx)
    }

    override fun getImage(url: String) {
        findNavController().navigate(
            ImagePreviewFragmentDirections.actionPreviewFragmentToFullImageFragment(
                image = url
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun changeTheme() {
        var switch = Settings.loadTheme(requireContext())
        binding.topicChanges.setOnClickListener {
            switch = !switch
            Settings.saveTheme(requireContext(), switch)
            requireActivity().recreate()
        }
    }

}