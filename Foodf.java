import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//will take in the preferences and search the Foodies object for 
//matches

public class Foodf {
	private static Scanner find;
	private static String cuisine;
	private static String name = "";
	private static int price;
	private static int rating;
	private static int distance;
	private static Collection<String> preferences;//inputed 
	private static Collection<Foodies> done = new ArrayList<Foodies>();
	private static Scanner line;//scanner for each individual line
	
	//Constructor that sets preferences as an ArrayList
	public Foodf () {
		preferences = new ArrayList<String>();//all in cuisines
	}
	
	//sets imported preferences to use as preferences
	public static void setPreferences (ArrayList<String> ipreferences){
			preferences = ipreferences;
	}
	
	public static Collection<String> getPreferences(){
		return preferences;
	}
	
	//call method which produces the randomc
	public static Collection<Foodies> Random(){
		Collections.shuffle((List<?>) done); 
		return done;//make random list altering  
	}
	
	//search through preferences and Foodb file to make listy
	public static void listy()throws FileNotFoundException{
		Collection<Foodies> listy = new ArrayList<Foodies>();//created from search
		find = new Scanner (new File("Foodb.txt"));//creates a local scanner for file base
		while (find.hasNextLine()){//if can continue in file
			String s = find.nextLine();//sets line to a variable for testing
			line = new Scanner (s);//scanner to test one line at a time
			
			//checks to see if cuisines match
			String pcuisine = line.next();//value of possible cuisine

			for(String pref : preferences){//searches through inputted preferences
				if(pref.equalsIgnoreCase(pcuisine)){//sees if they match up
					cuisine = pcuisine;//sets variable of the object to the possible cuisine from food base
					while (!line.hasNextInt()){ //sees that next is not an int, more strings
						String add = line.next()+" ";//section of name that must be added each time
						name+=add;//adds onto the name of the place
					}
					
					//sets remaining variables
					price = line.nextInt();//sets rating
					rating = line.nextInt();//sets rating
					distance = line.nextInt();//sets distance
					
					//creates new object using input from the line
					Foodies neuw = new Foodies (cuisine,name,price,rating,distance);
					listy.add(neuw);//adds food match to the list
					name = ""; //resetting name to empty
				}//checks for next preference
			}//will search for next line in foodb
		}//does not have next line in foodb
		done = listy;
	}

	//allows access to the list
	public static Collection<Foodies> getListy(){
		try {
			listy();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return done;
	}
}
