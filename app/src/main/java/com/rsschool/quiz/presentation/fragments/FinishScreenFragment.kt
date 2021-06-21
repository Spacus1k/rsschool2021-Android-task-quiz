package com.rsschool.quiz.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentFinishScreenBinding
import com.rsschool.quiz.domain.QuizInteractor
import com.rsschool.quiz.presentation.QuizActivity
import com.rsschool.quiz.presentation.utils.*

class FinishScreenFragment : Fragment() {
    companion object {
        fun newInstance() = FinishScreenFragment()
    }

    private var binding: FragmentFinishScreenBinding? = null
    private var activity: QuizActivity? = null
    private val quizInteractor = QuizInteractor()

    override fun onAttach(context: Context) {
        activity = getActivity() as QuizActivity
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.apply { setTheme(this, R.style.Theme_Quiz_SecondYellow, R.color.black) }
        binding = FragmentFinishScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            resultText.text =
                getResult(quizInteractor)
        }
        initButtons(iniListener())
    }

    private fun iniListener(): View.OnClickListener {
        return View.OnClickListener { view ->
            activity?.let {
                when (view.id) {
                    R.id.reset_button -> resetAction(it, quizInteractor)
                    R.id.exit_button -> exitAction(it, quizInteractor)
                    R.id.share_button -> sendMessageAction(activity, getReport(it, quizInteractor))
                }
            }
        }
    }

    private fun initButtons(listener: View.OnClickListener) {
        binding?.apply {
            resetButton.setOnClickListener(listener)
            exitButton.setOnClickListener(listener)
            shareButton.setOnClickListener(listener)
        }
    }
}