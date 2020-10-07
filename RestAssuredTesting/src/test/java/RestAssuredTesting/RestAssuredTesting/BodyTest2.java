package RestAssuredTesting.RestAssuredTesting;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class BodyTest2 {

	@Test
	public void bodyTest()
	{
		
		
		//define endpoint
		//RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employees";
				
		//define sender
		RequestSpecification mySender = RestAssured.given();
		
		//another way of defining uri is as below which doesn't need above baseuri
		mySender.baseUri("http://dummy.restapiexample.com/api/v1/employees");
		
		//send with para and get response
		Response myResponse = mySender.request(Method.GET); //or (Method.GET,"/")
		
	
		//get body as jsonpath obj. 
		JsonPath myJsonBody = myResponse.getBody().jsonPath(); //can also write as myResponse.jsonpath
		System.out.println("status is " + myJsonBody.get("status"));
			
	
	}
}
