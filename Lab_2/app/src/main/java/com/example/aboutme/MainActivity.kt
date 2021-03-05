package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val myName: MyName = MyName("Szymon Nowak")
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = myName

//        findViewById<Button>(R.id.done_button).setOnClickListener {
//            addNickname(it)
//        }
        binding.doneButton.setOnClickListener {
            addNickname(it)
        }

//        findViewById<TextView>(R.id.nickname_text).setOnClickListener{
//            updateNickname(it)
//        }
        binding.nicknameText.setOnClickListener {
            updateNickname(it)
        }

    }

    private fun addNickname(view: View) {
//        val editText = findViewById<EditText>(R.id.nickname_edit)
//        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)
        val editText = binding.nicknameEdit
        val nicknameTextView = binding.nicknameText

        //nicknameTextView.text = editText.text.toString()
        //myName.nickname = editText.text.toString()
        binding.apply {
            myName?.nickname = editText.text.toString()
            editText.visibility = View.GONE
            binding.doneButton.visibility = View.GONE
            nicknameTextView.visibility = View.VISIBLE
            invalidateAll()
        }
//        view.visibility = View.GONE
//        editText.visibility = View.GONE
//        nicknameTextView.visibility = View.VISIBLE

        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun updateNickname(view: View) {
//        val editText = findViewById<EditText>(R.id.nickname_edit)
//        val doneButton = findViewById<Button>(R.id.done_button)
        val editText = binding.nicknameEdit
        val doneButton = binding.doneButton
        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
//        view.visibility = View.GONE
        binding.nicknameText.visibility = View.GONE
        editText.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }
}