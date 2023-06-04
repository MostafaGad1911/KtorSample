package mostafagad.projects.ktorsample.app.presentation.myRepos

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import mostafagad.projects.ktorsample.R
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
                    val userIn = myReposVM.userIn.observeAsState().value
                    Box(modifier = Modifier.fillMaxSize()) {
                        MyBottomNavBar(
                            items = listOf(
                                BottomNavItem.Home,
                                BottomNavItem.MyStarred,
                                BottomNavItem.Forks,
                                BottomNavItem.Profile
                            )
                        )
                        if (userIn == false){
                            LoginUsingGithub()
                        }
                    }

                }
            }
        }

        myReposVM.errorMessage.observe(this) {
            Toast.makeText(this@MyRepos, it, Toast.LENGTH_LONG).show()
        }

    }

    override fun onResume() {
        super.onResume()
        myReposVM.isUserLogin(this)
    }
    @Composable
    fun LoginUsingGithub() {
        val userLogin = myReposVM.userLogin.observeAsState().value
        if (userLogin == false){
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(start = 15.dp, end = 15.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = Color.White,
                    border = BorderStroke(2.dp, Color.Gray),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(start = 10.dp, end = 10.dp)
                        .clickable(enabled = true, onClick = {
                            myReposVM.loginWithGithub(this@MyRepos)

                        })
                ) {

                    Row(modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            myReposVM.loginWithGithub(this@MyRepos)
                        }
                        .padding(start = 10.dp, end = 10.dp),
                        verticalAlignment = Alignment.CenterVertically) {

                        Image(
                            painter = painterResource(R.drawable.ic_github),
                            modifier = Modifier.size(40.dp),
                            contentScale = ContentScale.Fit, //https://stackoverflow.com/a/67153545
                            contentDescription = stringResource(R.string.github),
                        )

                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .fillMaxWidth(),
                            text = stringResource(R.string.login_github),
                            textAlign = TextAlign.Center,
                            color = Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                        )
                    }


                }

            }
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

