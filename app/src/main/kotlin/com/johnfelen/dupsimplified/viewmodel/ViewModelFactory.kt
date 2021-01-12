package com.johnfelen.dupsimplified.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.instanceOrNull

class ViewModelFactory(private val kodein: Kodein): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T =
        kodein.direct.instanceOrNull<ViewModel>(modelClass.simpleName) as T? ?: modelClass.newInstance()
}

inline fun <reified T: ViewModel> Kodein.Builder.bindViewModel(overrides: Boolean? = null): Kodein.Builder.TypeBinder<T> =
    bind<T>(T::class.java.simpleName, overrides)

inline fun <reified VM: ViewModel, T> T.viewModel(): Lazy<VM> where T: KodeinAware, T: AppCompatActivity = lazy {
    ViewModelProvider(this, direct.instance()).get(VM::class.java)
}

inline fun <reified VM: ViewModel, T> T.viewModel(): Lazy<VM> where T: KodeinAware, T: Fragment = lazy {
    ViewModelProvider(this, direct.instance()).get(VM::class.java)
}

inline fun <reified VM: ViewModel, T> T.sharedViewModel(): Lazy<VM> where T: KodeinAware, T: Fragment = lazy {
    ViewModelProvider(requireActivity()).get(VM::class.java)
}