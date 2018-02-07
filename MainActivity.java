package com.example.anjuli.kaj_hw1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //This object takes in integers and outputs a RGB value
    private RGBretriever rgbRetriever;

    //These are the RGB integers
    private int redValueInt;
    private int greenValueInt;
    private int blueValueInt;

    //The RGB values come from here from user
    private EditText redValueEditText;
    private EditText greenValueEditText;
    private EditText blueValueEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w("onCreate", "top of method ********");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgbRetriever = new RGBretriever(0, 0, 0);

        Log.w("onCreate", "the edit texts ********");
        //Get reference of editTexts
        redValueEditText = (EditText) findViewById(R.id.redValue);
        greenValueEditText = (EditText) findViewById(R.id.greenValue);
        blueValueEditText = (EditText) findViewById(R.id.blueValue);

        //Create textWatchers
        TextChangeHandler tch = new TextChangeHandler();

        redValueEditText.addTextChangedListener(tch);
        greenValueEditText.addTextChangedListener(tch);
        blueValueEditText.addTextChangedListener(tch);

        //Change label color
        Log.w("onCreate", "color set **********");

    }
    
    /* As an alternative you can change the entire app background color by replacing the "View v" parameter with 
    * "RelativeLayout r" and in the textChangerHandler replace "View colored_label = findViewById(R.id.colored_label)"
    * with "RelativeLayout lLayout = (RelativeLayout) findViewById(R.id.background_id);"
    * However, don't forget to create the id in activity_main.xml
    */

    protected void setBackColor(int redVal, int greenVal, int blueVal, View v) {
        String outStr1 = String.format("red: %d, green: %d, blue: %d",redVal,greenVal,blueVal);
        Log.w("setBackColor", outStr1);
        String rgb = rgbRetriever.getRgbCode(redVal, greenVal, blueVal);
        Log.w("setBackColor", "setting color **********");
        v.setBackgroundColor(Color.parseColor(rgb));
    }

    protected void fromTextWatcher(View v){
        Log.w("onCreate", "rgb values ******* ");
        //Locate RGB values
        redValueInt = Integer.parseInt(redValueEditText.getText().toString());
        greenValueInt = Integer.parseInt(greenValueEditText.getText().toString());
        blueValueInt = Integer.parseInt(blueValueEditText.getText().toString());

        setBackColor(redValueInt, greenValueInt, blueValueInt, v);

    }

    private class TextChangeHandler implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0) {
                View colored_label = findViewById(R.id.colored_label);
                fromTextWatcher(colored_label);
            }
        }
    }
}
