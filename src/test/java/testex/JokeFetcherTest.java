/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex;

import java.util.ArrayList;
import java.util.List;
//import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
//import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.hamcrest.CoreMatchers.*;

import static org.mockito.Mockito.when;
//import org.mockito.MockitoAnnotations.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import org.junit.Assert;
import static org.mockito.BDDMockito.given;
import testex.jokefetching.ChuckNorris;
import testex.jokefetching.EduJoke;
import testex.jokefetching.FetcherFactory;
import testex.jokefetching.IFetcherFactory;
import testex.jokefetching.IJokeFetcher;
import testex.jokefetching.Moma;
import testex.jokefetching.Tambal;

/**
 *
 * @author yoyo
 */
@RunWith(MockitoJUnitRunner.class)
public class JokeFetcherTest {

    // IDateFormatter anInterfaceMock = Mockito.mock(IDateFormatter.class);
    //creating JokeFetcher instance
    @Mock private IFetcherFactory factory;
    
    @Mock
    private EduJoke edu;
    
    @Mock
    private Moma moma;
    
    @Mock
    private Tambal tambal;
    
    @Mock
    private ChuckNorris chuck;
    
    @Mock
    private JokeFetcher jokeFetcher;

    @Mock
    IDateFormatter dateObject;
   
    JokeFetcher joke;
    JokeFetcher getJokes;
    
    JokeFetcher jokitos;

    public JokeFetcherTest() {
    }

    @Test
    public void CheckIfValidToken() {
      
      boolean good = joke.isStringValid("eduprog,chucknorris,moma,tambal");
        assertThat(good, is(true));
      
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @Test
    public void GetAvailableTypes() {
        ArrayList<String> types = new ArrayList<String>();
        types.add("eduprog");
        types.add("chucknorris");
        types.add("moma");
        types.add("tambal");
        // Mockito.when(dateObject).thenReturn(new DateFormatter());
        // jokeFetcher = new JokeFetcher(dateObject, "Europe/Kiev");
        when(jokeFetcher.getAvailableTypes()).thenReturn(types);
        List<String> list = jokeFetcher.getAvailableTypes();
        assertThat(list, hasSize(4));
        // assertThat(list, hasItems("eduprog","chucknorris","moma","tambal"));

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        
         given(dateObject.getFormattedDate(anyObject(),eq("Europe/Copenhagen"))).willReturn("17 feb. 2017 10:56 AM");
        // joke = new JokeFetcher(new DateFormatter(),"Europe/Copenhagen");
        IJokeFetcher joky = new EduJoke();
        IJokeFetcher joky2 = new ChuckNorris();
        IJokeFetcher joky3 = new Moma();
        IJokeFetcher joky4 = new Tambal();
        List<IJokeFetcher> fetchers = new ArrayList<IJokeFetcher>();
        fetchers.add(joky);
        fetchers.add(joky2);
        fetchers.add(joky3);
        fetchers.add(joky4);
        when(factory.getJokeFetchers("eduprog,chucknorris,moma,tambal")).thenReturn(fetchers); 
        
         ArrayList<String> types = new ArrayList<String>();
        types.add("eduprog");
        types.add("chucknorris");
        types.add("moma");
        types.add("tambal");
        
        when(factory.getAvailableTypes()).thenReturn(types);
        
        
         joke = new JokeFetcher(new DateFormatter(),new FetcherFactory());
         jokitos = new JokeFetcher (dateObject, factory);
         
         
         getJokes = new JokeFetcher(dateObject,new FetcherFactory());
  
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetJokes() throws Exception 
    {
                 
        
       // given(dateObject.getFormattedDate(anyObject(),eq("Europe/Copenhagen"))).willReturn("17 feb. 2017 10:56 AM");
       //  Mockito.when(dateObject.getFormattedDate(anyObject(),eq("Europe/Copenhagen"))).thenReturn("17 feb. 2017 10:56 AM");
    
        System.out.println("getJokes");
        // Jokes jokes = joke.getJokes("eduprog,chucknorris,chucknorris,moma,tambal","Europe/Copenhagen");
         
        String jokesToFetch = "eduprog,chucknorris,moma,tambal";
        String timeZone = "Europe/Copenhagen";
  
        // Jokes expResult = null;
        List<IJokeFetcher> result = factory.getJokeFetchers("eduprog,chucknorris,moma,tambal");
        List<String> typesOfJokes = factory.getAvailableTypes();
        Jokes list = getJokes.getJokes(jokesToFetch, timeZone);
        assertThat(typesOfJokes, hasSize(4));
        assertTrue( result.get(0) instanceof EduJoke);
        assertTrue( result.get(1) instanceof  ChuckNorris);
        assertTrue( result.get(2) instanceof Moma);
        assertTrue( result.get(3) instanceof Tambal);
        assertThat(list.getJokes(), is(not(empty())));
        assertThat(result, is(not(empty())));
        assertThat(list.getTimeZoneString(), is("17 feb. 2017 10:56 AM"));//does not work for me
        verify(dateObject, times(1)).getFormattedDate(anyObject(), anyObject());

    }

}
