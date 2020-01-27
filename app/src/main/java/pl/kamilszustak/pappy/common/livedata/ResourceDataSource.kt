package pl.kamilszustak.pappy.common.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.kamilszustak.pappy.common.data.Resource
import pl.kamilszustak.pappy.util.mapNotNull

class ResourceDataSource<T>(
    block: () -> LiveData<Resource<T>> = { MutableLiveData<Resource<T>>() }
) : RefreshableDataSource<Resource<T>>(block) {

    val data: LiveData<T> = this.result.mapNotNull {
        it.data
    }

    val isLoading: LiveData<Boolean> = ResourceLoadingLiveData(this.result)

    val error: SingleLiveEvent<String> = ResourceErrorLiveData(this.result)
}