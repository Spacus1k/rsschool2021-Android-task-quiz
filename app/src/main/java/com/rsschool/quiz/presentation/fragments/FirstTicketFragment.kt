package com.rsschool.quiz.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.R
import com.rsschool.quiz.domain.AnswersIndexes
import com.rsschool.quiz.databinding.FragmentFirstBinding
import com.rsschool.quiz.domain.AnswerModel
import com.rsschool.quiz.domain.IQuestionNumber
import com.rsschool.quiz.domain.QuizInteractor
import com.rsschool.quiz.presentation.QuizActivity
import com.rsschool.quiz.presentation.utils.openFragment

class FirstTicketFragment : Fragment(), IQuestionNumber {

    companion object {
        fun newInstance() = FirstTicketFragment()
    }

    override val currentQuestionNumber: Int = 1
    private var activity: QuizActivity? = null
    private var binding: FragmentFirstBinding? = null
    private val quizInteractor = QuizInteractor()

    override fun onAttach(context: Context) {
        activity = getActivity() as QuizActivity
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
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
            when (view.id) {
                R.id.next_button -> activity?.supportFragmentManager?.let { manager ->
                    openFragment(
                        manager,
                        SecondTicketFragment.newInstance()
                    )
                }
                R.id.option_one -> setAnswer(
                    AnswerModel(
                        AnswersIndexes.FIRST,
                        resources.getString(R.string.ques1_ans1)
                    )
                )
                R.id.option_two -> setAnswer(
                    AnswerModel(
                        AnswersIndexes.SECOND,
                        resources.getString(R.string.ques1_ans2)
                    )
                )
                R.id.option_three -> setAnswer(
                    AnswerModel(
                        AnswersIndexes.THIRD,
                        resources.getString(R.string.ques1_ans3)
                    )
                )
                R.id.option_four -> setAnswer(
                    AnswerModel(
                        AnswersIndexes.FOURTH,
                        resources.getString(R.string.ques1_ans4)
                    )
                )
                R.id.option_five -> setAnswer(
                    AnswerModel(
                        AnswersIndexes.FIFTH,
                        resources.getString(R.string.ques1_ans5)
                    )
                )
            }
        }
    }

    private fun setAnswer(answer: AnswerModel) {
        quizInteractor.setAnswer(this, answer)
        binding?.let {
            it.nextButton.isEnabled = true
        }
    }

    private fun initButtons(listener: View.OnClickListener) {
        binding?.apply {
            nextButton.setOnClickListener(listener)
            nextButton.isEnabled = false
            previousButton.isEnabled = false
            optionOne.setOnClickListener(listener)
            optionTwo.setOnClickListener(listener)
            optionThree.setOnClickListener(listener)
            optionFour.setOnClickListener(listener)
            optionFive.setOnClickListener(listener)
        }
    }

    private fun checkPreviousAnswer() {
        binding?.apply {
            if (quizInteractor.getAnswerIndex(this@FirstTicketFragment.currentQuestionNumber) != null) {
                this.nextButton.isEnabled = true

                when (quizInteractor.getAnswerIndex(this@FirstTicketFragment.currentQuestionNumber)) {
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