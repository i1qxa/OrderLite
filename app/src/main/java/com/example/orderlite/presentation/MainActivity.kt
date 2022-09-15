package com.example.orderlite.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.example.orderlite.R
import com.example.orderlite.databinding.ActivityMainBinding
import com.example.orderlite.presentation.order.FRAGMENT_NAME_ORDER_BODY
import com.example.orderlite.presentation.order.FRAGMENT_NAME_ORDER_LIST
import com.example.orderlite.presentation.product.FRAGMENT_NAME_PRODUCTS_LIST
import com.example.orderlite.presentation.product.FRAGMENT_NAME_PRODUCT_ITEM
import com.example.orderlite.presentation.units_o_m.list_units_o_m.ListUnitsOMFragment
import com.example.orderlite.presentation.product.ListGoodsFragment
import com.example.orderlite.presentation.units_o_m.list_units_o_m.FRAGMENT_NAME_LIST_UNITS_O_M
import com.example.orderlite.presentation.units_o_m.unit_o_m.FRAGMENT_NAME_UNIT_O_M
import com.example.orderlite.presentation.units_o_m.unit_o_m.MODE_ADD
import com.example.orderlite.presentation.units_o_m.unit_o_m.UnitOMFragment
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var materialToolBar: MaterialToolbar
    private lateinit var fragmentNameInstaller:FragmentNameInstaller

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
            when(it){
                FRAGMENT_NAME_LIST_UNITS_O_M ->{
                    setFabAddVisibility(true)
                    binding.fabAdd.setOnClickListener{
                        launchNewFragment(UnitOMFragment.newInstanceAddItem(MODE_ADD))
                    }
                }
                FRAGMENT_NAME_UNIT_O_M -> {
                    setFabAddVisibility(false)
                }
                FRAGMENT_NAME_PRODUCTS_LIST -> {
                    setFabAddVisibility(true)
                    binding.fabAdd.setOnClickListener {
                        TODO("Implement newInstance for FRAGMENT_NAME_PRODUCTS_LIST")
                    }
                }
                FRAGMENT_NAME_PRODUCT_ITEM ->{
                    setFabAddVisibility(false)
                }
                FRAGMENT_NAME_ORDER_LIST -> {
                    setFabAddVisibility(true)
                    binding.fabAdd.setOnClickListener {
                        TODO("Implement newInstance for FRAGMENT_NAME_ORDER_LIST")
                    }
                }
                FRAGMENT_NAME_ORDER_BODY -> {
                    setFabAddVisibility(false)
                }
                else -> {
                    throw RuntimeException("Incorrect Fragment Name: $it")
                }
            }
        }
    }

    private fun setFabAddVisibility(visibility:Boolean){
        when(visibility){
            true -> binding.fabAdd.visibility = View.VISIBLE
            false -> binding.fabAdd.visibility = View.GONE
        }
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