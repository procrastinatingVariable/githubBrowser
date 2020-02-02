package ro.gabi.githubbrowser.features.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class ListItemViewHolder<T>(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

    override val containerView: View
        get() = itemView

    abstract fun bind(data: T)
}