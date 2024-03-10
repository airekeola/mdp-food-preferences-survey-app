package com.airekeola.foodpreferencessurvey.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.airekeola.foodpreferencessurvey.R
import com.airekeola.foodpreferencessurvey.model.Question
import com.airekeola.foodpreferencessurvey.viewholder.QuestionViewHolder

class SurveyRecyclerViewAdapter(private var questions: List<Question>) :
    RecyclerView.Adapter<QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question, parent, false)
        return QuestionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.questionTextView.text = questions[position].question;
        val radioButtons = questions[position].options.map { opt ->
            RadioButton(holder.radioGroup.context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                text = opt
                textSize = 16f
            }
        }

        radioButtons.forEach{rb -> holder.radioGroup.addView(rb)}
    }

}
