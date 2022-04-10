package com.example.myapplication.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

object FragmentUtil {
    fun replaceFragment(fragment: Fragment?, id: Int, fragmentTransaction: FragmentTransaction) {
        fragmentTransaction.replace(id, fragment!!)
        fragmentTransaction.addToBackStack(fragment.javaClass.name)
        fragmentTransaction.commit()

    }
}