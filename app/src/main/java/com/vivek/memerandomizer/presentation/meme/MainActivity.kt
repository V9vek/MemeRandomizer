package com.vivek.memerandomizer.presentation.meme

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.vivek.memerandomizer.databinding.ActivityMainBinding
import com.vivek.memerandomizer.util.getBitmapFromUrl
import com.vivek.memerandomizer.util.loadImage
import com.vivek.memerandomizer.util.shareMeme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        collectUIState()
        collectUIEvents()

        binding.btnRefresh.setOnClickListener {
            viewModel.onRefreshMeme()
        }

        binding.btnShare.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                val bitmap = getBitmapFromUrl(
                    viewModel.uiState.value.meme.imgUrl,
                    context = binding.root.context
                )
                viewModel.onTriggerEvent(MemeScreenEvent.ShareMeme(bitmap))
            }
        }
    }

    private fun collectUIState() = lifecycleScope.launchWhenStarted {
        viewModel.uiState.collect { state ->
            binding.apply {
                pbLoading.isVisible = state.isLoading
                if (!state.isLoading) {
                    ivMeme.loadImage(state.meme.imgUrl)
                }
            }
        }
    }

    private fun collectUIEvents() = lifecycleScope.launchWhenStarted {
        viewModel.events.collect { event ->
            when (event) {
                is MemeScreenEvent.ShowToast -> {
                    Snackbar.make(
                        binding.root,
                        event.message,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is MemeScreenEvent.ShareMeme -> {
                    shareMeme(bitmap = event.bitmap)
                }
            }
        }
    }
}














