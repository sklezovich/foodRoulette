package com.example.foodroulettewheeltesting;

//HTML NOTE: Correct image file is "roulettewheelfixed"

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.*;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.TextView;
import android.annotation.TargetApi;
import java.lang.Math;
import java.util.Timer;
import java.util.TimerTask;

public class WheelTesting extends ActionBarActivity {
	
	//returns a random long between min and max!
	long randomWithRange(int min, int max)
	{
		
	   int range = (max - min) + 1;     
	   return (long)(Math.random() * range) + min;
	}
	public final float endPoint = 450; // randomWithRange(360, 800);
	public final int spinDuration = 4000; //randomWithRange(1000, 4000); //random duration between 1 & 4 seconds
	public static final int repeatCount = 0; //(int)Double.POSITIVE_INFINITY;
	//Log.d(constants.TAG, "Duration: "+ spinDuration+", Repeat Count: "+repeatCount);
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                   ((View) findViewById(R.id.imageView1)).startAnimation(anim); 
            }
        });
        

        
        /*anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            @TargetApi(14)
            public void onAnimationStart(Animation animation) {
            	//findViewById(R.id.imageView1).setRotation(90 + startPoint);
            }

            @Override
            @TargetApi(14)
            public void onAnimationEnd(Animation animation) {
            	findViewById(R.id.imageView1).setRotation(90 + startPoint);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        }); */
        //Cancels the spinning *FOR TESTING - NOT RELEASE VERSION*
        stopButton.setOnClickListener(new View.OnClickListener() {
        	@TargetApi(14)
            public void onClick(View v) {
            	anim.cancel();
            	findViewById(R.id.imageView1).setRotation(90 + startPoint);
            }});
    
        //Resets wheel orientation *FOR TESTING - NOT RELEASE VERSION*
        resetButton.setOnClickListener(new View.OnClickListener() {
        	@TargetApi(14)
            public void onClick(View v) {
        		findViewById(R.id.imageView1).setRotation(startPoint);  //Sets initial angle (resets to start)
            }
        });
    }
    	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

}