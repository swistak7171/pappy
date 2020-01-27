package pl.kamilszustak.pappy.ui.main.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import kotlinx.android.synthetic.main.fragment_dog_images.*
import org.jetbrains.anko.design.snackbar
import pl.kamilszustak.pappy.R
import pl.kamilszustak.pappy.data.item.DogImageItem
import pl.kamilszustak.pappy.data.model.DogImage
import pl.kamilszustak.pappy.databinding.FragmentDogImagesBinding
import pl.kamilszustak.pappy.ui.base.BaseFragment
import pl.kamilszustak.pappy.util.updateModels
import javax.inject.Inject

class DogImagesFragment : BaseFragment(R.layout.fragment_dog_images) {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory

    private val viewModel: DogImagesViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var modelAdapter: ModelAdapter<DogImage, DogImageItem>

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

        initializeRecyclerView()
        setListeners()
        observeViewModel()
    }

    private fun initializeRecyclerView() {
        modelAdapter = ModelAdapter {
            DogImageItem(it)
        }

        val fastAdapter = FastAdapter.with(modelAdapter)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        dogImagesRecyclerView.apply {
            this.layoutManager = layoutManager
            this.adapter = fastAdapter
        }
    }

    private fun setListeners() {
        searchButton.setOnClickListener {
            viewModel.onSearchButtonClick()
        }
    }

    private fun observeViewModel() {
        viewModel.dogImagesResource.data.observe(viewLifecycleOwner) { images ->
            modelAdapter.updateModels(images)
        }

        viewModel.dogImagesResource.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                progressBar.show()
            } else {
                progressBar.hide()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { message ->
            view?.snackbar(message)
        }
    }
}