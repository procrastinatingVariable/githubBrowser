package ro.gabi.githubbrowser.common.livedata

import androidx.lifecycle.Observer

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
sealed class Resource<out T, out ET>(val status: Status, val data: T?, val error: ET?) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    class Success<T>(data: T): Resource<T, Nothing>(Status.SUCCESS, data, null)
    class Error<T>(error: T): Resource<Nothing, T>(Status.ERROR, null, error)
    class Loading: Resource<Nothing, Nothing>(Status.LOADING, null, null)

}

open class ResourceObserver<T, ET> : Observer<Resource<T, ET>> {

    override fun onChanged(t: Resource<T, ET>) {
        when(t) {
            //It's safe to use the non-null operator because constructors takes non-null types
            is Resource.Success<T> -> onSuccess(t.data!!)
            is Resource.Error<ET> -> onError(t.error!!)
            is Resource.Loading -> onLoading()
        }
    }

    open fun onLoading() {

    }

    open fun onError(error: ET) {

    }

    open fun onSuccess(data: T) {

    }

}