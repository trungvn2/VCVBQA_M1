package VoiceOwner;
import java.util.List;
import java.lang.reflect.Method;
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


public class TS_VoiceOwner_Post extends BaseTest{
	
	
	@Test
	public void TS01_TC14_checkVoiceOwnerPost(){
		
		try {
            // first login
			
			String emailaddress="admin@swenson.org";
			String password="passworD";
			
            HttpResponse loginResponse = login(emailaddress,password);
            Assert.assertEquals(loginResponse.statusCode,200,"login status code is 200");
            
       
            
         // now prepare /user Post call
            
            HttpURLConnection conn = prepareVBSGet("/voice-owner");
            
            


            // we want this request authenticated using credentials from above login
            authenticateRequest(conn);

            // set Content-Type hedaer to say we're sending JSON
            conn.setRequestProperty("Content-Type", "application/json");

            String voiceOwnerName = "NewvoiceOwner";
            // set up post parameters
            String postParameters = new JSONObject().put("first_name",voiceOwnerName).put("user_id", 14).toString();

            // emit post parameters
            conn.setDoOutput(true);
            PrintStream os = new PrintStream(conn.getOutputStream());
            os.print(postParameters);
            os.flush();
            os.close();

            Assert.assertEquals(conn.getResponseCode(),200,"HTTP status code from /voice-owner POST is 200");
            JSONObject respBodyJson = new JSONObject(getResponseBody(conn));
            
            System.out.println(respBodyJson);
            String name = respBodyJson.getString("name");
            Assert.assertEquals(name,voiceOwnerName," verify voice owner name");
            
        
           
        }
        catch (Exception e) {
           System.out.println(e.toString());
        	//fail("Assertion raised: " + e);  
            
        }
	}
	
	
	
}
