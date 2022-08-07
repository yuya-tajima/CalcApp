package jp.techacademy.yuya.tajima.calcapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.graphics.Color
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plusButton.setOnClickListener(this)
        minusButton.setOnClickListener(this)
        timesButton.setOnClickListener(this)
        divideButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if (v == null) {
            Log.d("ERROR_LOG", "unexpected error occurred. view is null")
            return
        }

        val op1: Double? = operand1.text.toString().toDoubleOrNull()
        val op2: Double? = operand2.text.toString().toDoubleOrNull()

        Log.d("DEBUG_LOG", "$op1")
        Log.d("DEBUG_LOG", "$op2")

        if (op1 == null || op2 == null) {
            showAlert(v, "整数か小数を入力して下さい")
            return
        }

        val value: Double = when(v.id){
            R.id.plusButton -> {
                Log.d("DEBUG_LOG", "Clicked +")
                op1 + op2
            }
            R.id.minusButton -> {
                Log.d("DEBUG_LOG", "Clicked -")
                op1 - op2
            }
            R.id.timesButton -> {
                Log.d("DEBUG_LOG", "Clicked *")
                op1 * op2
            }
            R.id.divideButton -> {
                Log.d("DEBUG_LOG", "Clicked /")
                if (op2 == 0.0) {
                    showAlert(v, "0で割る事はできません")
                    return
                }
                op1 / op2
            }
            else -> {
                Log.d("ERROR_LOG", "unexpected error occurred. invalid operator")
                return
            }
        }

        Log.d("DEBUG_LOG", "$value")

        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("answer", value)

        startActivity(intent)
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val hideKey = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideKey.hideSoftInputFromWindow(view.windowToken, 0)
        } else {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
    }

    private fun showAlert (v: View, msg: String) {
        hideKeyboard()
        val snackbar = Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
        snackbar.getView().setBackgroundColor(Color.parseColor("red"))
        snackbar.show()
    }
}