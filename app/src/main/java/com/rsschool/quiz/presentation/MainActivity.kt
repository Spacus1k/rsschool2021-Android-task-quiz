package com.rsschool.quiz.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.rsschool.quiz.R
import com.rsschool.quiz.presentation.fragments.*
import com.rsschool.quiz.presentation.utils.replaceFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment(FirstTicketFragment.newInstance())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    fun openFragment(fragment: Fragment) {
        replaceFragment(
            supportFragmentManager,
            R.id.fragment_container,
            fragment
        )
    }

    fun clearBackStack() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            val entry: FragmentManager.BackStackEntry =
                supportFragmentManager.getBackStackEntryAt(0)
            supportFragmentManager.popBackStack(entry.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun onBackPressed() = if (supportFragmentManager.backStackEntryCount == 1) {
        this.finish()
    } else {
        super.onBackPressed()
    }
}