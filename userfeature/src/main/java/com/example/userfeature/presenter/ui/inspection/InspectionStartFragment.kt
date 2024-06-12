package com.example.userfeature.presenter.ui.inspection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.domain.model.QuestionaireeStatus
import com.example.domain.model.Questions
import com.example.domain.model.SurveyQuestion
import com.example.userfeature.R
import com.example.userfeature.presenter.activity.HomeActivity
import com.example.userfeature.presenter.user.intent.InspectionStartIntent
import com.example.userfeature.presenter.user.state.QuestionnaireState
import com.example.userfeature.presenter.viewmodel.InspectionStartViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InspectionStartFragment : Fragment() {

    private val args: InspectionStartFragmentArgs by navArgs()
    private var question: Questions? = null
    private var surveyQuestions: List<SurveyQuestion>? = emptyList()
    private lateinit var viewModel: InspectionStartViewModel
    private var index = 0
    private lateinit var btnNext: Button
    private lateinit var btnSubmit: Button
    private lateinit var progressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inspection_start, container, false)

        viewModel = ViewModelProvider(this)[InspectionStartViewModel::class.java]
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data: Questions? = args.argData

        btnNext = view.findViewById(R.id.btnNext)
        btnSubmit = view.findViewById(R.id.btnSubmit)
        progressBar = view.findViewById(R.id.loading)
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is QuestionnaireState.Loading -> {
                    // Show loading state
                    progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                }

                is QuestionnaireState.Success -> {
                    handleSurvey(state.question)
                }

                is QuestionnaireState.Error -> {
                    showError(state.message)
                }
            }
        }


        btnNext.setOnClickListener {
            checkButton()
            viewModel.processIntents(InspectionStartIntent.SaveQuestionnaire(question))
            displayQuestions(surveyQuestions?.get(index++))
        }

        btnSubmit.setOnClickListener {
            question?.score = viewModel.calculateScore(surveyQuestions)
            showDialog()
        }
        data?.let {
            handleSurvey(it)
        } ?: kotlin.run {
            viewModel.processIntents(InspectionStartIntent.StartNewInspection)
        }
    }

    private fun handleSurvey(question: Questions) {
        this.question = question
        surveyQuestions = question.surveyQuestions
        checkButton()
        if (surveyQuestions.isNullOrEmpty().not())
            displayQuestions(surveyQuestions?.get(index++))
        else
            hideNextButton()
    }

    private fun hideNextButton() {
        btnNext.visibility = View.GONE
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(getString(R.string.alert_dialog))
        builder.setMessage(getString(R.string.score_is).plus(question?.score))
        builder.setPositiveButton(getString(R.string.ok)) { dialog, _ ->
            question?.status = QuestionaireeStatus.COMPLETED
            viewModel.saveQuestionairee(question)
            dialog.dismiss() // Dismiss the dialog when the "OK" button is clicked
            (activity as? HomeActivity)?.onBackPressedDispatcher?.onBackPressed()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false) // Prevent back button dismiss
        alertDialog.setCanceledOnTouchOutside(false) //
        alertDialog.show()
    }

    private fun checkButton() {
        surveyQuestions?.let {
            if (it.size - 1 == index) {
                hideNextButton()
                btnSubmit.visibility = View.VISIBLE
            }
        } ?: {
            hideNextButton()
            btnSubmit.visibility = View.GONE
        }
    }

    // Function to display the questions
    private fun displayQuestions(surveyQuestion: SurveyQuestion?) {
        surveyQuestion?.let { question ->
            view?.findViewById<TextView>(R.id.tvQuestion)?.text = question.name

            val radioGroup = view?.findViewById<RadioGroup>(R.id.rgAnswers)
            radioGroup?.removeAllViews()
            val selectedAnswer = question.selectedAnswerChoiceId
            for (answer in question.answers) {
                val radioButton = RadioButton(context).apply {
                    id = answer.id
                    text = answer.name
                    visibility = View.VISIBLE
                    isChecked = selectedAnswer == answer.id
                }
                radioGroup?.addView(radioButton)
            }

            radioGroup?.setOnCheckedChangeListener { group, checkedId ->
                surveyQuestion.selectedAnswerChoiceId = checkedId
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.state.removeObservers(this)
        viewModel.intents.removeObservers(this)
    }
}