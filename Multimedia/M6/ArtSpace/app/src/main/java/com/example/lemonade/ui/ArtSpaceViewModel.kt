package com.example.lemonade.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ArtSpaceViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ArtSpaceUiState())
    val uiState: StateFlow<ArtSpaceUiState> = _uiState.asStateFlow()

    fun nextStep() {
        _uiState.value = _uiState.value.let {
            val nextStep = if (it.currentStep < 4) it.currentStep + 1 else 1
            it.copy(currentStep = nextStep)
        }
    }

    fun previousStep() {
        _uiState.value = _uiState.value.let {
            val previousStep = if (it.currentStep > 1) it.currentStep - 1 else 4
            it.copy(currentStep = previousStep)
        }
    }
}
