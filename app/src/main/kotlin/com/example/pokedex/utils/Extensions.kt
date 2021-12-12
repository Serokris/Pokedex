package com.example.pokedex.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.pokedex.PokedexApplication
import com.example.pokedex.di.component.AppComponent
import java.util.*

fun <T> LiveData<T>.observeOnce(observer: (T) -> Unit) {
    observeForever(object : Observer<T> {
        override fun onChanged(value: T) {
            removeObserver(this)
            observer(value)
        }
    })
}

fun String.capitalized(): String {
    return replaceFirstChar { char ->
        if (char.isLowerCase())
            char.titlecase(Locale.getDefault())
        else char.toString()
    }
}

val Fragment.appComponent: AppComponent
    get() = (requireContext().applicationContext as PokedexApplication).appComponent