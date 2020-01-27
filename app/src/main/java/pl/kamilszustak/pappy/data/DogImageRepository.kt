package pl.kamilszustak.pappy.data

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.pappy.common.data.NetworkBoundResource
import pl.kamilszustak.pappy.common.data.Resource
import pl.kamilszustak.pappy.data.database.dao.DogImageDao
import pl.kamilszustak.pappy.data.model.DogImage
import pl.kamilszustak.pappy.data.model.network.GetDogImagesByBreedResponseBody
import pl.kamilszustak.pappy.network.ApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogImageRepository @Inject constructor(
    private val dogImageDao: DogImageDao,
    private val apiService: ApiService
) {

    fun getAllByBreed(breed: String, shouldFetch: Boolean = true): Flow<Resource<List<DogImage>>> {
        return object : NetworkBoundResource<GetDogImagesByBreedResponseBody, List<DogImage>>() {
            override fun loadFromDatabase(): Flow<List<DogImage>> =
                dogImageDao.getAllByBreed(breed)

            override fun shouldFetch(data: List<DogImage>?): Boolean = shouldFetch

            override suspend fun fetchFromNetwork(): Response<GetDogImagesByBreedResponseBody> =
                apiService.getDogImagesByBreed(breed)

            override suspend fun saveFetchResult(result: GetDogImagesByBreedResponseBody) {
                val images = result.message.map {
                    DogImage(it, breed)
                }
                dogImageDao.insertAll(images)
            }
        }.asFlow()
    }
}