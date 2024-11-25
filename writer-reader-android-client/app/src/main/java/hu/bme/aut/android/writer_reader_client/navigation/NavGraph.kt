@file:OptIn(ExperimentalPagingApi::class)

package hu.bme.aut.android.writer_reader_client.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import hu.bme.aut.android.writer_reader_client.feature.home.HomeScreen
import hu.bme.aut.android.writer_reader_client.feature.login.LoginScreen
import hu.bme.aut.android.writer_reader_client.feature.messenger.user_list.UserListScreen
import hu.bme.aut.android.writer_reader_client.feature.read_work.ReadWork
import hu.bme.aut.android.writer_reader_client.feature.register.RegisterScreen
import hu.bme.aut.android.writer_reader_client.feature.work_details.WorkDetailsScreen


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun NavGraph(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Login.route
){
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ){
        composable(Screen.Home.route){
            HomeScreen(navHostController = navHostController)

        }
        composable(Screen.Login.route){
            LoginScreen(
                onSuccessfulLogin = {
                    navHostController.navigate(Screen.Home.route)
                 //   navHostController.navigate(Screen.ReadWork.passWorkId("workId"))
                },
                onRegisterButtonClicked = {
                    navHostController.navigate(Screen.Register.route)
                }
            )

        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onSuccessfulRegister = {
                    navHostController.navigate(Screen.Login.route)
                }
            )
        }

        composable(
            route = Screen.WorkDetails.route,
            arguments = listOf(
                navArgument("workId") {
                    type = NavType.StringType
                }
            )
        ){
            WorkDetailsScreen(
                onNavigateToReadWork = {
                    navHostController.navigate(Screen.ReadWork.passWorkId(it))
                },
                onNavigateBack = {
                    navHostController.popBackStack()
                },
                onNavigateToLogin = {
                    navHostController.navigate(Screen.Login.route)
                }
            )
        }

        composable(
            route = Screen.ReadWork.route,
            arguments = listOf(
                navArgument("workId") {
                    type = NavType.StringType
                }
            )
        ){
            ReadWork()
        }

        composable(
            route = Screen.UserList.route,
        ){
            UserListScreen(navHostController = navHostController)
        }

        composable(
            route = Screen.Conversation.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                }
            )
        ){
           // ConversationScreen()
        }

    }
}