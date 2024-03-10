package com.airekeola.foodpreferencessurvey

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airekeola.foodpreferencessurvey.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var layout:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        layout = ActivityMainBinding.inflate(layoutInflater)
        setContentView(layout.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initialize()
    }

    private fun initialize(){
        layout.startSurveyBtn.setOnClickListener(this::onStartSurveyBtnClick)
    }

    private fun onStartSurveyBtnClick(view:View){
        val checkedOption = findViewById<RadioButton>(layout.radioGroup.checkedRadioButtonId)
        if(checkedOption == null){
            Toast.makeText(this, "No survey option selected!", Toast.LENGTH_SHORT).show()
        }else{
            val surveyIntent = Intent(this, SurveyActivity::class.java)
            surveyIntent.putExtra("surveyTitle", checkedOption.text.toString())
            startActivity(surveyIntent)
        }


    }
}
