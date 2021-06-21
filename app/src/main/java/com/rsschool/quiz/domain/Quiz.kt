package com.rsschool.quiz.domain

class Quiz(private val rightAnswers: Array<AnswersIndexes>) {

    companion object {
        const val NUMBER_OF_QUESTIONS = 5
    }

    var answersList = mutableListOf<AnswerModel>()

    fun setAnswer(questionNumber: IQuestionNumber, answer: AnswerModel) {
        if (questionNumber.currentQuestionNumber - 1 >= answersList.size) {
            answersList.add(answer)
        } else {
            answersList[questionNumber.currentQuestionNumber - 1] = answer
        }
    }

    fun getAnswerIndex(numberAnswer: Int): AnswersIndexes? {
        return if (numberAnswer - 1 < answersList.size)
            answersList[numberAnswer - 1].answerIndex
        else
            null
    }

    fun getAnswerText(numberAnswer: Int): String {
        return if (numberAnswer - 1 < answersList.size)
            answersList[numberAnswer - 1].answerText
        else
            ""
    }

    fun getGrade(): Int {
        var counter = 0
        answersList.forEachIndexed { index, item ->
            if (item.answerIndex == rightAnswers[index]) {
                counter++
            }
        }
        return counter
    }
}