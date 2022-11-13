package com.example.orderlite.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.example.orderlite.R
import com.example.orderlite.databinding.ActivityMainBinding
import com.example.orderlite.presentation.order.ListOrderFragment
import com.example.orderlite.presentation.product.list_product_items.ListProductsFragment
import com.example.orderlite.presentation.product.list_product_items.MODE_LIST_VIEW
import com.example.orderlite.presentation.units_o_m.list_units_o_m.ListUnitsOMFragment
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
        observeFragmentName()

    }

    private fun observeFragmentName(){
        FragmentNameInstaller.currentFragmentName.observe(this){
            binding.fragmentName.text = it
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_goods -> {
                launchNewFragment(ListProductsFragment.newInstance(MODE_LIST_VIEW,-1))
                true
            }
            R.id.action_units_of_measurement ->{
                launchNewFragment(ListUnitsOMFragment.newInstance())
                true
            }
            R.id.action_order_list ->{
                launchNewFragment(ListOrderFragment.newInstance())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun launchNewFragment(fragment: Fragment){
        supportFragmentManager.apply {
            beginTransaction()
                .replace(R.id.mainContainerView,fragment)
                .addToBackStack(null)
                .commit()
        }

    }

}