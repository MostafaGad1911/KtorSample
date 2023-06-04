package mostafagad.projects.ktorsample.app.presentation.myRepos

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import mostafagad.projects.ktorsample.app.presentation.theme.BaseText
import mostafagad.projects.ktorsample.app.presentation.theme.BottomNavItem
import mostafagad.projects.ktorsample.ui.theme.KtorSampleTheme


@AndroidEntryPoint
class MyRepos : ComponentActivity() {

    private val myReposVM: MyReposVM by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KtorSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MyBottomNavBar(
                        items = listOf(
                            BottomNavItem.Home,
                            BottomNavItem.MyStarred,
                            BottomNavItem.Forks ,
                            BottomNavItem.Profile
                        )
                    )
                }
            }
        }

        myReposVM.errorMessage.observe(this) {
            Toast.makeText(this@MyRepos, it, Toast.LENGTH_LONG).show()
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyBottomNavBar(items: List<BottomNavItem>) {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigation(backgroundColor = Color.White, elevation = 5.dp) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination?.route
                    items.forEach { screen ->
                        val isSelected =
                            currentDestination == screen.route/*Replace with your logic*/
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painterResource(id = screen.icon),
                                    contentDescription = screen.title
                                )
                            },
                            label = {
                                BaseText(
                                    text = screen.title,
                                    color = if (isSelected) Black else LightGray
                                )
                            },
                            selectedContentColor = Black,
                            unselectedContentColor = LightGray,
                            alwaysShowLabel = true,
                            selected = currentDestination == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    // Avoid multiple copies of the same destination when
                                    // re selecting the same item
                                    launchSingleTop = true
                                    // Restore state when re selecting a previously selected item
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController,
                startDestination = BottomNavItem.Home.route,
                Modifier.padding(innerPadding)
            ) {
                composable(BottomNavItem.Home.route) { GithubProfile(myReposVM = myReposVM) }
                composable(BottomNavItem.MyStarred.route) { StarredReposSection(myReposVM = myReposVM) }
                composable(BottomNavItem.Forks.route) { ForkedReposSection(myReposVM = myReposVM) }
                composable(BottomNavItem.Profile.route) { FullProfileSection(myReposVM = myReposVM) }
            }
        }

    }
}

