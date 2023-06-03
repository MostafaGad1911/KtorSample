package mostafagad.projects.ktorsample.app.presentation.myRepos

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import mostafagad.projects.ktorsample.R
import mostafagad.projects.ktorsample.app.presentation.theme.BaseText
import mostafagad.projects.ktorsample.domain.entities.RepoDTO

@Composable
fun RepoItem(
    repo: RepoDTO,
    onRepoClick: (String) -> Unit,
) {
    Box(modifier = Modifier.clickable {
        onRepoClick(repo.clone_url.toString())
    }, contentAlignment = Alignment.Center) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .border(
                        1.dp,
                        Color.LightGray,
                        shape = RoundedCornerShape(8.dp),
                    )
            ) {
                Image(
                    painter = rememberAsyncImagePainter(R.drawable.ic_github),
                    modifier = Modifier
                        .padding(15.dp)
                        .height(90.dp)
                        .width(90.dp),
                    contentDescription = stringResource(
                        R.string.github
                    ),
                    contentScale = ContentScale.Inside
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    BaseText(
                        text = repo.name.toString(),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                            .padding(end = 20.dp, top = 4.dp),
                        maxLines = 1
                    )
                }

                Row(
                    modifier = Modifier.padding(4.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    BaseText(
                        text = repo.forks_count.toString(),
                    )

                    Spacer(modifier = Modifier.width(5.dp))
                    Image(
                        painter = painterResource(R.drawable.ic_forked),
                        contentDescription = stringResource(
                            R.string.fork
                        ),
                        contentScale = ContentScale.Inside
                    )


                }
                Row(
                    modifier = Modifier.padding(4.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    BaseText(
                        text = repo.watchers.toString(),
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Image(
                        painter = painterResource(R.drawable.ic_star_yellow),
                        contentDescription = stringResource(
                            R.string.fork
                        ),
                        contentScale = ContentScale.Inside
                    )


                }

            }
        }
    }
}
