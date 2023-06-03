package mostafagad.projects.ktorsample.app.presentation.myRepos

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import mostafagad.projects.ktorsample.R
import mostafagad.projects.ktorsample.app.presentation.theme.BaseText

@Composable
fun ShimmerRepo(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isLoading)
        Row(modifier = modifier) {
            Box(contentAlignment = Alignment.Center) {
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
                            painter = rememberAsyncImagePainter(R.drawable.ic_github_grey),
                            modifier = Modifier
                                .padding(15.dp)
                                .height(90.dp)
                                .width(90.dp)
                                .shimmerEffect(),
                            contentDescription = stringResource(
                                R.string.github
                            ),
                            contentScale = ContentScale.Inside
                        )
                        BaseText(
                            text = "",
                            modifier = Modifier
                                .width(0.dp)
                                .weight(1f)
                                .padding(end = 20.dp, top = 6.dp)
                                .shimmerEffect(),
                            maxLines = 1
                        )
                        Row(
                            modifier = Modifier.padding(4.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            BaseText(
                                modifier = Modifier.shimmerEffect(),
                                text = "",
                            )
                            Spacer(modifier = Modifier.width(5.dp))

                            Image(
                                modifier = Modifier.shimmerEffect(),
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
                                modifier = Modifier.shimmerEffect(),
                                text = "",
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Image(
                                modifier = Modifier.shimmerEffect(),
                                painter = painterResource(R.drawable.ic_star_grey),
                                contentDescription = stringResource(
                                    R.string.fork
                                ),
                                contentScale = ContentScale.Inside
                            )


                        }

                    }
                }
            }

        } else {
        contentAfterLoading()
    }

}


fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000)
        )
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5), Color(0xFF8F8B8B), Color(0xFFB8B5B5)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}