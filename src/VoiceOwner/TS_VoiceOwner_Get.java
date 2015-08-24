package VoiceOwner;
import java.util.List;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.HttpCookie;

import javax.net.ssl.HttpsURLConnection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.AfterTest;
import org.testng.reporters.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.annotations.ITest;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestNGMethod;
import org.testng.Reporter;
import org.testng.Assert.*;
import org.json.*;

import BaseTestClass.BaseTest;
import BaseTestClass.HttpResponse;

import java.lang.reflect.Method; 
public class TS_VoiceOwner_Get extends BaseTest{
	
	@BeforeMethod
	public void beforeMethod(final Method method) throws Exception {
		String testName = method.getName();
		DelareTestCaseName(testName);
		}
    
	@Test
	public void TS01_TC01_checkVoiceOwnerGet(){
		
		try {
            // first login
			
			String emailaddress="Samson@speechmorphing.com";
			String password="test123";
			
            HttpResponse loginResponse = login(emailaddress,password);
            Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
            

            // now prepare /user GET call
            String name="Samson";
            String userID="18";
           
            HttpURLConnection con = prepareVBSGet("/voice-owner?name="+name+"&userID="+userID);
           //?name=Samson&userID=18
            // we want this request authenticated	 using credentials from above login
            authenticateRequest(con);
            Assert.assertEquals(200, con.getResponseCode(),"HTTP status code from /voice-owner GET is 200");
            Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
            String json=getResponseBody(con);
            System.out.print(json);
            JSONObject respBodyJson = new JSONObject(json);
            int count = respBodyJson.getInt("count");
            Reporter.log("VP2:Check Count number " +count);
            Assert.assertTrue( count > 0,"Ensure count > 0");
            JSONArray results = respBodyJson.getJSONArray("results");
            if(count >10)
            {
            	 Reporter.log("VP3: Check the next and prev URL ");
            	//check next URL work
            	if(respBodyJson.opt("next")!="")
            	{
            	String nextURL = respBodyJson.getString("next");   	 
            	boolean f=checkURL(nextURL);
            	Assert.assertEquals(f, true);
            	}
           	    // check prev URL is null
                String prevURL = respBodyJson.optString("prev");
                Assert.assertEquals(prevURL, "");    
            }
            else
            {
            Reporter.log("VP3: Check the next and prev URL "); 
            String nextURL = respBodyJson.optString("next");
            Assert.assertEquals(nextURL, "");
            String prevtURL = respBodyJson.optString("prev");
            Assert.assertEquals(prevtURL, "");
            	
            }
            Reporter.log("VP4:Check number of result "+results.length());
            Assert.assertTrue(results.length() > 0,"Ensure results has at least 1 item");
          
        }
        catch (Exception e) {
            fail("Assertion raised: " + e);        }
	}
	

	@Test
	public void TS01_TC02_checkVoiceOwnerGet(){
		
		
		
		try {
			String emailaddress="admin@swenson.org";
			String password="passworD";
			
            HttpResponse loginResponse = login(emailaddress, password);
            Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
            

            // now prepare /user GET call
            String name="Samson";
            String userID="18";
           
            HttpURLConnection con = prepareVBSGet("/voice-owner?name="+name+"&userID="+userID);
            
            authenticateRequest(con);
            
            //Check Status code,Count,Result list
            Assert.assertEquals(200, con.getResponseCode(),"HTTP status code from /voice-owner GET is 200");
            Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
            String json=getResponseBody(con);
            System.out.println(json);
            JSONObject respBodyJson = new JSONObject(json);
            int count = respBodyJson.getInt("count");
            Reporter.log("VP2:Check Count number " +count);
            Assert.assertTrue( count > 0,"Ensure count > 0");
            JSONArray results = respBodyJson.getJSONArray("results");
            
            if(count >10)
            {
            	 Reporter.log("VP3: Check the next and prev URL ");
            	//check next URL work
            	if(respBodyJson.opt("next")!="")
            	{
            	String nextURL = respBodyJson.getString("next");   	 
            	boolean f=checkURL(nextURL);
            	Assert.assertEquals(f, true);
            	}
           	    // check prev URL is null
                String prevURL = respBodyJson.optString("prev");
                Assert.assertEquals(prevURL, "");           	
           	
            }
            else
            {
            Reporter.log("VP3: Check the next and prev URL "); 
            String nextURL = respBodyJson.optString("next");
            Assert.assertEquals(nextURL, "");
            String prevtURL = respBodyJson.optString("prev");
            Assert.assertEquals(prevtURL, "");
            	
            }
           
            Reporter.log("VP4:Check number of result "+results.length());
            Assert.assertTrue(results.length() > 0,"Ensure results has at least 1 item");
          
        }
        catch (Exception e) {
            fail("Assertion raised: " + e);        }
	}
	@Test
	public void TS01_TC03_checkVoiceOwnerGet(){
		
		
		try {
			String emailaddress="admin@swenson.org";
			String password="passworD";
			
            HttpResponse loginResponse = login(emailaddress, password);
            Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
            

            // now prepare /user GET call
           
           
            HttpURLConnection con = prepareVBSGet("/voice-owner");
            
            authenticateRequest(con);
            
            //Check Status code,Count,Result list
            Assert.assertEquals(con.getResponseCode(),200,"HTTP status code from /voice-owner GET is 200");
            Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
            String json=getResponseBody(con);
            System.out.println(json);
            JSONObject respBodyJson = new JSONObject(json);
            int count = respBodyJson.getInt("count");
            Reporter.log("VP2:Check Count number " +count);
            Assert.assertTrue( count > 0,"Ensure count > 0");
            JSONArray results = respBodyJson.getJSONArray("results");
            
            if(count >10)
            {
            	 Reporter.log("VP3: Check the next and prev URL ");
            	//check next URL work
            	if(respBodyJson.opt("next")!="")
            	{
            	String nextURL = respBodyJson.getString("next");   	 
            	boolean f=checkURL(nextURL);
            	Assert.assertEquals(f, true);
            	}
           	    // check prev URL is null
                String prevURL = respBodyJson.optString("prev");
                Assert.assertEquals(prevURL, "");           	
           	
            }
            else
            {
            Reporter.log("VP3: Check the next and prev URL "); 
            String nextURL = respBodyJson.optString("next");
            Assert.assertEquals(nextURL, "");
            String prevtURL = respBodyJson.optString("prev");
            Assert.assertEquals(prevtURL, "");
            	
            }
           
            Reporter.log("VP4:Check number of result "+results.length());
            Assert.assertTrue(results.length() > 0,"Ensure results has at least 1 item");
          
        }
        catch (Exception e) {
            fail("Assertion raised: " + e);        }
	}
	
	//add one more test case to  verify when user call from page 2
	@Test
	public void TS01_TC0301_checkVoiceOwnerGet(){
		
		try {
			String emailaddress="admin@swenson.org";
			String password="passworD";
			
            HttpResponse loginResponse = login(emailaddress, password);
            Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
            

            // now prepare /user GET call
           
           
            HttpURLConnection con = prepareVBSGet("/voice-owner/page/2");
            
            authenticateRequest(con);
            
            //Check Status code,Count,Result list
            Assert.assertEquals(200, con.getResponseCode(),"HTTP status code from /voice-owner GET is 200");
            Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
            String json=getResponseBody(con);
            System.out.println(json);
            JSONObject respBodyJson = new JSONObject(json);
            int count = respBodyJson.getInt("count");
            Reporter.log("VP2:Check Count number " +count);
            Assert.assertTrue( count > 0,"Ensure count > 0");
            JSONArray results = respBodyJson.getJSONArray("results");
            
            if(count >10)
            {
            	 Reporter.log("VP3: Check the next and prev URL ");
            	//check next URL work
            	if(respBodyJson.opt("next")!="")
            	{
            	String nextURL = respBodyJson.getString("next");   	 
            	boolean f=checkURL(nextURL);
            	Assert.assertEquals(f, true);
            	}
           	    // check prev URL is working
            	if(respBodyJson.opt("prev")!="")
            	{
            	String prevURL = respBodyJson.getString("prev");   	 
            	boolean f1=checkURL(prevURL);
            	Assert.assertEquals(f1, true);
            	}
                      	
           	
            }
            else
            {
            Reporter.log("VP3: Check the next and prev URL "); 
            String nextURL = respBodyJson.optString("next");
            Assert.assertEquals(nextURL, "");
            String prevtURL = respBodyJson.optString("prev");
            Assert.assertEquals(prevtURL, "");
            	
            }
           
            Reporter.log("VP4:Check number of result "+results.length());
            Assert.assertTrue(results.length() > 0,"Ensure results has at least 1 item");
          
        }
        catch (Exception e) {
            fail("Assertion raised: " + e);        }
	}
	
@Test	
public void TS01_TC04_checkVoiceOwnerGet(){
	
		try {
			String emailaddress="Samson@speechmorphing.com";
			String password="test123";
			
            HttpResponse loginResponse = login(emailaddress, password);
            Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
            

            // now prepare /user GET call
            String name="Samson";
            String userID="18";
           
            HttpURLConnection con = prepareVBSGet("/voice-owner");
            
            authenticateRequest(con);
            
            //Check Status code,Count,Result list
            Assert.assertEquals(con.getResponseCode(),200,"HTTP status code from /voice-owner GET is 200");
            Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
            String json=getResponseBody(con);
            System.out.println(json);
            JSONObject respBodyJson = new JSONObject(json);
            int count = respBodyJson.getInt("count");
            Reporter.log("VP2:Check Count number " +count);
            Assert.assertTrue( count > 0,"Ensure count > 0");
            JSONArray results = respBodyJson.getJSONArray("results");
            
            if(count >10)
            {
            	 Reporter.log("VP3: Check the next and prev URL ");
            	//check next URL work
            	if(respBodyJson.opt("next")!="")
            	{
            	String nextURL = respBodyJson.getString("next");   	 
            	boolean f=checkURL(nextURL);
            	Assert.assertEquals(f, true);
            	}
           	    // check prev URL is working
            	String prevURL = respBodyJson.optString("prev");
                Assert.assertEquals(prevURL, "");    
                      	
           	
            }
            else
            {
            Reporter.log("VP3: Check the next and prev URL "); 
            String nextURL = respBodyJson.optString("next");
            Assert.assertEquals(nextURL, "");
            String prevtURL = respBodyJson.optString("prev");
            Assert.assertEquals(prevtURL, "");
            	
            }
           
            Reporter.log("VP4:Check number of result "+results.length());
            Assert.assertTrue(results.length() > 0,"Ensure results has at least 1 item");
          
        }
        catch (Exception e) {
            fail("Assertion raised: " + e);        }
	}



@Test
public void TS01_TC05_checkVoiceOwnerGet(){
	
	
	
	try {
		String emailaddress="Samson@speechmorphing.com";
		String password="test123";
		
        HttpResponse loginResponse = login(emailaddress, password);
        Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
        

        // now prepare /user GET call
        String name="foo";
        String userID="14";
       
        HttpURLConnection con = prepareVBSGet("/voice-owner?name="+name+"&userID="+userID);
        
        authenticateRequest(con);
        
        //Check Status code,Count,Result list
        Assert.assertEquals(con.getResponseCode(),200,"HTTP status code from /voice-owner GET is 200");
        Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
       
    }
    catch (Exception e) {
        fail("Assertion raised: " + e);        }
}

@Test
public void TS01_TC06_checkVoiceOwnerGet(){
	
	
	
	try {
		String emailaddress="admin@swenson.org";
		String password="passworD";
		
        HttpResponse loginResponse = login(emailaddress, password);
        Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
        

        // now prepare /user GET call
        String name="foo";
        String userID="bar";
       
        HttpURLConnection con = prepareVBSGet("/voice-owner?name="+name+"&userID="+userID);
        
        authenticateRequest(con);
        //Check Status code,Count,Result list
        Assert.assertEquals(con.getResponseCode(),400,"HTTP status code from /voice-owner GET is 400");
        Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
        
       
    }
    catch (Exception e) {
        fail("Assertion raised: " + e);        }
}


@Test
public void TS01_TC07_checkVoiceOwnerGet(){
	
	
	
	try {
		String emailaddress="denzel@swenson.org";
		String password="passworD";
		
        HttpResponse loginResponse = login(emailaddress, password);
        Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
        

        // now prepare /user GET call
        String name="13";
        String userID="14";
       
        HttpURLConnection con = prepareVBSGet("/voice-owner?name="+name+"&userID="+userID);
       
        
        authenticateRequest(con);
        Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
        //Check Status code,Count,Result list
        Assert.assertEquals(con.getResponseCode(),400,"HTTP status code from /voice-owner GET is 400");
        Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
        
       
    }
    catch (Exception e) {
        fail("Assertion raised: " + e);        }
}

@Test
public void TS01_TC08_checkVoiceOwnerGet(){
	
	
	
	try {
		String emailaddress="denzel@swenson.org";
		String password="passworD";
		
        HttpResponse loginResponse = login(emailaddress, password);
        Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
        

        // now prepare /user GET call
        String name="13";
        String userID="0";
       
        HttpURLConnection con = prepareVBSGet("/voice-owner?name="+name+"&userID="+userID);
       
        
        authenticateRequest(con);
        Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
        //Check Status code,Count,Result list
        Assert.assertEquals(con.getResponseCode(),400,"HTTP status code from /voice-owner GET is 400");
        Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
        
       
    }
    catch (Exception e) {
        fail("Assertion raised: " + e);        }
}
@Test
public void TS01_TC09_checkVoiceOwnerGet(){
	
	
	
	try {
		String emailaddress="denzel@swenson.org";
		String password="passworD";
		
        HttpResponse loginResponse = login(emailaddress, password);
        Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
        

        // now prepare /user GET call
        String name="bar";
        String userID="-18";
       
        HttpURLConnection con = prepareVBSGet("/voice-owner?name="+name+"&userID="+userID);
       
        
        authenticateRequest(con);
        
        String json=getResponseBody(con);
        System.out.println(json);
        Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
        //Check Status code,Count,Result list
        Assert.assertEquals(con.getResponseCode(),400,"HTTP status code from /voice-owner GET is 400");
        Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
        
       
    }
    catch (Exception e) {
        fail("Assertion raised: " + e);        }
}
@Test
public void TS01_TC10_checkVoiceOwnerGet(){
	
	
	
	try {
		String emailaddress="denzel@swenson.org";
		String password="passworD";
		
        HttpResponse loginResponse = login(emailaddress, password);
        Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
        

        // now prepare /user GET call
        String name="bar";
        String userID="34000";
       
        HttpURLConnection con = prepareVBSGet("/voice-owner?name="+name+"&userID="+userID);
       
        
        authenticateRequest(con);
        
        String json=getResponseBody(con);
        System.out.println(json);
        Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
        //Check Status code,Count,Result list
        Assert.assertEquals(con.getResponseCode(),400,"HTTP status code from /voice-owner GET is 400");
        Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
        
       
    }
    catch (Exception e) {
        fail("Assertion raised: " + e);        }
}
@Test
public void TS01_TC11_checkVoiceOwnerGet(){
	
	
	
	try {
		String emailaddress="denzel@swenson.org";
		String password="passworD";
		
        HttpResponse loginResponse = login(emailaddress, password);
        Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
        

        // now prepare /user GET call
       
        String userID="999";
       
        HttpURLConnection con = prepareVBSGet("/voice-owner?userID="+userID);
       
        
        authenticateRequest(con);
        
        String json=getResponseBody(con);
        System.out.println(json);
        Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
        //Check Status code,Count,Result list
        Assert.assertEquals(con.getResponseCode(),404,"HTTP status code from /voice-owner GET is 404");
        Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
        
       
    }
    catch (Exception e) {
        fail("Assertion raised: " + e);        }
}

@Test
public void TS01_TC12_checkVoiceOwnerGet(){
	
	
	
	try {
		String emailaddress="admin@swenson.org";
		String password="passworD";
		
        HttpResponse loginResponse = login(emailaddress, password);
        Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
        

        // now prepare /user GET call
       
        String userID="189";
       
        HttpURLConnection con = prepareVBSGet("/voice-owner?userID="+userID);
       
        
        authenticateRequest(con);
        
        String json=getResponseBody(con);
        System.out.println(json);
        Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
        //Check Status code,Count,Result list
        Assert.assertEquals(con.getResponseCode(),404,"HTTP status code from /voice-owner GET is 404");
        Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
        
    }
    catch (Exception e) {
        fail("Assertion raised: " + e);        }
}

@Test
public void TS01_TC13_checkVoiceOwnerGet(){
	
	try {
		String emailaddress="admin@swenson.org";
		String password="passworD";
		
        HttpResponse loginResponse = login(emailaddress, password);
        Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
        
        // now prepare /user GET call
       
        String userID="189";
        HttpURLConnection con = prepareVBSGet("/voice-owner?userID="+userID);
       
        
       //not authenticate user
        
       //Check Status code,Count,Result list
        Assert.assertEquals(con.getResponseCode(),401,"HTTP status code from /voice-owner GET is 404");
        Reporter.log("VP1:Check Status code-- "+con.getResponseCode());
        
    }
    catch (Exception e) {
        fail("Assertion raised: " + e);        }
}

@Test
public void TS01_TC24_checkVoiceOwnerIdGet() {
    try {
        
    	
    	String emailaddress="denzel@swenson.org";
		String password="passworD";
		
    	// first login
        HttpResponse loginResponse = login(emailaddress,password);
        Reporter.log("VP1:Check login succesfully");
        Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
        

        // now prepare /user GET call
        HttpURLConnection con = prepareVBSGet("/voice-owner/1");

        // we want this request authenticated using credentials from above login
        authenticateRequest(con);
        Reporter.log("VP2:Check Response code");
        Assert.assertEquals(con.getResponseCode(),200,"HTTP status code from /voice-owner POST is 200");
        
        // get response body
        JSONObject respBodyJson = new JSONObject(getResponseBody(con));
         System.out.println(respBodyJson);
        int id = respBodyJson.getInt("id");
        String uri = respBodyJson.getString("uri");
        String name = respBodyJson.getString("first_name");
        Reporter.log("VP3:Check ID");
        Assert.assertEquals(name,"Karla");
        Reporter.log("VP4:Check name");
        Assert.assertEquals(id, 1);
        Reporter.log("VP5:Check URI");
        Assert.assertEquals(uri, "http://50.197.139.148:9001/voice-owner/1" );
       // assertTrue("incorrect voice owner user_id", JSONObject.NULL.equals(respBodyJson.get("user_id")));
    }
    catch (Exception e) {
        fail("Assertion raised: " + e);}
    }
    @Test
    public void TS01_TC25_checkVoiceOwnerIdGet() {
        try {
        	String emailaddress="denzel@swenson.org";
    		String password="passworD";
    		
        	// first login
            HttpResponse loginResponse = login(emailaddress,password);
            Reporter.log("VP1:Check login succesfully");
            Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
            

            // now prepare /user GET call
            HttpURLConnection con = prepareVBSGet("/voice-owner/0");

            // we want this request authenticated using credentials from above login
            authenticateRequest(con);
            Reporter.log("VP2:Check Response code");
            Assert.assertEquals(con.getResponseCode(),400);
            
            
        }
        catch (Exception e) {
            fail("Assertion raised: " + e);
        }
}
    @Test
    public void TS01_TC26_checkVoiceOwnerIdGet() {
        try {
        	String emailaddress="denzel@swenson.org";
    		String password="passworD";
    		
        	// first login
            HttpResponse loginResponse = login(emailaddress,password);
            Reporter.log("VP1:Check login succesfully");
            Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
            

            // now prepare /user GET call
            HttpURLConnection con = prepareVBSGet("/voice-owner/-12");

            // we want this request authenticated using credentials from above login
            authenticateRequest(con);
            Reporter.log("VP2:Check Response code");
            Assert.assertEquals(con.getResponseCode(),400);
            
            
        }
        catch (Exception e) {
            fail("Assertion raised: " + e);
        }
}
    
    @Test
    public void TS01_TC27_checkVoiceOwnerIdGet() {
        try {
        	String emailaddress="denzel@swenson.org";
    		String password="passworD";
    		
        	// first login
            HttpResponse loginResponse = login(emailaddress,password);
            Reporter.log("VP1:Check login succesfully");
            Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
            

            // now prepare /user GET call
            HttpURLConnection con = prepareVBSGet("/voice-owner/999999");

            // we want this request authenticated using credentials from above login
            authenticateRequest(con);
            Reporter.log("VP2:Check Response code");
            Assert.assertEquals(con.getResponseCode(),400);
            
            
        }
        catch (Exception e) {
            fail("Assertion raised: " + e);
        }
}
    
    @Test
    public void TS01_TC28_checkVoiceOwnerIdGet() {
        try {
        	String emailaddress="denzel@swenson.org";
    		String password="passworD";
    		
        	// first login
            HttpResponse loginResponse = login(emailaddress,password);
            Reporter.log("VP1:Check login succesfully");
            Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
            

            // now prepare /user GET call
            HttpURLConnection con = prepareVBSGet("/voice-owner/500");

            // we want this request authenticated using credentials from above login
            authenticateRequest(con);
            Reporter.log("VP2:Check Response code");
            Assert.assertEquals(con.getResponseCode(),404);
            
            
        }
        catch (Exception e) {
            fail("Assertion raised: " + e);
        }
}
    
    @Test
    public void TS01_TC29_checkVoiceOwnerIdGet() {
        try {
        	String emailaddress="denzel@swenson.org";
    		String password="passworD";
    		
        	// first login
            HttpResponse loginResponse = login(emailaddress,password);
            Reporter.log("VP1:Check login succesfully");
            Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
            

            // now prepare /user GET call
            HttpURLConnection con = prepareVBSGet("/voice-owner/0");

            // we want this request authenticated using credentials from above login
          //  authenticateRequest(con);
            Reporter.log("VP2:Check Response code");
            Assert.assertEquals(con.getResponseCode(),401);
            
            
        }
        catch (Exception e) {
            fail("Assertion raised: " + e);
        }
 }  
}



