package com.example.mymainapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mymainapp.components.NavBar
import com.example.mymainapp.ui.theme.MyMainAppTheme

@Composable
fun HomeScreen(navController: NavController){
    MyMainAppTheme {
        Scaffold(
            bottomBar = {
                NavBar(navController)
            }
        ) { innerPadding ->
            ThemeSwitcherApp(innerPadding)
        }
    }
}


@Composable
fun ThemeSwitcherApp(innerPadding: PaddingValues) {
    var isDarkTheme by remember { mutableStateOf(false) }

    MyMainAppTheme(darkTheme = isDarkTheme) {
        Surface(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isDarkTheme) "Темная тема" else "Светлая тема",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = { isDarkTheme = it }
                )
                Text(
                    text = "Переключить тему",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}