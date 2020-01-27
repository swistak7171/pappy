package pl.kamilszustak.pappy.data.database

import android.app.Application
import androidx.room.*
import pl.kamilszustak.pappy.data.database.converter.DateConverter
import pl.kamilszustak.pappy.data.model.DogImage
import pl.kamilszustak.pappy.R

@Database(
    entities = [
        DogImage::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateConverter::class)
abstract class ApplicationDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: ApplicationDatabase? = null

        operator fun invoke(application: Application): ApplicationDatabase {
            return INSTANCE ?: synchronized(ApplicationDatabase::class) {
                build(application).also {
                    INSTANCE = it
                }
            }
        }

        private fun build(application: Application): ApplicationDatabase {
            return Room.databaseBuilder(
                application.applicationContext,
                ApplicationDatabase::class.java,
                application.applicationContext.getString(R.string.database_name)
            )
                .build()
        }
    }
}