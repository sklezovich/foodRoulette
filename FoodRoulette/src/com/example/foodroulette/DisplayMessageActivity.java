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

import android.os.Handler;
import android.view.animation.Animation.AnimationListener;
import java.util.Timer;
import java.util.TimerTask;

public class DisplayMessageActivity extends ActionBarActivity {
	
	String[] diningList = {"Red Robin", "Salty's", "Subway", "Burger King", "Denny's", "Saigon Deli", "IHOP", "Applebee's",
			"KFC", "Cracker Barrel", "Spaghetti Factory", "Olive Garden", "Luna Cafe", "Husky Deli", "QFC", "Safeway", "Outback", "Purple Cafe"};
	//String[] sashaStuff = Collection.toArray(new String[sashaStuff.size]);
	int numChoicesOnWheel = 16;
	int listLength = diningList.length;
	int xPivot = 335;
	int yPivot = 450;
	double padX = 0.0;
	double padY = 0.0;
	//final ImageView imageview = new ImageView(this);
	
	
	
	
	    //returns a random long between min and max!
		long randomWithRange(int min, int max)
		{
		   int range = (max - min) + 1;     
		   return (long)(Math.random() * range) + min;
		}
		public final float endPoint = 450; // randomWithRange(360, 800);
		public final int spinDuration = 4000; //randomWithRange(1000, 4000); //random duration between 1 & 4 seconds
		public static final int repeatCount = 0; //(int)Double.POSITIVE_INFINITY;
		public final float startPoint = 11.25f;  //initial angle of wheel view
		//Log.d(constants.TAG, "Duration: "+ spinDuration+", Repeat Count: "+repeatCount);

		
		
		
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        //setContentView(R.layout.fragment_display_message);
        FrameLayout frameLayout = new FrameLayout(this);
        //frameLayout frameLayout = new frameLayout(R.layout.fragment_display_message, this);
        //int frameLayout = (R.layout.fragment_display_message);
        //setContentView(R.layout.fragment_display_message);
        
        getDimensions();
        
        ImageView imageview = new ImageView(this);
        imageview.setImageResource(R.drawable.roulettewheelfixed); 
        frameLayout.addView(imageview);
        
        //Displays the individual words in a circle format
        displayIndividualDiningOptions(frameLayout);
        
        ImageView imageArrow = new ImageView(this);
        imageArrow.setImageResource(R.drawable.arrow);
        frameLayout.addView(imageArrow);

        // Set the frameLayout as the activity layout
        setContentView(frameLayout);
        
        //rotates the words
        rotateWordsForWheel();
        //rotateView();
        
        //Displays in a string the options that should show up on the wheel
        String messageAndOptions = getMessageFromIntent();
        
        //Create the text view message
        //TextView textView = new TextView(this);
        //textView.setTextSize(25);
        //textView.setText(messageAndOptions);
        //textView.setPadding(0, 0, 0, 0); 
        //frameLayout.addView(textView);
        
        final RotateAnimation anim = new RotateAnimation(startPoint, endPoint + startPoint, //2nd float sets target angle
    	        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
 	   anim.setInterpolator(new AccelerateInterpolator(0.1f));
 	   anim.setRepeatCount(repeatCount);
 	   anim.setDuration(spinDuration); //sets duration of rotation
 	   imageview.setRotation(42 + startPoint);
 	   imageview.startAnimation(anim); 
 	   

 	   /*
 	  anim.setAnimationListener(new Animation.AnimationListener() {
 	        @Override
 	        @TargetApi(14)
 	        public void onAnimationStart(Animation animation) {
 	        	//imageview.setRotation(90 + startPoint);
 	        }

 	        @Override
 	        @TargetApi(14)
 	        public void onAnimationEnd(Animation animation) {
 	        	findViewById(R.id.imageView1).setRotation(90 + startPoint);

 	        }

 	        @Override
 	        public void onAnimationRepeat(Animation animation) {

 	        }
 	    }); 
 	   */
        /*
        final float startPoint = 11.25f;  //initial angle of wheel view
        final RotateAnimation anim = new RotateAnimation(startPoint, endPoint + startPoint, //2nd float sets target angle
    	        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        final Button spinButton = (Button) findViewById(R.id.button1);
        final Button stopButton = (Button) findViewById(R.id.button2);
        final Button resetButton = (Button) findViewById(R.id.button3);
        
      //TextView that should display the current value of the wheel's duration
        final TextView durationTracker = (TextView) this.findViewById(R.id.textView1);
        durationTracker.setText(String.valueOf(spinDuration));
 
        //Spins the image when the Spin button is pressed
        spinButton.setOnClickListener(new View.OnClickListener() {
        	@TargetApi(14)
            public void onClick(View v) {
            	findViewById(R.id.imageView1).setRotation(0);  //Sets initial angle (resets to start)
            	   anim.setInterpolator(new LinearInterpolator());  //AccelerateInterpolator(-0.1f)
            	   anim.setRepeatCount(repeatCount);
            	   anim.setDuration(spinDuration); //sets duration of rotation
            	  //anim.setFillAfter(true);
                   final Handler handler = new Handler();
                   handler.postDelayed(new Runnable() {
                     @Override
                     @TargetApi(14)
                     public void run() {
                    	 //anim.cancel();
                    	 findViewById(R.id.imageView1).setRotation(90 + startPoint);
                     }
                   }, spinDuration);
                   findViewById(R.id.imageView1).startAnimation(anim); 
            }
        } );
        
      */
      
        /*
        anim.setAnimationListener(new Animation.AnimationListener() {
        @Override
        @TargetApi(14)
        public void onAnimationStart(Animation animation) {
        	//imageview.setRotation(90 + startPoint);
        }

        @Override
        @TargetApi(14)
        public void onAnimationEnd(Animation animation) {
        	imageview.setRotation(90 + startPoint);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }); 
    //Cancels the spinning *FOR TESTING - NOT RELEASE VERSION*
       
       
    stopButton.setOnClickListener(new View.OnClickListener() {
    	@TargetApi(14)
        public void onClick(View v) {
        	anim.cancel();
        	imageview.setRotation(90 + startPoint);
        }});

    //Resets wheel orientation *FOR TESTING - NOT RELEASE VERSION*
    resetButton.setOnClickListener(new View.OnClickListener() {
    	@TargetApi(14)
        public void onClick(View v) {
    		imageview.setRotation(startPoint);  //Sets initial angle (resets to start)
        }
    });
    */  
        
        
        
        
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
    
    //gets height and width dimensions of the device screen
    public void getDimensions(){
    	int widthScreen = this.getResources().getDisplayMetrics().widthPixels;
    	int heightScreen = this.getResources().getDisplayMetrics().heightPixels;
    	xPivot = (int)(widthScreen / 2.5);
    	yPivot = (int)(heightScreen / 2.3);
    }
    
    //Gets message from FillWheel.java
    public String getMessageFromIntent(){
    	int widthScreen = this.getResources().getDisplayMetrics().widthPixels;
    	int heightScreen = this.getResources().getDisplayMetrics().heightPixels;
    	
    	Intent intent = getIntent();
        String message = intent.getStringExtra(FillWheel.EXTRA_MESSAGE);
        String x = ListOfOptions(diningList);
        int width = findViewById(R.id.dOptionOne).getMeasuredWidth();
        String messageOptions = "Height = " + heightScreen + " width = " + widthScreen + " " + message + " " + x + " and th value for width is " + width;
    	return messageOptions;
    }
    
    //Displays in a string the options that should show up on the wheel
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
        	String diningOptionX = diningOption(diningList, i);
        	TextView Option = new TextView(this);
        	Option.setText(diningOptionX);
        	Option.setId(IdNames.get(i));
        	
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
        	String restrauntName = Option.getText().toString();
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
        }
    }
    
    //turns the restaurant names into an array of strings
    public String diningOption(String[] diningList, int i){
    	String option = diningList[i];
    	return option;
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
    	//Rotate words around point (xPivot, yPivot)
    	//int height = findViewById(R.id.dOptionOne).getMeasuredHeight();
    	//int width = findViewById(R.id.dOptionOne).getMeasuredWidth();
    	//pulsates differently based on screen dimensions
    	//double inf = 0; //Double.POSITIVE_INFINITY;

    	ArrayList<RotateAnimation> animations = new ArrayList<RotateAnimation>();
    	for(int q = 0; q < numChoicesOnWheel; q++){
    		double calculatedX = 0; //Placeholder value - awaiting Marshall's equation
    		calculatedX = findXFloat (q, numChoicesOnWheel);
    		double calculatedY = 0; //Placeholder value - awaiting Marshall's equation
    		calculatedY = findYFloat (q, numChoicesOnWheel);
    		//RotateAnimation newAnimation = new RotateAnimation(0f, endPoint + startPoint, Animation.RELATIVE_TO_PARENT, 0.25f, 
    				//Animation.RELATIVE_TO_PARENT, 0.5f);
    		RotateAnimation newAnimation = new RotateAnimation(0f, endPoint + startPoint, Animation.RELATIVE_TO_SELF, (float)calculatedX, 
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
    
    //rotates the entire linear layout that contains all of the text views
    public void rotateView(){
    	//Rotate words around point (xPivot, yPivot)
    	/*LinearLayout layout1 = (LinearLayout) findViewById(R.id.circleWords);
    	Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotatewords);
        layout1.setAnimation(anim);
        layout1.startAnimation(anim);*/
        
    	//LinearLayout layout1 = (LinearLayout) findViewById(R.id.circleWords);
    	//Animation rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotatewords);
        //LayoutAnimationController animController = new LayoutAnimationController(rotateAnim, 0);
        //layout1.setLayoutAnimation(animController);
    }
}
