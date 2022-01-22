package com.pluang.imagesearchapp.data.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Entity
@JsonClass(generateAdapter = true)
@Parcelize
data class Photo(
    @Json(name = "id")
    @ColumnInfo(name = "id")
    var id: Long = 0 ,
    @PrimaryKey(autoGenerate = true)
    @Json(name = "save_id")
    @ColumnInfo(name = "save_id")
    var saveId: Long = 0,
    @Json(name = "owner")
    @ColumnInfo(name = "owner")
    val owner: String,
    @Json(name = "secret")
    @ColumnInfo(name = "secret")
    val secret: String,
    @Json(name = "server")
    @ColumnInfo(name = "server")
    val server: String,
    @Json(name = "farm")
    @ColumnInfo(name = "farm")
    var farm: String? = "",
    @Json(name = "title")
    @ColumnInfo(name = "title")
    var title: String? = "",
    @Json(name = "ispublic")
    @ColumnInfo(name = "ispublic")
    var ispublic: String? = "",
    @Json(name = "isfriend")
    @ColumnInfo(name = "isfriend")
    var isfriend: String? = "",
    @Json(name = "isfamily")
    @ColumnInfo(name = "isfamily")
    var isfamily: String? = "",

    ): Parcelable
{
    fun imageUrl() : String {
        /**
        https://live.staticflickr.com/{server-id}/{id}_{secret}_{size-suffix}.jpg
         **/

        return "https://live.staticflickr.com/"+server+"/"+id+"_"+secret+"_w.jpg"

    }
}
