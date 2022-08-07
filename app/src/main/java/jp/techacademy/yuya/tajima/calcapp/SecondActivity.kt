package jp.techacademy.yuya.tajima.calcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val value = intent.getDoubleExtra("answer", 0.0)
        val formattedValue = value.toString().replace(Regex("\\.[0]+$"),"")

        answer.text = formattedValue
    }
}