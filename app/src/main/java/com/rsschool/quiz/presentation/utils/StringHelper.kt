package com.rsschool.quiz.presentation.utils

import android.app.Activity
import com.rsschool.quiz.R
import com.rsschool.quiz.domain.Quiz
import com.rsschool.quiz.domain.QuizInteractor

fun getResult(quizInteractor: QuizInteractor): String =
    "Your result: ${quizInteractor.getGrade()}/${Quiz.NUMBER_OF_QUESTIONS}"

fun getReport(activity: Activity, quizInteractor: QuizInteractor, ): String {
    return activity.let {
        """
        Quiz results
            
        ${getResult(quizInteractor)}
        
        1) ${it.resources.getString(R.string.ques1)}
        Your answer: ${quizInteractor.getAnswerText(1)}
        
         2) ${it.getString(R.string.ques2)}
         Your answer: ${quizInteractor.getAnswerText(2)}
        
         3) ${it.resources.getString(R.string.ques3)}
        Your answer: ${quizInteractor.getAnswerText(3)}
        
         4) ${it.resources.getString(R.string.ques4)}
        Your answer: ${quizInteractor.getAnswerText(4)}
        
         5) ${it.resources.getString(R.string.ques5)}
        Your answer: ${quizInteractor.getAnswerText(5)}
    """.trimIndent()
    }
}