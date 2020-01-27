package pl.kamilszustak.pappy.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "dog_images")
@Parcelize
data class DogImage(
    @ColumnInfo(name = "url")
    var url: String = "",

    @ColumnInfo(name = "breed")
    var breed: String = ""
) : DatabaseEntity()