package com.rsschool.quiz.data

import com.rsschool.quiz.domain.AnswersIndices
import com.rsschool.quiz.domain.Quiz

class Data {
    companion object {
        var quiz = Quiz(
            arrayOf(
                AnswersIndices.FIRST_ANS,
                AnswersIndices.SECOND_ANS,
                AnswersIndices.THIRD_ANS,
                AnswersIndices.FOURTH_ANS,
                AnswersIndices.FIFTH_ANS
            )
        )
    }
}

