package com.hanif.medical.Screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.hanif.medical.R
import com.hanif.medical.Screens.commo.CommonProgress
import com.hanif.medical.Screens.doctor.DoctorSharedViewModel
import com.hanif.medical.models.ListOfCategories
import com.hanif.medical.models.SubsCategories
import com.hanif.medical.ui.theme.DMSans
import com.hanif.medical.ui.theme.SimplePurple
import com.hanif.medical.utils.Routes
import com.hanif.medical.utils.Routes.ALL_DOCTOR_SCREEN
import com.hanif.medical.utils.Routes.DOCTOR_LOGIN
import com.hanif.medical.utils.Routes.REPORT_SCREEN
import com.hanif.medical.utils.Routes.SHOPPING_ORDER_LIST_SCREEN
import com.hanif.medical.utils.Routes.SHOPPING_SCREEN
import com.hanif.medical.utils.graphs.UIEvent
import com.hanif.medical.viewmodel.DoctorListingsState
import com.hanif.medical.viewmodel.HomeViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    sharedViewModel: DoctorSharedViewModel
) {


    //this composable function is called every single time our to-do list screen updates
    // val todos = viewModel.fetchList.collectAsStateWithLifecycle()


    LaunchedEffect(key1 = true) {
        viewModel.getSettingData()
    }

    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state

    Scaffold(modifier = modifier.fillMaxSize()) {
        val paddingValues = it
        Column(
            modifier = modifier
                .fillMaxSize()
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
                        text = "Welcome Back!",
                        fontFamily = DMSans,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.h4
                    )
                }
                Image(
                    Icons.Default.Person, contentDescription = "",
                    Modifier
                        .clickable {
                            onNavigate(
                                UIEvent.Navigate(
                                    DOCTOR_LOGIN
                                )
                            )
                        }
                        .border(
                            1.dp,
                            Color.Gray, shape = RoundedCornerShape(20)
                        )
                        .padding(10.dp)
                )
            }
            AutoSlideWindo()

            /*Services*/
            Text(
                text = "Our Services",
                Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(), textAlign = TextAlign.Start,

                fontFamily = DMSans, fontWeight = FontWeight.Bold
            )
            Row(
                modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BaseServiceComp(R.drawable.doctor) {
                    onNavigate(
                        UIEvent.Navigate(
                            ALL_DOCTOR_SCREEN
                        )
                    )
                }
                BaseServiceComp(R.drawable.medicine)
                {
                    onNavigate(
                        UIEvent.Navigate(
                            SHOPPING_SCREEN
                        )
                    )
                }
                BaseServiceComp(R.drawable.medical_report) {
                    onNavigate(
                        UIEvent.Navigate(
                            REPORT_SCREEN
                        )
                    )
                }
                BaseServiceComp(R.drawable.baseline_shopping_cart_24) {
                    onNavigate(
                        UIEvent.Navigate(
                            SHOPPING_ORDER_LIST_SCREEN
                        )
                    )
                }
            }

            Spacer(modifier = modifier.height(10.dp))

            /*Top Doctors*/
            Text(
                text = "Top Doctors",
                Modifier
                    .fillMaxWidth()
                    .size(30.dp),
                textAlign = TextAlign.Start,

                fontFamily = DMSans, fontWeight = FontWeight.Bold
            )
            Spacer(modifier = modifier.height(10.dp))

            DoctorListView(state, sharedViewModel, onNavigate)
        }

        /*  if (state.isLoading){
              CommonProgress()
          }*/
    }
}

@Composable
private fun AutoSlideWindo() {
    //auto Slide banner
    val images = listOf(
        R.drawable.banner1,
        R.drawable.banner2,
        R.drawable.banner3,
        R.drawable.banner4,
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
                    contentScale = ContentScale.Inside,
                    modifier = Modifier.height(200.dp)
                )
                Image(
                    painterResource(id = images[index]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(200.dp)
                )
            }
        )
    }
}

@Composable
fun DoctorListView(
    state: DoctorListingsState,
    sharedViewModel: DoctorSharedViewModel,
    onNavigate: (UIEvent.Navigate) -> Unit
) {
    LazyColumn() {
        /*items(state.companies.size) { i ->
            val company = state.companies[i]
            DoctorItem(company) {
                sharedViewModel.addDoctorModel(company)
                onNavigate(UIEvent.Navigate(Routes.DETAIL_DOCTOR_SCREEN))
            }
        }*/

        items(state.companies) {
            DoctorItem(it) {
                sharedViewModel.addDoctorModel(it)
                onNavigate(UIEvent.Navigate(Routes.DETAIL_DOCTOR_SCREEN))
            }
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 5000L,
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
) {
    val pagerState = remember { androidx.compose.foundation.pager.PagerState(0) }

    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    LaunchedEffect(pagerState.currentPage) {
        delay(autoSlideDuration)
        pagerState.animateScrollToPage((pagerState.currentPage + 1) % itemsCount)
    }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        androidx.compose.foundation.pager.HorizontalPager(
            pageCount = itemsCount,
            state = pagerState
        ) { page ->
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
        painter = painterResource(id = image), contentDescription = "", Modifier
            .border(
                1.dp,
                Color.Gray, shape = RoundedCornerShape(20)
            )
            .padding(20.dp)
            .clickable { onClick() }
    )
}


