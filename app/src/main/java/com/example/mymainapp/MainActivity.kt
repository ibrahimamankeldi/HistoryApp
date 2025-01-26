package com.example.mymainapp
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mymainapp.data.NavRoutes
import com.example.mymainapp.logic.StreakViewModel
import com.example.mymainapp.screens.AnimatedWelcomeScreen
import com.example.mymainapp.screens.HomeScreen
import com.example.mymainapp.screens.LessonsScreen
import com.example.mymainapp.ui.theme.MyMainAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val streakViewModel = StreakViewModel(applicationContext)
            MyMainAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StreakViewModel(viewModel = streakViewModel)
                }
            }
        }
    }
}


@Composable
fun App(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome"){
        composable(NavRoutes.WelcomeScreen.route){ AnimatedWelcomeScreen(navController) }
        composable(NavRoutes.HomeScreen.route){ HomeScreen(navController) }
        composable(NavRoutes.LessonsScreen.route){ LessonsScreen(navController) }
    }
}

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
//git status
//git commit -am "first change"
///
@Composable
fun StreakViewModel(viewModel: StreakViewModel) {
    val fileContent by viewModel.fileContent.collectAsState()

    Column {
        fileContent.forEach { line ->
            Text(text = line)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.readFile("LastStreak")
        viewModel.updateStreak("LastStreak")
    }
}