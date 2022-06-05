package com.example.myapplication.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.entitiy.category.DataX
import com.example.myapplication.data.soure.local.RoomDao
import com.example.myapplication.databinding.RootCategoryBinding
import com.example.myapplication.ui.IItemClickListener
import com.example.myapplication.utils.Extension.loadImage

class CategoryAdapter constructor(
    var roomDao: RoomDao,
    val context: Context,
    private val onClick: IItemClickListener
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private var list: ArrayList<DataX> = ArrayList()


    inner class ViewHolder( val  binding:  RootCategoryBinding, val onClick: IItemClickListener)
     : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var mItemClickListener: IItemClickListener

        @SuppressLint("ResourceType")
        fun bind(category: DataX) {
            binding.apply {
                binding.tvName.text = category.name
                //   binding.imgCategory.text = category.old_price.toString()
                if (category.image!!.isEmpty()) {
                    binding.imgCategory.loadImage(
                        context.resources.getString(R.drawable.nophoto),
                        context
                    )
                } else {
                    binding.imgCategory.loadImage(category.image!!, context)
                }
                mItemClickListener = onClick


            }
        }

        override fun onClick(p0: View?) {
            if (p0 != null) {
                mItemClickListener?.onItemClickListener(p0, layoutPosition)
            }
        }
    }

    fun getCategoryList(): List<DataX?> {
        return list
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCategoryList(ProductList: List<DataX>) {
        list = ProductList as ArrayList<DataX>
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = LayoutInflater.from(context)
        return ViewHolder(
            RootCategoryBinding.inflate(li), onClick
        )
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.bind(currentItem)

    }


    override fun getItemCount(): Int {
        return list.size
    }
    
}