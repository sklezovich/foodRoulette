import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.Collections;
import java.util.Scanner;


public class Foodt {

	private static Collection<Foodies> listy = new ArrayList<Foodies>();
	private static Scanner find;
	private static String cuisine;
	private static String name = "";
	private static int price;
	private static int rating;
	private static int distance;
	private static Scanner line;

	public static void main(String[] args) {
		//Foodies ex = new Foodies ("cuisine","name",price,rating,distance);
		//price = $1-5
		//rating = 1-5 stars by 0.5
		//distance in miles, should be under number they provide
		Foodies f1 = new Foodies ("Japanese","Ohiyo",2, 3, 5);
		Foodies f2 = new Foodies ("Italian","Pasta Pasta",5, 4, 20);
		
		Foodies(f1, f2); //working test for foodies
		System.out.println("Test Complete\n");
		
		try {
			Foodb(); //working test for Foodb and foodies
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		System.out.println("Test Complete\n");
		
		preferencesCollection();//uses an imported list and sets as a collection
		
		System.out.println("Test Complete\n");
		
		//checks to see what the current preferences are now
		for(String s : Foodf.getPreferences()){ //tests if the arraylist is adopted into a
			//collection in Foodf
			System.out.println(s);
		}
		System.out.println("Test Complete\n");
		
		//Listy();//prints foods from listy()
		
		try {
			listy();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Test Complete\n");
		
		Listy();
		
		System.out.println("Test Complete\n");
		
		/*try {
			Foodf.listy();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		for(Foodies f : Foodf.getListy()){ 
			System.out.println(f);
		}
		
		System.out.println("Test Complete\n");
		
		for (Foodies f : Foodf.Random()){
			System.out.println(f);
		}
		
		System.out.println("Test Complete\n");
		
		preferencesCollectionPlus();//preferences with a change 
		for (String p : Foodf.getPreferences()){//does show a change
			System.out.println(p);
		}
		//CHECK RANDOM THEN LISTY OUTPUT
		for (Foodies f : Foodf.getListy()){
			System.out.println(f);
		}
		System.out.println();
		for (Foodies f : Foodf.Random()){
			System.out.println(f);
		}
		
	}
	
	//already tests for the object foodies
	public static void Foodies(Foodies f1, Foodies f2){
		System.out.println(f1);
		System.out.println(f2);
	}
	
	//tests for import foodb to make object foodies
	public static void Foodb() throws FileNotFoundException{
		find = new Scanner (new File("Foodb.txt"));
		Scanner line;
		while (find.hasNextLine()){
			
			//use this code for foodf for search in listy
			String s = find.nextLine();
			line = new Scanner (s);//creating scanner for line individually
			
			cuisine = line.next();
			
			//creating the name input
			while (!line.hasNextInt()){ //sees that next is not an int, more strings
				String add = line.next()+" ";
				name+=add;
			}
			
			price = line.nextInt();
			rating = line.nextInt();
			distance = line.nextInt();
			
			//creates new object using input from the line
			Foodies neuw = new Foodies (cuisine,name,price,rating,distance);
			System.out.println(neuw);
			name = ""; //resetting name to empty
		}
	}
	
	public static void preferencesCollection(){
		ArrayList<String> ipreferences = new ArrayList<String> (); // creates an arraylist
		//sets choice of preferences, must go through all
		ipreferences.add("Italian");//does find and print correctly
		ipreferences.add("Korean");//should not be found later on
		ipreferences.add("Japanese");//finds it but cannot break foreach loop to next line
		ipreferences.add("Diner");
		
		Foodf.setPreferences (ipreferences);//should set as collection
		
		for(String s : Foodf.getPreferences()){ //tests if the arraylist is adopted into a
												//collection in Foodf
		    System.out.println(s);
		}
		
	}
	public static void preferencesCollectionPlus(){
		ArrayList<String> ipreferences = new ArrayList<String> (); // creates an arraylist
		//sets choice of preferences, must go through all
		ipreferences.add("Italian");//does find and print correctly
		ipreferences.add("Korean");//should not be found later on
		ipreferences.add("Japanese");//finds it but cannot break foreach loop to next line
		ipreferences.add("Diner");
		ipreferences.add("Yummy");
		
		Foodf.setPreferences (ipreferences);//should set as collection
		
		for(String s : Foodf.getPreferences()){ //tests if the arraylist is adopted into a
												//collection in Foodf
		    System.out.println(s);
		}
	}

	//preferences should have been set by above method
	//creates method to search using preferences
	public static void Listy(){
		for(Foodies food : listy){//searches through each food in listy
			System.out.println(food);//should return the toString
		}
	}
	
	public static void listy()throws FileNotFoundException{
		
		//COME BACK TO THIS
		//for (String pref : Foodf.getPreferences()){//for each preference
			//System.out.println(pref);
		//}//COME BACK TO THIS
		
		//for (int i=0; i < Foodf.getPreferences().size(); i++){
			//System.out.println("PREF INDEX = "+i);//what round it is at
			//String pref = (String) Foodf.getPreferences().toArray()[i];//sets cuisine pref
			//System.out.println("PREF = "+pref);//prints what pref it is on
			find = new Scanner (new File("Foodb.txt"));//creates a local scanner for file base
			while (find.hasNextLine()){
				System.out.println("HAS NEXT LINE");//sees if this is being reached
				String s = find.nextLine();
				System.out.println("LINE IN FOODB IS = "+s);//prints out what the next line is
				line = new Scanner (s);
				
				//checks to see if cuisines match
				String pcuisine = line.next();//value of possible cuisine
				System.out.println("CUSINE = "+pcuisine);//to see what the next food is
				
					System.out.println("GO IS TRUE\n");
					//if (go==true){//should continue
						for(String pref : Foodf.getPreferences()){
							//pref should be the string at smaller-larger index
							System.out.println("PREF = "+pref);//sees what pref is at each place
							if(pref.equalsIgnoreCase(pcuisine)){//sees if they match up
								System.out.println("A MATCH");//if it gets here
								System.out.println("GO IS FALSE");//go will be set at the end
								cuisine = pcuisine;
								
								//creating the name input
								while (!line.hasNextInt()){ //sees that next is not an int, more strings
									System.out.println("SEVERAL WORD NAME");
									String add = line.next()+" ";
									name+=add;//adds onto the name of the place
									System.out.println("NAME IS = "+name);//to see it adds on properly
								}
								
								//sets remaining variables
								price = line.nextInt();
								System.out.println("PRICE IS = "+price);//checks price
								rating = line.nextInt();
								System.out.println("RATING IS = "+rating);//checks rating
								distance = line.nextInt();
								System.out.println("DISTANCE IS = "+distance);//checks distance
								
								//creates new object using input from the line
								Foodies neuw = new Foodies (cuisine,name,price,rating,distance);
								System.out.println(neuw+"\n");//sound return toString
								
								System.out.println("HERE");
								listy.add(neuw);//issue adding to list
								
								System.out.println("TESTING");
								int j = 0;
								for (Foodies f : listy){//should test for contents of listy
									System.out.println(f);
									
									System.out.println(j);
									j++;
								}
								System.out.println("TESTING\n");
								
								//System.out.println(listy.toArray()[i]);//should print above at that index
								name = ""; //resetting name to empty
								System.out.println("GO IS FALSE");//ERROR IS HERE HERE HERE
								
							} else {
								System.out.println("NOT A MATCH");
								
								System.out.println("GO IS STILL TRUE");
								//listy();
								//System.out.println("RECURSSION HAPPENED2");
							}
							System.out.println("WILL CHECK NEXT PREF\n");
							//listy();
							//System.out.println("RECURSSION HAPPENED3");
						}
						System.out.println("WILL SEARCH FOR NEXT LINE IN FOODB");
					//}else {//go is false
						//System.out.println("WILL SEARCH FOR NEXT LINE IN FOODB");
					//}
			}
			System.out.println("DOES NOT HAVE NEXT LINE");
			//so far, works when there are no matches
		}
	}