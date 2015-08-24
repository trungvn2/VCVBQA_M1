package BaseTestClass;
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











import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.Assert.*;
import org.json.*;



public class BaseTest {
	
	protected static CookieManager cookieManager;
	protected static String umsBaseURL = "http://50.197.139.148:9000";
    protected static String voiceBankServiceBaseURL = "http://50.197.139.148:9001";
    protected String getResponseBody(HttpURLConnection con) throws java.io.IOException {
        InputStream is = null;
        if (con.getResponseCode() == 200) {
            is = con.getInputStream();
        }
        else {
            is = con.getErrorStream();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String inputLine;
        StringBuffer resp = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            resp.append(inputLine);
        }
        in.close();

        return resp.toString();
    }

    protected HttpURLConnection prepareUMSRequest(String request, String method) throws Exception {
        URL url = new URL(umsBaseURL + request);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method);
        return con;
    }
    protected HttpURLConnection prepareVBSRequest(String request, String method) throws Exception {
       
    	URL url = new URL(voiceBankServiceBaseURL + request);
    	
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        
        con.setRequestMethod(method);
       
        return con;
       
     
    }
    protected HttpURLConnection prepareUMSGet(String request) throws Exception {
        return prepareUMSRequest(request, "GET");
    }

    protected HttpURLConnection prepareUMSPost(String request) throws Exception {
        return prepareUMSRequest(request, "POST");
    }

    protected HttpURLConnection prepareVBSGet(String request) throws Exception {
        return prepareVBSRequest(request, "GET");
    }

    protected HttpURLConnection prepareVBSPost(String request) throws Exception {
        return prepareVBSRequest(request, "POST");
    }

    protected HttpURLConnection prepareVBSPut(String request) throws Exception {
        return prepareVBSRequest(request, "PUT");
    }

    protected HttpResponse login(String emailAddress, String password) throws Exception {
        HttpURLConnection con = prepareUMSPost("/user/login");

        // set Content-Type hedaer to say we're sending JSON
        con.setRequestProperty("Content-Type", "application/json");

        // set up post parameters
        String postParameters = new JSONObject().put("email_address", emailAddress).put("password", password).toString();

        // emit post parameters
        con.setDoOutput(true);
        PrintStream os = new PrintStream(con.getOutputStream());
        os.print(postParameters);
        os.flush();
        os.close();
        

        return new HttpResponse(con.getResponseCode(), getResponseBody(con));
    }

    protected String getAuthenticationToken() throws Exception {
        CookieStore cookieJar = cookieManager.getCookieStore();
        List<HttpCookie> cookies = cookieJar.get(new URI(umsBaseURL));
        String xsrfToken = null;
        for (HttpCookie c : cookies) {
            if (c.getName().startsWith("XSRF-TOKEN")) {
                xsrfToken = c.getValue();
            }
        }
        return xsrfToken;
    }

    protected void authenticateRequest(HttpURLConnection con) throws Exception {
       
    	String authenticationToken = getAuthenticationToken();
        if (authenticationToken != null) {
            con.setRequestProperty("Cookie", "XSRF-TOKEN=" + authenticationToken);
            con.setRequestProperty("X-XSRF-TOKEN", authenticationToken);
           
            
        }
       
    }

    private void dumpCookies() {
        System.out.println("Cookies:");
        CookieStore cookieJar = cookieManager.getCookieStore();
        for (URI uri : cookieJar.getURIs()) {
            System.out.println("uri: " + uri.getHost());
        }
        for (HttpCookie cookie : cookieJar.getCookies()) {
            System.out.println("cookie: " + cookie);
        }
    }
    
    public boolean checkURL(String URL) throws Exception{
    	
    	HttpURLConnection con = prepareVBSGet(URL.substring(26,URL.length()));
    	authenticateRequest(con); 
    	
    	if(con.getResponseCode()==200) return true;
    	
    	return false;
    	
 
    }
    
    public void DelareTestCaseName(String TestCaseName)
    {
    	Reporter.log("-----------------------------------------");
    	Reporter.log(TestCaseName,true);
    	Reporter.log("-----------------------------------------");
    }

    @BeforeClass
    public static void setupCookieManager() {
        cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
    }
}







