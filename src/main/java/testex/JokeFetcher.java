
package testex;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.response.ExtractableResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import testex.jokefetching.FetcherFactory;
import testex.jokefetching.IFetcherFactory;
import testex.jokefetching.IJokeFetcher;

/**
 * Class used to fetch jokes from a number of external joke API's
 */
public class JokeFetcher
{
    
    private IDateFormatter dateObject; //dependancy
    private String timeZone;
    private IFetcherFactory factory;
    public JokeFetcher(IDateFormatter dateObject, IFetcherFactory factory) 
    {
         // Inject required dependency in the constructor.
        this.dateObject = dateObject;
        this.factory = factory;
    }
    
    
  
  /**
   * These are the valid types that can be used to indicate required jokes
   * eduprog: Contains joke related to programming and education. API only returns a new value each hour
   * chucknorris: Fetch a chucknorris joke. Not always political correct ;-)
   * moma: Fetch a "MOMA" joke. Defenitely never political correct ;-)
   * tambal: Just random jokes
   */
  private final List<String> availableTypes = Arrays.asList("eduprog","chucknorris","moma","tambal");

//    JokeFetcher(IDateFormatter dateObject, String europeKiev) 
//    {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

//    JokeFetcher(IDateFormatter dateObject, String europeKiev) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
  
//  private Joke getEducationalProgrammingJoke()
//  {
//    try{
//    ExtractableResponse res =  given().get("http://jokes-plaul.rhcloud.com/api/joke").then().extract();
//    String joke = res.path("joke");
//    String reference = res.path("reference");
//    return new Joke(joke,reference);
//    }catch(Exception e){
//      return null;
//    }
//  }
  
//  private Joke getChuckNorrisJoke(){
//    try{
//    String joke  = given().get("http://api.icndb.com/jokes/random").path("value.joke");
//    return new Joke(joke,"http://api.icndb.com/");
//    }catch(Exception e){
//      return null;
//    }
//  }
//  
//  private Joke getYoMommaJoke(){   
//    try{
//    //API does not set response type to JSON, so we have to force the response to read as so
//    String joke = given().get("http://api.yomomma.info/").andReturn().jsonPath().getString("joke");
//    return new Joke(joke,"http://api.yomomma.info/");
//    }catch(Exception e){
//      return null;
//    }
//  }
  
//  private Joke getTambalJoke(){
//    try{
//    String joke  = given().get("http://tambal.azurewebsites.net/joke/random").path("joke");
//    return new Joke(joke,"http://tambal.azurewebsites.net/joke/random");
//    }catch(Exception e){
//      return null;
//    }
//  }
  
  /**
   * The valid string values to use in a call to getJokes(..)
   * @return All the valid strings that can be used
   */
  public List<String> getAvailableTypes()
  {
    return availableTypes;
  }
  
  /**
   * Verifies whether a provided value is a valid string (contained in availableTypes)
   * @param jokeTokens. Example (with valid values only): "eduprog,chucknorris,chucknorris,moma,tambal"
   * @return true if the param was a valid value, otherwise false
   */
  boolean isStringValid(String jokeTokens)
  {
    String[] tokens = jokeTokens.split(",");
      for(String token: tokens)
      {
      if(!availableTypes.contains(token))
      {
        return false;
      }
    }
    return true;
  }
  
  /**
   * Fetch jokes from external API's as given in the input string - jokesToFetch
   * @param jokesToFetch A comma separated string with values (contained in availableTypes) indicating the jokes
   * to fetch. Example: "eduprog,chucknorris,chucknorris,moma,tambal" will return five jokes (two chucknorris)
   * @param timeZone. Must be a valid timeZone string as returned by: TimeZone.getAvailableIDs()  
   * @return A Jokes instance with the requested jokes + time zone adjusted string representing fetch time
   * (the jokes list can contain null values, if a server did not respond correctly)
   * @throws JokeException. Thrown if either of the two input arguments contains illegal values
   */
  public Jokes getJokes(String jokesToFetch, String timeZone) throws JokeException
  {
//    if(!isStringValid(jokesToFetch))
//    {
//      throw new JokeException("Inputs (jokesToFetch) contain types not recognized");
//    }
//    String[] tokens = jokesToFetch.split(",");
//    Jokes jokes = new Jokes();
//    for(String token : tokens)
//    {
//      switch(token)
//      {
//        case "eduprog" : jokes.addJoke(getEducationalProgrammingJoke());break;
//        case "chucknorris" : jokes.addJoke(getChuckNorrisJoke());break;
//        case "moma" : jokes.addJoke(getYoMommaJoke());break;
//        case "tambal" : jokes.addJoke(getTambalJoke());break;
//      }
//    }
//    //Now date has to be injected
//   // String timeZoneString = new DateFormatter().getFormattedDate(timeZone);
//    String theDateString = dateObject.getFormattedDate(new Date(),timeZone);
//    jokes.setTimeZoneString(theDateString);
//    return jokes;
      
      
         // checkIfValidToken(jokesToFetch);
        if(!isStringValid(jokesToFetch))
    {
      throw new JokeException("Inputs (jokesToFetch) contain types not recognized");
    }
    Jokes jokes = new Jokes();
    for (IJokeFetcher fetcher : factory.getJokeFetchers(jokesToFetch)) {
      jokes.addJoke(fetcher.getJoke());
    }
    String tzString = dateObject.getFormattedDate(new Date(),timeZone );
    jokes.setTimeZoneString(tzString);
    return jokes;
  }
  
  /**
   * DO NOT TEST this function. It's included only to get a quick way of executing the code
   * @param args 
   */
  public static void main(String[] args) throws JokeException 
  {
    JokeFetcher jf = new JokeFetcher(new DateFormatter(),new FetcherFactory());
      System.out.println("The object is "+jf);
    Jokes jokes = jf.getJokes("eduprog,chucknorris,chucknorris,moma,tambal","Europe/Copenhagen");
    jokes.getJokes().forEach((joke) -> {
      System.out.println(joke);
    });
    System.out.println("Is String Valid: "+jf.isStringValid("edu_prog,xxx"));
  }
}
