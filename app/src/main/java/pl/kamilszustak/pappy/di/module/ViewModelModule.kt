package pl.kamilszustak.pappy.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.kamilszustak.pappy.common.AndroidViewModelFactory
import pl.kamilszustak.pappy.di.ViewModelKey
import pl.kamilszustak.pappy.ui.main.images.DogImagesViewModel

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindAndroidViewModelFactory(factory: AndroidViewModelFactory): ViewModelProvider.AndroidViewModelFactory

    @Binds
    @IntoMap
    @ViewModelKey(DogImagesViewModel::class)
    abstract fun bindDogImagesViewModel(dogImagesViewModel: DogImagesViewModel): ViewModel
}