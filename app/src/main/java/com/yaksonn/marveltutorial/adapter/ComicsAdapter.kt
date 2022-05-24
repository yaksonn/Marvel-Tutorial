package com.yaksonn.marveltutorial.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.yaksonn.marveltutorial.common.ext.load
import com.yaksonn.marveltutorial.core.domain.model.comics.Comic
import com.yaksonn.marveltutorial.databinding.CharacterListItemBinding
import javax.inject.Inject

class ComicsAdapter @Inject constructor(
    private val circularProgressDrawable: CircularProgressDrawable
) : ListAdapter<Comic, ComicsAdapter.ComicViewHolder>(COMIC_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder =
        ComicViewHolder(
            CharacterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    inner class ComicViewHolder(private val binding: CharacterListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comic: Comic) {
            binding.apply {
                characterImage.load(
                    url = comic.image,
                    circularProgressDrawable = circularProgressDrawable
                )
                characterNameTextView.text = comic.title
            }
        }
    }




    companion object {
        val COMIC_COMPARATOR = object : DiffUtil.ItemCallback<Comic>() {
            override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
                return oldItem == newItem
            }
        }
    }
}