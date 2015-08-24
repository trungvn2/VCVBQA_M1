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
public class TS_VoiceOwner_Put extends BaseTest {
	
	@BeforeMethod
	public void beforeMethod(final Method method) throws Exception {
		String testName = method.getName();
		DelareTestCaseName(testName);
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
	            HttpURLConnection con = prepareVBSPut("/voice-owner/24");

	            // we want this request authenticated using credentials from above login
	          //  authenticateRequest(con);
	            Reporter.log("VP2:Check Response code");
	            Assert.assertEquals(con.getResponseCode(),200);
	            
	            
	        }
	        catch (Exception e) {
	            fail("Assertion raised: " + e);
	        }
	 }
}
