package mostafagad.projects.ktorsample.app.presentation.myRepos

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import mostafagad.projects.ktorsample.app.presentation.theme.BaseText

@Composable
fun GithubProfile(myReposVM:MyReposVM) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
    ) {

        ProfileSection(myReposVM)
        ReposSection(myReposVM)
    }
}

@Composable
fun ProfileSection(myReposVM:MyReposVM) {
    val owner = myReposVM.listOfRepos.observeAsState().value?.first()?.owner?.toEntity()
    owner?.let {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .border(width = .5.dp, color = Color.LightGray, shape = RoundedCornerShape(5.dp))
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(owner.avatar),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.LightGray, CircleShape)
                )

            }
            Box(modifier = Modifier.weight(2f), contentAlignment = Alignment.CenterStart) {
                BaseText(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                        .wrapContentHeight(align = Alignment.CenterVertically),
                    fontWeight = FontWeight.Medium,
                    text = owner.name,
                    maxLines = 1,
                    color = Color.Black,
                    fontSize = 15.sp,
                )
            }
        }
    }

}

@Composable
fun ReposSection(myReposVM:MyReposVM) {
    val context = LocalContext.current
    val repos = myReposVM.listOfRepos.observeAsState().value?.filter { it.fork == false }
    repos?.let {
        Column {
            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                for (repo in repos) {
                    item {
                        RepoItem(repo = repo, onRepoClick = { url ->
                            val i = Intent(Intent.ACTION_VIEW)
                            i.data = Uri.parse(url)
                            context.startActivity(i)
                        })
                    }
                }
            }
        }
    }
}