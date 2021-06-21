package com.rsschool.quiz.presentation.utils

import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.rsschool.quiz.R


fun addFragment(
    fragmentManager: FragmentManager,
    containerViewId: Int,
    fragment: Fragment,
    isAddToBackStack: Boolean = true
) {
    val transaction = fragmentManager
        .beginTransaction()
        .add(containerViewId, fragment, fragment.tag)

    if (isAddToBackStack) transaction.addToBackStack(fragment.tag)

    transaction.commit()
}

fun clearBackStack(fragmentManager: FragmentManager) {
    if (fragmentManager.backStackEntryCount > 0) {
        val entry: FragmentManager.BackStackEntry =
            fragmentManager.getBackStackEntryAt(0)
        fragmentManager.popBackStack(entry.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}

fun openFragment(fragmentManager: FragmentManager, fragment: Fragment) {
    addFragment(
        fragmentManager,
        R.id.fragment_container,
        fragment
    )
}

fun setTheme(activity: FragmentActivity, @StyleRes theme: Int, @ColorRes statusBarColor: Int) {
    activity.apply {
        setTheme(theme)
        window.statusBarColor = ContextCompat.getColor(this, statusBarColor)
    }
}