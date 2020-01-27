package pl.kamilszustak.pappy.data.item

import android.view.View
import android.widget.ImageView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.ModelAbstractItem
import kotlinx.android.synthetic.main.item_dog_images_list.view.*
import pl.kamilszustak.pappy.R
import pl.kamilszustak.pappy.data.model.DogImage
import pl.kamilszustak.pappy.util.load

class DogImageItem(dogImage: DogImage) : ModelAbstractItem<DogImage, DogImageItem.ViewHolder>(dogImage) {

    override var identifier: Long
        get() = this.model.id
        set(value) {}

    override val type: Int
        get() = R.id.fastadapter_dog_image_item_id

    override val layoutRes: Int
        get() = R.layout.item_dog_images_list

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    class ViewHolder(view: View) : FastAdapter.ViewHolder<DogImageItem>(view) {

        private val dogImageView: ImageView = view.dogImageView

        override fun bindView(item: DogImageItem, payloads: MutableList<Any>) {
            dogImageView.load(item.model.url)
        }

        override fun unbindView(item: DogImageItem) {
            dogImageView.setImageDrawable(null)
        }
    }
}