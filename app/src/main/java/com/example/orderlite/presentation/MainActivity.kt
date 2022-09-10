package com.example.orderlite.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.example.orderlite.R
import com.example.orderlite.databinding.ActivityMainBinding
import com.example.orderlite.presentation.units_o_m.list_units_o_m.ListUnitsOMFragment
import com.example.orderlite.presentation.product.ListGoodsFragment
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var materialToolBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        materialToolBar = binding.mainToolBar
        setSupportActionBar(materialToolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_goods -> {
                launchNewFragment(ListGoodsFragment.newInstance())
                true
            }
            R.id.action_units_of_measurement ->{
                launchNewFragment(ListUnitsOMFragment.newInstance())
                true
            }
            R.id.action_order_list ->{
                launchNewFragment(ListGoodsFragment.newInstance())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun launchNewFragment(fragment: Fragment){
        supportFragmentManager.apply {
            popBackStack()
            beginTransaction()
                .replace(R.id.mainContainerView,fragment)
                .addToBackStack(null)
                .commit()
        }

    }

}