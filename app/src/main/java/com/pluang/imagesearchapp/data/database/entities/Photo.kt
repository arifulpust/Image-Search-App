package com.pluang.imagesearchapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@Entity
@JsonClass(generateAdapter = true)
data class Photo(
    @PrimaryKey
    @Json(name = "id")
    var id: Long = 0 ,
    @Json(name = "owner")
    val owner: String,
    @Json(name = "secret")
    val secret: String,
    @Json(name = "server")
    val server: String,
    @Json(name = "farm")
    var farm: String? = "",
    @Json(name = "title")
    var title: String? = "",
    @Json(name = "ispublic")
    var ispublic: String? = "",
    @Json(name = "isfriend")
    var isfriend: String? = "",
    @Json(name = "isfamily")
    var isfamily: String? = "",

    )
{
    fun imageUrl() : String {
        /**
        https://live.staticflickr.com/{server-id}/{id}_{secret}_{size-suffix}.jpg
        https://live.staticflickr.com/{server}/{id}_{secret}_{size-suffix}.jpg
         */

        return "https://live.staticflickr.com/{server}/{id}_{secret}_w.jpg"

    }
}
