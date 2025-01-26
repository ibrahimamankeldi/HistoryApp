package com.example.mymainapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mymainapp.components.NavBar

@Composable
fun HomeScreen(navController: NavController){
    Scaffold(
            bottomBar = {
            NavBar(navController)
        }
    ) { innerPadding ->
        Text(text = "Hello World", modifier = Modifier.padding(innerPadding))
    }
}


