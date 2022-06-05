package com.example.myapplication.ui.fragment.home

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Resource
import com.example.myapplication.data.entitiy.CartDataEntity
import com.example.myapplication.data.entitiy.DataX
import com.example.myapplication.data.soure.local.RoomDao
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.LayHomeDialogBinding
import com.example.myapplication.ui.IItemClickListener
import com.example.myapplication.ui.adapter.SlidingAdapter
import com.example.shopping.ui.adapter.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), IItemClickListener {
    private lateinit var productItem: DataX
    private lateinit var binding: FragmentHomeBinding
    lateinit var homeVm: HomeViewModel

    private lateinit var adapter: ProductAdapter
    private var slidingImageAdapter: SlidingAdapter? = null
    private var urls: MutableList<String> = mutableListOf()
    var isLoading = false

    @Inject
    lateinit var roomDao: RoomDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //0 n  val matcher = Pattern.compile(ch.toString()).matcher(s)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        activity?.let { homeVm.setActivity(it) }
        setVm()
        initViews()

        return binding.root
    }

    private fun setVm() = {
        with(binding) {
            withFragment(this)
            binding.vm = this@HomeFragment.homeVm
            binding.lifecycleOwner = this.lifecycleOwner
        }

    }

    private fun initViews() {
        //    binding.pbHome.visibility = View.VISIBLE
        binding.dotsIndicator.visibility = View.GONE
        adapter = ProductAdapter(roomDao, requireContext(), this)
        binding.RecHomeProduct.init(requireContext(), adapter, 2)
        homeVm.getProducts()
        CoroutineScope(Dispatchers.Main).launch {
            homeVm.getBanners()
            launch {
                homeVm.bannerList.collect() {
                    urls.clear()
                    if (it.data != null) {
                        for (x in it.data!!) {
                            urls.add(x.image)
                            slidingImageAdapter?.notifyDataSetChanged()

                        }
                    }
                }
            }
        }
        initSlider(urls)

        homeVm.productsList.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        adapter.setProductList(response.data)
                        adapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    fun initSlider(urls: MutableList<String>) {

        slidingImageAdapter = SlidingAdapter(requireContext(), this.urls)
        binding.viewpager.adapter = slidingImageAdapter
        binding.dotsIndicator.visibility = View.VISIBLE
        binding.dotsIndicator.setViewPager(binding.viewpager)
        val swipeTimer = Timer()

        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                //  Handler(Looper.getMainLooper()).post(update!!)
            }
        }, 5000, 5000)
    }

    private fun hideProgressBar() {

        binding.progressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        isLoading = true
    }




    fun RecyclerView.init(
        context: Context?,
        adapter: RecyclerView.Adapter<*>?,
        column: Int
    ) {
        val layoutManager = GridLayoutManager(context, column)
        this.layoutManager = layoutManager
        this.setHasFixedSize(true)
        this.adapter = adapter
    }

    override fun onItemClickListener(view: View, itemId: Int) {
        productItem = adapter.getProductList()[itemId]!!
        when (view.id) {
            R.id.img_list -> showDialog(itemId)
        }
    }

    private fun showDialog(itemId: Int) {
        val dialogBinding: LayHomeDialogBinding? =
            DataBindingUtil.inflate(
                LayoutInflater.from(activity),
                R.layout.lay_home_dialog,
                null,
                false
            )
        CoroutineScope(Dispatchers.IO).launch {
            val product = productItem!!.name?.let { roomDao.fetchById(it) }
            dialogBinding?.ivFavoriteDialog?.isChecked = product != null
        }

        val customDialog = AlertDialog.Builder(activity, 0).create()

        customDialog.apply {
            setView(dialogBinding?.root)
        }.show()



        dialogBinding?.ivFavoriteDialog?.setOnClickListener {
            if (dialogBinding.ivFavoriteDialog.isChecked) {

             homeVm.insertFav(productItem)
                Toast.makeText(activity, getString(R.string.add_to_fav), Toast.LENGTH_LONG).show()

            } else {

                homeVm.deletFav(productItem)

                Toast.makeText(activity, getString(R.string.remove_from_fav), Toast.LENGTH_LONG)
                    .show()

            }
            customDialog.dismiss()

        }
        dialogBinding?.ivFavoriteCart?.setOnClickListener(View.OnClickListener {
                var cartItem = CartDataEntity(productItem.id
                    ,productItem.description,
                  productItem.discount,
                  productItem.image,
                  productItem.images,
                  productItem.in_cart,
                  productItem.in_favorites,
                  productItem.name,
                  productItem.old_price,
                  productItem.price)
                homeVm.addToCart(cartItem)
            customDialog.dismiss()
            Toast.makeText(activity, getString(R.string.added), Toast.LENGTH_LONG)
                .show()

        })
        dialogBinding?.ivFavoriteDetails?.setOnClickListener(View.OnClickListener {
            val bundle = bundleOf("Key" to productItem)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        })
    }
}

fun Fragment.withFragment(dataBinding: ViewDataBinding) {
    dataBinding.lifecycleOwner = this.viewLifecycleOwner
}
