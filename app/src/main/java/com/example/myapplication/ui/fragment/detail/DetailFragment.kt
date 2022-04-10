package com.example.myapplication.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.data.entitiy.DataX
import com.example.myapplication.databinding.FragmentDetailBinding

class DetailFragment constructor(var product: DataX) : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var orderNum = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        initData()
        return binding.root
    }

    private fun initData() {
        binding.tvDetailsDescription.text = product.description
        binding.tvDetailsName.text = product.name
        binding.tvDetailsPrice.text = product.price.toString()
        binding.tvDetailsQuantity.text = orderNum.toString()
    //    bindinfgggggggglg.

    }

}