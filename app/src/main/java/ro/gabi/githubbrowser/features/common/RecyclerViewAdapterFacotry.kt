package ro.gabi.githubbrowser.features.common

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import ro.gabi.githubbrowser.features.repolist.RepoListAdapter
import kotlin.reflect.KClass

class RecyclerViewAdapterFactory {

    fun <T : RecyclerView.Adapter<VH>, VH : RecyclerView.ViewHolder> create(
        context: Context,
        kclass: KClass<T>
    ): T = when(kclass) {
        RepoListAdapter::class -> RepoListAdapter(context) as T
        else -> throw IllegalArgumentException("No definitions for ${kclass.simpleName}")
    }

}