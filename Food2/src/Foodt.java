
import java.util.ArrayList;

import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;


//http://www.androidhive.info/2012/07/android-gps-location-manager-tutorial/

//double lat = Double.parseDouble(f.lat());
//double lng = Double.parseDouble(f.lng());

//Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
//Uri.parse("geo:0,0?q=lat,lng (" + name + ")"));
//startActivity(intent);

//Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
	//    Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=lat,lng"));
	//startActivity(intent);

public class Foodt {

	OAuthService service;
	Token accessToken;
	private static double r = 0.0;

	public static void main(String[] args) {
		ArrayList<String> ipreferences = new ArrayList<String> (); 
		ipreferences.add("Japanese");
		ipreferences.add("Thai");
		Foodf.setPreferences(ipreferences);
		//setRatings(3.5);
		
		int i =1;
		for (Foodies f : Foodf.Random(args,r)){

			System.out.print(i+". "+f+"\n\n");
			i++;
		}
	}
	
	public static void setRatings(double rating){
		r = rating;
	}
}