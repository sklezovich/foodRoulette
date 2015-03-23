package com.example.foodroulette;
//use use use use
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import com.beust.jcommander.JCommander;

public class PreferencesActivity extends ActionBarActivity implements OnRatingBarChangeListener, OnSeekBarChangeListener, OnClickListener{
	
   static ArrayList<String> cuisine;
   
   Intent basdf;
   double ratings;
   double numstars;
   double distance;
   private SeekBar distances;
   String[] args;
   Button spin;
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
	
	public PreferencesActivity() {
 	}
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
	    cuisine = new ArrayList<String>();
	    basdf = new Intent (this, PrintPLease.class);
        
        setContentView(R.layout.activity_preferences);
     
     spin = (Button) findViewById(R.id.button_SPIN);
     spin.setOnClickListener(this);

    RatingBar ratings = (RatingBar) findViewById(R.id.ratingBar_ratings);
    ratings.setOnRatingBarChangeListener(this);
    
    distances = (SeekBar) findViewById(R.id.seekBar_distance);
    
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
		
    	}
    });
    }
    
    class preferencesThread extends AsyncTask< String, Void,String> {
        public preferencesThread(PreferencesActivity preferencesActivity) {
		}
        
        Collection<Foodies> listy = new ArrayList<Foodies>();
        
		@Override
        protected String doInBackground(String... params) {
			Log.v("blah","checky checky checky");
        	try {
                Thread.sleep(1000);
                Log.v("blah","checky checky checky");
                YelpAPI.setLocation("Seattle", "WA");
				for (String pref : preferences){
					try {
						YelpAPI.setLimit(20);
						Log.v("blah","ten");
					} catch (Exception e) {
						Log.v(pref, "Lookie here "+e.toString());
					}
					
					
					YelpAPI.setTerm(pref);
					YelpAPI yelper = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
					YelpAPI.YelpAPICLI yelperApiCli = new YelpAPI.YelpAPICLI();
					Foodies neuw = new Foodies (cuisiney,name,price,rating,distancey,lat,lng);
					
					for (Object firstB :YelpAPI.queryAPI(yelper, yelperApiCli)){
						JSONObject b = (JSONObject) firstB;
						cuisiney = pref;
						name = b.get("name").toString();
						price = 0;
						rating = Double.parseDouble(b.get("rating").toString());
						String location = b.get("location").toString();
						distancey = FoodfActivity.getDistance(location);
						lat = FoodfActivity.getLat();
						lng = FoodfActivity.getLng();
						Foodies neuwy = new Foodies (cuisiney,name,price,rating,distancey,lat,lng);
						neuw = neuwy;
						listy.add(neuw);
					}
				}
				done = listy;
            } catch (Exception e) {
                e.printStackTrace();
			}
            return "";
        }
		
        @Override
        protected void onPostExecute(String result) {
        	int i = 1;
        	Collection<Foodies> whatever = FoodfActivity.Random(args,done,numstars);
        	
        	//whatever is what we want!!!!
        	//can either pass as a parameter right here or make an over-arching variable
        	//if pass, call intent here? 
        	
        	//comment bottom portion during integration 
        	String[] stringArr = new String[whatever.size()];
        	Collection<Foodies> sfg = whatever;
        	
        	int j = 0;
        		for (Foodies f: whatever){
        			stringArr[j] = f.toString();
        			j++;
        		}

        	
        	Bundle bundle = new Bundle(); 
        	bundle.putStringArray("key", stringArr);
        	basdf.putExtras(bundle);
        	startActivity(basdf);
        	//for (Foodies f: whatever){
        		//Log.v("PA",i+". "+f.toString());
    
        		//ugh.setText("a");//not doing anything?
        		//TextView myT = new TextView(null);
        		//yT.setText(f.toString());
        		
        		//ugh.setText(f.toString());
        		//ugh.addView(myT);
        		i++;
        }
        
       @Override
        protected void onPreExecute() {
        	
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.preferences, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void onCheckboxClicked(View view) {
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
	
	public void onRatingChanged(RatingBar ratingBar, float rating,
		boolean fromTouch) {
		numstars = rating;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {	
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	}		
	
	public void onClick(View v) {	
		switch(v.getId()) {
			case R.id.button_SPIN:
				
				ArrayList<String> ip = new ArrayList<String> ();
				ip = cuisine;
				preferences = ip;
				preferencesThread preT = new preferencesThread(this);
				preT.execute("");
				
			break;
	
		}
	}
}
	


