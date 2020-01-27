package pl.kamilszustak.pappy.network

import pl.kamilszustak.pappy.data.model.DogImage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/breed/{breed}/images")
    suspend fun getDogImagesByBreed(@Query("breed") breed: String): Response<List<DogImage>>
}