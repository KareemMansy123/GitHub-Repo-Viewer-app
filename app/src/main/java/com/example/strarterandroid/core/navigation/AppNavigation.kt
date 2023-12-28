package com.example.strarterandroid.core.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.strarterandroid.pricentation.details_screen.DetailScreen
import com.example.strarterandroid.pricentation.details_screen.DetailsVm
import com.example.strarterandroid.pricentation.issues_screen.IssuesScreen
import com.example.strarterandroid.pricentation.issues_screen.IssuesVm
import com.example.strarterandroid.pricentation.main_screen.MainScreenUI
import com.example.strarterandroid.pricentation.main_screen.MainViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MainNavigation(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = OuterClass.NavCommand.Main.route) {
        composable(OuterClass.NavCommand.Main.route) {
            MainScreenUI(navController, viewModel)
        }
        mainRoute(this, navController)
        detailsRoute(this)
    }
}
fun mainRoute(navGraphBuilder: NavGraphBuilder, navController: NavHostController){
    navGraphBuilder.composable(
        route = OuterClass.NavCommand.Details_ROUTE_PATTERN,
        arguments = listOf(
            navArgument("name") { type = NavType.StringType },
            navArgument("owner") { type = NavType.StringType },
        )
    ) { backStackEntry ->
        val name = backStackEntry.arguments?.getString("name") ?: "N/A"
        val owner = backStackEntry.arguments?.getString("owner") ?: "N/A"
        val detailsViewModel = getViewModel<DetailsVm>()
        DetailScreen(name, owner, detailsViewModel, navController)
    }
}

fun detailsRoute(navGraphBuilder: NavGraphBuilder){
    navGraphBuilder.composable(
            route = OuterClass.NavCommand.Issues_ROUTE_PATTERN,
    arguments = listOf(
        navArgument("name") { type = NavType.StringType },
        navArgument("owner") { type = NavType.StringType },
    )
    ) { backStackEntry ->
        val name = backStackEntry.arguments?.getString("name") ?: "N/A"
        val owner = backStackEntry.arguments?.getString("owner") ?: "N/A"
        val issuesVm = getViewModel<IssuesVm>()
        IssuesScreen(name, owner, issuesVm)
    }
}
