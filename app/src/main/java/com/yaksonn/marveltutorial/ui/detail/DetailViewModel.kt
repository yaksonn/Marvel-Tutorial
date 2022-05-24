package com.yaksonn.marveltutorial.ui.detail

import androidx.lifecycle.*
import com.yaksonn.marveltutorial.core.data.repository.abstraction.CharactersRepository
import com.yaksonn.marveltutorial.core.domain.model.characters.Character
import com.yaksonn.marveltutorial.core.remote.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val charactersRepo: CharactersRepository,
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _character = MutableLiveData<Character>()

    val comics = Transformations.switchMap(_id) {
        liveData {
            emit(Resource.Loading())
            emit(charactersRepo.fetchComics(it))
        }
    }

    init {
        savedStateHandle.get<Character>("character")?.let {
            setCharacter(it)
            getComics(it.id)
        }
    }

    private fun setCharacter(character: Character) {
        _character.value = character
    }

    fun getComics(id: Int) {
        _id.value = id
    }
}