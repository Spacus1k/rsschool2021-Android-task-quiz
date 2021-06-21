package com.rsschool.quiz.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.rsschool.quiz.R
import com.rsschool.quiz.domain.QuizInteractor
import com.rsschool.quiz.presentation.fragments.FirstTicketFragment
import com.rsschool.quiz.presentation.fragments.QuizViewModel
import com.rsschool.quiz.presentation.utils.openFragment

class QuizActivity : AppCompatActivity() {

    private val quizViewModel = QuizViewModel(
        quizInteractor = QuizInteractor()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment(supportFragmentManager, FirstTicketFragment.newInstance())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onBackPressed() = if (supportFragmentManager.fragments.size == 1) {
        finishAndRemoveTask()
        quizViewModel.clearAnswers()
    } else {
        super.onBackPressed()
    }
}