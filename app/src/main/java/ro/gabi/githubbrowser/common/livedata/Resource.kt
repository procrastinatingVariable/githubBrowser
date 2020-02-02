package ro.gabi.githubbrowser.common.livedata

import androidx.lifecycle.Observer

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Throwable): Resource<T> {
            return Resource(Status.ERROR, null, error)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }

    enum class Status {
        SUCCESS, ERROR, LOADING
    }
}

open class ResourceObserver<T> : Observer<Resource<T>> {

    override fun onChanged(t: Resource<T>) {
        when(t.status) {
            //It's safe to use the non-null operator because constructors takes non-null types
            Resource.Status.SUCCESS -> onSuccess(t.data!!)
            Resource.Status.ERROR -> onError(t.error!!)
            Resource.Status.LOADING -> onLoading()
        }
    }

    open fun onLoading() {
    }

    open fun onError(error: Throwable) {
    }

    open fun onSuccess(data: T) {
    }

    companion object {

        fun <T> onChanged(onSuccess: (T)->Unit = {}, onError: (Throwable)->Unit = {}, onLoading: ()->Unit = {}): ResourceObserver<T> =
            object : ResourceObserver<T>() {
                override fun onLoading() {
                    onLoading()
                }

                override fun onError(error: Throwable) {
                    onError(error)
                }

                override fun onSuccess(data: T) {
                    onSuccess(data)
                }
            }

        fun <T> onSuccess(block: (T)->Unit): ResourceObserver<T> {
            return object : ResourceObserver<T>() {
                override fun onSuccess(data: T) {
                    block(data)
                }
            }
        }

        fun <T> onError(block: (Throwable)->Unit): ResourceObserver<T> {
            return object : ResourceObserver<T>() {
                override fun onError(error: Throwable) {
                    block(error)
                }
            }

        }
    }

}