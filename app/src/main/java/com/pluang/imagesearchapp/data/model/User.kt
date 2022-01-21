package com.pluang.imagesearchapp.data.model

import android.os.Parcelable

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
data class User(


    @Json(name ="id")
    val id: Long = 0,
    @Json(name ="avatar")
    val avatar: String = "",
     @Json(name ="login")
    val login: String = "",
     @Json(name ="node_id")
    val node_id: String = "",
     @Json(name ="avatar_url")
    val avatar_url: String = "",

     @Json(name ="gravatar_id")
    val gravatar_id: String = "",
     @Json(name ="url")
    val url: String = "",
     @Json(name ="html_url")
    val html_url: String = "",
     @Json(name ="followers_url")
    val followers_url: String = "",

     @Json(name ="starred_url")
    val starred_url: String = "",
     @Json(name ="subscriptions_url")
    val subscriptions_url: String = "",
     @Json(name ="organizations_url")
    val organizations_url: String = "",
     @Json(name ="repos_url")
    val repos_url: String = "",

     @Json(name ="events_url")
    val events_url: String = "",
     @Json(name ="received_events_url")
    val received_events_url: String = "",
     @Json(name ="type")
    val type: String = "",
     @Json(name ="site_admin")
    val site_admin: Boolean = false,
     @Json(name ="note")
    var note: String = "",

     @Json(name ="name")
    val name: String = "",
     @Json(name ="company")
    val company: String = "",
     @Json(name ="blog")
    val blog: String = "",
     @Json(name ="location")
    val location: String = "",
     @Json(name ="followers")
    var followers: Int = 0,
     @Json(name ="following")
    var following: Int = 0,

): Parcelable {

}