package pl.kamilszustak.pappy.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import pl.kamilszustak.pappy.common.AndroidViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindAndroidViewModelFactory(factory: AndroidViewModelFactory): ViewModelProvider.AndroidViewModelFactory
}