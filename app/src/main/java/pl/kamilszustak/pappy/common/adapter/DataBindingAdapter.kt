package pl.kamilszustak.pappy.common.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object DataBindingAdapter {

    @BindingAdapter("android:src")
    @JvmStatic
    fun ImageView.setImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}