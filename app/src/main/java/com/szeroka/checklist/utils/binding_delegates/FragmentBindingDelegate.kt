package com.szeroka.checklist.utils.binding_delegates

import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentBindingDelegate<out T : ViewDataBinding>(
    private val fragment: Fragment,
    @LayoutRes private val layoutRes: Int,
) : ReadOnlyProperty<Fragment, T> {

    private var binding: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            val viewLifecycleOwnerLiveDataObserver =
                Observer<LifecycleOwner?> {
                    val viewLifecycleOwner = it ?: return@Observer

                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            binding = null
                        }
                    })
                }

            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observeForever(viewLifecycleOwnerLiveDataObserver)
            }

            override fun onDestroy(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.removeObserver(viewLifecycleOwnerLiveDataObserver)
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return binding ?: kotlin.run {
            val lifecycle = fragment.viewLifecycleOwner.lifecycle
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED).not()) {
                throw IllegalStateException("Should not attempt to get bindings when Fragment's views are destroyed.")
            }
            createBinding(thisRef).also {
                binding = it.apply {
                    lifecycleOwner = thisRef
                }
            }
        }
    }

    private fun createBinding(fragment: Fragment): T {
        return DataBindingUtil.inflate(
            LayoutInflater.from(fragment.requireContext()), layoutRes,
            null, true
        )
    }
}

fun <T : ViewDataBinding> Fragment.dataBinding(@LayoutRes layoutRes: Int) = FragmentBindingDelegate<T>(this, layoutRes)