package com.example.myapplication.ui.fragment.wishList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.entitiy.CartDataEntity
import com.example.myapplication.data.entitiy.DataX
import com.example.myapplication.data.local.RoomDao
import com.example.myapplication.databinding.FragmentWishListBinding
import com.example.myapplication.ui.IItemClickListener
import com.example.myapplication.ui.adapter.WishListAdapter
import com.example.myapplication.ui.fragment.detail.DetailFragment
import com.example.myapplication.utils.FragmentUtil
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class WishListFragment : Fragment(), IItemClickListener {

    private lateinit var product: DataX
    private lateinit var viewModel: WishListViewModel
    private lateinit var binding: FragmentWishListBinding
    private lateinit var adapter: WishListAdapter
    var wishList: ArrayList<DataX> = ArrayList()

    @Inject
    lateinit var roomDao: RoomDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.let { ViewModelProvider(it).get(WishListViewModel::class.java) }!!
        // activity?.let { viewModel.setActivity(it) }
/*
          viewModel.getData().observe(viewLifecycleOwner, {
                wishList?.let { adapter.setWishList(it) }
                adapter.notifyDataSetChanged()
            })*/
        viewModel.getData().observe(this, {
            adapter.setWishList(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_wish_list,
            container,
            false
        )


        initRec()
        viewModel.getData()
        return binding.root
    }

    private fun initRec() {
        //     binding..title = requireActivity().resources.getString(R.string.WishList)
        adapter = WishListAdapter(roomDao, requireContext(), this)
        val layoutManager = LinearLayoutManager(activity)
        binding.recWishlist.layoutManager = layoutManager
        binding.recWishlist.setHasFixedSize(true)
        binding.recWishlist.adapter = adapter
    }

    override fun onItemClickListener(view: View, itemId: Int) {
        product = adapter.getWishList()[itemId]!!
        when (view.id) {
            R.id.iv_Cart -> addToCart(product)
            R.id.cardView -> showDetails(product)
        }
    }

    private fun showDetails(item: DataX) {
       /* val detailFragment = DetailFragment(item)
        activity?. supportFragmentManager?.let {
            FragmentUtil.replaceFragment(
                detailFragment,
                R.id.FragmentLoad,
                it?.beginTransaction()
            )
        }*/
        val bundle = bundleOf("Key" to item)
        findNavController().navigate(R.id.action_wishListFragment_to_detailFragment, bundle)


    }

    private fun addToCart(item: DataX) {
        var cartItem = CartDataEntity(item.id
        ,item.description,
            item.discount,
            item.image,
            item.images,
            item.in_cart,
            item.in_favorites,
            item.name,
            item.old_price,
            item.price
        )
        viewModel.addToCart(cartItem)
    }

}