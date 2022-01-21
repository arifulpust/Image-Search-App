package com.pluang.imagesearchapp.data.models

import com.pluang.imagesearchapp.data.database.entities.Photo
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class PhotosResponse(
    @Json(name = "stat")
    val stat: String,
    @Json(name = "photos")
    val photos: PhotosData,

    )

@JsonClass(generateAdapter = true)
data class PhotosData(
    @Json(name = "photo")
    val photo: MutableList<Photo>,
    @Json(name = "page")
    val page: Int,
    @Json(name = "pages")
    val pages: Int,
    @Json(name = "perpage")
    val perpage: Int,
    @Json(name = "total")
    val total: Int,
)



