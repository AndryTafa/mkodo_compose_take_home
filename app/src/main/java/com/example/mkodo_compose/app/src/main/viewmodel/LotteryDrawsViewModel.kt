package com.example.mkodo_compose.app.src.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mkodo_compose.app.src.main.data.model.LotteryDraw
import com.example.mkodo_compose.app.src.main.repository.LotteryDrawsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LotteryDrawsViewModel @Inject constructor(
    private val repository: LotteryDrawsRepository
) : ViewModel() {
    private val _lotteryDraws = MutableStateFlow<List<LotteryDraw>>(emptyList())
    val lotteryDraws: StateFlow<List<LotteryDraw>> = _lotteryDraws

    init {
        fetchLotteryDraws()
    }

    private fun fetchLotteryDraws() {
        viewModelScope.launch {
            _lotteryDraws.value = repository.getLotteryDraws()
        }
    }
}

