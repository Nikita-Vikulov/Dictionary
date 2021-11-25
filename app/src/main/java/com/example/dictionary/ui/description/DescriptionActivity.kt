package com.example.dictionary.ui.description

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.dictionary.R
import com.example.dictionary.databinding.AcDescriptionBinding
import kotlinx.coroutines.*
import com.example.dictionary.ui.base.isOnline

class DescriptionActivity : AppCompatActivity() {

    private val binding by lazy { AcDescriptionBinding.inflate(layoutInflater) }

    private val word by lazy { intent.extras?.getString(KEY_WORD).orEmpty() }
    private val description by lazy { intent.extras?.getString(KEY_DESCRIPTION).orEmpty() }
    private val imageUrl by lazy { intent.extras?.getString(KEY_IMAGE_URL) }

    private val coroutineScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setActionBarHomeButton()
        binding.root.setOnRefreshListener {
            startLoadingOrShowError()
        }
        binding.root.isRefreshing = true

        setData()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionBarHomeButton() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setData() {
        binding.descriptionHeader.text = word
        binding.descriptionText.text = description
        val imageUrl = imageUrl
        if (imageUrl == null) {
            stopLoading()
        } else {
            useCoilToLoadPhoto(imageUrl)
        }
    }

    private fun startLoadingOrShowError() {
        if (isOnline(this)) {
            setData()
        } else {
            // todo
            stopLoading()
        }
    }

    private fun stopLoading() {
        binding.root.isRefreshing = false
    }

    private fun useCoilToLoadPhoto(imageUrl: String) {
        val request = ImageRequest.Builder(this)
            .data("https:$imageUrl")
            .target(
                onStart = {},
                onSuccess = { result ->
                    stopLoading()
                    binding.descriptionImage.setImageDrawable(result)
                },
                onError = {
                    stopLoading()
                    binding.descriptionImage.setImageResource(R.drawable.ic_launcher_foreground)
                }
            )
            .transformations(
                CircleCropTransformation(),
            )
            .build()

        coroutineScope.launch {
            ImageLoader(this@DescriptionActivity).enqueue(request)
        }
    }

    companion object {

        private const val KEY_WORD = "KEY_WORD"
        private const val KEY_DESCRIPTION = "KEY_DESCRIPTION"
        private const val KEY_IMAGE_URL = "KEY_IMAGE_URL"

        fun getIntent(
            context: Context,
            word: String,
            description: String,
            imageUrl: String?,
        ) = Intent(context, DescriptionActivity::class.java).apply {
            putExtra(KEY_WORD, word)
            putExtra(KEY_DESCRIPTION, description)
            putExtra(KEY_IMAGE_URL, imageUrl)
        }
    }
}