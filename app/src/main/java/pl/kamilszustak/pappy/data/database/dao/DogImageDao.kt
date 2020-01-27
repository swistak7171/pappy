package pl.kamilszustak.pappy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.pappy.data.model.DogImage

@Dao
interface DogImageDao {

    @Query("SELECT * FROM dog_images WHERE LOWER(breed) = LOWER(:breed)")
    fun getAllByBreed(breed: String): Flow<List<DogImage>>

    @Query("SELECT * FROM dog_images WHERE id = :id")
    fun getById(id: Long): Flow<DogImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dogImage: DogImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dogImages: List<DogImage>)

    @Query("DELETE FROM dog_images")
    suspend fun deleteAll()
}