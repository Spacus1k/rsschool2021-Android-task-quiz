package com.rsschool.quiz.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.rsschool.quiz.presentation.MainActivity
import com.rsschool.quiz.R
import com.rsschool.quiz.data.Data
import com.rsschool.quiz.databinding.FragmentFifthBinding
import com.rsschool.quiz.domain.AnswersIndices
import com.rsschool.quiz.domain.IQuestionNumber


class FifthTicketFragment : Fragment(), IQuestionNumber {

    private var binding: FragmentFifthBinding? = null
    override val currentQuestionNumber: Int = 5

    companion object {
        fun newInstance() = FifthTicketFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            it.setTheme(R.style.Theme_Quiz_FifthRed)
            it.window.statusBarColor = ContextCompat.getColor(it, R.color.red_100_dark)
        }
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
            when (view.id) {
                R.id.submit_button -> {
                    (activity as MainActivity).clearBackStack()
                    (activity as MainActivity).openFragment(FinishScreenFragment.newInstance())
                }
                R.id.previous_button -> (activity as MainActivity).onBackPressed()
                R.id.toolbar -> (activity as MainActivity).onBackPressed()

                R.id.option_one -> setAnswer(
                    Pair(
                        AnswersIndices.FIRST_ANS,
                        resources.getString(R.string.ques5_ans1)
                    )
                )
                R.id.option_two -> setAnswer(
                    Pair(
                        AnswersIndices.SECOND_ANS,
                        resources.getString(R.string.ques5_ans2)
                    )
                )
                R.id.option_three -> setAnswer(
                    Pair(
                        AnswersIndices.THIRD_ANS,
                        resources.getString(R.string.ques5_ans3)
                    )
                )
                R.id.option_four -> setAnswer(
                    Pair(
                        AnswersIndices.FOURTH_ANS,
                        resources.getString(R.string.ques5_ans4)
                    )
                )
                R.id.option_five -> setAnswer(
                    Pair(
                        AnswersIndices.FIFTH_ANS,
                        resources.getString(R.string.ques5_ans5)
                    )
                )
            }
        }
    }

    private fun setAnswer(answer: Pair<AnswersIndices, String>) {
        Data.quiz.setAnswer(this, answer)
        binding?.let { it.submitButton.isEnabled = true }
    }

    private fun initButtons(listener: View.OnClickListener) {
        binding?.let {
            it.submitButton.setOnClickListener(listener)
            it.submitButton.isEnabled = false
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
            binding?.let { it.submitButton.isEnabled = true }

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