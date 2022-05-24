package com.yaksonn.marveltutorial.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.yaksonn.marveltutorial.R
import com.yaksonn.marveltutorial.databinding.LoadStateFooterViewItemBinding


typealias Retry = () -> Unit

class CharactersLoadStateViewHolder(
    private val binding: LoadStateFooterViewItemBinding,
    retry: Retry
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.reloadButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorLoadingTextView.text = loadState.error.localizedMessage
        }
        binding.loadingProgressBar.isVisible = loadState is LoadState.Loading
        binding.reloadButton.isVisible = loadState is LoadState.Error
        binding.errorLoadingTextView.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): CharactersLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.load_state_footer_view_item, parent, false)
            val binding = LoadStateFooterViewItemBinding.bind(view)
            return CharactersLoadStateViewHolder(binding, retry)
        }
    }
}