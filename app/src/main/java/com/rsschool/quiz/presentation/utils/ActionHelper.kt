package com.rsschool.quiz.presentation.utils

import android.content.Intent
import com.rsschool.quiz.domain.QuizInteractor
import com.rsschool.quiz.presentation.QuizActivity
import com.rsschool.quiz.presentation.fragments.FirstTicketFragment

fun resetAction(activity: QuizActivity, interactor: QuizInteractor) {
    interactor.removeAnswers()
    openFragment(
        activity.supportFragmentManager,
        FirstTicketFragment.newInstance()
    )
}

 fun exitAction(activity: QuizActivity, interactor: QuizInteractor) {
    interactor.removeAnswers()
    activity.finish()
}

fun sendMessageAction(activity: QuizActivity?, message: String) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, message)
        type = "text/plain"
    }
   activity?.startActivity(intent)
}