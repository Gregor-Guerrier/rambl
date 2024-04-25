package com.example.rambl

import NavigationIsland
import ProfileScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cornellappdev.android.eateryblue.ui.model.NewsModel
import com.cornellappdev.android.eateryblue.ui.viewmodels.NewsViewModel
import com.example.rambl.screens.HomeScreen
import com.example.rambl.screens.NewsScreen
import com.example.rambl.screens.RamblScreen
import com.example.rambl.ui.theme.NavIsland
import com.example.rambl.ui.theme.RamblTheme
import com.example.rambl.viewmodels.NavbarViewModel

class MainActivity : ComponentActivity(
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        val newsModel = NewsModel()
        val navbarViewModel = NavbarViewModel()
        val newsViewModel = NewsViewModel(newsModel)
        super.onCreate(savedInstanceState)
        setContent {
            RamblTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Box{
                        val navController = rememberNavController()
                        NavHost(navController, startDestination = "home") {
                            composable("home") {
                                navbarViewModel.onReturn()
                                HomeScreen(navbarViewModel)
                            }
                            composable("profile") {
                                navbarViewModel.onProfile(false)
                                ProfileScreen()
                            }
                            composable("news") {
                                navbarViewModel.onProfile(false)
                                NewsScreen(navbarViewModel, newsViewModel)
                            }
                            composable("rambl") {

                                RamblScreen()
                            }
                            // Add more destinations as needed
                        }
                        Column(
                            modifier = Modifier
                                .zIndex(1f)
                                .height(64.dp)
                                .align(Alignment.BottomCenter)
                                .offset(y = (-32).dp)
                                .shadow(
                                    elevation = 16.dp
                                )
                                .background(
                                    color = NavIsland,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .animateContentSize()
                        ) {
                            NavigationIsland(navbarViewModel, navController)
                        }
                    }
                }
            }
        }
    }
}