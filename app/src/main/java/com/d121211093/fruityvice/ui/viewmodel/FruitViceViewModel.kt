package com.d121211093.fruityvice.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211093.fruityvice.FruitViceApplication
import com.d121211093.fruityvice.FruitViceDetailsState
import com.d121211093.fruityvice.FruitViceMainState
import com.d121211093.fruityvice.FruitViceUiState
import com.d121211093.fruityvice.data.model.FruitsItem
import com.d121211093.fruityvice.data.repository.FruitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class FruitViceViewModel(private val fruitViceRepository: FruitRepository) : ViewModel() {
    private val _uistate = MutableStateFlow(
        FruitViceUiState(
            mainScreenState = FruitViceMainState.Loading,
            detailScreenState = FruitViceDetailsState.Loading
        )
    )

    val uiState: StateFlow<FruitViceUiState> = _uistate.asStateFlow()

    init {
        getFruit()
    }


    fun getFruit(){
        _uistate.update { currentState -> currentState.copy(mainScreenState = FruitViceMainState.Loading) }
        viewModelScope.launch {
            try {
                val data: List<FruitsItem> = fruitViceRepository.getFruits()
                println("Check")
                _uistate.update { currentState -> currentState.copy(test = "Data Diambil") }
                if(data.isEmpty()){
                    _uistate.update { currentState -> currentState.copy(mainScreenState = FruitViceMainState.Failure) }
                }else{
                    _uistate.update { currentState ->  currentState.copy(mainScreenState = FruitViceMainState.Success(data))}
                }
            } catch (e: IOException){
                println(e)
            }
        }
    }

    fun getFruitInfo(fruitsItem: FruitsItem){
        _uistate.update { currentState -> currentState.copy(detailScreenState = FruitViceDetailsState.Loading) }
        viewModelScope.launch {
            try {
                _uistate.update { currentState -> currentState.copy(test = "Data Diambil") }
                if(fruitsItem==null){
                    _uistate.update { currentState -> currentState.copy(detailScreenState = FruitViceDetailsState.Failure) }
                }else{
                    _uistate.update { currentState ->  currentState.copy(detailScreenState = FruitViceDetailsState.Success(fruitsItem))}
                }
            } catch (e: IOException){
                println(e)
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FruitViceApplication)
                val fruitViceRepository = application.container.fruitRepository
                FruitViceViewModel(fruitViceRepository = fruitViceRepository)
            }
        }
    }
}