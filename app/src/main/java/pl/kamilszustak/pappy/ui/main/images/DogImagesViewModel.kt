package pl.kamilszustak.pappy.ui.main.images

import android.app.Application
import androidx.lifecycle.LiveData
import pl.kamilszustak.pappy.common.livedata.ResourceDataSource
import pl.kamilszustak.pappy.common.livedata.SingleLiveEvent
import pl.kamilszustak.pappy.common.livedata.UniqueLiveData
import pl.kamilszustak.pappy.data.DogImageRepository
import pl.kamilszustak.pappy.data.model.DogImage
import pl.kamilszustak.pappy.ui.base.BaseViewModel
import javax.inject.Inject

class DogImagesViewModel @Inject constructor(
    application: Application,
    private val dogImageRepository: DogImageRepository
) : BaseViewModel(application) {

    val dogImagesResource: ResourceDataSource<List<DogImage>> = ResourceDataSource()

    val breedName: UniqueLiveData<String> = UniqueLiveData()

    private val _error: SingleLiveEvent<String> = SingleLiveEvent()
    val error: LiveData<String> = _error

    fun onSearchButtonClick() {
        val breed = breedName.value?.toLowerCase()
        if (breed.isNullOrBlank()) {
            _error.value = "Breed name cannot be blank"
            return
        }

        dogImagesResource.changeFlowSource {
            dogImageRepository.getAllByBreed(breed)
        }
    }
}