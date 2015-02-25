
import java.util.ArrayList;
import java.util.Collection;

import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

public class Foodt {

	OAuthService service;
	Token accessToken;
	private static Collection<Foodies> temp = new ArrayList<Foodies>();
	private static double r = 0.0;

	public static void main(String[] args) {
		ArrayList<String> ipreferences = new ArrayList<String> (); 
		ipreferences.add("Korean");
		ipreferences.add("Thai");
		Foodf.setPreferences(ipreferences);
		setRatings(4.0);
	
		//should print out original without ratings involved
		int j = 1;
		for (Foodies f : Foodf.Random(args)){
			if (f.rating() >= r){
				temp.add(f);
			}
			System.out.print(j+". "+f+"\n\n");
			j++;
		}
		
		System.out.println("This");
		int i = 1;
		for (Foodies f : temp){
			System.out.print(i+". "+f+"\n\n");
			i++;
		}
		//System.out.println("Test Complete\n");
		System.out.println("This");
	}
	
	public static void setRatings(double rating){
		r = rating;
	}
}