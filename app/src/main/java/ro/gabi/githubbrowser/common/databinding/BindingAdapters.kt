package ro.gabi.githubbrowser.common.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import ro.gabi.githubbrowser.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view).load(url).error(R.drawable.ic_avatar).into(view)
}
