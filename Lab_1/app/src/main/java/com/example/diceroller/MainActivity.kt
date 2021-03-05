package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton : Button = findViewById<Button>(R.id.roll_button)
      //  val countUpButton : Button = findViewById<Button>(R.id.countUp_button)
      //  val resultText : TextView = findViewById(R.id.result_text)
        //   countUpButton.setOnClickListener { countUp(resultText) }
        val diceImage: ImageView = findViewById(R.id.dice_image)
        rollButton.setOnClickListener{rollDice(diceImage)}


    }
    private fun rollDice( dI : ImageView ){
        val randomInt = Random().nextInt(6) + 1
        Toast.makeText(this, "button clicked "+ randomInt.toInt(),
            Toast.LENGTH_SHORT).show()
        //tV.text = "Dice Rolled!"
        val drawableResource = when (randomInt){
            1-> R.drawable.dice_1
            2->R.drawable.dice_2
            3->R.drawable.dice_3
            4->R.drawable.dice_4
            5->R.drawable.dice_5
            else->R.drawable.dice_6
        }
        dI.setImageResource(drawableResource)
    }
    private fun countUp(tV: TextView){
        tV.text = if(tV.text == "Hello World!") "1"
        else {
            if(tV.text.toString().toInt() == 6) "6"
            else { (tV.text.toString().toInt()+1).toString()
            }
        }
    }
}