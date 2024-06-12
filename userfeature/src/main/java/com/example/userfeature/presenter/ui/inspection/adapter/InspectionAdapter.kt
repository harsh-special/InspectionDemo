package com.example.userfeature.presenter.ui.inspection.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.QuestionaireeStatus
import com.example.domain.model.Questions
import com.example.userfeature.R

class QuestionAdapter(
    private val inspections: List<Questions>,
    private val onItemClick: (Questions) -> Unit
) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val inspectionName: TextView = itemView.findViewById(R.id.inspection_name)
        val inspectionStatus: TextView = itemView.findViewById(R.id.inspection_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inspection, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val context = holder.itemView.context
        val inspection = inspections[position]
        holder.inspectionName.text =
            context.getString(R.string.survey).plus(inspection.id.toString())
        holder.inspectionStatus.text =
            if (inspection.status == QuestionaireeStatus.COMPLETED) context.getString(
                R.string.completed
            ) else context.getString(R.string.incomplete)

        holder.itemView.setOnClickListener {
            onItemClick(inspection)
        }
    }

    override fun getItemCount(): Int {
        return inspections.size
    }
}