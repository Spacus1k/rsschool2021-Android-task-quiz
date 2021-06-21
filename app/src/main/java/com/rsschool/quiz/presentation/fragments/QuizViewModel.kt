package com.rsschool.quiz.presentation.fragments

import androidx.lifecycle.ViewModel
import com.rsschool.quiz.domain.QuizInteractor

class QuizViewModel(private val quizInteractor: QuizInteractor) : ViewModel() {

    fun clearAnswers(){
        quizInteractor.removeAnswers()
    }
}