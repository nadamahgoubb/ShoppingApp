package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.ui.fragment.HomeFragment
import com.example.myapplication.utils.CurvedBottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var homeFragment: HomeFragment
    private var parentView: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val curvedBottomNavigationView: CurvedBottomNavigationView =
            findViewById(R.id.curve_bottom_navigation_view)
        curvedBottomNavigationView.inflateMenu(R.menu.bottom_menu)
        curvedBottomNavigationView.getChildAt(0)
        curvedBottomNavigationView.selectedItemId = R.id.navigation_home
        homeFragment = HomeFragment()
        replaceFragment(
            homeFragment,
            R.id.FragmentLoad,
            supportFragmentManager.beginTransaction()
        )
        curvedBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_category -> {
                    /*val categoryFragment = CategoryFragment()
                    replaceFragment(
                        categoryFragment,
                        R.id.FragmentLoad,
                        supportFragmentManager.beginTransaction()
                    )
*/
                }
                R.id.navigation_cart -> {
                    /*  val cartFragment = CartFragment()
                      replaceFragment(
                          cartFragment,
                          R.id.FragmentLoad,
                          supportFragmentManager.beginTransaction()
                      )
  */
                }
                R.id.navigation_account -> {
                    /*    val accountFragment = AccountFragment()
                        replaceFragment(
                            accountFragment,
                            R.id.FragmentLoad,
                            supportFragmentManager.beginTransaction()
                        )*/
                }
                R.id.navigation_info -> {
                    /* val infoFragment = InfoFragment()
                     replaceFragment(
                         infoFragment,
                         R.id.FragmentLoad,
                         supportFragmentManager.beginTransaction()
                     )*/
                }

            }

            true

        }

        val fab = findViewById<FloatingActionButton>(R.id.fab_button)
        fab.setOnClickListener(this)
        parentView = findViewById(R.id.view_parentView)

    }
    fun replaceFragment(fragment: Fragment?, id: Int, fragmentTransaction: FragmentTransaction) {
        fragmentTransaction.replace(id, fragment!!)
        fragmentTransaction.addToBackStack(fragment.javaClass.name)
        fragmentTransaction.commit()

    }

    override fun onClick(p0: View?) {
    }
}