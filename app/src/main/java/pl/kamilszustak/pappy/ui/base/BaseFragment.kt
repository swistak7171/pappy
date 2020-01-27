package pl.kamilszustak.pappy.ui.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import pl.kamilszustak.pappy.ui.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class BaseFragment : Fragment, HasAndroidInjector {

    constructor() : super()

    constructor(@LayoutRes layoutResourceId: Int) : super(layoutResourceId)

    @Inject
    protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    protected fun hideActionBar() {
        (activity as? BaseActivity)?.supportActionBar?.hide()
    }

    protected fun showActionBar() {
        (activity as? BaseActivity)?.supportActionBar?.show()
    }
}