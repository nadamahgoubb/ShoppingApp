package com.example.myapplication.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Resource
import com.example.myapplication.data.RoomDao
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.IItemClickListener
import com.example.myapplication.ui.adapter.SlidingAdapter
import com.example.shopping.ui.adapter.ProductAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import java.util.regex.Pattern

class HomeFragment : Fragment(R.layout.fragment_home), IItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    lateinit var homeVm: HomeViewModel

    private lateinit var adapter: ProductAdapter
    private var slidingImageAdapter: SlidingAdapter? = null
    private var urls: MutableList<String> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       //0 val matcher = Pattern.compile(ch.toString()).matcher(s)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        homeVm = activity?.let { ViewModelProvider(it).get(HomeViewModel::class.java) }!!
        activity?.let { homeVm.setActivity(it) }

        initViews()

        return binding.root
    }

    private fun initViews() {
        //    binding.pbHome.visibility = View.VISIBLE
        binding.dotsIndicator.visibility = View.GONE
        val roomDao = RoomDao()
        adapter = ProductAdapter(roomDao, requireContext(), this)
        binding.RecHomeProduct.init(requireContext(), adapter, 2)
        homeVm.getProducts()
        CoroutineScope(Dispatchers.Main).launch {
            homeVm.getBanners()
            launch {
                homeVm.bannerList.collect() {
                    urls.clear()
                    if(it.data != null){
                    for (x in it.data!!) {
                        urls.add(x.image)
                        slidingImageAdapter?.notifyDataSetChanged()

                    }}
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

    private fun initSlider(urls: List<String>) {
        /* slidingImageAdapter = SlidingAdapter(requireContext(), urls)
        binding.viewpager.adapter = slidingImageAdapter
        binding.dotsIndicator.setViewPager(binding.viewpager)
        numPages = urls.size
        update = Runnable {
            if (currentPage == numPages) {
                currentPage = 0
            }
            binding.pager.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post(update!!)
            }
        }, 5000, 5000)
    }*/
        slidingImageAdapter = SlidingAdapter(requireContext(), urls)
        binding.viewpager.adapter = slidingImageAdapter
        binding.dotsIndicator.visibility= View.VISIBLE
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


    var isError = false
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

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
        TODO("Not yet implemented")
    }
}