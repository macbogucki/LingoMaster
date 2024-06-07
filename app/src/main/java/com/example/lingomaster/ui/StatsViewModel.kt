package com.example.lingomaster.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.lingomaster.data.StatsData
import com.example.lingomaster.data.StatsRepository
import kotlinx.coroutines.launch

class StatsViewModel(private val repository: StatsRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allStats: LiveData<List<StatsData>> = repository.allStats.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(stats: StatsData) = viewModelScope.launch {
        repository.insertStats(stats)
    }

    fun update(stats: StatsData) = viewModelScope.launch {
        repository.updateStats(stats)
    }

    fun updateWins() = viewModelScope.launch {
        val currentStats = allStats.value?.firstOrNull()
        currentStats?.let {
            val updatedStats = it.copy(wins = (it.wins + 1), games = (it.games + 1))
            update(updatedStats)
        }
    }

    fun updateLosses() = viewModelScope.launch {
        val currentStats = allStats.value?.firstOrNull()
        currentStats?.let {
            val updatedStats = it.copy(failures = (it.failures + 1), games = (it.games + 1))
            update(updatedStats)
        }
    }

    fun changeLanguage(newLanguage: String) = viewModelScope.launch {
        val currentStats = allStats.value?.firstOrNull()
        currentStats?.let {
            val updatedStats = it.copy(language = newLanguage)
            update(updatedStats)
        }
    }

    fun resetStats() = viewModelScope.launch {
        val currentStats = allStats.value?.firstOrNull()
        currentStats?.let {
            val resetStats = it.copy(games = 0, wins = 0, failures = 0, language = "angielski")
            update(resetStats)
        }
    }
}

class StatsViewModelFactory(private val repository: StatsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StatsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}