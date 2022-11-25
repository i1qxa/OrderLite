package com.example.orderlite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object FragmentNameInstaller {
    private var _currentFragmentName = MutableLiveData<Int>()
    val currentFragmentName:LiveData<Int>
    get() = _currentFragmentName
    fun setName(fragmentNameInt:Int){
        _currentFragmentName.value = fragmentNameInt
    }
}