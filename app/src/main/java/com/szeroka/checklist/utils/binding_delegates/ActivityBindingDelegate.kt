package com.szeroka.checklist.utils.binding_delegates

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityBindingDelegate<out T : ViewDataBinding>(
    private val activity: AppCompatActivity,
    @LayoutRes private val layoutRes: Int,
) : ReadOnlyProperty<AppCompatActivity, T> {

    private var binding: T? = null

    init {
        activity.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                binding = null
            }
        })
    }

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        return binding ?: kotlin.run {
            val lifecycle = activity.lifecycle
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED).not()) {
                throw IllegalStateException("Should not attempt to get bindings when Activity's views are destroyed.")
            }
            createBinding(thisRef).also {
                binding = it.apply {
                    lifecycleOwner = thisRef
                }
            }
        }
    }

    private fun createBinding(activity: AppCompatActivity): T {
        return DataBindingUtil.setContentView(activity, layoutRes)
    }
}

fun <T : ViewDataBinding> AppCompatActivity.dataBinding(@LayoutRes layoutRes: Int) =
    ActivityBindingDelegate<T>(this, layoutRes)