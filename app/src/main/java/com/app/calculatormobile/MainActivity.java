package com.app.calculatormobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class MainActivity extends AppCompatActivity {
    String newNumber = "";
    String oldNumber;
    String oldOperation = "";
    String operator;
    String text;
    String inputText;
    boolean ishaveOutput = false;
    boolean isdeleted = false;
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
        double minplus;

        if (isdeleted) {
            newNumber = "";
            isdeleted = false;
        }

        switch (view.getId()) {
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
                oldOperation = "";
                newNumber = "";
                ishaveOutput = false;
                output.setText("");
                break;
            case R.id.minus_or_plus_Button:
                minplus = NumberFormat.getInstance().parse(newNumber).doubleValue() * -1;
                newNumber = String.valueOf(minplus);
                break;
        }

        inputText = oldOperation + newNumber;
        input.setText(inputText);

        if (oldOperation != "" && newNumber != "") {
            output.setText(operate());
        }
    }

    public void operatorEvent(View view) {
        int number;
        newNumber = "";
        String stringvalue;

        if (isdeleted) {
            stringvalue = String.valueOf(input.getText().toString());
            if (stringvalue != "/" || stringvalue != "*"
                    || stringvalue != "%" || stringvalue != "+"
                    || stringvalue != "-" ) {
                oldNumber = input.getText().toString();
                operator ="";
                oldOperation = "";
            } else if (input.getText().toString().substring(0,
                    input.getText().toString().length() - 1) == operator) {
                number = Integer.valueOf(input.getText().toString());
                oldNumber = String.valueOf(number);
            }
            isdeleted = false;
        } else {
            oldNumber = input.getText().toString();
        }

        if (ishaveOutput) {
            oldNumber = output.getText().toString();
            output.setText("");
            ishaveOutput = false;
        }

        if (operator != "") {
            operator = "";
        }

        switch (view.getId()) {
            case R.id.divideButton:
                operator = "/";
                break;
            case R.id.multiplyButton:
                Button:
                operator = "x";
                break;
            case R.id.modulusButton:
                Button:
                operator = "%";
                break;
            case R.id.plusButton:
                Button:
                operator = "+";
                break;
            case R.id.minusButton:
                Button:
                operator = "-";
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

    public String operate() {
        double result = 0.0;
        DecimalFormat df = new DecimalFormat("#.##");

        switch (operator) {
            case "+":
                result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);
                break;
            case "-":
                result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber);
                break;
            case "x":
                result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber);
                break;
            case "/":
                result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
                break;
            case "%":
                result = Double.parseDouble(oldNumber) / 100 * Double.parseDouble(newNumber);
                break;
        }

        ishaveOutput = true;
        return df.format(result);
    }

    public void backEvent(View view) {
        setContentView(R.layout.activity_main);

        input = (TextView) findViewById(R.id.inputText);
        output = (TextView) findViewById(R.id.resultsText);

        oldOperation = "";
        newNumber = "";
        ishaveOutput = false;
        output.setText("");
    }

    public void deleteEvent(View view) {
        int numbervalue;
        text = input.getText().toString();
        if (text != "") {
            text = text.substring(0, text.length() - 1);
            input.setText(text);
            if(input.getText().toString().substring(0,
                    input.getText().toString().length() - 1) == oldNumber){
                numbervalue = Integer.valueOf(text);
                oldNumber = String.valueOf(numbervalue);
            }
        }else{
            input.setText("");
            oldOperation = "";
        }

        newNumber = "";
        ishaveOutput = false;
        output.setText("");
        isdeleted = true;
    }

}