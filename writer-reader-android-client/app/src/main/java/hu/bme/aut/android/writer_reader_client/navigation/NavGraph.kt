package hu.bme.aut.android.writer_reader_client.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.android.writer_reader_client.feature.home.HomeScreen
import hu.bme.aut.android.writer_reader_client.feature.login.LoginScreen
import hu.bme.aut.android.writer_reader_client.feature.register.RegisterScreen


@OptIn(ExperimentalMaterial3Api::class)
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
            HomeScreen()

        }
        composable(Screen.Login.route){
            LoginScreen(
                onSuccessfulLogin = {
                    navHostController.navigate(Screen.Home.route)
                },
                onRegisterButtonClicked = {
                    navHostController.navigate(Screen.Register.route)
                }
            )

        }
        composable(Screen.Register.route) {
            RegisterScreen()
        }
    }
}