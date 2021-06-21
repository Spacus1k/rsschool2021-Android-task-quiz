package com.rsschool.quiz.data

import com.rsschool.quiz.domain.AnswersIndexes
import com.rsschool.quiz.domain.Quiz

class Data {
    companion object {
        val quiz = Quiz(
            arrayOf(
                AnswersIndexes.FOURTH,
                AnswersIndexes.FIRST,
                AnswersIndexes.SECOND,
                AnswersIndexes.THIRD,
                AnswersIndexes.FOURTH
            )
        )
    }
}

