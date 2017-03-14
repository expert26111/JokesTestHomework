/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex.jokefetching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author yoyo
 */
public class FetcherFactory implements IFetcherFactory {

    private final List<String> availableTypes
            = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");

    @Override
    public List<String> getAvailableTypes() {
        return availableTypes;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public List<IJokeFetcher> getJokeFetchers(String jokesToFetch) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools
        List<IJokeFetcher> getJokes = new ArrayList<IJokeFetcher>();
        String[] tokens = jokesToFetch.split(",");
        //Jokes jokes = new Jokes();
        for (String token : tokens) 
        {
            switch (token) 
            {
                case "eduprog":
                    getJokes.add(new EduJoke());
                    break;
                case "chucknorris":
                    getJokes.add(new ChuckNorris());
                    break;
                case "moma":
                    getJokes.add(new Moma());
                    break;
                case "tambal":
                    getJokes.add(new Tambal());
                    break;
            }
        }
        return getJokes;
    }

}
