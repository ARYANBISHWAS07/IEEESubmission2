package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var etShowNumber: TextView
    private var isNewOp = true
    private var dot = false
    private var op = "X"
    private var oldNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etShowNumber = findViewById(R.id.etshownumber)
        val buttons = listOf<Button>(
            findViewById(R.id.bu0),
            findViewById(R.id.bu1),
            findViewById(R.id.bu2),
            findViewById(R.id.bu3),
            findViewById(R.id.bu4),
            findViewById(R.id.bu5),
            findViewById(R.id.bu6),
            findViewById(R.id.bu7),
            findViewById(R.id.bu8),
            findViewById(R.id.bu9),
            findViewById(R.id.buDot),
            findViewById(R.id.buPlusMinus),
            findViewById(R.id.buMul),
            findViewById(R.id.buDiv),
            findViewById(R.id.buSub),
            findViewById(R.id.buSum)
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                when (button.id) {
                    R.id.buDot -> {
                        if (!dot) {
                            etShowNumber.append(".")
                            dot = true
                        }
                    }
                    R.id.buPlusMinus -> {
                        etShowNumber.text = "-${etShowNumber.text}"
                    }
                    R.id.buMul, R.id.buDiv, R.id.buSub, R.id.buSum -> {
                        handleOperator(button.text.toString())
                    }
                    else ->{
                        etShowNumber.append(button.text)
                    }
                }
            }
        }
    }

    private fun handleOperator(operator: String) {
        if (!isNewOp) {
            buEqualEvent(null)
        }
        op = operator
        oldNumber = etShowNumber.text.toString()
        isNewOp = true
        dot = false
    }

    fun buEqualEvent(view: View?) {
        val newNumber = etShowNumber.text.toString()
        var finalNumber: Double? = null
        when (op) {
            "X" -> finalNumber = oldNumber.toDouble() * newNumber.toDouble()
            "÷" -> finalNumber = oldNumber.toDouble() / newNumber.toDouble()
            "-" -> finalNumber = oldNumber.toDouble() - newNumber.toDouble()
            "+" -> finalNumber = oldNumber.toDouble() + newNumber.toDouble()
        }
        etShowNumber.text = finalNumber.toString()
        isNewOp = true
    }

    fun buPercentEvent(view: android.view.View?) {
        val number = etShowNumber.text.toString().toDouble() / 100
        etShowNumber.text = number.toString()
        isNewOp = true
    }

    fun buCleanEvent(view: android.view.View?) {
        etShowNumber.text = " "
        isNewOp = true
        dot = false
    }
}