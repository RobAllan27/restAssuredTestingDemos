package restAssuredAPIDemosPackage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import dataProvider.ExcelFileReader;
//import io.restassured.http.Cookie;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.fail;

import org.apache.http.cookie.Cookie;

public class BasicAPITest {

	@Test
	public void exampleRestTest() {
	// To show when an unknown resource is called, that a 404 is returned
		
		String paramBeingPassed = "valuebeingpassedin";
		String pathaddition = "2017";
		
		given()
		.param("textValue", paramBeingPassed)
		.pathParam("pathtoken", pathaddition)
		.when()
		.get("http://localhost:3000/{pathtoken}/answer")
		.then()
		.statusCode(404);
	}
	
	@Test
	public void testHeadersAndCookies(){
		
		//Cookie createdCookie = new Cookie("session_id", "1234");
		
		given().header("User-Agent-Extra", "MyAppName")
		.header("Timezone", "uk")
		//.cookie(createdCookie)
		.when()
		.get("http://localhost:3000/answer")
		.then()
		.statusCode(200);
		
	}
	
	  @Test(dataProvider = "DP4RESTpathstobetested")
	  // I have 5 fields in the excel chart - TestCaseName	URL_value	Expected Response	1st_date	2nd_date
	  
	  public void RESTPathsTest(String TestCaseName, String URL_value, String Expected_Response, String	first_date, String second_date) {
			//String paramBeingPassed = "valuebeingpassedin";
			
			given()
			.param("textValue", TestCaseName)
			.when()
			.get("http://localhost:3000/answer")
			.then()
			.statusCode(200);	  
	  }
	  
	  @Ignore
	  @Test(dataProvider = "DP4RESTJSONMessageContents")
	  // I have 2 fields in the excel chart - TestCaseName	UserName
	  public void RESTJSONContentsWithBuilder(String TestCaseName, String inArtist, String inRecordedCity) {
	  //fail();
	  
		  JSONMessageBuilder jsonMsgBldr = new JSONMessageBuilder();
			jsonMsgBldr.buildClassModel(inArtist, inRecordedCity);
			String JSONString = jsonMsgBldr.jsonString();
			
			given()
			.contentType("application/json")
			.body(JSONString)
			.when()
			.post("http://localhost:3000/dolphins")
			.then()
			.statusCode(200);	  
	  }

	  //{"album":[{"title":"Groovy Days","songs":["Happy Days","Sunny Days","Lovely Days"],"musician":[{"name":"James Dean","instrument":"Guitar"},{"name":"Brad Pitt","instrument":"Guitar"},{"name":"Bruce Willis","instrument":"Drums"}],"links":["link1","link2","link3"],"artist":{"name":"Ringo Star","birthDate":"-1344252310000"},"recordedcity":"Birkenhead"},{"title":"Happy Nights","songs":["Happy Nights","Sunny Nights","Lovely Nights"],"musician":[{"name":"Leo Tolstoy","instrument":"Guitar"},{"name":"Willian Shakespeare","instrument":"Guitar"},{"name":"Charles Dickens","instrument":"Drums"}],"links":["link11","link12"],"artist":{"name":"John Steinbeck","birthDate":"-1344252310000"},"recordedcity":"Liverpool"},{"title":"Happy Nights","songs":["Happy Nights","Sunny Nights","Lovely Nights"],"musician":[{"name":"Kevin Keegan","instrument":"Guitar"},{"name":"Michael Owen","instrument":"Guitar"},{"name":"Robbie Fowler","instrument":"Drums"}],"links":["link11","link12"],"artist":{"name":"John Steinbeck","birthDate":"-1344252310000"},"recordedcity":"Liverpool"}],"collection_type":"music","collection":"Rob's Collection"}

	  @Test(dataProvider = "DP4RESTJSONMessageContents")
	  // I have 2 fields in the excel chart - TestCaseName	UserName
	  public void RESTJSONContentsEditString(String TestCaseName, String inArtist, String inRecordedCity) {
	  //fail();
	  
			String JSONString = "{\"album\":[{\"title\":\"Groovy Days\",\"songs\":[\"Happy Days\",\"Sunny Days\",\"Lovely Days\"],\"musician\":[{\"name\":\"James Dean\",\"instrument\":\"Guitar\"},{\"name\":\"Brad Pitt\",\"instrument\":\"Guitar\"},{\"name\":\"Bruce Willis\",\"instrument\":\"Drums\"}],\"links\":[\"link1\",\"link2\",\"link3\"],\"artist\":{\"name\":\""+inArtist+"\",\"birthDate\":\"-1344252310000\"},\"recordedcity\":\""+inRecordedCity+"\"},{\"title\":\"Happy Nights\",\"songs\":[\"Happy Nights\",\"Sunny Nights\",\"Lovely Nights\"],\"musician\":[{\"name\":\"Leo Tolstoy\",\"instrument\":\"Guitar\"},{\"name\":\"Willian Shakespeare\",\"instrument\":\"Guitar\"},{\"name\":\"Charles Dickens\",\"instrument\":\"Drums\"}],\"links\":[\"link11\",\"link12\"],\"artist\":{\"name\":\"John Steinbeck\",\"birthDate\":\"-1344252310000\"},\"recordedcity\":\"Liverpool\"},{\"title\":\"Happy Nights\",\"songs\":[\"Happy Nights\",\"Sunny Nights\",\"Lovely Nights\"],\"musician\":[{\"name\":\"Kevin Keegan\",\"instrument\":\"Guitar\"},{\"name\":\"Michael Owen\",\"instrument\":\"Guitar\"},{\"name\":\"Robbie Fowler\",\"instrument\":\"Drums\"}],\"links\":[\"link11\",\"link12\"],\"artist\":{\"name\":\"John Steinbeck\",\"birthDate\":\"-1344252310000\"},\"recordedcity\":\"Liverpool\"}],\"collection_type\":\"music\",\"collection\":\"Rob's Collection\"}";
		  
			given()
			.contentType("application/json")
			.body(JSONString)
			.when()
			.post("http://localhost:3000/dolphins")
			.then()
			.statusCode(200);	  
	  }
	  
	  @Ignore
	  @Test(dataProvider = "DP4URLLoginsToBeTested")  
	  //I have 3 fields in the excel chart - TestCaseName	UserName	Password
	  public void URLsToBTested(String TestCaseName, String	UserName, String Password) {
	 fail();
	  }
		
/* Three Data providers are listed here - we will use them to set values in APIs 	
	
	Object[][] testObjArray = ExcelFileReader.getTableArray("C:/Users/Rob/Userdata/DX Software Testing/Tools/set_of_test_data_for_automation.xlsx","RESTpathstobetested");	

	The set of pages are - but this could be expanded upon in future
	 - RESTpathstobetested
	 - RESTJSONMessageContents
	 - URLLoginsToBeTested
	*/
	
	 @Ignore 
	 @DataProvider
	 public Object[][] DP4RESTpathstobetested() throws Exception{
	 
			Object[][] testObjArray = ExcelFileReader.getTableArray("C:/Users/Rob/Userdata/DX Software Testing/Tools/set_of_test_"
					+ "data_for_automation.xlsx","RESTpathstobetested");	
		    	return (testObjArray);
			}
	
	  @DataProvider
	  public Object[][] DP4RESTJSONMessageContents() throws Exception{
	 
			Object[][] testObjArray = ExcelFileReader.getTableArray("C:/Users/Rob/Userdata/DX Software Testing/Tools/set_of_test_"
					+ "data_for_automation.xlsx","RESTJSONMessageContents");	
		    	return (testObjArray);
			}
	  
	  @DataProvider
	  public Object[][] DP4URLLoginsToBeTested() throws Exception{
	 
			Object[][] testObjArray = ExcelFileReader.getTableArray("C:/Users/Rob/Userdata/DX Software Testing/Tools/set_of_test_"
					+ "data_for_automation.xlsx","URLLoginsToBeTested");	
		    	return (testObjArray);
			}
	  
	  
}
