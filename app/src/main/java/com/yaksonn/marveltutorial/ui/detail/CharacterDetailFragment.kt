package com.yaksonn.marveltutorial.ui.detail

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.yaksonn.marveltutorial.adapter.ComicsAdapter
import com.yaksonn.marveltutorial.common.base.BaseFragment
import com.yaksonn.marveltutorial.common.ext.load
import com.yaksonn.marveltutorial.common.ext.show
import com.yaksonn.marveltutorial.core.domain.model.characters.Character
import com.yaksonn.marveltutorial.core.domain.model.comics.Comic
import com.yaksonn.marveltutorial.core.remote.util.Resource
import com.yaksonn.marveltutorial.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<DetailViewModel, FragmentCharacterDetailBinding>(
        FragmentCharacterDetailBinding::inflate
    ) {
    private val args: CharacterDetailFragmentArgs by navArgs()

    private val mViewModel by viewModels<DetailViewModel>()

    @Inject
    lateinit var circularProgressDrawable: CircularProgressDrawable

    @Inject
    lateinit var mAdapter: ComicsAdapter

    override fun getViewModelKey(): Class<DetailViewModel> =
        DetailViewModel::class.java

    override fun initUI() {
        bindCharacter(args.data.character)
        mViewModel.getComics(args.data.character.id)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        mViewModel.comics.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    showComicsProgress(false)
                }
                is Resource.Loading -> {
                    showComicsProgress(true)
                }
                is Resource.Success -> {
                    val show = !it.data.isNullOrEmpty()
                    showComicList(show)
                    if (show) {
                        bindComics(it.data!!)
                    }
                }
            }
        }

    }

    private fun showComicsProgress(show: Boolean) {
        binding.loadingProgress.show(show)
    }

    private fun showComicList(show: Boolean) {
        binding.charComicsRecyclerView.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    private fun bindComics(data: List<Comic>) {
        showComicsProgress(false)
        binding.charComicsRecyclerView.apply {
            adapter = mAdapter
            setHasFixedSize(true)
        }
        mAdapter.submitList(data)
    }

    private fun bindCharacter(character: Character) {
        binding.apply {
            characterImage.load(character.imageUrl, circularProgressDrawable)
            characterName.text = character.name
            characterDescription.text = character.description
        }
    }
}