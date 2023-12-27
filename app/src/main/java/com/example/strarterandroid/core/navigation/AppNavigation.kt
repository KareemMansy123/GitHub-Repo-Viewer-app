package com.example.strarterandroid.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.strarterandroid.pricentation.details_screen.DetailScreen
import com.example.strarterandroid.pricentation.details_screen.DetailsVm
import com.example.strarterandroid.pricentation.main_screen.MainScreenUI
import com.example.strarterandroid.pricentation.main_screen.MainViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavigation(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = "main") {
        composable(OuterClass.NavCommand.Main.route) {
            MainScreenUI(navController, viewModel)
        }
        composable(
            route = OuterClass.NavCommand.Details_ROUTE_PATTERN,
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("owner") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val detailsViewModel = getViewModel<DetailsVm>()

            // Retrieve arguments
            val name = backStackEntry.arguments?.getString("name") ?: "N/A"
            val owner = backStackEntry.arguments?.getString("owner") ?: "N/A"
            // Call DetailScreen with the ViewModel and arguments
            DetailScreen(name, owner, detailsViewModel)
        }
    }
}
