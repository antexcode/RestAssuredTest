package RestAssuredTesting.RestAssuredTesting;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class GetTest {

	//weather api doesnot work so instead used the url below
	@Test 
	public void getWeatherMethod() {

		//define endpoint
		RestAssured.baseURI = "https://reqres.in/api/unknown";
		
		//define sender obj
		RequestSpecification mySender= RestAssured.given();
		
		//define response obj along with parameter
		Response myResponse = mySender.request(Method.GET,"/5");
		
		//log the body
		System.out.println(myResponse.getBody().asString());
		
		//assert response code
		int myCode = myResponse.getStatusCode();
		System.out.println(myCode);
		Assert.assertEquals(myCode, 200);
		
		//assert response line
		String myStatusLine = myResponse.getStatusLine();
		System.out.println(myStatusLine);
		Assert.assertEquals(myStatusLine, "HTTP/1.1 200 OK");
				
				
	}

}
