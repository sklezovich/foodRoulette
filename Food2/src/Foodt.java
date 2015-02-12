
import java.util.ArrayList;
import java.util.Collection;

import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

public class Foodt {

	OAuthService service;
	Token accessToken;

	public static void main(String[] args) {
		ArrayList<String> ipreferences = new ArrayList<String> (); 
		ipreferences.add("Korean");
		ipreferences.add("Hot Dog");
		Foodf.setPreferences(ipreferences);
		Foodf.setRatings(3.5);
	
		int i = 1;
		for (Foodies f : Foodf.Random(args)){
			System.out.print(i+". "+f+"\n\n");
			i++;
		}
		System.out.println("Test Complete\n");
	}
}