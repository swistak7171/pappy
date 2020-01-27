package pl.kamilszustak.pappy.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "dog_images")
@JsonClass(generateAdapter = true)
@Parcelize
data class DogImage(
    @ColumnInfo(name = "url")
    @Json(name = "url")
    var url: String = ""
) : DatabaseEntity()