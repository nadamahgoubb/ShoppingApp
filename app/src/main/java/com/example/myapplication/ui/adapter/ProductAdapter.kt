package com.example.shopping.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.RoomDao
import com.example.myapplication.data.entitiy.DataX
import com.example.myapplication.databinding.RootProductBinding
import com.example.myapplication.ui.IItemClickListener


class ProductAdapter constructor(
    var roomDao: RoomDao,
    val context: Context,
    private val onClick: IItemClickListener
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>(), Filterable {
    private var productsList: ArrayList<DataX> = ArrayList()
    var productsSearchList: ArrayList<DataX> = ArrayList()
    fun getProductList(): List<DataX?> {
        return productsList
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setProductList(ProductList: List<DataX>) {
        productsList = ProductList as ArrayList<DataX>
        productsSearchList = productsList
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = LayoutInflater.from(context)
        return ViewHolder(
            RootProductBinding.inflate(li), onClick
        )
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val currentItem = productsSearchList[position]
        holder.bind(currentItem)

    }

    override fun getItemCount(): Int {
        return productsSearchList.size
    }


    inner class ViewHolder(
        private val binding: RootProductBinding,
        private val mOnItemClickListener: IItemClickListener
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private var mItemClickListener: IItemClickListener? = null

        @SuppressLint("SetTextI18n", "ResourceType")
        fun bind(products: DataX) {
            binding.apply {
                binding.tvName.text = products.name
                binding.tvPrice.text = products.old_price.toString()
                if (products.images!!.isEmpty()) {
                    Glide.with(context).load(context.resources.getString(R.drawable.nophoto))
                        .into(binding.imgProduct)

                } else {
                    Glide.with(context).load(products.image).into(binding.imgProduct)

                }
                if (products.discount != 0) {
                    binding.tvDiscountPercent.text = products.discount.toString() + "%"
                    binding.txtCategoryProductRegularPrice.text = products.price.toString()
                    binding.tvPrice.paintFlags =
                        binding.tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    binding.tvDiscountPercent.visibility = View.INVISIBLE
                    binding.txtCategoryProductRegularPrice.visibility = View.INVISIBLE
                }
                mItemClickListener = mOnItemClickListener
                binding.imgList.setOnClickListener(this@ViewHolder)
            }

        }

        override fun onClick(v: View) {
            mItemClickListener?.onItemClickListener(v, layoutPosition)
        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                productsSearchList = if (charString.isEmpty()) productsList else {
                    val filteredList = ArrayList<DataX>()
                    productsList
                        .filter {
                            it.name!!.lowercase().startsWith(charString, false)
                        }
                        .forEach { filteredList.add(it) }
                    filteredList

                }
                return FilterResults().apply { values = productsSearchList }


            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                productsSearchList = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<DataX>
                notifyDataSetChanged()
            }
        }
    }

}