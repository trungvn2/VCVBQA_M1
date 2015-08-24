package BaseTestClass;

public class HttpResponse {
	public int statusCode;
    public String responseBody;

    HttpResponse(int statusCode, String responseBody) {
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

}
