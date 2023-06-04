package mostafagad.projects.ktorsample.app.presentation.theme

import mostafagad.projects.ktorsample.R
import mostafagad.projects.ktorsample.data.utils.NavRoutes

sealed class BottomNavItem(var title:String , var icon:Int , var route:String){
    object Home : BottomNavItem("Home", R.drawable.ic_github,NavRoutes.HOME_ROUT)
    object MyStarred: BottomNavItem("My Starred",R.drawable.baseline_star_outline_24, NavRoutes.STARRED_ROUT)
    object Forks: BottomNavItem("My Forked",R.drawable.ic_forked_black, NavRoutes.FORKED_ROUT)
    object Profile: BottomNavItem("Profile",R.drawable.ic_profile, NavRoutes.PROFILE_ROUT)

}
