package pl.kamilszustak.pappy.data.model.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetDogImagesByBreedResponseBody(
    @Json(name = "message")
    var message: List<String>,

    @Json(name = "status")
    var status: String
)