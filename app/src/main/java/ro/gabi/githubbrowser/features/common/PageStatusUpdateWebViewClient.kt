package ro.gabi.githubbrowser.features.common

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PageStatusUpdateWebViewClient()
    : WebViewClient() {

    private val _pageStatusLiveData = MutableLiveData<PageStatus>()
    val pageStatusLiveData: LiveData<PageStatus>
        get() = _pageStatusLiveData

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        _pageStatusLiveData.value = PageStatus.FINISHED
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        _pageStatusLiveData.value = PageStatus.STARTED
    }

    enum class PageStatus {
        STARTED, FINISHED
    }

}