package com.ottsolution.demo.ui.base.frameworks.base

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ottsolution.demo.R
import com.ottsolution.demo.data.utils.ThemesUtil
import com.ottsolution.demo.ui.base.frameworks.extensions.alert
import dagger.android.support.DaggerAppCompatActivity
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.util.*
import javax.inject.Inject


abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun getContentViewId(): Int
    abstract fun getRootLayoutContainer(): View

    fun alert(stringRes: Int) {
        getRootLayoutContainer().alert(stringRes)
    }

    fun alert(string: String) {
        getRootLayoutContainer().alert(string)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val hashMapResource = ThemesUtil.getHashMapResource(this, R.xml.theme_map)
        val themeExtra = intent.getStringExtra("theme")
        Log.e("Theme", themeExtra ?: "null")
        if (themeExtra != null && hashMapResource.containsKey(themeExtra)) {
            setTheme(ThemesUtil.getResId(hashMapResource[themeExtra] ?: error("default"), R.style::class.java))
        }
        super.onCreate(savedInstanceState)
        setContentView(getContentViewId())
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}

inline fun <reified T : ViewModel> FragmentActivity.viewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}