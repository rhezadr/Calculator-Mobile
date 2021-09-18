package com.app.calculatormobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class MainActivity extends AppCompatActivity {
    String newNumber = "";
    String oldNumber;
    String oldOperation = "";
    String operator;
    boolean ishaveOutput = false;
    TextView input;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (TextView) findViewById(R.id.inputText);
        output = (TextView) findViewById(R.id.resultsText);
    }

    public void numberEvent(View view) throws ParseException {
        int minplus;

        switch (view.getId()){
            case R.id.button0:
                newNumber += "0";
                break;
            case R.id.button1:
                newNumber += "1";
                break;
            case R.id.button2:
                newNumber += "2";
                break;
            case R.id.button3:
                newNumber += "3";
                break;
            case R.id.button4:
                newNumber += "4";
                break;
            case R.id.button5:
                newNumber += "5";
                break;
            case R.id.button6:
                newNumber += "6";
                break;
            case R.id.button7:
                newNumber += "7";
                break;
            case R.id.button8:
                newNumber += "8";
                break;
            case R.id.button9:
                newNumber += "9";
                break;
            case R.id.commaButton:
                newNumber += ".";
                break;
            case R.id.resetButton:
                oldOperation= "";
                newNumber = "";
                ishaveOutput = false;
                output.setText("");
                break;
            case R.id.minus_or_plus_Button:
                minplus = NumberFormat.getInstance().parse(newNumber).intValue() * -1;
                newNumber = String.valueOf(minplus);
                break;
            case R.id.deleteButton:
                String newtext = input.getText().toString().substring(0,input.length()-1);
                input.setText(newtext);
                break;
        }

        input.setText(oldOperation + newNumber);

        if(oldOperation != "" && newNumber != "") {
            output.setText(operate());
        }
    }

    public void operatorEvent(View view) {
        oldNumber = input.getText().toString();
        newNumber = "";

        if(ishaveOutput){
            oldNumber = output.getText().toString();
            output.setText("");
            ishaveOutput = false;
        }

        switch (view.getId()){
            case R.id.divideButton:
                operator = " / ";
                break;
            case R.id.multiplyButton:Button:
                operator = " x ";
                break;
            case R.id.modulusButton:Button:
                operator = " % ";
                break;
            case R.id.plusButton:Button:
                operator = " + ";
                break;
            case R.id.minusButton:Button:
                operator = " - ";
                break;
        }

        input.setText(oldNumber + operator);
        oldOperation = input.getText().toString();
    }

    public void equalEvent(View view) {
        output.setText(operate());
    }

    public void historyEvent(View view) {
        setContentView(R.layout.activity_history);
    }

    public String operate(){
        double result = 0.0;
        DecimalFormat df = new DecimalFormat("#.##");

        switch (operator){
            case " + " :
                result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);
                break;
            case " - " :
                result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber);
                break;
            case " x " :
                result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber);
                break;
            case " / " :
                result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
                break;
            case " % " :
                result = Double.parseDouble(oldNumber) / 100 * Double.parseDouble(newNumber);
                break;
        }

        ishaveOutput = true;
        return df.format(result);
    }

}