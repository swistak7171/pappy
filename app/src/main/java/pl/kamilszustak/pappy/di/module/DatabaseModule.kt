package pl.kamilszustak.pappy.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import pl.kamilszustak.pappy.data.database.ApplicationDatabase
import pl.kamilszustak.pappy.data.database.dao.DogImageDao
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): ApplicationDatabase =
        ApplicationDatabase(application)

    @Provides
    @Singleton
    fun provideDogImageDao(applicationDatabase: ApplicationDatabase): DogImageDao =
        applicationDatabase.getDogImageDao()
}