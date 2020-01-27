package pl.kamilszustak.pappy.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.util.*

abstract class DatabaseEntity : Parcelable {

    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "created_at")
    var createdAt: Date = Date()

    @ColumnInfo(name = "updated_at")
    var updatedAt: Date = Date()
}