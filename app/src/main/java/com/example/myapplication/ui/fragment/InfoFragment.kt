package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentInfoBinding
import com.example.myapplication.ui.fragment.wishList.WishListFragment
import com.example.myapplication.utils.FragmentUtil

class InfoFragment : Fragment(R.layout.fragment_info) {


    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.fragment_info, container, false)
       binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_info,
            container,
            false)
      //  binding= DataBindingUtil.inflate(inflater,R.layout.)
/**/
        initViews()

        return binding.root
    }

    private fun initViews() {
        binding.txtWishList.setOnClickListener(View.OnClickListener {
           // findNavController().navigate(R.id.action_infoFragment_to_wishListFragment)
         var fragment = WishListFragment()
            activity?.supportFragmentManager?.let { it1 ->
                FragmentUtil.replaceFragment(
                    fragment,
                    R.id.FragmentLoad,
                    it1.beginTransaction()
                )
            }
        })
    }


}