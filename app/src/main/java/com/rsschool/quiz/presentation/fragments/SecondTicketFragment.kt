package com.rsschool.quiz.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.presentation.MainActivity
import com.rsschool.quiz.R
import com.rsschool.quiz.data.Data
import com.rsschool.quiz.databinding.FragmentSecondBinding
import com.rsschool.quiz.domain.AnswersIndices
import com.rsschool.quiz.domain.IQuestionNumber

class SecondTicketFragment : Fragment(), IQuestionNumber {

    private var binding: FragmentSecondBinding? = null
    override val currentQuestionNumber: Int = 2

    companion object {
        fun newInstance() = SecondTicketFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).theme.applyStyle(R.style.Theme_Quiz_Second, true)
        binding = FragmentSecondBinding.inflate(inflater, container, false)
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
                    ThirdTicketFragment.newInstance()
                )
                R.id.previous_button -> {
                    (activity as MainActivity).onBackPressed()

                }
                R.id.toolbar -> (activity as MainActivity).onBackPressed()

                R.id.option_one -> setAnswer(
                    Pair(
                        AnswersIndices.FIRST_ANS,
                        resources.getString(R.string.ques2_ans1)
                    )
                )
                R.id.option_two -> setAnswer(
                    Pair(
                        AnswersIndices.SECOND_ANS,
                        resources.getString(R.string.ques2_ans2)
                    )
                )
                R.id.option_three -> setAnswer(
                    Pair(
                        AnswersIndices.THIRD_ANS,
                        resources.getString(R.string.ques2_ans3)
                    )
                )
                R.id.option_four -> setAnswer(
                    Pair(
                        AnswersIndices.FOURTH_ANS,
                        resources.getString(R.string.ques2_ans4)
                    )
                )
                R.id.option_five -> setAnswer(
                    Pair(
                        AnswersIndices.FIFTH_ANS,
                        resources.getString(R.string.ques2_ans5)
                    )
                )
            }
        }
    }

    private fun setAnswer(answer: Pair<AnswersIndices, String>) {
        Data.quiz.setAnswer(this, answer)
        binding?.let { it.nextButton.isEnabled = true }
    }

    private fun initButtons(listener: View.OnClickListener) {
        binding?.let {
            it.nextButton.setOnClickListener(listener)
            it.nextButton.isEnabled = false
            it.toolbar.setOnClickListener(listener)
            it.previousButton.setOnClickListener(listener)
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