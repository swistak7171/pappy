package pl.kamilszustak.pappy.network

import pl.kamilszustak.pappy.data.model.network.GetDogImagesByBreedResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/breed/{breed}/images")
    suspend fun getDogImagesByBreed(@Path("breed") breed: String): Response<GetDogImagesByBreedResponseBody>
}