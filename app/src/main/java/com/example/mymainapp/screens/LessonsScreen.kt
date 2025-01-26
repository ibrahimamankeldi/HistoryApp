package com.example.mymainapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mymainapp.components.NavBar
import com.example.mymainapp.components.TopicsDetails

@Composable
fun LessonsScreen(navController: NavController){
    Scaffold(
        bottomBar = {
            NavBar(navController)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)){
            TopicsDetails("Hello World", contents = arrayOf("Hello World"))
        }
    }
}