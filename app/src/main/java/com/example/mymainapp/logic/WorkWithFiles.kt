package com.example.mymainapp.logic

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun OverwriteFile(fileName: String, newContent: String) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        try {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
                outputStream.write(newContent.toByteArray())
            }
            Log.d("FileUpdate", "Файл успешно перезаписан: $fileName")
        } catch (e: Exception) {
            Log.e("FileUpdate", "Ошибка перезаписи файла: ${e.message}")
        }
    }
}

@Composable
fun ReadFile(fileName: String) : List<String> {
    val context = LocalContext.current
    var lines by remember { mutableStateOf(listOf<String>()) }

    LaunchedEffect(fileName) {
        try {
            lines = context.openFileInput(fileName).bufferedReader().use { it.readLines() }
        } catch (e: Exception) {
            lines = listOf("Ошибка чтения файла: ${e.message}")
        }
    }

    return lines
}