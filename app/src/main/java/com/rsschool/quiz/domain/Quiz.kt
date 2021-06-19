package com.rsschool.quiz.domain

class Quiz(private val rightAnswers: Array<AnswersIndices>) {

    companion object {
        const val NUMBER_OF_QUESTIONS = 5
    }

    private var answersList = mutableListOf<Pair<AnswersIndices, String>>()

    fun setAnswer(questionNumber: IQuestionNumber, answer: Pair<AnswersIndices, String>) {
        if (questionNumber.currentQuestionNumber - 1 >= answersList.size) {
            answersList.add(answer)
        } else {
            answersList[questionNumber.currentQuestionNumber - 1] = answer
        }
    }

    fun getAnswerIndex(numberAnswer: Int): AnswersIndices? {
        return if (numberAnswer - 1 >= answersList.size)
            null
        else
            answersList[numberAnswer - 1].first
    }

    fun getAnswerText(numberAnswer: Int): String {
        return if (numberAnswer - 1 >= answersList.size)
            ""
        else
            answersList[numberAnswer - 1].second
    }

    fun removeAnswers() {
        answersList = mutableListOf()
    }

    fun getGrade(): Int {
        var counter = 0
        answersList.forEachIndexed { index, _ ->
            if (answersList[index].first == rightAnswers[index]) {
                counter++
            }
        }
        return counter
    }
}