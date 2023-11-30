package com.example.artspace

import androidx.annotation.DrawableRes

data class ImageInfo(
    @DrawableRes val imageId: Int,
    val imageTitle: String,
    val imageArtist: String
)
