package com.example.myapplication.ui.adapter
import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.myapplication.R

class SlidingAdapter(private val context: Context, private var urls: List<String>) :

    PagerAdapter() {
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return urls.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val rootLayout = LayoutInflater.from(context).inflate(R.layout.slidingimages_layout, view, false)!!
        val imageView = rootLayout
            .findViewById<View>(R.id.image) as ImageView
        Glide.with(context)
            .load(urls[position])
            .into(imageView)
        view.addView(rootLayout, 0)
        return rootLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}
    override fun saveState(): Parcelable? {
        return null
    }
}