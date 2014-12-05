package com.example.foodroulette;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;


public class PreferencesActivity extends ActionBarActivity implements OnItemSelectedListener, OnRatingBarChangeListener, OnSeekBarChangeListener {
	
    ArrayList<String> cuisine = new ArrayList<String>();
   int budget;
   double ratings;
   double numstars;
   double distance;
   private SeekBar distances;
   private TextView distanceValue;
   private TextView ratingsValue;
   private TextView budgetValue;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        
     Spinner spinner = (Spinner) findViewById(R.id.spinner_budget);
     // Create an ArrayAdapter using the string array and a default spinner layout
     ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
             R.array.budget_array, android.R.layout.simple_spinner_item);
     // Specify the layout to use when the list of choices appears
     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     // Apply the adapter to the spinner/    
     spinner.setAdapter(adapter);
     spinner.setOnItemSelectedListener(this);
     budgetValue = (TextView) findViewById(R.id.BudgetValue);
     
//    Spinner spinner2 = (Spinner) findViewById(R.id.spinner_ratings);
//    ArrayAdapter<CharSequence> adapter2  = ArrayAdapter.createFromResource(this,
//    		R.array.ratings_array, android.R.layout.simple_spinner_item);
//    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//    spinner2.setAdapter(adapter2);
//    spinner2.setOnItemSelectedListener(this);
    
    RatingBar ratings = (RatingBar) findViewById(R.id.ratingBar_ratings);
    ratings.setOnRatingBarChangeListener(this);
    ratingsValue = (TextView) findViewById(R.id.RatingsValue);
    
    distances = (SeekBar) findViewById(R.id.seekBar_distance);
    distanceValue = (TextView) findViewById(R.id.distanceText);
    distanceValue.setText(distances.getProgress() + "/" + distances.getMax());
    
    distances.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
    	@Override
    	public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
    		distance = progressValue;
    	}


    	@Override
    	public void onStartTrackingTouch(SeekBar arg0) {
	
    	}


    	@Override
		public void onStopTrackingTouch(SeekBar arg0) {
    		distanceValue.setText(distances.getProgress()+ "/" + distances.getMax());
		
    	}
    });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.preferences, menu);
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
    
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        CheckBox x = (CheckBox) view;
        if (checked) {
            cuisine.add(x.getText().toString());
        }
       else  {
        	cuisine.remove(cuisine.indexOf(x.getText().toString()));
       }
        	
    }
	public void onItemSelected(AdapterView<?> parent, View view, 
        int pos, long id) {
        switch (parent.getId()) {
        case R.id.spinner_budget:
        	budget = pos + 1;
        	budgetValue.setText(budget + "/ 5");
            break;
//        case R.id.spinner_ratings:
//        	ratings = pos;
//            break;
//        default:
//            break;
	}
	}  

	public void onNothingSelected(AdapterView<?> parent) {
		// Another interface callback
	}
	
	public void onRatingChanged(RatingBar ratingBar, float rating,
		boolean fromTouch) {
		numstars = rating;
		ratingsValue.setText(numstars + " / 5");
			
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}		
}
	


