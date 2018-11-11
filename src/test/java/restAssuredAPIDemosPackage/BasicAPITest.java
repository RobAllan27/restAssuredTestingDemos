package restAssuredAPIDemosPackage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import dataProvider.ExcelFileReader;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import yamlgenerator.BasicSNAKEYAMLCreator;
import yamlgenerator.Contact;

import org.yaml.snakeyaml.Yaml;
import io.restassured.config.EncoderConfig;
import io.restassured.config.EncoderConfig.*;
//import io.restassured.config.EncoderConfig.encoderConfig;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.cookie.Cookie;

public class BasicAPITest {

	@Ignore
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
	
	@Ignore
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
	 
	  @Ignore
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

	  @Ignore
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
	  
	  @Ignore
	  @Test
	  public void RESTYAMLGenerator() {
	  //fail();
	  
		  	BasicSNAKEYAMLCreator myBasicSNAKEYAMLCreator =  new BasicSNAKEYAMLCreator();
		  
		  	RestAssured.config = new RestAssuredConfig().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8").encodeContentTypeAs("application/yaml", ContentType.TEXT));
		  	
		  	//RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
		  	
			String myYAMLString = myBasicSNAKEYAMLCreator.createContactArray();
			
			given()
			.contentType("application/yaml")
			//.config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/yaml", ContentType.TEXT)))
			.body(myYAMLString)
			.when()
			.post("http://localhost:3000/dolphinsyaml")
			.then()
			.statusCode(200);
	  }
	  
	  @Ignore
	  @Test
	  public void RESTYAMLTester() {
	  //fail();
			
			Response response = RestAssured.given().post("http://localhost:3000/dolphinsyaml");
			String bodyAsString = response.getBody().asString();
			
			Yaml yaml = new Yaml();
			Map loadedyamlmap = (Map) yaml.load(bodyAsString);			
			String foundAddress = (String)loadedyamlmap.get("address");
			Integer foundage = (Integer)loadedyamlmap.get("age");
			ArrayList<Map> foundPhoneNumbers = (ArrayList)loadedyamlmap.get("phonenumbers");

			assertEquals(34, foundage.intValue());
		    assertEquals("333 171st Ave S", foundAddress);
			
			Map phoneNumberMap = foundPhoneNumbers.get(0);
			assertEquals("HomeUpdated",phoneNumberMap.get("name"));
			assertEquals("206-555-1111",phoneNumberMap.get("number"));
			phoneNumberMap = foundPhoneNumbers.get(1);
			assertEquals("Work",phoneNumberMap.get("name"));
			assertEquals("425-555-2222",phoneNumberMap.get("number"));
	  }
		
/* Three Data providers are listed here - we will use them to set values in APIs 	
	
	Object[][] testObjArray = ExcelFileReader.getTableArray("C:/Users/Rob/Userdata/DX Software Testing/Tools/set_of_test_data_for_automation.xlsx","RESTpathstobetested");	

	The set of pages are - but this could be expanded upon in future
	 - RESTpathstobetested
	 - RESTJSONMessageContents
	 - URLLoginsToBeTested
	*/
	
	  @Test
	  public void checkXMLForBooks1stBookCategory() {

		  given().
		  when().
		  get("http://localhost:3000/dolphinsxml2").
		  then().
		  assertThat().
		  //body("store.booklist.book[0].category", equalTo("reference"));
		body("store.booklist.book[0].category", equalTo("reference"));
	  } 
	  
	  @Test
	  public void checkXMLForBooks3rdBookCategory() {

		  given().
		  when().
		  get("http://localhost:3000/dolphinsxml2").
		  then().
		  assertThat().
		  body("store.booklist.book[2].subcategory", equalTo("romance"));
	  }
	  
	  @Test
	  public void checkXMLForBooksLastBookCategory() {

		  given().
		  when().
		  get("http://localhost:3000/dolphinsxml2").
		  then().
		  assertThat().
		  body("store.booklist.book[-1].subcategory", equalTo("action"));
	  }
	  
	  @Test
	  public void checkXMLCountNumberofActionBooks() {
		  given().
		  when().
		  get("http://localhost:3000/dolphinsxml2").
		  then().
		  assertThat().
		  body("store.booklist.book.findAll{it.subcategory =='action'}.size()", equalTo(1));
	  }
	  
	  @Test
	  public void checkXMLCountNumberofBooksORstatement() {
		  given().
		  when().
		  get("http://localhost:3000/dolphinsxml2").
		  then().
		  assertThat().
		  body("store.booklist.book.findAll { book -> (book.subcategory =='action' || book.itemnumber == 24 )}.size()", equalTo(2));		  
	  }
	  
	  @Test
	  public void checkXMLCountNumberofBooksANDstatement() {
		  given().
		  when().
		  get("http://localhost:3000/dolphinsxml2").
		  then().
		  assertThat().
		  body("store.booklist.book.findAll { book -> (book.subcategory =='action' && book.itemnumber == 31 )}.size()", equalTo(1));
	  }
	 
	  @Test
	  public void checkXMLCountNumberofBooksNOTstatement() {
		  given().
		  when().
		  get("http://localhost:3000/dolphinsxml2").
		  then().
		  assertThat().
		  body("store.booklist.book.findAll { book -> book.subcategory !='action'}.size()", equalTo(3));
	  }
	  
	  @Test
	  public void checkXMLCountNumberofBooksInaSet() {
		  given().
		  when().
		  get("http://localhost:3000/dolphinsxml2").
		  then().
		  assertThat().
		  body("store.booklist.book.findAll{it.subcategory in ['romance' ,'action' ]}.size()", equalTo(3));
	  }

	  @Test
	  public void checkXMLCountNumberofBooksPriceRange() {
		  given().
		  when().
		  get("http://localhost:3000/dolphinsxml2").
		  then().
		  assertThat().
		  body("store.booklist.book.findAll { book -> book.price.toDouble() >= 5 && book.price.toDouble() <= 15 }.size()", equalTo(3));
	  }
	  
	  @Test
	  public void checkXMLCountNumberofBooksItemRange() {
		  given().
		  when().
		  get("http://localhost:3000/dolphinsxml2").
		  then().
		  assertThat().
		  body("store.booklist.book.findAll { book -> book.itemnumber.toInteger() >= 22 && book.itemnumber.toInteger() <= 27 }.size()", equalTo(3));
	  }
	  
	  @Ignore
	  @Test
	  public void checkJSONFor1stBookCategory() {

		  given().
		  when().
		  get("http://localhost:3000/dolphinsjson").
		  then().
		  assertThat().
		  body("store.book[0].category", equalTo("reference"));
	  } 
	  
	  @Ignore
	  @Test
	  public void checkJSONForBooks3rdBookCategory() {

		  given().
		  when().
		  get("http://localhost:3000/dolphinsjson").
		  then().
		  assertThat().
		  body("store.book[2].subcategory", equalTo("romance"));
	  }
	  
	  @Ignore
	  @Test
	  public void checkJSONForBooksLastBookCategory() {

		  given().
		  when().
		  get("http://localhost:3000/dolphinsjson").
		  then().
		  assertThat().
		  body("store.book[-1].subcategory", equalTo("action"));
	  }
	  
	  @Ignore
	  @Test
	  public void checkJSONCountNumberofActionBooks() {
		  given().
		  when().
		  get("http://localhost:3000/dolphinsjson").
		  then().
		  assertThat().
		  body("store.book.findAll{it.subcategory =='action'}.size()", equalTo(1));
	  }
	  
	  @Ignore
	  @Test
	  public void checkJSONCountNumberofBooksORstatement() {
		  given().
		  when().
		  get("http://localhost:3000/dolphinsjson").
		  then().
		  assertThat().
		  body("store.book.findAll { book -> (book.subcategory =='action' || book.itemnumber == 24 )}.size()", equalTo(2));		  
	  }
	  
	  @Ignore
	  @Test
	  public void checkJSONCountNumberofBooksANDstatement() {
		  given().
		  when().
		  get("http://localhost:3000/dolphinsjson").
		  then().
		  assertThat().
		  body("store.book.findAll { book -> (book.subcategory =='action' && book.itemnumber == 31 )}.size()", equalTo(1));
	  }
	 
	  @Ignore
	  @Test
	  public void checkJSONCountNumberofBooksNOTstatement() {
		  given().
		  when().
		  get("http://localhost:3000/dolphinsjson").
		  then().
		  assertThat().
		  body("store.book.findAll { book -> book.subcategory !='action'}.size()", equalTo(3));
	  }
	  
	  @Ignore
	  @Test
	  public void checkJSONCountNumberofBooksInaSet() {
		  given().
		  when().
		  get("http://localhost:3000/dolphinsjson").
		  then().
		  assertThat().
		  body("store.book.findAll{it.subcategory in ['romance' ,'action' ]}.size()", equalTo(3));
	  }

	  @Ignore
	  @Test
	  public void checkJSONCountNumberofBooksPriceRange() {
		  given().
		  when().
		  get("http://localhost:3000/dolphinsjson").
		  then().
		  assertThat().
		  body("store.book.findAll { book -> book.price >= 5 && book.price <= 15 }.size()", equalTo(3));
	  }
	  
	  @Ignore
	  @Test
	  public void checkJSONCountNumberofBooksItemRange() {
		  given().
		  when().
		  get("http://localhost:3000/dolphinsjson").
		  then().
		  assertThat().
		  body("store.book.findAll { book -> book.itemnumber >= 22 && book.itemnumber <= 27 }.size()", equalTo(3));
	  }
	  
	  @Ignore
	  @Test
	  public void checkJSONExectueOperationThenChecks() {
		  Response response = RestAssured.given().get("http://localhost:3000/dolphinsjson");
		  String bodyAsString = response.getBody().asString();
		  
		  List<Map> books = io.restassured.path.json.JsonPath.with(bodyAsString).get("store.book.findAll { book -> book.price >= 5 && book.price <= 15 }");
		  System.out.println("Books Size " + books.size());
		  assertEquals(3,books.size());
		  String firstCategory = io.restassured.path.json.JsonPath.with(bodyAsString).get("store.book[0].category");
		  assertEquals("reference",firstCategory);
	  }
	  
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
