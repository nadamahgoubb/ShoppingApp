package com.example.myapplication.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.fragment.*
import com.example.myapplication.ui.fragment.home.HomeFragment
import com.example.myapplication.utils.CurvedBottomNavigationView
import com.example.myapplication.utils.FragmentUtil
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var homeFragment: HomeFragment
    private var parentView: ConstraintLayout? = null
private lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
      binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        /*val curvedBottomNavigationView: CurvedBottomNavigationView =
                findViewById(R.id.curve_bottom_navigation_view)*/
        //`      curvedBottomNavigationView.getChildAt(0)
        //     curvedBottomNavigationView.selectedItemId = R.id.navigation_home
       /* homeFragment = HomeFragment()
        FragmentUtil.replaceFragment(
            homeFragment,
            R.id.FragmentLoad,
            supportFragmentManager.beginTransaction()
        )*/
       val curvedBottomNavigationView: CurvedBottomNavigationView =
            binding.curveBottomNavigationView


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        curvedBottomNavigationView.setupWithNavController(navController)
//        curvedBottomNavigationView.inflateMenu(R.menu.bottom_menu)
        fab_button.setOnClickListener(this)
        /*  curvedBottomNavigationView.setOnItemSelectedListener {
              when (it.itemId) {
                  R.id.navigation_category -> {
                      val categoryFragment = CategoryFragment()
                      FragmentUtil.replaceFragment(
                          categoryFragment,
                          R.id.FragmentLoad,
                          supportFragmentManager.beginTransaction()
                      )

                  }
                  R.id.navigation_home -> {
                      FragmentUtil.replaceFragment(
                          homeFragment,
                          R.id.FragmentLoad,
                          supportFragmentManager.beginTransaction()
                      )

                  }
                  R.id.navigation_cart -> {
                      val cartFragment = CartFragment()
                      FragmentUtil.replaceFragment(
                          cartFragment,
                          R.id.FragmentLoad,
                          supportFragmentManager.beginTransaction()
                      )

                  }
                  R.id.navigation_account -> {
                      val accountFragment = AccountFragment()
                      FragmentUtil.replaceFragment(
                          accountFragment,
                          R.id.FragmentLoad,
                          supportFragmentManager.beginTransaction()
                      )
                  }
                  R.id.navigation_info -> {
                      val infoFragment = InfoFragment()
                      FragmentUtil.replaceFragment(
                          infoFragment,
                          R.id.FragmentLoad,
                          supportFragmentManager.beginTransaction()
                      )
                  }

              }

              true

          }
  */
        val fab = findViewById<FloatingActionButton>(R.id.fab_button)
        fab.setOnClickListener(this)
        parentView = findViewById(R.id.view_parentView)

    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            homeFragment = HomeFragment()
            if (p0.id == fab_button.id) {
                FragmentUtil.replaceFragment(
                    homeFragment,
                    R.id.FragmentLoad,
                    supportFragmentManager.beginTransaction()
                )
            }
        }
    }
}