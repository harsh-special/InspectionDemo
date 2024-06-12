package com.example.userfeature.presenter.ui.inspection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.QuestionaireeStatus
import com.example.userfeature.R
import com.example.userfeature.presenter.activity.HomeActivity
import com.example.userfeature.presenter.user.intent.InspectionIntent
import com.example.userfeature.presenter.user.state.InspectionState
import com.example.userfeature.presenter.ui.inspection.adapter.QuestionAdapter
import com.example.userfeature.presenter.viewmodel.InspectionViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [InspectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
// InspectionsFragment.kt

@AndroidEntryPoint
class InspectionFragment : Fragment() {

    private lateinit var viewModel: InspectionViewModel
    private lateinit var inspectionAdapter: QuestionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[InspectionViewModel::class.java]
        return inflater.inflate(R.layout.fragment_inspection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_inspections)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.state.observe(viewLifecycleOwner) { inspections ->
            when (inspections) {
                is InspectionState.LoadQuestions -> {
                    inspectionAdapter =
                        QuestionAdapter(inspections.questionsList) { inspection ->
                            if (inspection.status == QuestionaireeStatus.COMPLETED) {
                                showDialog(inspection.score)
                            } else {
                                (activity as? HomeActivity)?.navigateToStartInspectionScreen(inspection)
                            }
                        }
                    recyclerView.adapter = inspectionAdapter
                }
            }
        }

        val startButton: Button = view.findViewById(R.id.button_start_new_inspection)
        startButton.setOnClickListener {
            viewModel.processIntents(InspectionIntent.StartNewInspection)
        }

        viewModel.effect.observe(viewLifecycleOwner) {
            (activity as? HomeActivity)?.navigateToStartInspectionScreen(null)
        }
        viewModel.processIntents(InspectionIntent.LoadInspections)
    }

    private fun showDialog(score: Double?) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(getString(R.string.alert_dialog))
        builder.setMessage(
            getString(
                R.string.these_survey_is_already_completed_and_your_score_is,
                score.toString()
            )
        )
        builder.setPositiveButton(getString(R.string.ok)) { dialog, _ ->
            dialog.dismiss() // Dismiss the dialog when the "OK" button is clicked
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false) // Prevent back button dismiss
        alertDialog.setCanceledOnTouchOutside(false) //
        alertDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.effect.removeObservers(viewLifecycleOwner)
        viewModel.state.removeObservers(viewLifecycleOwner)
    }
}
