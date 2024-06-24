package com.example.mkodo_compose.app.src.main.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mkodo_compose.app.src.main.data.model.LotteryDraw
import com.example.mkodo_compose.app.src.main.viewmodel.LotteryDrawsViewModel
import com.example.mkodo_compose.ui.theme.Mkodo_composeTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: LotteryDrawsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Mkodo_composeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = "drawsList", modifier = Modifier.padding(innerPadding)) {
                        composable("drawsList") {
                            val lotteryDraws = viewModel.lotteryDraws.collectAsState().value
                            DrawsList(draws = lotteryDraws, navController = navController)
                        }
                        composable(
                            "drawDetails/{draw}",
                            arguments = listOf(navArgument("draw") { type = NavType.StringType })
                        ) { backStackEntry ->

                            backStackEntry.arguments?.getString("draw")?.let { json ->
                                val draw = Gson().fromJson(json, LotteryDraw::class.java)
                                DrawDetailsScreen(draw = draw)
                            }
                        }
                    }
                }
            }
        }
    }
}