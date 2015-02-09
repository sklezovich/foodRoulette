package com.example.foodroulette;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.System;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import com.beust.jcommander.JCommander;

public class PreferencesActivity extends ActionBarActivity implements OnItemSelectedListener, OnRatingBarChangeListener, OnSeekBarChangeListener, OnClickListener{
	
   static ArrayList<String> cuisine = new ArrayList<String>();
   int budget;
   double ratings;
   double numstars;
   double distance;
   private SeekBar distances;
   private TextView distanceValue;
   private TextView ratingsValue;
   private TextView budgetValue;
   String[] args;
   Button spin;
    
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
     
     spin = (Button) findViewById(R.id.button_SPIN);
     spin.setOnClickListener(this);

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
    
    class preferencesThread extends AsyncTask< String, Void,String> {
        public preferencesThread(PreferencesActivity preferencesActivity) {
			// TODO Auto-generated constructor stub
		}
        
        Collection<Foodies> listy = new ArrayList<Foodies>();
        
		@Override
        protected String doInBackground(String... params) {
			//for (int i=0; i < 5; i++){
			
        	try {
                Thread.sleep(1000);
                //listy(params);
                
                //YelpAPI yelper = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
				//YelpAPI.YelpAPICLI yelperApiCli = new YelpAPI.YelpAPICLI();
				//YelpAPI.queryAPI(yelper, yelperApiCli);
                YelpAPI.setLocation("Seattle", "WA");
				for (String pref : preferences){
					YelpAPI.setLimit(5);
					YelpAPI.setTerm(pref);
					YelpAPI yelper = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
					YelpAPI.YelpAPICLI yelperApiCli = new YelpAPI.YelpAPICLI();
					//new JCommander(yelperApiCli, args);
					Foodies neuw = new Foodies (cuisiney,name,price,rating,distancey,lat,lng);
					
					for (Object firstB :YelpAPI.queryAPI(yelper, yelperApiCli)){
						JSONObject b = (JSONObject) firstB;
						cuisiney = pref;
						name = b.get("name").toString();
						price = 0;
						rating = Double.parseDouble(b.get("rating").toString());
						String location = b.get("location").toString();
						distancey = getDistance(location);
						Foodies neuwy = new Foodies (cuisiney,name,price,rating,distancey,lat,lng);
						neuw = neuwy;
						//Foodies neuw = new Foodies (cuisiney,name,price,rating,distancey,lat,lng);
						listy.add(neuw);
						//return neuw.toString();
						//return neuw.toString();
					}
					return neuw.toString();
				}
            } catch (InterruptedException e) {
                e.printStackTrace();
                //Thread.interrupted();
            //}
            //TextView txt = (TextView) findViewById(R.id.output);
           // txt.setText("Executed");
			}
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            //TextView txt = (TextView) findViewById(R.id.output);
            //txt.setText(result); // txt.setText(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
			
        	
        	for (Foodies f: listy){
        		
        		TextView txt = (TextView) findViewById(R.id.output);
        		txt.setText(f.toString());
        	}
        }
        
        @Override
        protected void onPreExecute() {
        	
        }
        
        /*
        protected void onPostExecute(Boolean result) {
            //TextView txt = (TextView) findViewById(R.id.output);
            //txt.setText(result); // txt.setText(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        	TextView txt = (TextView) findViewById(R.id.output);
            txt.setText("Executed");
        	
        }*/

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
    //}
    
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
    
    public static ArrayList<String> getCuisines() {
    	return cuisine;
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
	
	public void onClick(View v) {
		
		switch(v.getId()) {
			case R.id.button_SPIN:
				Intent intent = new Intent (this, PrintPLease.class);
				startActivity(intent);
				
				
				ArrayList<String> ip = new ArrayList<String> ();
				//ip.add("Korean");
				//ip.add("Japanese");
				ip = cuisine;
				setPreferences(ip);
				preferencesThread preT = new preferencesThread(this);
				preT.execute("");
				
				//Collection<String> ap = new ArrayList<String> ();
				//ap = ip;//FoodfActivity.getPreferences();
				int i = 1;
				//for (Foodies f :Random(args)){
				//	System.out.print(i+". ");
					//System.out.println(f+"\n");
					i++;
				//}
				/*Intent intent = new Intent (this, FoodfActivity.class);
					startActivity(intent);*/
			break;
	
		}
	}
	//*FoodF Code*/
	//call method which produces the randomc
	private static String cuisiney;
	private static String name = "";
	private static int price;
	private static double rating;
	private static double distancey;
	private static String lat;
	private static String lng;
	
	private static Collection<String> preferences;
	private static Collection<Foodies> done = new ArrayList<Foodies>();
	
	private static final String CONSUMER_KEY = "2-9FkxPyl4-R-8ouw9Ilww";
	private static final String CONSUMER_SECRET = "894AUSkNSOvx_Smt7VBssFieSNQ";
	private static final String TOKEN = "qB13Ii8bQ3jToJxQRsI9L3huiikX01Bd";
	private static final String TOKEN_SECRET = "BTcOJmACrxAg6jBJ_trM3nXCfvs";
	OAuthService service;
	Token accessToken;
	
			public static void setPreferences (ArrayList<String> ipreferences){
				preferences = ipreferences;
			}
			public Collection<Foodies> Random(String[] args){
				getListy(args);
				Collections.shuffle((List<?>) done); 
				return done;//make random list altering  
			}
			
			//search through cuisines to produce a list of food restaurants from yelp
			public void listy(String[] args){
				Collection<Foodies> listy = new ArrayList<Foodies>();
				YelpAPI.setLocation("Seattle", "WA");
				for (String pref : preferences){
					YelpAPI.setLimit(5);
					YelpAPI.setTerm(pref);
					YelpAPI yelper = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
					YelpAPI.YelpAPICLI yelperApiCli = new YelpAPI.YelpAPICLI();
					//new JCommander(yelperApiCli, args);
					
					for (Object firstB :YelpAPI.queryAPI(yelper, yelperApiCli)){
						JSONObject b = (JSONObject) firstB;
						cuisiney = pref;
						name = b.get("name").toString();
						price = 0;
						rating = Double.parseDouble(b.get("rating").toString());
						String location = b.get("location").toString();
						distancey = getDistance(location);

						Foodies neuw = new Foodies (cuisiney,name,price,rating,distancey,lat,lng);
						listy.add(neuw);
					}
				}
				done = listy;
			}

			//allows access to the list
			public Collection<Foodies> getListy(String[] args){
				listy(args);
				return done;
			}
			public static double getDistance(String location){
				double lng1 = -122.301436; //using garfield
				double lat1 = 47.605387; //using garfield
				
				int startIndex1 = location.indexOf("longitude");//finds the start value of this word
				lng = location.substring(startIndex1+11, startIndex1+21);//getting the longitude component
				int startIndex2 = location.indexOf("latitude");
				lat = location.substring(startIndex2+10, startIndex2+18);//getting the latitude component	

				double lng2 = Double.parseDouble(lng);
				double lat2 = Double.parseDouble(lat);

				double R = 6371; // km
				double vlat1 = lat1*Math.PI/180; //radians = degrees*pi/180
				double vlat2 = lat2*Math.PI/180;
				double changelat = (lat2-lat1)*Math.PI/180;
				double changelng = (lng2-lng1)*Math.PI/180;
				double a = Math.sin(changelat/2) * Math.sin(changelat/2) +
				        Math.cos(vlat1) * Math.cos(vlat2) *
				        Math.sin(changelng/2) * Math.sin(changelng/2);
				double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
				double d = R * c;
				
				DecimalFormat df = new DecimalFormat("#.##");
		        String dis = df.format(d);
		        double dist = Double.parseDouble(dis);
		        
				return dist;
			}
			
			
			///http://stackoverflow.com/questions/20950948/android-tumblr-api-jumblr-for-android-oauthconnectionexception
}
	


