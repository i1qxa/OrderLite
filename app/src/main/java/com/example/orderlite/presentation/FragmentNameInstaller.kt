package com.example.orderlite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object FragmentNameInstaller {
    private var _currentFragmentName = MutableLiveData<String>()
    val currentFragmentName:LiveData<String>
    get() = _currentFragmentName
    fun setName(name:String){
        _currentFragmentName.value = name
    }
}