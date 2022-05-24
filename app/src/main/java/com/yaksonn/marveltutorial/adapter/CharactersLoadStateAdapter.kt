package com.yaksonn.marveltutorial.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.yaksonn.marveltutorial.adapter.viewholder.CharactersLoadStateViewHolder
import com.yaksonn.marveltutorial.adapter.viewholder.Retry


class CharactersLoadStateAdapter(private val retry: Retry) :
    LoadStateAdapter<CharactersLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: CharactersLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CharactersLoadStateViewHolder {
        return CharactersLoadStateViewHolder.create(parent, retry)
    }
}