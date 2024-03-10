package com.airekeola.foodpreferencessurvey

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airekeola.foodpreferencessurvey.adapter.SurveyRecyclerViewAdapter
import com.airekeola.foodpreferencessurvey.databinding.ActivitySurveyBinding
import com.airekeola.foodpreferencessurvey.model.Question
import com.airekeola.foodpreferencessurvey.viewholder.QuestionViewHolder

class SurveyActivity : AppCompatActivity() {
    private lateinit var layout:ActivitySurveyBinding
    private lateinit var questions: List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        layout = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(layout.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initialize()
    }

    private fun initialize(){
        val title = intent.getStringExtra("surveyTitle")!!;
        layout.title.text = title;
        layout.responseTextview.text = "";
        if(title.contains("food", ignoreCase = true)){
            questions = listOf(
                Question(
                    resources.getString(R.string.food_q1),
                    resources.getStringArray(R.array.food_q1_options)),
                Question(
                    resources.getString(R.string.food_q2),
                    resources.getStringArray(R.array.food_q2_options)),
                Question(
                    resources.getString(R.string.food_q3),
                    resources.getStringArray(R.array.yes_no_options)),
                Question(
                    resources.getString(R.string.food_q4),
                    resources.getStringArray(R.array.yes_no_options)),
                Question(
                    resources.getString(R.string.food_q5),
                    resources.getStringArray(R.array.yes_no_options)),
            )
        }else{
            questions = listOf(
                Question(
                    resources.getString(R.string.dietary_q1),
                    resources.getStringArray(R.array.yes_no_options)),
                Question(
                    resources.getString(R.string.dietary_q2),
                    resources.getStringArray(R.array.yes_no_options)),
                Question(
                    resources.getString(R.string.dietary_q3),
                    resources.getStringArray(R.array.yes_no_options)),
                Question(
                    resources.getString(R.string.dietary_q4),
                    resources.getStringArray(R.array.yes_no_options)),
                Question(
                    resources.getString(R.string.dietary_q5),
                    resources.getStringArray(R.array.yes_no_options)),
            )
        }

        val adapter = SurveyRecyclerViewAdapter(questions)
        layout.recyclerView.layoutManager = LinearLayoutManager(this)
        layout.recyclerView.adapter = adapter

        layout.submitBtn.setOnClickListener(this::onSubmitBtnClick)
    }

    private fun onSubmitBtnClick(view:View){
        val viewHolders = layout.recyclerView.children.map { itemView -> layout.recyclerView.getChildViewHolder(itemView) }

        // validate that all questions are answered
        if(viewHolders.any{ itemView: RecyclerView.ViewHolder? -> (itemView as QuestionViewHolder).radioGroup.checkedRadioButtonId == -1}){
            Toast.makeText(this, "You've not answered one or more questions.", Toast.LENGTH_SHORT).show()
        }else{
            var response = ""
            viewHolders.forEach { itemView ->
                run {
                    val qView = itemView as QuestionViewHolder
                    val rad = findViewById<RadioButton>(qView.radioGroup.checkedRadioButtonId)
                    response += "${qView.questionTextView.text}: ${rad.text}\n"
                }
            }
            layout.responseTextview.text = response;
        }
    }
}
