package pl.kamilszustak.pappy.ui.main.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.fragment_dog_images.*
import org.jetbrains.anko.design.snackbar
import pl.kamilszustak.pappy.R
import pl.kamilszustak.pappy.databinding.FragmentDogImagesBinding
import pl.kamilszustak.pappy.ui.base.BaseFragment
import timber.log.Timber
import javax.inject.Inject

class DogImagesFragment : BaseFragment(R.layout.fragment_dog_images) {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory

    private val viewModel: DogImagesViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<FragmentDogImagesBinding>(
            inflater,
            R.layout.fragment_dog_images,
            container,
            false
        ).apply {
            this.viewModel = this@DogImagesFragment.viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        observeViewModel()
    }

    private fun setListeners() {
        searchButton.setOnClickListener {
            viewModel.onSearchButtonClick()
        }
    }

    private fun observeViewModel() {
        viewModel.error.observe(this) {
            view?.snackbar(it)
        }

        viewModel.dogImagesResource.data.observe(this) {
            Timber.i(it.toString())
        }
    }
}