package com.example.devmobb.ui.defibrillator

import Defibrillator
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DefibrillatorViewModel : ViewModel() {

    private val _defibrillators = MutableLiveData<List<Defibrillator>>().apply {
        value = ArrayList()
    }
    val defibrillators: MutableLiveData<List<Defibrillator>> = _defibrillators
}