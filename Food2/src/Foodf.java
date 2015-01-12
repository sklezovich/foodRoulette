import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import com.beust.jcommander.JCommander;

//will take in the preferences and search the Foodies object for 
//matches

public class Foodf {
	private static String cuisine;
	private static String name = "";
	private static int price;
	private static double rating;
	private static double distance;
	private static String lat;
	private static String lng;
	
	private static Collection<String> preferences;
	private static double ratings;
	private static int pricings;
	private static double distances;
	
	private static Collection<Foodies> done = new ArrayList<Foodies>();
	
	private static final String CONSUMER_KEY = "2-9FkxPyl4-R-8ouw9Ilww";
	private static final String CONSUMER_SECRET = "894AUSkNSOvx_Smt7VBssFieSNQ";
	private static final String TOKEN = "qB13Ii8bQ3jToJxQRsI9L3huiikX01Bd";
	private static final String TOKEN_SECRET = "BTcOJmACrxAg6jBJ_trM3nXCfvs";
	OAuthService service;
	Token accessToken;
	
	//Constructor that sets preferences as ArrayLists
	public Foodf () {
		preferences = new ArrayList<String>();
	}
	
	//sets imported preferences to use as preferences
	public static void setPreferences (ArrayList<String> ipreferences){
			preferences = ipreferences;
	}
	
	//sets imported ratings to use as ratings variable for narrowing
	public static void setRatings (double irating){
			ratings = irating;
	}
	
	//sets imported pricings to use as pricings variable for narrowing
	public static void setPricings (int ipricings){
			pricings = ipricings;
	}
	
	//sets imported distances to use as distances variable for narrowing
	public static void setDistances (double idistances){
			distances = idistances;
	}
	
	//allows for the return of preferences
	public static Collection<String> getPreferences(){
		return preferences;
	}
	
	//call method which produces the randomc
	public static Collection<Foodies> Random(String[] args){
		getListy(args);
		searchByRating();
		Collections.shuffle((List<?>) done); 
		return done;//make random list altering  
	}
	
	//search through cuisines to produce a list of food restaurants from yelp
	public static void listy(String[] args){
		Collection<Foodies> listy = new ArrayList<Foodies>();
		YelpAPI.setLocation("Seattle", "WA");
		for (String pref : preferences){
			YelpAPI.setLimit(5);
			YelpAPI.setTerm(pref);
			YelpAPI yelper = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
			YelpAPI.YelpAPICLI yelperApiCli = new YelpAPI.YelpAPICLI();
			new JCommander(yelperApiCli, args);
			
			for (Object firstB :YelpAPI.queryAPI(yelper, yelperApiCli)){
				JSONObject b = (JSONObject) firstB;
				cuisine = pref;
				name = b.get("name").toString();
				price = 0;
				rating = Double.parseDouble(b.get("rating").toString());
				String location = b.get("location").toString();
				distance = getDistance(location);

				Foodies neuw = new Foodies (cuisine,name,price,rating,distance,lat,lng);
				listy.add(neuw);
			}
		}
		done = listy;
	}

	//allows access to the list
	public static Collection<Foodies> getListy(String[] args){
		listy(args);
		return done;
	}
	
	//allows to limit search by the ratings
	public static void searchByRating(){
		Collection<Foodies> temp = new ArrayList<Foodies>();
		for (Foodies f : done){
			if ((f.rating() >= (ratings))){ //|| (f.rating() < (ratings - 1))){
				temp.add(f);	//get rid of that food object
			}
		}
		done = temp;
	}
	
	//allows for the calculation of distance from lat and lng coordinates
	public static double getDistance(String location){
		double lng1 = -122.301436; //using garfield
		double lat1 = 47.605387; //using garfield
		
		int startIndex1 = location.indexOf("longitude");//finds the start value of this word
		lng = location.substring(startIndex1+11, startIndex1+22);//getting the longitude component
		int startIndex2 = location.indexOf("latitude");
		lat = location.substring(startIndex2+10, startIndex2+19);//getting the latitude component	

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
}

