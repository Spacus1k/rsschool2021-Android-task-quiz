package com.rsschool.quiz.domain

import com.rsschool.quiz.data.Data

class QuizInteractor {

    fun setAnswer(questionNumber: IQuestionNumber, answer: AnswerModel) {
        Data.quiz.setAnswer(questionNumber, answer)
    }

    fun getAnswerIndex(numberAnswer: Int): AnswersIndexes? = Data.quiz.getAnswerIndex(numberAnswer)

    fun getAnswerText(numberAnswer: Int): String = Data.quiz.getAnswerText(numberAnswer)

    fun removeAnswers() {
        Data.quiz.answersList.clear()
    }

    fun getGrade(): Int = Data.quiz.getGrade()
}