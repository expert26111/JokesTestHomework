/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex.jokefetching;

import static com.jayway.restassured.RestAssured.given;
import testex.Joke;

/**
 *
 * @author yoyo
 */
public class ChuckNorris implements IJokeFetcher
{

    @Override
    public Joke getJoke() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            String joke = given().get("http://api.icndb.com/jokes/random").path("value.joke");
            return new Joke(joke, "http://api.icndb.com/");
        } catch (Exception e) {
            return null;
        }
    }
    
}
