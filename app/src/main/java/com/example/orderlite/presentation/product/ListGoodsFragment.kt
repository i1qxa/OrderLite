package com.example.orderlite.presentation.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.orderlite.R
import com.example.orderlite.presentation.FragmentNameInstaller

const val FRAGMENT_NAME_GOODS_LIST = "Goods List"

class ListGoodsFragment : Fragment() {

    private lateinit var fragmentNameInstaller:FragmentNameInstaller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_list_goods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNameInstaller = FragmentNameInstaller
        fragmentNameInstaller.setName(FRAGMENT_NAME_GOODS_LIST)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListGoodsFragment()
    }
}