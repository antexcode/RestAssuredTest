package RestAssuredTesting.RestAssuredTesting;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDriven {

	//below test uses values from dataprovier in its parameters
	@Test(dataProvider = "myDataProvider")
	public static void dataDriven(String name, String job) {
		
		//define endpoint
		RestAssured.baseURI = "https://reqres.in";
		
		//define sender
		RequestSpecification mySender = RestAssured.given();
		
		//define payload and its type
		JSONObject myPayload = new JSONObject();
		mySender.header("Content-Type","application/json");
				
		//define payload data
		myPayload.put(name, "NameTest");
		myPayload.put(job, "JobTest");
		
		//insert above data into request body
		mySender.body(myPayload.toJSONString());
		
		//send with para and get response
		Response myResponse = mySender.request(Method.POST,"/api/users");
		
		//validate body as string text
		String bodyText = myResponse.getBody().asString();
		System.out.println(bodyText);
		
		//get status code
		System.out.println(myResponse.getStatusCode());
		
		//get success message from response body
		System.out.println(myResponse.getBody().jsonPath().get("createdAt"));
		
		//get CONNECTION field from header
		System.out.println(myResponse.getHeader("Connection"));
		
		
	}
	
	//dataprovider method below
	@DataProvider (name = "myDataProvider")
	public String[][] dataProvider()
	{
		String myData[][] = {{"first name", "first job"},{"sec name", "sec job"},{"third name","third job"}};
		return myData;
	}	
	

}
