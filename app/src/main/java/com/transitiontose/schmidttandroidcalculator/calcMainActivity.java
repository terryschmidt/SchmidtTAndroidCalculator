package com.transitiontose.schmidttandroidcalculator;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.OnClickListener;
import java.util.*;

public class calcMainActivity extends Activity {

    private TextView scr;
    private String operation = "";
    private int firstNum;
    private ButtonClickListener btnClick;

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
        public void onClick (View v) {
            switch (v.getId()) {
                case R.id.buttonClear: scr.setText("0"); firstNum = 0; operation = ""; break;
                case R.id.buttonPlus: mMath("+"); break;
                case R.id.buttonEquals: mResult(); break;
                default: String num = ((Button) v).getText().toString(); getKeyboard(num); break;
            }
        }
    }

    public void mMath (String str) {
        firstNum = Integer.parseInt(scr.getText().toString());
        operation = str;
        scr.setText("0");
    }

    public void mResult () {
        int secondNum = Integer.parseInt(scr.getText().toString());
        int result;
        if (operation.equals("+")) {
            result = secondNum + firstNum;
        } else {
            result = 000;
        }

        scr.setText(String.valueOf(result));
    }

    public void getKeyboard(String str) {
        String scrCurrent = scr.getText().toString();
        if (scrCurrent.equals("0")) {
            scrCurrent = "";
        }
        scrCurrent += str;
        scr.setText(scrCurrent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calc_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
