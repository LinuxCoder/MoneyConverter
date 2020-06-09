package com.example.moneyconverter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertButton = findViewById(R.id.convertButton);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder moneyInputDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                moneyInputDialogBuilder.setTitle("Rubles to dollars conversion");
                moneyInputDialogBuilder.setMessage("Input sum in rubles");

                final EditText moneyInput = new EditText(moneyInputDialogBuilder.getContext());
                makeOnlyNumberInput(moneyInput);

                moneyInputDialogBuilder.setView(moneyInput);
                moneyInputDialogBuilder.setNeutralButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                double money = Double.parseDouble(moneyInput.getText().toString());
                                double dollarCourse = 74;

                                AlertDialog.Builder conversionResultDialogBuilder =
                                    new AlertDialog.Builder(MainActivity.this);

                                TextView conversionResult = new TextView(MainActivity.this);
                                conversionResult.setText(
                                        String.format(Locale.getDefault(), "%f",
                                                money * dollarCourse));
                                conversionResult.setGravity(Gravity.CENTER_HORIZONTAL);
                                conversionResult.setTypeface(Typeface.SERIF, Typeface.BOLD);
                                conversionResultDialogBuilder.setView(conversionResult);

                                AlertDialog conversionResultDialog = conversionResultDialogBuilder.create();
                                conversionResultDialog.show();

                                centerButton(conversionResultDialog, DialogInterface.BUTTON_NEUTRAL);
                            }
                        });
                AlertDialog moneyInputDialog = moneyInputDialogBuilder.create();
                moneyInputDialog.show();

                centerButton(moneyInputDialog, DialogInterface.BUTTON_NEUTRAL);
            }
        });
    }

    public void centerButton(AlertDialog dialog, final int BUTTON) {
        final Button neutralButton = dialog.getButton(BUTTON);
        LinearLayout.LayoutParams neutralButtonLL = (LinearLayout.LayoutParams)neutralButton.getLayoutParams();
        neutralButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
        neutralButton.setLayoutParams(neutralButtonLL);
    }

    public void makeOnlyNumberInput(EditText editText) {
        editText.setInputType(InputType.TYPE_CLASS_NUMBER );
        editText.setKeyListener(DigitsKeyListener.getInstance(".0123456789"));
        editText.setSingleLine(true);
        editText.setGravity(Gravity.CENTER);
        editText.setEllipsize(TextUtils.TruncateAt.START);
    }
}