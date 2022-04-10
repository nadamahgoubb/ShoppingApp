package com.example.myapplication.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.entitiy.DataX
import com.example.myapplication.data.local.RoomDao
import com.example.myapplication.databinding.RootWishListBinding
import com.example.myapplication.ui.IItemClickListener
import com.example.myapplication.utils.Extension.loadImage

class WishListAdapter constructor(
    var roomDao: RoomDao,
    val context: Context,
    private val onClick: IItemClickListener
) :
    RecyclerView.Adapter<WishListAdapter.ViewHolder>() {
    inner class ViewHolder( val  binding:  RootWishListBinding, val onClick: IItemClickListener)
     : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var mItemClickListener: IItemClickListener

        @SuppressLint("ResourceType")
        fun bind(products: DataX) {
            binding.apply {
                binding.tvWishListTitle.text = products.name
                binding.tvWishListPrice.text = products.old_price.toString()
               if (products.images!!.isEmpty()) {
                    binding.ivWishListFav.loadImage(
                        context.resources.getString(R.drawable.nophoto),
                        context
                    )
                } else {
                    binding.ivWishListFav.loadImage(products.image!!, context)
                }
                mItemClickListener = onClick
                binding.ivCart.setOnClickListener(this@ViewHolder)
                binding.cardView.setOnClickListener(this@ViewHolder)

            }

        }

        override fun onClick(p0: View?) {
            if (p0 != null) {
                mItemClickListener?.onItemClickListener(p0, layoutPosition)
            }
        }

    }

    private var wishList: ArrayList<DataX> = ArrayList()
    fun getWishList(): List<DataX?> {
        return wishList
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setWishList(ProductList: List<DataX>) {
        wishList = ProductList as ArrayList<DataX>
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = LayoutInflater.from(context)
        return ViewHolder(
            RootWishListBinding.inflate(li), onClick
        )
    }

    override fun onBindViewHolder(holder: WishListAdapter.ViewHolder, position: Int) {
        val currentItem = wishList[position]
        holder.bind(currentItem)

    }
    fun getProductList(): List<DataX?> {
        return wishList
    }

    override fun getItemCount(): Int {
        return wishList.size
    }
    
}