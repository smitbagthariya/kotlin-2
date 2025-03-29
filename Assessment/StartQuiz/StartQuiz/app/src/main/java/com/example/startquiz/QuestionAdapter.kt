package com.example.startquiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuestionAdapter(private val questions: List<QuestionModel>) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.txtQuestion)
        val radioGroup: RadioGroup = view.findViewById(R.id.radioGroup)
        val optionA: RadioButton = view.findViewById(R.id.radioOptionA)
        val optionB: RadioButton = view.findViewById(R.id.radioOptionB)
        val optionC: RadioButton = view.findViewById(R.id.radioOptionC)
        val optionD: RadioButton = view.findViewById(R.id.radioOptionD)

        fun bind(question: QuestionModel) {
            textView.text = question.question
            optionA.text = question.options[0]
            optionB.text = question.options[1]
            optionC.text = question.options[2]
            optionD.text = question.options[3]

            // Disable radio buttons
            optionA.isEnabled = false
            optionB.isEnabled = false
            optionC.isEnabled = false
            optionD.isEnabled = false

            // Reset selection
            radioGroup.clearCheck()

            // Reset colors
            optionA.setTextColor(android.graphics.Color.BLACK)
            optionB.setTextColor(android.graphics.Color.BLACK)
            optionC.setTextColor(android.graphics.Color.BLACK)
            optionD.setTextColor(android.graphics.Color.BLACK)

            // Auto-select and highlight the correct answer
            when (question.correctAnswer) {
                optionA.text.toString() -> {
                    optionA.isChecked = true
                    optionA.setTextColor(android.graphics.Color.GREEN)
                }
                optionB.text.toString() -> {
                    optionB.isChecked = true
                    optionB.setTextColor(android.graphics.Color.GREEN)
                }
                optionC.text.toString() -> {
                    optionC.isChecked = true
                    optionC.setTextColor(android.graphics.Color.GREEN)
                }
                optionD.text.toString() -> {
                    optionD.isChecked = true
                    optionD.setTextColor(android.graphics.Color.GREEN)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount(): Int = questions.size
}
