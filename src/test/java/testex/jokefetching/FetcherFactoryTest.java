/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex.jokefetching;

import java.util.List;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoyo
 */
public class FetcherFactoryTest {
    
    public FetcherFactoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAvailableTypes method, of class FetcherFactory.
     */
//    @Test
//    public void testGetAvailableTypes() 
//    {
//        System.out.println("getAvailableTypes");
//        FetcherFactory instance = new FetcherFactory();
//        List<String> expResult = null;
//        List<String> result = instance.getAvailableTypes();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getJokeFetchers method, of class FetcherFactory.
     */
    @Test
    public void testGetJokeFetchers() 
    {
        System.out.println("getJokeFetchers");
        String jokesToFetch = "";
        FetcherFactory instance = new FetcherFactory();
    
        List<IJokeFetcher> result = instance.getJokeFetchers("eduprog,chucknorris,moma,tambal");
      
             assertThat(result.get(0), instanceOf(EduJoke.class));
             assertThat(result.get(1), instanceOf(ChuckNorris.class));
             assertThat(result.get(2), instanceOf(Moma.class));
             assertThat(result.get(3), instanceOf(Tambal.class));
      
  
    }
    
}
