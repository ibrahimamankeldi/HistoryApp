package com.example.mymainapp.logic

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.time.LocalDate

class StreakViewModel(val context: Context) : ViewModel() {

    private val _fileContent = MutableStateFlow(listOf<String>())
    val fileContent = _fileContent.asStateFlow()

    fun readFile(fileName: String) {
        viewModelScope.launch {
            try {
                val file = File(context.filesDir, fileName)
                if (!file.exists()) {
                    file.writeText("${LocalDate.now().minusDays(1)}\n0")
                }
                _fileContent.value = file.readLines()
            } catch (e: Exception) {
                _fileContent.value = listOf("Error reading file: ${e.message}")
            }
        }
    }

    fun updateStreak(fileName: String) {
        viewModelScope.launch {
            val lines = _fileContent.value
            val lastUpdate = lines.getOrElse(0) { "Error" }
            val streak = lines.getOrElse(1) { "Error" }.toIntOrNull()

            if (lastUpdate == "Error" || streak == null) return@launch

            var lastUpdateDate = LocalDate.parse(lastUpdate)
            val today = LocalDate.now()

            val updatedStreak: Int
            when (lastUpdateDate) {
                today.minusDays(1) -> {
                    lastUpdateDate = today
                    updatedStreak = streak + 1
                }
                today -> return@launch
                else -> {
                    lastUpdateDate = today
                    updatedStreak = 1
                }
            }

            val file = File(context.filesDir, fileName)
            file.writeText("${lastUpdateDate}\n$updatedStreak")
            readFile(fileName)
        }
    }
}


