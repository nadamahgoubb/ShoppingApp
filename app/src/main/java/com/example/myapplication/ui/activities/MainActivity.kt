package com.example.myapplication.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.fragment.home.HomeFragment
import com.example.myapplication.utils.CurvedBottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var curvedBottomNavigationView: CurvedBottomNavigationView
    private lateinit var homeFragment: HomeFragment
    private var parentView: ConstraintLayout? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
        initNav()

    }

    private fun initNav() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        curvedBottomNavigationView.setupWithNavController(navController)
    }

    private fun initView() {
        curvedBottomNavigationView = binding.curveBottomNavigationView
        val fab = binding.fabButton
        fab_button.setOnClickListener(this)
        parentView = binding.viewParentView
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            homeFragment = HomeFragment()
            if (p0.id == fab_button.id) {

                val navController: NavController =
                    Navigation.findNavController(this@MainActivity, R.id.navHostFragment)
                navController.navigateUp()
                navController.navigate(R.id.action_to_homeFragment)


            }
        }
    }
}