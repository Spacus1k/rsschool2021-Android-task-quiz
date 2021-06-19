package com.rsschool.quiz.presentation.fragments

import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.R
import com.rsschool.quiz.data.Data
import com.rsschool.quiz.databinding.FragmentFinishScreenBinding
import com.rsschool.quiz.domain.Quiz
import com.rsschool.quiz.presentation.MainActivity

class FinishScreenFragment : Fragment() {

    private var binding: FragmentFinishScreenBinding? = null

    companion object {
        fun newInstance() = FinishScreenFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFinishScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            it.resultText.text =
                getResult()
        }
        initButtons(iniListener())
    }

    private fun iniListener(): View.OnClickListener {
        return View.OnClickListener { view ->
            when (view.id) {
                R.id.reset_button -> {
                    Data.quiz.removeAnswers()
                    (activity as MainActivity).openFragment(FirstTicketFragment.newInstance())
                }
                R.id.exit_button -> {
                    Data.quiz.removeAnswers()
                    (activity as MainActivity).finish()
                }
                R.id.share_button -> sendMessageAction(getReport())
            }
        }
    }

    private fun initButtons(listener: View.OnClickListener) {
        binding?.let {
            it.resetButton.setOnClickListener(listener)
            it.exitButton.setOnClickListener(listener)
            it.shareButton.setOnClickListener(listener)
        }
    }

    private fun sendMessageAction(message: String) {
        val intent = Intent().apply {
            action = ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }
        startActivity(intent)
    }

    private fun getResult(): String =
        "Your result: ${Data.quiz.getGrade()}/${Quiz.NUMBER_OF_QUESTIONS}"

    private fun getReport(): String {
        return """
        Quiz results
            
        ${getResult()}
        
        1) ${resources.getString(R.string.ques1)}
        Your answer: ${Data.quiz.getAnswerText(1)}
        
         2) ${resources.getString(R.string.ques2)}
         Your answer: ${Data.quiz.getAnswerText(2)}
        
         3) ${resources.getString(R.string.ques3)}
        Your answer: ${Data.quiz.getAnswerText(3)}
        
         4) ${resources.getString(R.string.ques4)}
        Your answer: ${Data.quiz.getAnswerText(4)}
        
         5) ${resources.getString(R.string.ques5)}
        Your answer: ${Data.quiz.getAnswerText(5)}
    """.trimIndent()
    }
}