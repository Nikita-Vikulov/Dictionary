package com.example.history.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.BaseActivity
import com.example.history.R
import com.example.models.AppState
import com.example.history.databinding.AcHistoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : BaseActivity<AppState>() {

    private val binding by lazy { AcHistoryBinding.inflate(layoutInflater) }
    private val adapter by lazy { HistoryAdapter() }

    override val model by viewModel<HistoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    adapter.submitList(dataModel)
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = View.VISIBLE
                    binding.progressBarRound.visibility = View.GONE
                    binding.progressBarHorizontal.progress = appState.progress!!
                } else {
                    binding.progressBarHorizontal.visibility = View.GONE
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.t.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            model.getData()
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = View.VISIBLE
        binding.loadingFrameLayout.visibility = View.GONE
        binding.errorLinearLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = View.GONE
        binding.loadingFrameLayout.visibility = View.VISIBLE
        binding.errorLinearLayout.visibility = View.GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = View.GONE
        binding.loadingFrameLayout.visibility = View.GONE
        binding.errorLinearLayout.visibility = View.VISIBLE
    }

    companion object {

        fun createIntent(context: Context) =
            Intent(context, HistoryActivity::class.java)
    }
}