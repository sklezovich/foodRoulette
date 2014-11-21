
//creates object where foods can be created
public class Foodies {
	private String cuisine;
	private String name;
	private int pricing;
	private int rating;
	private int distance;
	
	//initializes
	public Foodies (String c, String n, int p, int r, int d){
		cuisine = c;
		name = n;
		pricing = p;
		rating = r;
		distance = d;
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
	public int rating(){
		return rating;
	}
	
	//allows the rerturn of the distance
	public int distance(){
		return distance;
	}
	
	@Override
    //prints out rank, suit, and point value 
    public String toString() {
       return (name+" is "+cuisine+" and has a rating of "+rating+". Pricing is "
    		   +pricing+" and it is "+distance+" miles away.");
    }
}
