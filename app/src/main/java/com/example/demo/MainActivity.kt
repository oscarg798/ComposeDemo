package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

sealed interface X {


}

@ExperimentalPagerApi
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loading = remember { mutableStateOf(true) }
            Column {
                WelcomePager(Modifier.weight(.8f))
                TextoBlanco(
                    texto = "Fijo ", modifier = Modifier
                        .weight(0.1f)
                        .padding(16.dp)
                )

                Culito(isLoading = loading.value, onClick = {
                    loading.value = !loading.value
                }, modifier = Modifier.weight(0.1f))

            }
        }
    }
}

@Composable
fun TextoBlanco(texto: String, modifier: Modifier) {
    Text(
        text = texto, style = TextStyle(color = Color.White),
        modifier = modifier
    )
}

@Composable
fun Culito(isLoading: Boolean, onClick: () -> Unit, modifier: Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier.background(Color.Red).fillMaxSize().padding(16.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(30.dp))
        } else {
            TextoBlanco(
                texto = "Culito", modifier = Modifier
                    .weight(0.2f)
                    .padding(16.dp)
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun WelcomePager(modifier: Modifier) {
    val data = listOf(
        WelcomeItem(R.drawable.ic_launcher_background, "A"),
        WelcomeItem(R.drawable.ic_launcher_background, "B"),
        WelcomeItem(R.drawable.ic_launcher_background, "C"),
        WelcomeItem(R.drawable.ic_launcher_background, "D"),
        WelcomeItem(R.drawable.ic_launcher_background, "E"),
        WelcomeItem(R.drawable.ic_launcher_background, "F")
    )

    val pagerState = rememberPagerState(pageCount = data.size)
    HorizontalPager(state = pagerState, modifier) {
        WelcomeCard(welcomeItem = data[currentPage])
    }
}

data class WelcomeItem(@DrawableRes val image: Int, val text: String)


@Composable
fun WelcomeCard(welcomeItem: WelcomeItem) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = welcomeItem.image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .weight(.8f)
        )

        TextoBlanco(texto = welcomeItem.text, modifier = Modifier.weight(0.2f))
    }
}