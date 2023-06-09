package mostafagad.projects.ktorsample.app.presentation.myRepos

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

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
                .clickable {
                    myReposVM.updateWebUrl("https://github.com/${owner.name}")
                }
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .width(0.dp)
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
            Box(modifier = Modifier.weight(2f)
                .height(80.dp)
                .width(0.dp), contentAlignment = Alignment.CenterStart) {
                Text(
                    modifier = Modifier
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                        .wrapContentHeight(align = Alignment.CenterVertically),
                    fontWeight = FontWeight.Medium,
                    text = owner.name,
                    maxLines = 1,
                    color = Color.Black,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}