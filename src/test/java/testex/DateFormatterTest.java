/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
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
public class DateFormatterTest 
{
    
    public DateFormatterTest() {
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
     * Test of getFormattedDate method, of class DateFormatter.
     */
    @Test
    public void testGetFormattedDate() throws Exception
    {

        Calendar cal = Calendar.getInstance();
        cal.set(1990, 6, 26, 18, 20);
        Date d = cal.getTime();
        DateFormatter dateBusiness = new DateFormatter();
        String compare =    dateBusiness.getFormattedDate(d, "Europe/Sofia");
        String real =  "26 Jul 1990 07:20 PM";
        assertEquals(compare,real);
    
    }

    
}
