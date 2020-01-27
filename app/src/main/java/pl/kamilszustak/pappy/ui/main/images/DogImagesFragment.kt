package pl.kamilszustak.pappy.ui.main.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeableMethod
import kotlinx.android.synthetic.main.fragment_dog_images.*
import org.jetbrains.anko.design.snackbar
import pl.kamilszustak.pappy.R
import pl.kamilszustak.pappy.data.item.DogImageItem
import pl.kamilszustak.pappy.data.model.DogImage
import pl.kamilszustak.pappy.databinding.FragmentDogImagesBinding
import pl.kamilszustak.pappy.ui.base.BaseFragment
import pl.kamilszustak.pappy.util.updateModels
import timber.log.Timber
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

        initializeCardStackView()
        setListeners()
        observeViewModel()
    }

    private fun initializeCardStackView() {
        modelAdapter = ModelAdapter {
            DogImageItem(it)
        }
        val fastAdapter = FastAdapter.with(modelAdapter)
        val listener = getCardStackListener()

        val layoutManager = CardStackLayoutManager(context, listener).apply {
            this.setStackFrom(StackFrom.TopAndRight)
            this.setVisibleCount(3)
            this.setTranslationInterval(8.0f)
            this.setScaleInterval(0.95f)
            this.setSwipeThreshold(0.3f)
            this.setMaxDegree(40.0f)
            this.setDirections(Direction.FREEDOM)
            this.setCanScrollHorizontal(true)
            this.setCanScrollVertical(true)
            this.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            this.setOverlayInterpolator(LinearInterpolator())
        }

        cardStackView.apply {
            this.layoutManager = layoutManager
            this.adapter = fastAdapter
            this.itemAnimator = DefaultItemAnimator()
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

    private fun getCardStackListener(): CardStackListener {
        return object : CardStackListener {
            override fun onCardSwiped(direction: Direction?) {
                when (direction) {
                    Direction.Left -> {
                        Timber.i("Swiped left")
                    }

                    Direction.Right -> {
                        Timber.i("Swiped right")
                    }

                    Direction.Top, Direction.Bottom -> {
                        Timber.i("Swiped top/bottom")
                    }

                    else -> {
                        Timber.i("Swiped somewhere else")
                    }
                }
            }

            override fun onCardDisappeared(view: View?, position: Int) {
            }

            override fun onCardDragging(direction: Direction?, ratio: Float) {
            }

            override fun onCardCanceled() {
            }

            override fun onCardAppeared(view: View?, position: Int) {
            }

            override fun onCardRewound() {
            }
        }
    }
}