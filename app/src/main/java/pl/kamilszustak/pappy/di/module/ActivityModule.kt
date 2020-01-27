package pl.kamilszustak.pappy.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kamilszustak.pappy.di.scope.ActivityScope
import pl.kamilszustak.pappy.ui.main.MainActivity

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}