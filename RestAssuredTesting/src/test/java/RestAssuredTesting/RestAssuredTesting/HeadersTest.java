package RestAssuredTesting.RestAssuredTesting;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;


public class HeadersTest {

	@Test
	public static void headerTest() {
		
		//define endpoint
		RestAssured.baseURI = "https://reqres.in";
		
		//define sender
		RequestSpecification mySender = RestAssured.given();
		
		//define payload and its type
		JSONObject myPayload = new JSONObject();
		mySender.header("Content-Type","application/json");
				
		//define payload data
		myPayload.put("name", "NameTest");
		myPayload.put("job", "JobTest");
		
		//insert above data into request body
		mySender.body(myPayload.toJSONString());
		
		//send with para and get response
		Response myResponse = mySender.request(Method.POST,"/api/users");
		
		
		
		//get headers
		//System.out.println(myResponse.getHeaders()); gives same result
		Headers myHeaders = myResponse.headers();
		//System.out.println(myHeaders);
		
		//another way of getting headers and customizing the output using for-loop-shortcut
		for(Header myHeader: myHeaders)
		{
			System.out.println(myHeader.getName() + " -----  " + myHeader.getValue());
		}
	
		
	}

}
