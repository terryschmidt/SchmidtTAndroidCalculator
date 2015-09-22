package com.transitiontose.schmidttandroidcalculator;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

// CSC472, Terry Schmidt, Fall 2015

public class calcMainActivity extends Activity {

    private boolean userIsTypingNumber = false;
    private TextView scr;
    private int firstNum = 0;
    private int secondNum = 0;
    private ButtonClickListener btnClick;
    private int operationPressedCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_main);
        scr = (TextView) findViewById(R.id.calcTextView);
        btnClick = new ButtonClickListener();

        int[] idList = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
                        R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.buttonPlus, R.id.buttonEquals, R.id.buttonClear};

        for (int id : idList) {
            View v = (View) findViewById(id);
            v.setOnClickListener(btnClick);
        }
    }

    private class ButtonClickListener implements OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonClear:
                    clearPressed();
                    break;
                case R.id.buttonPlus:
                    operationPressedCount++;
                    plusPressed();
                    break;
                case R.id.buttonEquals:
                    equalsPressed();
                    break;
                default:
                    String numPressed = ((Button) v).getText().toString();
                    String currentDisplay = scr.getText().toString();
                    if (userIsTypingNumber) {
                        String newDisplay = currentDisplay + numPressed;
                        scr.setText(newDisplay);
                    } else {
                        scr.setText(numPressed);
                        userIsTypingNumber = true;
                    }
            }
        }
    }

    public void plusPressed () {
        if (operationPressedCount == 1) {
            userIsTypingNumber = false;
            firstNum = Integer.parseInt(scr.getText().toString());
        } else if (operationPressedCount == 2) {
            userIsTypingNumber = false;
            secondNum = Integer.parseInt(scr.getText().toString());
            equalsPressed();
        }
    }

    public void equalsPressed () {
        int result = 0;
        if (operationPressedCount == 1) {
            userIsTypingNumber = false;
            secondNum = Integer.parseInt(scr.getText().toString());
            result = firstNum + secondNum;
            scr.setText(Integer.toString(result));
            operationPressedCount = 0;
        } else if (operationPressedCount == 2) {
            result = firstNum + secondNum;
            scr.setText(Integer.toString(result));
            secondNum = result;
            operationPressedCount = 0;
        } else {
            int temp = 0;
            temp = secondNum + Integer.parseInt(scr.getText().toString());
            scr.setText(Integer.toString(temp));
        }
    }

    public void clearPressed () {
        userIsTypingNumber = false;
        scr.setText("0");
        firstNum = 0;
        secondNum = 0;
    }
}
