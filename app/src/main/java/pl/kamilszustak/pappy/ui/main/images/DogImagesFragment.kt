package pl.kamilszustak.pappy.ui.main.images

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.pappy.R
import pl.kamilszustak.pappy.ui.base.BaseFragment
import javax.inject.Inject

class DogImagesFragment : BaseFragment(R.layout.fragment_dog_images) {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory

    private val viewModel: DogImagesViewModel by viewModels {
        viewModelFactory
    }
}