package com.rsschool.quiz.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentFifthBinding
import com.rsschool.quiz.domain.AnswerModel
import com.rsschool.quiz.domain.AnswersIndexes
import com.rsschool.quiz.domain.IQuestionNumber
import com.rsschool.quiz.domain.QuizInteractor
import com.rsschool.quiz.presentation.QuizActivity
import com.rsschool.quiz.presentation.utils.clearBackStack
import com.rsschool.quiz.presentation.utils.openFragment
import com.rsschool.quiz.presentation.utils.setTheme


class FifthTicketFragment : Fragment(), IQuestionNumber {

    companion object {
        fun newInstance() = FifthTicketFragment()
    }

    override val currentQuestionNumber: Int = 5
    private var activity: QuizActivity? = null
    private var binding: FragmentFifthBinding? = null
    private val quizInteractor = QuizInteractor()

    override fun onAttach(context: Context) {
        activity = getActivity() as QuizActivity
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let { setTheme(it, R.style.Theme_Quiz_FifthRed, R.color.red_100_dark) }
        binding = FragmentFifthBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons(initClickListener())
        checkPreviousAnswer()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun initClickListener(): View.OnClickListener {
        return View.OnClickListener { view ->
            activity?.let {
                when (view.id) {
                    R.id.submit_button -> {
                        clearBackStack(it.supportFragmentManager)
                        openFragment(
                            it.supportFragmentManager,
                            FinishScreenFragment.newInstance()
                        )
                    }
                    R.id.previous_button -> it.onBackPressed()
                    R.id.toolbar -> it.onBackPressed()

                    R.id.option_one -> setAnswer(
                        AnswerModel(
                            AnswersIndexes.FIRST,
                            resources.getString(R.string.ques5_ans1)
                        )
                    )
                    R.id.option_two -> setAnswer(
                        AnswerModel(
                            AnswersIndexes.SECOND,
                            resources.getString(R.string.ques5_ans2)
                        )
                    )
                    R.id.option_three -> setAnswer(
                        AnswerModel(
                            AnswersIndexes.THIRD,
                            resources.getString(R.string.ques5_ans3)
                        )
                    )
                    R.id.option_four -> setAnswer(
                        AnswerModel(
                            AnswersIndexes.FOURTH,
                            resources.getString(R.string.ques5_ans4)
                        )
                    )
                    R.id.option_five -> setAnswer(
                        AnswerModel(
                            AnswersIndexes.FIFTH,
                            resources.getString(R.string.ques5_ans5)
                        )
                    )
                }
            }
        }
    }

    private fun setAnswer(answer: AnswerModel) {
        quizInteractor.setAnswer(this, answer)
        binding?.let { it.submitButton.isEnabled = true }
    }

    private fun initButtons(listener: View.OnClickListener) {
        binding?.apply {
            submitButton.setOnClickListener(listener)
            submitButton.isEnabled = false
            toolbar.setOnClickListener(listener)
            previousButton.setOnClickListener(listener)
            optionOne.setOnClickListener(listener)
            optionTwo.setOnClickListener(listener)
            optionThree.setOnClickListener(listener)
            optionFour.setOnClickListener(listener)
            optionFive.setOnClickListener(listener)
        }
    }

    private fun checkPreviousAnswer() {
        binding?.apply {
            if (quizInteractor.getAnswerIndex(this@FifthTicketFragment.currentQuestionNumber) != null) {
                this.submitButton.isEnabled = true

                when (quizInteractor.getAnswerIndex(this@FifthTicketFragment.currentQuestionNumber)) {
                    AnswersIndexes.FIRST -> this.optionOne.isChecked = true
                    AnswersIndexes.SECOND -> this.optionTwo.isChecked = true
                    AnswersIndexes.THIRD -> this.optionThree.isChecked = true
                    AnswersIndexes.FOURTH -> this.optionFour.isChecked = true
                    AnswersIndexes.FIFTH -> this.optionFive.isChecked = true
                }
            }
        }
    }
}