package com.yaksonn.marveltutorial.ui.characters


import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.yaksonn.marveltutorial.adapter.CharactersAdapter
import com.yaksonn.marveltutorial.adapter.CharactersLoadStateAdapter
import com.yaksonn.marveltutorial.common.ItemClickListener
import com.yaksonn.marveltutorial.common.base.BaseFragment
import com.yaksonn.marveltutorial.core.domain.model.characters.Character
import com.yaksonn.marveltutorial.databinding.FragmentCharactersBinding
import com.yaksonn.marveltutorial.ui.detail.CharacterDetailParameters
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment :
    BaseFragment<CharactersViewModel, FragmentCharactersBinding>(FragmentCharactersBinding::inflate),
    ItemClickListener<Character> {


    override fun getViewModelKey(): Class<CharactersViewModel> =
        CharactersViewModel::class.java

    @Inject
    lateinit var mAdapter: CharactersAdapter

    private val mViewModel by viewModels<CharactersViewModel>()

    override fun initUI() {
        initAdapter()
        initSwipeToRefresh()
        subscribeObservers()
    }

    private fun initAdapter() {

        mAdapter.apply {
            setListener(this@CharactersFragment)
            binding.reloadDataButton.setOnClickListener {
                retry()
            }
            val headerFooterAdapter = CharactersLoadStateAdapter(mAdapter::retry)
            val concatAdapter = mAdapter.withLoadStateFooter(
                footer = headerFooterAdapter
            )
            binding.characterListRecyclerView.adapter = concatAdapter
            (binding.characterListRecyclerView.layoutManager as GridLayoutManager).spanSizeLookup =
                object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position == mAdapter.itemCount && headerFooterAdapter.itemCount > 0) {
                            2
                        } else {
                            1
                        }
                    }
                }
            addLoadStateListener { loadState ->
                val isListEmpty = loadState.refresh is LoadState.NotLoading && itemCount == 0
                showList(!isListEmpty)
                showProgress(loadState.source.refresh is LoadState.Loading)
                binding.reloadDataButton.isVisible = loadState.source.refresh is LoadState.Error
            }
        }
    }

    private fun initSwipeToRefresh() {
        binding.swipeRefresh.apply {
            setOnRefreshListener {
                mAdapter.refresh()
            }
            setColorSchemeColors(
                ContextCompat.getColor(
                    requireContext(),
                    com.yaksonn.marveltutorial.common.R.color.primary200
                ),
                ContextCompat.getColor(
                    requireContext(),
                    com.yaksonn.marveltutorial.common.R.color.secondary900
                )
            )
        }
    }

    private fun subscribeObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            mViewModel.characters.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

    private fun showList(show: Boolean) {
        binding.characterListRecyclerView.isVisible = show
    }

    private fun showProgress(isRefreshing: Boolean) {
        binding.swipeRefresh.isRefreshing = isRefreshing
    }

    override fun onItemClick(item: Character) {
        navigate(
            CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(
                CharacterDetailParameters(item)
            )
        )
    }
}