package com.example.foodroulette;

import android.support.v7.app.ActionBarActivity;
import android.widget.RelativeLayout;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.EditText;
import java.lang.Math;
import android.graphics.Matrix;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.annotation.TargetApi;
import android.widget.GridLayout.LayoutParams;
import android.view.animation.*;
import java.util.*;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.PointF;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;

public class DisplayMessageActivity extends ActionBarActivity {
	
	String[] diningList = {"Red Robin", "Salty's", "Subway", "Burger King", "Denny's", "Saigon Deli", "IHOP", "Applebee's",
			"KFC", "Cracker Barrel", "Spaghetti Factory", "Olive Garden", "Luna Cafe", "Husky Deli", "QFC", "Safeway"};
	//String[] sashaStuff = Collection.toArray(new String[sashaStuff.size]);
	int numChoicesOnWheel = 4;
	int listLength = diningList.length;
	int xPivot = 335;
	int yPivot = 450;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        RelativeLayout relativeLayout = new RelativeLayout(this);
        
        //Displays in a string the options that should show up on the wheel
        String messageAndOptions = getMessageFromIntent();
        
        getDimensions();
        
        //Displays the individual words in a circle format
        displayIndividualDiningOptions(relativeLayout);

        // Create the text view message
        TextView textView = new TextView(this);
        textView.setTextSize(25);
        textView.setText(messageAndOptions);
        textView.setPadding(0, 0, 0, 0); 
        relativeLayout.addView(textView);

        // Set the relativeLayout as the activity layout
        setContentView(relativeLayout);
        
        //rotates the words
        //rotateWordsForWheel();
        //rotateView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
              View rootView = inflater.inflate(R.layout.fragment_display_message,
                      container, false);
              return rootView;
        }
    }
    
    public void getDimensions(){
    	int widthScreen = this.getResources().getDisplayMetrics().widthPixels;
    	int heightScreen = this.getResources().getDisplayMetrics().heightPixels;
    	xPivot = (int)(widthScreen / 2.4);
    	yPivot = (int)(heightScreen / 2.7);
    }
    
    //Gets message from FillWheel.java
    public String getMessageFromIntent(){
    	int widthScreen = this.getResources().getDisplayMetrics().widthPixels;
    	int heightScreen = this.getResources().getDisplayMetrics().heightPixels;
    	
    	Intent intent = getIntent();
        String message = intent.getStringExtra(FillWheel.EXTRA_MESSAGE);
        String x = ListOfOptions(diningList);
        String messageOptions = "Height = " + heightScreen + " width = " + widthScreen + " " + message + " " + x;
    	return messageOptions;
    }
    
    //Displays in a string the options that should show up on the wheel
    public String ListOfOptions(String[] diningList){
    	if (listLength < numChoicesOnWheel){
    		numChoicesOnWheel = listLength;
    	}
		String printListString = "Your options are ";
		for (int i = 0; i < numChoicesOnWheel; i++){
			if (i == 0){
				printListString = diningList[i];
			} else if ((i + 1) == numChoicesOnWheel){
				printListString = printListString + ", or " + diningList[i] + ".";
			} else {
				printListString = printListString + ", " + diningList[i];
			}
		}
		printListString = "Your options are " + printListString;
		return printListString;
	}
    
    @TargetApi(14)
    public void displayIndividualDiningOptions(RelativeLayout relativeLayout){
    	ArrayList<Integer> IdNames = new ArrayList<Integer>();
    	IdNames.add(R.id.dOptionOne);
    	IdNames.add(R.id.dOptionTwo);
    	IdNames.add(R.id.dOptionThree);
    	IdNames.add(R.id.dOptionFour);
    	int widthScreen = this.getResources().getDisplayMetrics().widthPixels;
    	int heightScreen = this.getResources().getDisplayMetrics().heightPixels;
    	int screenVariable = 0;
    	if (widthScreen > heightScreen){
    		screenVariable = heightScreen;
    	}else{
    		screenVariable = widthScreen;
    	}
    	
    	if (listLength < numChoicesOnWheel){
    		numChoicesOnWheel = listLength;
    	}
        for (int i = 0; i < numChoicesOnWheel; i++){
        	//sets what is in the textView
        	int fontSize = 16;
        	String diningOptionX = diningOption(diningList, i);
        	TextView Option = new TextView(this);
        	Option.setTextSize(fontSize);
        	Option.setText(diningOptionX);
        	Option.setId(IdNames.get(i));
        	
        	int numOptions = numChoicesOnWheel;
        	double padX = setPaddingX(i, numOptions, screenVariable);
        	double padY = setPaddingY(i, numOptions, screenVariable);
        	int xValue = (int) padX + xPivot;
        	int yValue = (int) padY + yPivot;
        	//dOption1.setPadding(xValue, yValue, 0, 0);
        	
        	//sets the angle of text
        	double angleOfText = setAngle(i, numOptions, xValue, screenVariable);
        	Option.setRotation((float)angleOfText);
        	
        	//gets text from text view
        	String restrauntName = Option.getText().toString();
        	int numCharacters = restrauntName.length();
        	double width = (numCharacters * fontSize / 1.5) + 15 + ((screenVariable / 3.2) / Math.pow(numCharacters, 2));
        	
        	//Creates location for textViews of dining locations and sets the size
        	RelativeLayout.LayoutParams params;
        	params = new RelativeLayout.LayoutParams((int)width, 30);
        	params.leftMargin = xValue;
        	params.topMargin = yValue;
        	
        	relativeLayout.addView(Option, params);
        }
    }
    
    public String diningOption(String[] diningList, int i){
    	String option = diningList[i];
    	return option;
    }
    
    public double setPaddingX(int i, int numOptions, int screenVariable) {
    	int x = i;
    	double coefficientValue = Math.PI * 2 / numOptions;
    	double xVariableCoordinate = Math.sin(x * coefficientValue);
    	double setPadding = xVariableCoordinate * (screenVariable / 3.2);
    	return setPadding;
    }
    
    public double setPaddingY(int i, int numOptions, int screenVariable) {
    	int x = i;
    	double coefficientValue = Math.PI * 2 / numOptions;
    	double yVariableCoordinate = Math.cos(x * coefficientValue);
    	double setPadding = yVariableCoordinate * (screenVariable / 3.2);
    	return setPadding;
    }
    
    public int setAngle(int i, int numOptions, int xValue, int screenVariable) {
    	int angle = 0;
    	//(screenVariable / 3.2) come from double setPadding = xVariableCoordinate * (screenVariable / 3.2) in the setPaddingX method
    	
    	/*if (xValue < (screenVariable / 3.2)){
    		angle = -90 - (360 / numOptions * i) - 6;
    	} else {
    		angle = 90 - (360 / numOptions * i);
    	}*/
    	
    	angle = 90 - (360 / numOptions * i);
    	return angle;
    }
    
    public void rotateWordsForWheel() {
    	//Rotate words around point (xPivot, yPivot)
        RotateAnimation anim = new RotateAnimation (0f, 360f, 50, -215); // -(250 - ((width"150" / 2 ) - 40)) = -215
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(2000);
        ((View) findViewById(R.id.dOptionOne)).startAnimation(anim);
    }
    
    public void rotateView(){
    	//Rotate words around point (xPivot, yPivot)
    	/*LinearLayout layout1 = (LinearLayout) findViewById(R.id.circleWords);
    	Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotatewords);
        layout1.setAnimation(anim);
        layout1.startAnimation(anim);*/
        
    	LinearLayout layout1 = (LinearLayout) findViewById(R.id.circleWords);
    	Animation rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotatewords);
        LayoutAnimationController animController = new LayoutAnimationController(rotateAnim, 0);
        layout1.setLayoutAnimation(animController);
    }
}
