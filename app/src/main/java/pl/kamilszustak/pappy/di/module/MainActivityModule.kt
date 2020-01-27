package pl.kamilszustak.pappy.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kamilszustak.pappy.di.scope.FragmentScope
import pl.kamilszustak.pappy.ui.main.images.DogImagesFragment

@Module
abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeDogImagesFragment(): DogImagesFragment
}