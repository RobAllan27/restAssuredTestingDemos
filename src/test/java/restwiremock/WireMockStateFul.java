package restwiremock;

import org.testng.annotations.Test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import dataProvider.ExcelFileReader;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

//import static com.github.tomakehurst.wiremock.client.WireMock.*;
//import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WireMockStateFul {

	public void run() {
		testWireMockStateful();
	
		for (int i = 1;i < 11;i++) {
			try {
				System.out.println("Doing ith run" + i);
				testWireMockStateful();
				Thread.sleep(5000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
	}
	/*
	private void stubfortests() {
	    stubFor(get(urlEqualTo("/todo/items")).inScenario("To do list")
	            .whenScenarioStateIs(STARTED)
	            .willReturn(aResponse()
	                    .withBody("<items>" +
	                            "   <item>Buy milk</item>" +
	                            "</items>")));

	    stubFor(post(urlEqualTo("/todo/items")).inScenario("To do list")
	            .whenScenarioStateIs(STARTED)
	            .withRequestBody(containing("Cancel newspaper subscription"))
	            .willReturn(aResponse().withStatus(201))
	            .willSetStateTo("Cancel newspaper item added"));

	    stubFor(get(urlEqualTo("/todo/items")).inScenario("To do list")
	            .whenScenarioStateIs("Cancel newspaper item added")
	            .willReturn(aResponse()
	                    .withBody("<items>" +
	                            "   <item>Buy milk</item>" +
	                            "   <item>Cancel newspaper subscription</item>" +
	                            "</items>")));
	}
	
	*/
	private void testWireMockStateful() {
		  
		  /* Part 1 - Configuring the Wiremock */
	  	
		/*
		 //1st step a get
		  String configCommand  = "{\"scenarioName\": \"Selling My Bike\",\"requiredScenarioState\": \"Started\",\"request\": {\"method\": \"GET\",\"url\": \"/possession/my_bicycle\"},\"response\": {\"status\": 200,\"headers\": {\"Content-Type\": \"application/json\",\"Cache-Control\": \"no-cache\"},\"body\" : {\"Price\":\"Not For Sale\"}}}";
		  Response responseToWireMockBuilder = RestAssured.given().contentType("application/json")
					.body(configCommand).when().post("http://localhost:8080/__admin/scenarios");
		 
		  // Let's check it loaded
		  assertEquals(200,responseToWireMockBuilder.getStatusCode());
		  
		  //2nd step a post to start the sale process
		  configCommand  = "{\"scenarioName\": \"Selling My Bike\",\"requiredScenarioState\": \"Started\",\"newScenarioState\": \"Bike on Sale\",\"request\": {\"method\": \"POST\",\"url\": \"/possession/my_bicycle\",\"bodyPatterns\": [{\"contains\": \"SELL\" }]},\"response\": {\"status\": 201}}";
		  responseToWireMockBuilder = RestAssured.given().contentType("application/json")
					.body(configCommand).when().post("http://localhost:8080/__admin/scenarios");
		  // Let's check it loaded
		  assertEquals(200,responseToWireMockBuilder.getStatusCode());
		  
		  //3rd step a get
		  configCommand  = "{\"scenarioName\": \"Selling My Bike\",\"requiredScenarioState\": \"Bike on Sale\",\"request\": {\"method\": \"GET\",\"url\": \"/possession/my_bicycle\"},\"response\": {\"status\": 200,\"headers\": {\"Content-Type\": \"application/json\",\"Cache-Control\": \"no-cache\"},\"body\" : {\"Price\":\"5000 New English Pounds\"}}}";
		  responseToWireMockBuilder = RestAssured.given().contentType("application/json")
					.body(configCommand).when().post("http://localhost:8080/__admin/scenarios");
		  // Let's check it loaded
		  assertEquals(200,responseToWireMockBuilder.getStatusCode());
		  
		  
		//4th step a post to sell	  	  
		  configCommand  = 	  "{\"scenarioName\": \"Selling My Bike\",\"requiredScenarioState\": \"Bike on Sale\",\"newScenarioState\": \"Bike Sold\",\"request\": {\"method\": \"POST\",\"url\": \"/possession/my_bicycle\",\"bodyPatterns\": [{ \"contains\": \"SOLD\" }]},\"response\": {\"status\": 201}}";
		  
		  responseToWireMockBuilder = RestAssured.given().contentType("application/json")
					.body(configCommand).when().post("http://localhost:8080/__admin/scenarios");
		  // Let's check it loaded
		  assertEquals(200,responseToWireMockBuilder.getStatusCode());
		  
		//5th step a get  
		  configCommand = "{\"scenarioName\": \"Selling My Bike\",\"requiredScenarioState\": \"Bike Sold\",\"request\": {\"method\": \"GET\",\"url\": \"/possession/my_bicycle\"},\"response\": {\"status\": 200,\"headers\": {\"Content-Type\": \"application/json\",\"Cache-Control\": \"no-cache\"},\"body\" : {\"Price\":\"Already Sold\"}}}";
		  responseToWireMockBuilder = RestAssured.given().contentType("application/json")
					.body(configCommand).when().post("http://localhost:8080/__admin/scenarios");
		  // Let's check it loaded
		  assertEquals(200,responseToWireMockBuilder.getStatusCode());

		  */
		  
		  /* Part 2 – Using the configured Wiremock */
		  
		  //1st Step call the Get
	      Response responseToWireMockStep = given().
		  when().
		  get("http://localhost:8080/possession/my_bicycle");
		  
		  String bodyOfResponse = responseToWireMockStep.getBody().asString();
		  
		  assertEquals(responseToWireMockStep.getStatusCode(),200);
		  
		  assertTrue(bodyOfResponse.contains("<price>Not For Sale</price>"));
		 
		  //2nd Step call the Post 
	      
		  String actionCommand  = "{ \"action\": \"SELL\" }";
		    
		  responseToWireMockStep = given().
		  contentType("application/json").
		  body(actionCommand).
		  when().
		  post("http://localhost:8080/possession/my_bicycle");
		  
		  assertEquals(responseToWireMockStep.getStatusCode(),201);
		  
		  //3rd Step call the Get now on sale
	      responseToWireMockStep = given().
		  when().
		  get("http://localhost:8080/possession/my_bicycle");
		  
	      bodyOfResponse = responseToWireMockStep.getBody().asString();
		  
		  assertEquals(responseToWireMockStep.getStatusCode(),200);
		  
		  System.out.println(bodyOfResponse);
		  
		  assertTrue(bodyOfResponse.contains("<price>5000 New English Pounds</price>"));
		 
		  //4th Step call the Post with status of Sold
	      
		  actionCommand  = "{ \"action\": \"SOLD\" }";
		    
		  responseToWireMockStep = given().
		  contentType("application/json").
		  body(actionCommand).
		  when().
		  post("http://localhost:8080/possession/my_bicycle");
		  
		  assertEquals(responseToWireMockStep.getStatusCode(),201);
		  
		  //5th Step call the Get now sold
	      responseToWireMockStep = given().
		  when().
		  get("http://localhost:8080/possession/my_bicycle");
		  
		  bodyOfResponse = responseToWireMockStep.getBody().asString();

		  assertEquals(responseToWireMockStep.getStatusCode(),200);
		  
		  System.out.println("5th step" + bodyOfResponse);
		  
		  assertTrue(bodyOfResponse.contains("<price>Already Sold</price>"));
		  
		  //6th Step reset
	      
		  actionCommand  = "{ \"action\": \"RESET\" }";
		    
		  responseToWireMockStep = given().
		  contentType("application/json").
		  body(actionCommand).
		  when().
		  patch("http://localhost:8080/possession/my_bicycle");
		  
		  assertEquals(responseToWireMockStep.getStatusCode(),302);
	  }
	
}
