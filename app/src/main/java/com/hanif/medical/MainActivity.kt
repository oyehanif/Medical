package com.hanif.medical

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.hanif.medical.ui.theme.MedicalTheme
import com.hanif.medical.utils.graphs.RootNavigationGraph
import com.hanif.medical.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }

        setContent {
            MedicalTheme(false) {
                MobileAds.initialize(this) {}
                val startDestination by splashViewModel.startDestination
                /*Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(bottomBar = { BottomNavigation(navController = navController) }) {
                        Modifier.padding(it)
                        SetupNavGraph(
                            navController = navController,
                            startDestination = startDestination
                        )
                    }
                }*/
                BannerAd(adId = "ca-app-pub-3940256099942544/6300978111")
                RootNavigationGraph(
                    navController = rememberNavController(),if (splashViewModel.currentUser.value != null) 1 else 0,
                    if (startDestination) 1 else 0
                )
            }

        }
    }
}


@Composable
fun BannerAd(adId :String) {
    Column {
        Spacer(modifier = Modifier.height(10.dp))
        AndroidView(factory = {
            Modifier.fillMaxWidth()
            AdView(it).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = adId
                loadAd(AdRequest.Builder().build())
            }
        })
    }
}
