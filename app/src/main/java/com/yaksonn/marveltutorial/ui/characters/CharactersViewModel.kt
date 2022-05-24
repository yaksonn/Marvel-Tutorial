package com.yaksonn.marveltutorial.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yaksonn.marveltutorial.common.base.BaseViewModel
import com.yaksonn.marveltutorial.core.data.repository.abstraction.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repo: CharactersRepository
) : BaseViewModel() {

    val characters = repo.fetchCharacters().cachedIn(viewModelScope)
}