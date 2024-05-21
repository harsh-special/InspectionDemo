package com.example.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<in T, V> : ViewModel() {

    private val _effect = MutableSharedFlow<V>()
    val effect: SharedFlow<V> = _effect
    abstract fun processIntent(intent: T)
    protected fun setEffect(builder: () -> V) {
        viewModelScope.launch { _effect.emit(builder()) }
    }
}