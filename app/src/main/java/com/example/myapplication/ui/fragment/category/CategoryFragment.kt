package com.example.myapplication.ui.fragment.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.soure.local.RoomDao
import com.example.myapplication.databinding.FragmentCategoryBinding
import com.example.myapplication.ui.IItemClickListener
import com.example.myapplication.ui.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoryFragment : Fragment(), IItemClickListener {


    private lateinit var categoryVm: CategoryViewModel
    private lateinit var adapter: CategoryAdapter
    private lateinit var binding: FragmentCategoryBinding

    @Inject
    lateinit var roomDao: RoomDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        categoryVm = activity?.let { ViewModelProvider(it).get(CategoryViewModel::class.java) }!!
        categoryVm.setActivity(activity!!)

        initRec()
        categoryVm.gerCategory()
        categoryVm.categoryList.observe(this, {
            it.data?.let { it1 ->
                adapter.setCategoryList(it1) }
            adapter.notifyDataSetChanged()
        })
        return binding.root
    }

    private fun initRec() {

        adapter = CategoryAdapter(roomDao, requireContext(), this)
            val layoutManager = GridLayoutManager(activity,2)
        binding.recCartList.layoutManager = layoutManager
        binding.recCartList.setHasFixedSize(true)
        binding.recCartList.adapter = adapter
    }

    override fun onItemClickListener(view: View, itemId: Int) {
        TODO("Not yet implemented")
    }


}