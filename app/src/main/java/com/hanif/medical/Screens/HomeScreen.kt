package com.hanif.medical.Screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.hanif.medical.R
import com.hanif.medical.models.OnBoardingPage
import com.hanif.medical.ui.theme.SimplePurple
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = modifier) {
                    Text(text = "\uD83D\uDC4B Hello!")
                    Text(
                        text = "Hanif Shaikh",
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.h4
                    )
                }
                Image(
                    Icons.Default.Person, contentDescription = "",
                    Modifier
                        .border(
                            1.dp,
                            Color.Gray, shape = RoundedCornerShape(20)
                        )
                        .padding(10.dp)
                )
            }

            //Search Filed
            val (value, onValueChange) = remember { mutableStateOf("") }

            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(fontSize = 17.sp),
                leadingIcon = { Icon(Icons.Filled.Search, null, tint = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SimplePurple)
                    .clip(RoundedCornerShape(16.dp)),
                placeholder = { Text(text = "") },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.DarkGray
                )
            )

            /*Services*/
            Text(
                text = "Services",
                Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(), textAlign = TextAlign.Start
            )
            Row(
                modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BaseServiceComp(R.drawable.doctor, {})
                BaseServiceComp(R.drawable.medicine, {})
                BaseServiceComp(R.drawable.medical_report, {})
                BaseServiceComp(R.drawable.covid, {})
            }

            //auto Slide banner
            val images = listOf(
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
            )

            Card(
                Modifier.padding(vertical = 20.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                AutoSlidingCarousel(
                    itemsCount = images.size,
                    itemContent = { index ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(images[index])
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.height(200.dp)
                        )
                    }
                )
            }

            /*Top Doctors*/
            Text(
                text = "Top Doctors",
                Modifier
                    .fillMaxWidth()
                    .size(30.dp),
                textAlign = TextAlign.Start,
            )
        }
    }
}

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = SimplePurple /* Color.Yellow */,
    unSelectedColor: Color = Color.Gray /* Color.Gray */,
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 2000L,
    pagerState: PagerState = remember { PagerState() },
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(pagerState.currentPage) {
        delay(autoSlideDuration)
        pagerState.animateScrollToPage((pagerState.currentPage + 1) % itemsCount)
    }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        HorizontalPager(count = itemsCount, state = pagerState) { page ->
            itemContent(page)
        }

        // you can remove the surface in case you don't want
        // the transparant bacground
        Surface(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter),
            shape = CircleShape,
            color = Color.Black.copy(alpha = 0.5f)
        ) {
            DotsIndicator(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                totalDots = itemsCount,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
                dotSize = 8.dp
            )
        }
    }
}


@Composable
fun BaseServiceComp(@DrawableRes image: Int, onClick: () -> Unit) { //
    Image(
        painter = painterResource(id = R.drawable.doctor), contentDescription = "", Modifier
            .border(
                1.dp,
                Color.Gray, shape = RoundedCornerShape(20)
            )
            .padding(20.dp)
    )
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun Item_Doctor() {
    Row(
        Modifier.fillMaxWidth().height(100.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.doctor),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(10.dp)                     // clip to the circle shape
                .border(1.dp, Color.Gray,RoundedCornerShape(20))
                .weight(.3f)   // add a border (optional)
        )

        Column(Modifier.padding(vertical = 10.dp).weight(.6f).fillMaxHeight()) {
            Text(
                text = "Dr. Hanif Shaikh",
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )

            Text(text = "Cardiologist")
        }
        Column(Modifier.weight(.2f)) {
        }
    }
}
