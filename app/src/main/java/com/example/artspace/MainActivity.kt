package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme




class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(){
    var artworks: List<com.example.artspace.ImageInfo> = listOf(
        ImageInfo(R.drawable.baseline_airline_seat_recline_extra_24,"作品1", "畫家1"),
        ImageInfo(R.drawable.baseline_airline_stops_24,"作品2", "畫家2"),
        ImageInfo(R.drawable.ic_launcher_foreground,"作品3", "畫家3")

    )

    var currentArtworkId = remember { mutableStateOf(0) }
    var currentArtwork = remember { mutableStateOf(artworks[0]) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = currentArtwork.value.imageId) ,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .weight(1F)
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color.Black))
        )
        Spacer(modifier = Modifier.height(4.dp))
        ImageInfo(currentArtwork.value.imageTitle, currentArtwork.value.imageArtist)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            Modifier.padding(4.dp)
        ) {
            Button(
                onClick = {
                    currentArtworkId.value = previousImage(artworks, currentArtworkId.value)
                    currentArtwork.value = artworks[currentArtworkId.value]
                },
                modifier = Modifier
                    .weight(1F)
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "上一頁")
            }
            Button(
                onClick = {
                    currentArtworkId.value = nextImage(artworks, currentArtworkId.value)
                    currentArtwork.value = artworks[currentArtworkId.value]
                },
                modifier = Modifier
                    .weight(1F)
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "下一頁")
            }

        }
    }
}

@Composable
fun ImageInfo(imageTitle: String, imageArtist: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(4.dp)
    ) {
        Text(
            text = imageTitle,
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold, //粗體
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
        Text(
            text = imageArtist,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
    }
}
/*
@Composable
fun Buttons(modifier: Modifier = Modifier, currentArtworkId: MutableState<Int>, currentArtwork: MutableState<ImageInfo>){
    var currentArtwork = currentArtwork
    Row(
        modifier.padding(4.dp)
    ) {
        Button(
            onClick = {
                currentArtworkId.value = previousImage(currentArtworkId.value)
            },
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "上一頁")
        }
        Button(
            onClick = {
                currentArtworkId.value = nextImage(currentArtworkId.value)

            },
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "下一頁")
        }

    }
}*/

fun nextImage(artworks: List<ImageInfo>, currentArtworkId: Int): Int {
    var nextArtworkId = 0
    if (artworks.size-1 != currentArtworkId) {
        nextArtworkId = currentArtworkId + 1
    }
    return nextArtworkId
}

fun previousImage(artworks: List<ImageInfo>, currentArtworkId: Int): Int {
    var nextArtworkId = artworks.size-1
    if (currentArtworkId != 0) {
        nextArtworkId = currentArtworkId - 1
    }
    return nextArtworkId
}


@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}