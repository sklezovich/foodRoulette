package com.example.foodroulette;

import android.support.v7.app.ActionBarActivity;
import android.widget.FrameLayout;
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
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.widget.GridLayout.LayoutParams;
import android.view.animation.*;
import java.util.*;

import android.app.Activity;
import android.graphics.PointF;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.view.animation.Animation.AnimationListener;
import java.util.Timer;
import java.util.TimerTask;

import com.example.foodroulette.PreferencesActivity.preferencesThread;


@SuppressLint("NewApi")
public class DisplayMessageActivity extends ActionBarActivity {

	//This is a sample list of dining places used for testing purposes
	String[] diningList = {"Red Robin", "Salty's", "Subway", "Burger King", "Denny's", "Saigon Deli", "IHOP", "Applebee's",
			"KFC", "Cracker Barrel", "Spaghetti Factory", "Olive Garden", "Luna Cafe", "Husky Deli", "QFC", "Safeway", "Outback", "Purple Cafe"};
	int numChoicesOnWheel = 16;
	int listLength = diningList.length;
	int xPivot = 335;
	int yPivot = 450;
	double padX = 0.0;
	double padY = 0.0;
	String[] message = null;
	
	FrameLayout frameLayout = null;
	ImageView imageview = null;
	
	//final ImageView imageview = new ImageView(this);
	
	    //returns a random long between min and max!
		long randomWithRange(int min, int max)
		{
		   int range = (max - min) + 1;     
		   return (long)(Math.random() * range) + min;
		}

		public final float endPoint = 1080; // randomWithRange(360, 800);
		public final int spinDuration = 8000; //randomWithRange(1000, 4000); //random duration between 1 & 4 seconds
		public static final int repeatCount = 0; //(int)Double.POSITIVE_INFINITY;

		public final float startPoint = 11.25f;  //initial angle of wheel view
		//Log.d(constants.TAG, "Duration: "+ spinDuration+", Repeat Count: "+repeatCount);
		
		RotateAnimation anim = null;

		
		
		
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		Bundle bundle = (Bundle) getIntent().getExtras();		
		message = bundle.getStringArray("key"); //message = collection array
		
		String result = Arrays.toString(message);

        FrameLayout frameLayout = new FrameLayout(this);
        
        getDimensions();
        
        ImageView imageview = new ImageView(this);
        imageview.setImageResource(R.drawable.roulettewheelfixed); 
        frameLayout.addView(imageview);
        
        //Displays the individual words in a circle format
        displayIndividualDiningOptions(frameLayout);
        
        ImageView imageArrow = new ImageView(this);
        imageArrow.setImageResource(R.drawable.arrow);
        frameLayout.addView(imageArrow);
        
        //Button spinButton = (Button) this.findViewById(R.id.button1);
        Button spinButton = new Button(this);
        frameLayout.addView(spinButton);

        // Set the frameLayout as the activity layout
        setContentView(frameLayout);
        
        //rotates the words
        rotateWordsForWheel();
        
        anim = new RotateAnimation(startPoint, endPoint, //2nd float sets target angle
    	        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
 	   anim.setInterpolator(new AccelerateInterpolator(0.1f));
 	   anim.setRepeatCount(repeatCount);
 	   anim.setDuration(spinDuration); //sets duration of rotation
 	   imageview.setRotation(startPoint);
 	   imageview.startAnimation(anim); 
    }


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
    
    //gets height and width dimensions of the device screen
    public void getDimensions(){
    	int widthScreen = this.getResources().getDisplayMetrics().widthPixels;
    	int heightScreen = this.getResources().getDisplayMetrics().heightPixels;
    	xPivot = (int)(widthScreen / 2.5);
    	yPivot = (int)(heightScreen / 2.3);
    }
    
    //Testing case: Displays in a string the options that should show up on the wheel
    public String ListOfOptions(String[] diningList){
    	if (listLength < numChoicesOnWheel){
    		numChoicesOnWheel = listLength;
    	}
    	if (listLength > 16){
    		listLength = 16;
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
    //displays all the dining options in a circle format
    public void displayIndividualDiningOptions(FrameLayout frameLayout){
    	ArrayList<Integer> IdNames = new ArrayList<Integer>();
    	IdNames.add(R.id.dOptionOne);
    	IdNames.add(R.id.dOptionTwo);
    	IdNames.add(R.id.dOptionThree);
    	IdNames.add(R.id.dOptionFour);
    	IdNames.add(R.id.dOptionFive);
    	IdNames.add(R.id.dOptionSix);
    	IdNames.add(R.id.dOptionSeven);
    	IdNames.add(R.id.dOptionEight);
    	IdNames.add(R.id.dOptionNine);
    	IdNames.add(R.id.dOptionTen);
    	IdNames.add(R.id.dOptionEleven);
    	IdNames.add(R.id.dOptionTwelve);
    	IdNames.add(R.id.dOptionThirteen);
    	IdNames.add(R.id.dOptionFourteen);
    	IdNames.add(R.id.dOptionFifteen);
    	IdNames.add(R.id.dOptionSixteen);
    	int widthScreen = this.getResources().getDisplayMetrics().widthPixels;
    	int heightScreen = this.getResources().getDisplayMetrics().heightPixels;
    	int screenVariable = 0;
    	Collection<Foodies> foods = FoodfActivity.getBlahh();
    	Collections.shuffle((List<?>) foods);
    	if (widthScreen > heightScreen){
    		screenVariable = heightScreen;
    	}else{
    		screenVariable = widthScreen;
    	}
    	
    	if (foods.size() < numChoicesOnWheel){
    		numChoicesOnWheel = foods.size();
    	}
 
    	int i = 0;
    	for (Foodies f : foods){
    		if (i < numChoicesOnWheel){
    			//sets what is in the textView
    			
            	//String diningOptionX = diningOption(f.name(), i);
            	TextView Option = new TextView(this);
            	Option.setText(f.name());
            	Option.setId(IdNames.get(i));
            	
            	//sets location
            	int numOptions = numChoicesOnWheel;                       
            	padX = setPaddingX(i, numOptions, screenVariable);                                               
            	padY = setPaddingY(i, numOptions, screenVariable);
            	int xValue = (int) padX + xPivot;
            	int yValue = (int) padY + yPivot;
            	//dOption1.setPadding(xValue, yValue, 0, 0);
            	
            	//sets the angle of text
            	double angleOfText = setAngle(i, numOptions, xValue, screenVariable);
            	Option.setRotation((float)angleOfText);
            	
            	//gets text from text view
            	//String restrauntName = Option.getText().toString();
            	String restrauntName = f.name();
            	int numCharacters = restrauntName.length();
            	int fontSize = setFontSize(numCharacters);
            	Option.setTextSize(fontSize);
            	double width = 150.0;
            	//double width = (numCharacters * fontSize / 1.5) + 15 + ((screenVariable / 3.2) / Math.pow(numCharacters, 2));
            	
            	//Creates location for textViews of dining locations and sets the size
            	FrameLayout.LayoutParams params;
            	params = new FrameLayout.LayoutParams((int)width, 30);
            	params.leftMargin = xValue;
            	params.topMargin = yValue;
            	
            	frameLayout.addView(Option, params);
        		i++;
    		}}
    	}
    	
    
    //sets the distance between the text view of a restaurant name and the center of the pivot point in the x direction
    public double setPaddingX(int i, int numOptions, int screenVariable) {
    	int x = i;
    	double coefficientValue = Math.PI * 2 / numOptions;
    	double xVariableCoordinate = Math.sin(x * coefficientValue);
    	double setPadding = xVariableCoordinate * (screenVariable / 3.2);
    	return setPadding;
    }
    
    //sets the fonts size of the restaurant name based on the number of characters in the name
    public int setFontSize(int numCharacters) {
    	int font = 16;
    	if ((numCharacters > 12) && (numCharacters < 17)){
    		font = 16 - (numCharacters - 12);
    	}
    	if ((numCharacters > 16) && (numCharacters < 25)){
    		font = 12 - ((numCharacters - 16) / 2);
    	}
    	if (numCharacters > 24){
    		font = 10;
    	}
    	return font;
    }
    
    //sets the distance between the text view of a restaurant name and the center of the pivot point in the y direction
    public double setPaddingY(int i, int numOptions, int screenVariable) {
    	int x = i;
    	double coefficientValue = Math.PI * 2 / numOptions;
    	double yVariableCoordinate = Math.cos(x * coefficientValue);
    	double setPadding = yVariableCoordinate * (screenVariable / 3.2);
    	return setPadding;
    }
    
    //sets the angle of the text views relative to its location to the center of the circle
    public int setAngle(int i, int numOptions, int xValue, int screenVariable) {
    	int angle = 0;
    	angle = 90 - (360 / numOptions * i);
    	return angle;
    }
    
    //rotates individual text views all at once
    public void rotateWordsForWheel() {
    	ArrayList<RotateAnimation> animations = new ArrayList<RotateAnimation>();
    	for(int q = 0; q < numChoicesOnWheel; q++){
    		double calculatedX = 0; //Placeholder value - awaiting Marshall's equation
    		calculatedX = findXFloat (q, numChoicesOnWheel);
    		double calculatedY = 0; //Placeholder value - awaiting Marshall's equation
    		calculatedY = findYFloat (q, numChoicesOnWheel);
    		//RotateAnimation newAnimation = new RotateAnimation(0f, endPoint + startPoint, Animation.RELATIVE_TO_PARENT, 0.25f, 
    		//Animation.RELATIVE_TO_PARENT, 0.5f);
    		RotateAnimation newAnimation = new RotateAnimation(startPoint, endPoint, Animation.RELATIVE_TO_SELF, (float)calculatedX, 
    				Animation.RELATIVE_TO_SELF, (float)calculatedY);
    		newAnimation.setInterpolator(new AccelerateInterpolator(0.1f));
    		newAnimation.setRepeatCount(repeatCount);
    		newAnimation.setDuration(spinDuration);
    		animations.add(newAnimation);
    	}
    	
    	int count = 0;
    	if (count < numChoicesOnWheel){
    		((View) findViewById(R.id.dOptionOne)).startAnimation(animations.get(0));
    		count++;
    	}
    	if (count < numChoicesOnWheel){
    		((View) findViewById(R.id.dOptionTwo)).startAnimation(animations.get(1));
    		count++;
    	}
    	if (count < numChoicesOnWheel){
    		((View) findViewById(R.id.dOptionThree)).startAnimation(animations.get(2));
    		count++;
    	}
    	if (count < numChoicesOnWheel){
    		((View) findViewById(R.id.dOptionFour)).startAnimation(animations.get(3));
    		count++;
    	}
    	if (count < numChoicesOnWheel){
    		((View) findViewById(R.id.dOptionFive)).startAnimation(animations.get(4));
    		count++;
    	}
    	if (count < numChoicesOnWheel){
    		((View) findViewById(R.id.dOptionSix)).startAnimation(animations.get(5));
    		count++;
    	}
    	if (count < numChoicesOnWheel){
    		((View) findViewById(R.id.dOptionSeven)).startAnimation(animations.get(6));
    		count++;
    	}
    	if (count < numChoicesOnWheel){
    		((View) findViewById(R.id.dOptionEight)).startAnimation(animations.get(7));
    		count++;
    	}
    	if (count < numChoicesOnWheel){
    		((View) findViewById(R.id.dOptionNine)).startAnimation(animations.get(8));
    		count++;
    	}
    	if (count < numChoicesOnWheel){
    		((View) findViewById(R.id.dOptionTen)).startAnimation(animations.get(9));
    		count++;
    	}
    	if (count < numChoicesOnWheel){
    		((View) findViewById(R.id.dOptionEleven)).startAnimation(animations.get(10));
    		count++;
    	}
    	if (count < numChoicesOnWheel){
    		((View) findViewById(R.id.dOptionTwelve)).startAnimation(animations.get(11));
    		count++;
    	}
    	if (count < numChoicesOnWheel){
    		((View) findViewById(R.id.dOptionThirteen)).startAnimation(animations.get(12));
    		count++;
    	}
    	if (count < numChoicesOnWheel){
    		((View) findViewById(R.id.dOptionFourteen)).startAnimation(animations.get(13));
    		count++;
    	}
    	if (count < numChoicesOnWheel){
    		((View) findViewById(R.id.dOptionFifteen)).startAnimation(animations.get(14));
    		count++;
    	}
    	if (count < numChoicesOnWheel){
    		((View) findViewById(R.id.dOptionSixteen)).startAnimation(animations.get(15));
    		count++;
    	}
    }
    
    public double findXFloat(int i, int numOptions) {
    	double x = (double)i / (double)numOptions;
    	double variable = Math.sin((Math.PI / 2) * (x * ((double)numOptions / ((double)numOptions / 4)))) * -1.7;
    	x = variable + 0.50;
    	return x;
    }
    
    public double findYFloat(int i, int numOptions) {
    	double x = (double)i / (double)numOptions * 8.0;
    	double variable = 8.2016 * Math.sin(0.79617 * x - 1.614);
    	return variable;
    }
    
    public void rotateView(){
    }

}
