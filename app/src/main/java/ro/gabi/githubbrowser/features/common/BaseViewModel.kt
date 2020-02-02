package ro.gabi.githubbrowser.features.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ro.gabi.githubbrowser.data.GithubRepository

abstract class BaseViewModel : ViewModel() {

    protected val _errorLd = MutableLiveData<Throwable>()
    val errorLd: LiveData<Throwable>
        get() = _errorLd

}
