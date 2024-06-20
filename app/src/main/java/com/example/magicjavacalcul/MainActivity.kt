package com.example.magicjavacalcul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentNumber: Double = 0.0
    private var operation: Char = ' '
    private var isOperationPerformed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
        val authorTextView = findViewById<TextView>(R.id.authorTextView)
        authorTextView.text = "Иванов И.И."
    }

    fun onDigitClick(view: View) {
        if (isOperationPerformed) {
            resultTextView.text = ""
            isOperationPerformed = false
        }
        val digit = (view as Button).text.toString()
        resultTextView.append(digit)
        currentNumber = resultTextView.text.toString().toDouble()
    }

    fun onOperationClick(view: View) {
        if (operation != ' ') {
            performOperation()
        }
        operation = (view as Button).text.toString()[0]
        isOperationPerformed = true
    }

    fun onEqualsClick(view: View) {
        performOperation()
        operation = ' '
    }

    fun onClearClick(view: View) {
        resultTextView.text = ""
        currentNumber = 0.0
        operation = ' '
        isOperationPerformed = false
    }

    fun onSquareRootClick(view: View) {
        if (currentNumber >= 0) {
            resultTextView.text = sqrt(currentNumber).toString()
            currentNumber = resultTextView.text.toString().toDouble()
            operation = ' '
            isOperationPerformed = true
        } else {
            showError("Cannot take square root of a negative number")
        }
    }

    fun onSquareClick(view: View) {
        resultTextView.text = (currentNumber * currentNumber).toString()
        currentNumber = resultTextView.text.toString().toDouble()
        operation = ' '
        isOperationPerformed = true
    }

    fun onPercentClick(view: View) {
        if (operation != ' ') {
            performOperation()
            resultTextView.text = (currentNumber / 100).toString()
            currentNumber = resultTextView.text.toString().toDouble()
            operation = ' '
            isOperationPerformed = true
        } else {
            showError("No operation selected")
        }
    }

    private fun performOperation() {
        when (operation) {
            '+' -> currentNumber += resultTextView.text.toString().toDouble()
            '-' -> currentNumber -= resultTextView.text.toString().toDouble()
            '*' -> currentNumber *= resultTextView.text.toString().toDouble()
            '/' -> {
                if (resultTextView.text.toString().toDouble() == 0.0) {
                    showError("Cannot divide by zero")
                    return
                }
                currentNumber /= resultTextView.text.toString().toDouble()
            }
        }
        resultTextView.text = currentNumber.toString()
        isOperationPerformed = true
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}