package com.yaksonn.marveltutorial.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.yaksonn.marveltutorial.common.ItemClickListener
import com.yaksonn.marveltutorial.common.ext.load
import com.yaksonn.marveltutorial.core.domain.model.characters.Character
import com.yaksonn.marveltutorial.databinding.CharacterListItemBinding
import javax.inject.Inject

class CharactersAdapter @Inject constructor(
    private val circularProgressDrawable: CircularProgressDrawable
) : PagingDataAdapter<Character, CharactersAdapter.CharactersViewHolder>(CharacterComparator) {

    private lateinit var listener: ItemClickListener<Character>

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder =
        CharactersViewHolder(
            CharacterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    inner class CharactersViewHolder(private val binding: CharacterListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(characterItem: Character) {
            binding.apply {
                root.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = getItem(position)
                        item?.let {
                            listener.onItemClick(it)
                        }
                    }
                }
                characterImage.load(
                    characterItem.imageUrl,
                    circularProgressDrawable = circularProgressDrawable
                )
                characterNameTextView.text = characterItem.name
            }
        }
    }

    fun setListener(listener: ItemClickListener<Character>) {
        this.listener = listener
    }

    object CharacterComparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}

