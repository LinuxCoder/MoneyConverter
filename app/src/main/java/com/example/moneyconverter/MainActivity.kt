package com.example.moneyconverter

import android.content.DialogInterface
import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.text.method.DigitsKeyListener
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private var convertButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        convertButton = findViewById(R.id.convertButton)
        convertButton.setOnClickListener(View.OnClickListener {
            val moneyInputDialogBuilder = AlertDialog.Builder(this@MainActivity)
            moneyInputDialogBuilder.setTitle("Rubles to dollars conversion")
            moneyInputDialogBuilder.setMessage("Input sum in rubles")
            val moneyInput = EditText(moneyInputDialogBuilder.context)
            makeOnlyNumberInput(moneyInput)
            moneyInputDialogBuilder.setView(moneyInput)
            moneyInputDialogBuilder.setNeutralButton(
                    "OK"
            ) { dialog, which ->
                val money = moneyInput.text.toString().toDouble()
                val dollarCourse = 74.0
                val conversionResultDialogBuilder = AlertDialog.Builder(this@MainActivity)
                val conversionResult = TextView(this@MainActivity)
                conversionResult.text = String.format(Locale.getDefault(), "%f",
                        money * dollarCourse)
                conversionResult.gravity = Gravity.CENTER_HORIZONTAL
                conversionResult.setTypeface(Typeface.SERIF, Typeface.BOLD)
                conversionResultDialogBuilder.setView(conversionResult)
                val conversionResultDialog = conversionResultDialogBuilder.create()
                conversionResultDialog.show()
                centerButton(conversionResultDialog, DialogInterface.BUTTON_NEUTRAL)
            }
            val moneyInputDialog = moneyInputDialogBuilder.create()
            moneyInputDialog.show()
            centerButton(moneyInputDialog, DialogInterface.BUTTON_NEUTRAL)
        })
    }

    fun centerButton(dialog: AlertDialog, BUTTON: Int) {
        val neutralButton = dialog.getButton(BUTTON)
        val neutralButtonLL = neutralButton.layoutParams as LinearLayout.LayoutParams
        neutralButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT
        neutralButton.layoutParams = neutralButtonLL
    }

    fun makeOnlyNumberInput(editText: EditText) {
        editText.inputType = InputType.TYPE_CLASS_NUMBER
        editText.keyListener = DigitsKeyListener.getInstance(".0123456789")
        editText.isSingleLine = true
        editText.gravity = Gravity.CENTER
        editText.ellipsize = TextUtils.TruncateAt.START
    }
}