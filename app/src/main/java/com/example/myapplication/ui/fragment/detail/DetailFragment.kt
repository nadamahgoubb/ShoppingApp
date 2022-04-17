package com.example.myapplication.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.entitiy.DataX
import com.example.myapplication.databinding.FragmentDetailBinding
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment constructor() : Fragment(), View.OnClickListener {
    private lateinit var product: DataX
    private lateinit var binding: FragmentDetailBinding
    private var orderNum = 1
    private lateinit var detailVm: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailVm = activity?.let { ViewModelProvider(it).get(DetailViewModel::class.java) }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )
         product = arguments?.getParcelable<DataX>("Key")!!
        initView()
        initData()
        return binding.root
    }

    private fun initView() {
        binding.ivDetailsMinus.setOnClickListener(this)
        binding.ivDetailsPlus.setOnClickListener(this)
        binding.ivDetailsFavorite.setOnClickListener(this)
    }

    private fun initData() {
        var favProduct = detailVm.fetchProductById(product.name)
        binding.ivDetailsFavorite.isChecked=favProduct!=null
        binding.tvDetailsDescription.text = product.description
        binding.tvDetailsName.text = product.name
        binding.tvDetailsPrice.text = product.price.toString()
        binding.tvDetailsQuantity.text = orderNum.toString()

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.iv_Details_Favorite ->
                if (iv_Details_Favorite.isChecked) {

                    detailVm.insertFav(product)
                    Toast.makeText(activity, getString(R.string.add_to_fav), Toast.LENGTH_LONG)
                        .show()

                } else {

                    detailVm.deletFav(product)

                    Toast.makeText(activity, getString(R.string.remove_from_fav), Toast.LENGTH_LONG)
                        .show()

                }
            R.id.iv_Details_Minus ->
                if (orderNum == 1)
                    binding.tvDetailsQuantity.text = "1"
                else {
                    orderNum--
                    binding.tvDetailsQuantity.text = orderNum.toString()
                }
            R.id.iv_Details_Plus -> {
                orderNum = +1
                binding.tvDetailsQuantity.text = orderNum.toString()


            }

        }


    }

}