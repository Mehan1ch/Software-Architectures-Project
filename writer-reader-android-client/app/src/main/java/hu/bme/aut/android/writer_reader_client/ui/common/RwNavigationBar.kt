package hu.bme.aut.android.writer_reader_client.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.android.writer_reader_client.navigation.Screen

data class NavigationItem(
    val screen: Screen,
    val label: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)


@Composable
fun RwNavigationBar(
    navHostController: NavHostController = rememberNavController()
) {
    val navigationItems = listOf(
        NavigationItem(Screen.Home, "Kezdőlap", Icons.AutoMirrored.Filled.LibraryBooks, Icons.Filled.Home),
        NavigationItem(Screen.Login, "Profil", Icons.Outlined.Person, Icons.Filled.Person),
        NavigationItem(Screen.Register, "Gyűjtemények", Icons.AutoMirrored.Outlined.Message, Icons.AutoMirrored.Filled.LibraryBooks
        ),
        //NavigationItem(Screen.Messenger, "Üzenetek", Icons.Outlined.Notifications, Icons.Filled.Notifications),
    )

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        navigationItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (item.screen.route == navHostController.currentBackStackEntry?.destination?.route) item.selectedIcon else item.unselectedIcon,
                        contentDescription = null
                    )
                },
                label = { Text(item.label) },
                selected = false,
                onClick = { navHostController.navigate(item.screen.route) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationBarPreview() {
    MaterialTheme {
        Surface {
            RwNavigationBar()
        }
    }
}