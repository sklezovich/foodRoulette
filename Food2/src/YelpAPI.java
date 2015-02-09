import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

/**
* Code sample for accessing the Yelp API V2.
*
* This program demonstrates the capability of the Yelp API version 2.0 by using the Search API to
* query for businesses by a search term and location, and the Business API to query additional
* information about the top result from the search query.
*
* <p>
* See <a href="http://www.yelp.com/developers/documentation">Yelp Documentation</a> for more info.
*
*/

public class YelpAPI {
	private static final String API_HOST = "api.yelp.com";
	private static String DEFAULT_TERM = "dinner";//can be altered but isn't taking
	private static String DEFAULT_LOCATION = "San Francisco, CA";//can be altered but isn't taking
	private static int SEARCH_LIMIT = 3;//can be altered
	private static final String SEARCH_PATH = "/v2/search";
	private static final String BUSINESS_PATH = "/v2/business";
	
	/*
	 * Updated OAuth credentials below from the Yelp Developers API site:
	 * http://www.yelp.com/developers/getting_started/api_access
	 */
	
	/*private static final String CONSUMER_KEY = "2-9FkxPyl4-R-8ouw9Ilww";
	private static final String CONSUMER_SECRET = "894AUSkNSOvx_Smt7VBssFieSNQ";
	private static final String TOKEN = "qB13Ii8bQ3jToJxQRsI9L3huiikX01Bd";
	private static final String TOKEN_SECRET = "BTcOJmACrxAg6jBJ_trM3nXCfvs";*/
	
	static OAuthService service;
	static Token accessToken;
	
	/**
	 * Setup the Yelp API OAuth credentials.
	 *
	 * @param consumerKey Consumer key
	 * @param consumerSecret Consumer secret
	 * @param token Token
	 * @param tokenSecret Token secret
	 */
	
	//Sets up location if it has changed
	public static void setLocation(String City, String State){
		DEFAULT_LOCATION = City+", "+State;//SKETCHY //does update
	}
	
	//Allows for the return of the location
	public String getLocation(){
		return DEFAULT_LOCATION; //works very well
	}
	
	//Sets the search limit
	public static void setLimit(int num){
		SEARCH_LIMIT = num;
	}
	
	//Allows for the return of the search limit
	public int getLimit(){
		return SEARCH_LIMIT;
	}
	
	//Sets the term
	public static void setTerm(String food){
		DEFAULT_TERM = food;
	}
	
	//Allows for the return of the term
	public String getTerm(){
		return DEFAULT_TERM;
	}
	
	public YelpAPI(String consumerKey, String consumerSecret, String token, String tokenSecret) {
		YelpAPI.service =	new ServiceBuilder().provider(YelpV2API.class).apiKey(consumerKey)
						.apiSecret(consumerSecret).build();
		YelpAPI.accessToken = new Token(token, tokenSecret);
	}
	
	/**
	 * Creates and sends a request to the Search API by term and location.
	 * <p>
	 * See <a href="http://www.yelp.com/developers/documentation/v2/search_api">Yelp Search API V2</a>
	 * for more info.
	 *
	 * @param term <tt>String</tt> of the search term to be queried
	 * @param location <tt>String</tt> of the location
	 * @return <tt>String</tt> JSON Response
	 */
	
	public static String searchForBusinessesByLocation(String term, String location) {
		OAuthRequest request = createOAuthRequest(SEARCH_PATH);
		request.addQuerystringParameter("term", term);
		request.addQuerystringParameter("location", location);
		request.addQuerystringParameter("limit", String.valueOf(SEARCH_LIMIT));
		request.addQuerystringParameter("sort", String.valueOf(1));//searches based on distance
		return sendRequestAndGetResponse(request);
	}
	
	/**
	 * Creates and sends a request to the Business API by business ID.
	 * <p>
	 * See <a href="http://www.yelp.com/developers/documentation/v2/business">Yelp Business API V2</a>
	 * for more info.
	 *
	 * @param businessID <tt>String</tt> business ID of the requested business
	 * @return <tt>String</tt> JSON Response
	 */
	
	/*public String searchByBusinessId(String businessID) {
		OAuthRequest request = createOAuthRequest(BUSINESS_PATH + "/" + businessID);
		return sendRequestAndGetResponse(request);
	}*/
	
	/**
	 * Creates and returns an {@link OAuthRequest} based on the API endpoint specified.
	 *
	 * @param path API endpoint to be queried
	 * @return <tt>OAuthRequest</tt>
	 */
	
	private static OAuthRequest createOAuthRequest(String path) {
		OAuthRequest request = new OAuthRequest(Verb.GET, "http://" + API_HOST + path);
		return request;
	}
	
	/**
	 * Sends an {@link OAuthRequest} and returns the {@link Response} body.
	 *
	 * @param request {@link OAuthRequest} corresponding to the API request
	 * @return <tt>String</tt> body of API response
	 */
	
	private static String sendRequestAndGetResponse(OAuthRequest request) {
		//OAuthRequest request = new OAuthRequest(Verb.GET, "http://" + API_HOST + path);
		//System.out.println("Querying " + request.getCompleteUrl() + " ...");
		service.signRequest(accessToken, request);
		Response response = request.send();
		return response.getBody();
		//see what methods can be searched individually
	}
	
	/**
	 * Queries the Search API based on the command line arguments and takes the first result to query
	 * the Business API.
	 *
	 * @param yelpApi <tt>YelpAPI</tt> service instance
	 * @param yelpApiCli <tt>YelpAPICLI</tt> command line arguments
	 * @return 
	 */
	
	static JSONArray queryAPI(YelpAPI yelpApi, YelpAPICLI yelpApiCli) {
		String searchResponseJSON =	YelpAPI.searchForBusinessesByLocation(yelpApiCli.term, yelpApiCli.location);
		JSONParser parser = new JSONParser();
		JSONObject response = null;
		try {
			response = (JSONObject) parser.parse(searchResponseJSON);
		} catch (ParseException pe) {
			System.out.println("Error: could not parse JSON response:");
			System.out.println(searchResponseJSON);
			System.exit(1);
		}
		JSONArray businesses = (JSONArray) response.get("businesses");
		return businesses;
	}
	
	/**
	 * Command-line interface for the sample Yelp API runner.
	 */
	
	static class YelpAPICLI {
		@Parameter(names = {"-q", "--term"}, description = "Search Query Term")
		public String term = DEFAULT_TERM;
		@Parameter(names = {"-l", "--location"}, description = "Location to be Queried")
		public String location = DEFAULT_LOCATION;
	}
}