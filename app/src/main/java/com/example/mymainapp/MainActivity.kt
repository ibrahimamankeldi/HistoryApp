package com.example.mymainapp
import android.os.Bundle
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
import androidx.compose.ui.Modifier
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
//            val streakViewModel = StreakViewModel(applicationContext)
            MyMainAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    StreakViewModel(viewModel = streakViewModel)
                    App()
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

//git status
//git push origin master