/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex.jokefetching;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.response.ExtractableResponse;
import testex.Joke;

/**
 *
 * @author yoyo
 */
public class EduJoke implements IJokeFetcher
{

    @Override
    public Joke getJoke() 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            ExtractableResponse res = given().get("http://jokes-plaul.rhcloud.com/api/joke").then().extract();
            String joke = res.path("joke");
            String reference = res.path("reference");
            return new Joke(joke, reference);
        } catch (Exception e) {
            return null;
        }
    }
    
}
