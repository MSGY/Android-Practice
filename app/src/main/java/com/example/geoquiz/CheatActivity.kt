package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "CheatActivity"
const val EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown"

private const val EXTRA_ANSWER_IS_TRUE =
    "com.example.geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView : TextView
    private lateinit var versionTextView: TextView
    private lateinit var showAnswerButton : Button

    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        versionTextView  = findViewById(R.id.current_sdk_version)
        val currentSDK = Build.VERSION.SDK_INT.toString()
        versionTextView.text = "API 레벨 " + currentSDK

        showAnswerButton.setOnClickListener {

                val answerText = when {
                    answerIsTrue -> R.string.true_button
                    else -> R.string.false_button
                }
                answerTextView.setText(answerText)
                setAnswerShownResult(true)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

    private fun setAnswerShownResult(isAnswerShown: Boolean){
        val data = Intent().apply{
            Log.d(TAG, "1aasdfqwersdf")
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }
    companion object{
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}