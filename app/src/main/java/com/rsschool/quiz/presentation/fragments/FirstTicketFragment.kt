package com.rsschool.quiz.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.R
import com.rsschool.quiz.domain.AnswersIndices
import com.rsschool.quiz.data.Data
import com.rsschool.quiz.databinding.FragmentFirstBinding
import com.rsschool.quiz.domain.IQuestionNumber
import com.rsschool.quiz.presentation.MainActivity

class FirstTicketFragment : Fragment(), IQuestionNumber {

    private var binding: FragmentFirstBinding? = null
    override val currentQuestionNumber: Int = 1

    companion object {
        fun newInstance() = FirstTicketFragment()
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
                R.id.next_button -> (activity as MainActivity).openFragment(
                    SecondTicketFragment.newInstance()
                )
                R.id.option_one -> setAnswer(
                    Pair(
                        AnswersIndices.FIRST_ANS,
                        resources.getString(R.string.ques1_ans1)
                    )
                )
                R.id.option_two -> setAnswer(
                    Pair(
                        AnswersIndices.SECOND_ANS,
                        resources.getString(R.string.ques1_ans2)
                    )
                )
                R.id.option_three -> setAnswer(
                    Pair(
                        AnswersIndices.THIRD_ANS,
                        resources.getString(R.string.ques1_ans3)
                    )
                )
                R.id.option_four -> setAnswer(
                    Pair(
                        AnswersIndices.FOURTH_ANS,
                        resources.getString(R.string.ques1_ans4)
                    )
                )
                R.id.option_five -> setAnswer(
                    Pair(
                        AnswersIndices.FIFTH_ANS,
                        resources.getString(R.string.ques1_ans5)
                    )
                )
            }
        }
    }

    private fun setAnswer(answer: Pair<AnswersIndices, String>) {
        Data.quiz.setAnswer(this, answer)
        binding?.let {
            it.nextButton.isEnabled = true
        }
    }

    private fun initButtons(listener: View.OnClickListener) {
        binding?.let {
            it.nextButton.setOnClickListener(listener)
            it.nextButton.isEnabled = false
            it.previousButton.isEnabled = false
            it.optionOne.setOnClickListener(listener)
            it.optionTwo.setOnClickListener(listener)
            it.optionThree.setOnClickListener(listener)
            it.optionFour.setOnClickListener(listener)
            it.optionFive.setOnClickListener(listener)
        }
    }

    private fun checkPreviousAnswer() {
        if (Data.quiz.getAnswerIndex(this.currentQuestionNumber) != null) {
            binding?.let { it.nextButton.isEnabled = true }

            when (Data.quiz.getAnswerIndex(this.currentQuestionNumber)) {
                AnswersIndices.FIRST_ANS -> binding?.let { it.optionOne.isChecked = true }
                AnswersIndices.SECOND_ANS -> binding?.let { it.optionTwo.isChecked = true }
                AnswersIndices.THIRD_ANS -> binding?.let { it.optionThree.isChecked = true }
                AnswersIndices.FOURTH_ANS -> binding?.let { it.optionFour.isChecked = true }
                AnswersIndices.FIFTH_ANS -> binding?.let { it.optionFive.isChecked = true }
            }
        }
    }
}