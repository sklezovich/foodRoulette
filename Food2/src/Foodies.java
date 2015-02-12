
//creates object where foods can be created
public class Foodies {
	private String cuisine;
	private String name;
	private int pricing;
	private double rating;
	private double distance;
	private String lat;
	private String lng;
	
	//initializes
	public Foodies (String c, String n, int p, double r, double d, String la, String lo){
		cuisine = c;
		name = n;
		pricing = p;
		rating = r;
		distance = d;
		lat=la;
		lng=lo;
	}
	
	//allows the return of the cuisine
	public String cuisine(){
		return cuisine;
	}
	
	//allows the return of the name
	public String name(){
		return name;
	}
	
	//allows the return of the pricing
	public int pricing(){
		return pricing;
	}
	
	//allows the return of the rating
	public double rating(){
		return rating;
	}
	
	//allows the rerturn of the distance
	public double distance(){
		return distance;
	}
	
	//allows the return of latitude
	public String lat(){
		return lat;
	}
	
	//allows the return of longitude
	public String lng(){
		return lng;
	}
	
	@Override
    //prints out rank, suit, and point value 
    public String toString() {
       return (name+" is "+cuisine+" and has a rating of "+rating+". Pricing is non-existant "+
       		"and it is "+distance+" miles away. Latitude is "+lat+" and the "
    		   +"Longitude is "+lng+".");
    }
}
