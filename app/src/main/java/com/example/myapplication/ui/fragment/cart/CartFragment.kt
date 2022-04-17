package com.example.myapplication.ui.fragment.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.entitiy.CartDataEntity
import com.example.myapplication.data.entitiy.DataX
import com.example.myapplication.data.local.RoomDao
import com.example.myapplication.databinding.FragmentCartBinding
import com.example.myapplication.ui.IItemClickListener
import com.example.myapplication.ui.adapter.CartListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class CartFragment : Fragment(), IItemClickListener {
    private lateinit var product: CartDataEntity
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var adapter: CartListAdapter
    var wishList: ArrayList<DataX> = ArrayList()

    @Inject
    lateinit var roomDao: RoomDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.let { ViewModelProvider(it).get(CartViewModel::class.java) }!!
        viewModel.getData().observe(this, {
            adapter.setCartList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun initView() {
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_cart,
            container,
            false
        )
        initView()
        initRec()
        viewModel.getData()
        return binding.root
    }


    private fun initRec() {
        //     binding..title = requireActivity().resources.getString(R.string.WishList)
        adapter = CartListAdapter(roomDao, requireContext(), this)
        val layoutManager = LinearLayoutManager(activity)
        binding.recCartList.layoutManager = layoutManager
        binding.recCartList.setHasFixedSize(true)
        binding.recCartList.adapter = adapter
    }

    override fun onItemClickListener(view: View, itemId: Int) {
        product = adapter.getCartList()[itemId]!!
        when (view.id) {
            R.id.cardView -> showDetails(product)
        }
    }

    private fun showDetails(item: CartDataEntity) {
        var cartItem = DataX(item.id
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

        val bundle = bundleOf("Key" to cartItem)
        findNavController().navigate(R.id.action_cartFragment_to_detailFragment, bundle)


    }
}