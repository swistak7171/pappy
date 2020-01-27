package pl.kamilszustak.pappy.common.livedata

import androidx.lifecycle.LiveData
import pl.kamilszustak.pappy.common.data.Resource
import pl.kamilszustak.pappy.common.data.Status

class ResourceLoadingLiveData<T>(
    source: LiveData<Resource<T>>
) : LoadingLiveData<Resource<T>>(source, {
    it.status == Status.LOADING && it.data == null
})