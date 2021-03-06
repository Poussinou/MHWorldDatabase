package com.gatheringhallstudios.mhworlddatabase.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat

// A collection of extension functions used by the app.
// Only those that could reasonably belong to a separate library should be here.
// Anything with stronger coupling (like Fragment.getRouter()) should not be here

/**
 * Adds a bundle to a fragment and then returns the fragment.
 * A lambda is used to set values to the bundle
 */
fun <T: androidx.fragment.app.Fragment> T.applyArguments(block: Bundle.() -> Unit): T {
    val bundle = Bundle()
    bundle.block()
    this.arguments = bundle
    return this
}

/**
 * Merges multiple live data into a single result
 */
fun <T> mergeLiveDataMany(vararg data: LiveData<*>, transform: (List<*>) -> T): LiveData<T> {
    return MediatorLiveData<T>().apply {
        var encountered = 0
        val results = MutableList<Any?>(data.size) { null }

        for ((idx, subData) in data.withIndex()) {
            addSource(subData) {
                results[idx] = it
                encountered++

                if (encountered == data.size) {
                    this.value = transform(results)
                }
            }
        }
    }
}

fun <T, A, B> mergeLiveData(a: LiveData<A>, b: LiveData<B>, transform: (Pair<A, B>) -> T): LiveData<T> {
    return mergeLiveDataMany(a, b) {
        @Suppress("UNCHECKED_CAST")
        val result = Pair(it[0] as A, it[1] as B)
        transform(result)
    }
}

fun <T, A, B, C> mergeLiveData(
        a: LiveData<A>,
        b: LiveData<B>,
        c: LiveData<C>,
        transform: (Triple<A, B, C>) -> T): LiveData<T> {
    return mergeLiveDataMany(a, b, c) {
        @Suppress("UNCHECKED_CAST")
        val result = Triple(it[0] as A, it[1] as B, it[2] as C)
        transform(result)
    }
}

/**
 * Extension: Retrieves a drawable associated with a resource id
 * via ContextCompat using the called context.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Context.getDrawableCompat(@DrawableRes id: Int): Drawable? {
    return AppCompatResources.getDrawable(this, id)
}

/**
 * Extension: Retrieves a color associated with a resource id
 * via ContextCompat using the called context.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Context.getColorCompat(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}

/**
 * Extension: Converts an int to DP and from DP to int
 */
val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()