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

public class WiremockServiceVirtualisation {
  @Ignore 
  @Test(dataProvider = "DP4WireMockJSONMessageContents")
  // I have 3 fields in the excel chart - TestCaseName	- BookStore - Address
  public void testWireMockSpecificURL(String TestCaseName, String inBookStore, String inAddress) {
	  
	  /* Part 1 - Configuring the Wiremock */
	  String JSONStringToBeLoaded = "{\\\"store\\\": {\\\"name\\\": \\\""+inBookStore+"\\\",\\\"Address\\\": \\\""+inAddress+"\\\",\\\"book\\\": [{\\\"category\\\": \\\"reference\\\",\\\"subcategory\\\": \\\"stamps\\\",\\\"author\\\": \\\"Nigel Philatelist\\\",\\\"title\\\": \\\"Stamps of the Europe\\\",\\\"isbn\\\": \\\"0-12-7778899-3=2\\\",\\\"price\\\": 7.99,\\\"itemnumber\\\": 23},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"romance\\\",\\\"author\\\": \\\"Evelyn Waugh\\\",\\\"title\\\": \\\"Bride's Romance\\\",\\\"isbn\\\": \\\"0-12-456311-3\\\",\\\"price\\\": 12.99,\\\"itemnumber\\\": 24},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"romance\\\",\\\"author\\\": \\\"Jane Austen\\\",\\\"title\\\": \\\"Pride and Prejudice\\\",\\\"isbn\\\": \\\"0-12-456311-3\\\",\\\"price\\\": 12.99,\\\"itemnumber\\\": 25},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"action\\\",\\\"author\\\": \\\"J. R. R. Tolkien\\\",\\\"title\\\": \\\"The Lord of the Rings\\\",\\\"isbn\\\": \\\"0-871-92135-8\\\",\\\"price\\\": 27.99,\\\"itemnumber\\\": 31}],\\\"bicycle\\\": [{\\\"model\\\": \\\"MX99S\\\",\\\"brand\\\": \\\"Giant\\\",\\\"color\\\": \\\"red\\\",\\\"price\\\": 599.95,\\\"item number\\\": 451},{\\\"model\\\": \\\"MXS65S\\\",\\\"brand\\\": \\\"Giant\\\",\\\"color\\\": \\\"blue\\\",\\\"price\\\": 607.95,\\\"item number\\\": 452}]}}";
	  
	  String configCommand  = "{ \"request\": { \"url\": \"/bookstore/"+ inBookStore +"\", \"method\": \"GET\" }, \"response\": {\"transformers\": [\"response-template\"], \"status\": 200, \"headers\": {\"Content-Type\": \"application/json\",\"DateTime\": \"{now},\",\"Cache-Control\": \"no-cache\"},\"body\": \"" + JSONStringToBeLoaded + "\" }}";

	  Response responseToWireMockBuilder = RestAssured.given().contentType("application/json")
				.body(configCommand).when().post("http://localhost:8080/__admin/mappings/new");
	  

	  /* Part 2 – Using the configured Wiremock */
	  given().
	  when().
	  get("http://localhost:8080/bookstore/"+inBookStore).
	  then().
	  assertThat().
	  body("store.name", equalTo(inBookStore));
  }
  
  @Ignore
  @Test
  public void testWireDefaultURL() {  
	  /* Part 1 - Configuring the Wiremock */
	  String JSONStringToBeLoaded = "{\\\"store\\\": {\\\"name\\\": \\\"defaultBookstore\\\",\\\"Address\\\": \\\"defaultAddress\\\",\\\"book\\\": [{\\\"category\\\": \\\"reference\\\",\\\"subcategory\\\": \\\"stamps\\\",\\\"author\\\": \\\"Nigel Philatelist\\\",\\\"title\\\": \\\"Stamps of the Europe\\\",\\\"isbn\\\": \\\"0-12-7778899-3=2\\\",\\\"price\\\": 7.99,\\\"itemnumber\\\": 23},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"romance\\\",\\\"author\\\": \\\"Evelyn Waugh\\\",\\\"title\\\": \\\"Bride's Romance\\\",\\\"isbn\\\": \\\"0-12-456311-3\\\",\\\"price\\\": 12.99,\\\"itemnumber\\\": 24},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"romance\\\",\\\"author\\\": \\\"Jane Austen\\\",\\\"title\\\": \\\"Pride and Prejudice\\\",\\\"isbn\\\": \\\"0-12-456311-3\\\",\\\"price\\\": 12.99,\\\"itemnumber\\\": 25},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"action\\\",\\\"author\\\": \\\"J. R. R. Tolkien\\\",\\\"title\\\": \\\"The Lord of the Rings\\\",\\\"isbn\\\": \\\"0-871-92135-8\\\",\\\"price\\\": 27.99,\\\"itemnumber\\\": 31}],\\\"bicycle\\\": [{\\\"model\\\": \\\"MX99S\\\",\\\"brand\\\": \\\"Giant\\\",\\\"color\\\": \\\"red\\\",\\\"price\\\": 599.95,\\\"item number\\\": 451},{\\\"model\\\": \\\"MXS65S\\\",\\\"brand\\\": \\\"Giant\\\",\\\"color\\\": \\\"blue\\\",\\\"price\\\": 607.95,\\\"item number\\\": 452}]}}";
	  
	 String configCommand  = "{ \"request\": { \"urlPattern\": \"/bookstore/.*\", \"method\": \"ANY\" }, \"response\": {\"transformers\": [\"response-template\"], \"status\": 200, \"headers\": {\"Content-Type\": \"application/json\",\"DateTime\": \"{now},\",\"Cache-Control\": \"no-cache\"},\"body\": \"" + JSONStringToBeLoaded + "\" }}";
		
	  Response responseToWireMockBuilder = RestAssured.given().contentType("application/json")
				.body(configCommand).when().post("http://localhost:8080/__admin/mappings/new");
	  
	  /* Part 2 – Using the configured Wiremock */
	  given().
	  when().
	  post("http://localhost:8080/bookstore/Dymocks").
	  then().
	  assertThat().
	  body("store.name", equalTo("defaultBookstore"));
  }
 
  @Ignore
  @Test(dataProvider = "DP4WireMockJSONMessageContents")
  // I have 3 fields in the excel chart - TestCaseName	- BookStore - Address
  public void testWireMockFixedDelay(String TestCaseName, String inBookStore, String inAddress) {
	  
	  /* Part 1 - Configuring the Wiremock */
	  String JSONStringToBeLoaded = "{\\\"store\\\": {\\\"name\\\": \\\""+inBookStore+"\\\",\\\"Address\\\": \\\""+inAddress+"\\\",\\\"book\\\": [{\\\"category\\\": \\\"reference\\\",\\\"subcategory\\\": \\\"stamps\\\",\\\"author\\\": \\\"Nigel Philatelist\\\",\\\"title\\\": \\\"Stamps of the Europe\\\",\\\"isbn\\\": \\\"0-12-7778899-3=2\\\",\\\"price\\\": 7.99,\\\"itemnumber\\\": 23},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"romance\\\",\\\"author\\\": \\\"Evelyn Waugh\\\",\\\"title\\\": \\\"Bride's Romance\\\",\\\"isbn\\\": \\\"0-12-456311-3\\\",\\\"price\\\": 12.99,\\\"itemnumber\\\": 24},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"romance\\\",\\\"author\\\": \\\"Jane Austen\\\",\\\"title\\\": \\\"Pride and Prejudice\\\",\\\"isbn\\\": \\\"0-12-456311-3\\\",\\\"price\\\": 12.99,\\\"itemnumber\\\": 25},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"action\\\",\\\"author\\\": \\\"J. R. R. Tolkien\\\",\\\"title\\\": \\\"The Lord of the Rings\\\",\\\"isbn\\\": \\\"0-871-92135-8\\\",\\\"price\\\": 27.99,\\\"itemnumber\\\": 31}],\\\"bicycle\\\": [{\\\"model\\\": \\\"MX99S\\\",\\\"brand\\\": \\\"Giant\\\",\\\"color\\\": \\\"red\\\",\\\"price\\\": 599.95,\\\"item number\\\": 451},{\\\"model\\\": \\\"MXS65S\\\",\\\"brand\\\": \\\"Giant\\\",\\\"color\\\": \\\"blue\\\",\\\"price\\\": 607.95,\\\"item number\\\": 452}]}}";

	  //String configCommand  = "{ \"request\": { \"url\": \"/get/that\", \"method\": \"GET\" }, \"response\": { \"status\": 200, \"body\": " + JSONStringToBeLoaded + " }}";
	  
	  /*
	  *"fixedDelay": 500 
	  */
	  
	 String configCommand  = "{ \"request\": { \"url\": \"/bookstore/"+ inBookStore +"\", \"method\": \"GET\" }, \"response\": { \"status\": 200, \"fixedDelayMilliseconds\": 10000, \"headers\": {\"Content-Type\": \"application/json\",\"Cache-Control\": \"no-cache\"},\"body\": \"" + JSONStringToBeLoaded + "\" }}";
	 
	  Response responseToWireMockBuilder = RestAssured.given().contentType("application/json")
				.body(configCommand).when().post("http://localhost:8080/__admin/mappings/new");
	  

	  /* Part 2 – Using the configured Wiremock */
	  given().
	  when().
	  get("http://localhost:8080/bookstore/"+inBookStore).
	  then().
	  assertThat().
	  body("store.name", equalTo(inBookStore));
  }
  
  @Ignore
  @Test(dataProvider = "DP4WireMockJSONMessageContents")
  // I have 3 fields in the excel chart - TestCaseName	- BookStore - Address
  public void testWireMockLogDelayDistribution(String TestCaseName, String inBookStore, String inAddress) {
	  
	  /* Part 1 - Configuring the Wiremock */ 
	  String JSONStringToBeLoaded = "{\\\"store\\\": {\\\"name\\\": \\\""+inBookStore+"\\\",\\\"Address\\\": \\\""+inAddress+"\\\",\\\"book\\\": [{\\\"category\\\": \\\"reference\\\",\\\"subcategory\\\": \\\"stamps\\\",\\\"author\\\": \\\"Nigel Philatelist\\\",\\\"title\\\": \\\"Stamps of the Europe\\\",\\\"isbn\\\": \\\"0-12-7778899-3=2\\\",\\\"price\\\": 7.99,\\\"itemnumber\\\": 23},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"romance\\\",\\\"author\\\": \\\"Evelyn Waugh\\\",\\\"title\\\": \\\"Bride's Romance\\\",\\\"isbn\\\": \\\"0-12-456311-3\\\",\\\"price\\\": 12.99,\\\"itemnumber\\\": 24},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"romance\\\",\\\"author\\\": \\\"Jane Austen\\\",\\\"title\\\": \\\"Pride and Prejudice\\\",\\\"isbn\\\": \\\"0-12-456311-3\\\",\\\"price\\\": 12.99,\\\"itemnumber\\\": 25},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"action\\\",\\\"author\\\": \\\"J. R. R. Tolkien\\\",\\\"title\\\": \\\"The Lord of the Rings\\\",\\\"isbn\\\": \\\"0-871-92135-8\\\",\\\"price\\\": 27.99,\\\"itemnumber\\\": 31}],\\\"bicycle\\\": [{\\\"model\\\": \\\"MX99S\\\",\\\"brand\\\": \\\"Giant\\\",\\\"color\\\": \\\"red\\\",\\\"price\\\": 599.95,\\\"item number\\\": 451},{\\\"model\\\": \\\"MXS65S\\\",\\\"brand\\\": \\\"Giant\\\",\\\"color\\\": \\\"blue\\\",\\\"price\\\": 607.95,\\\"item number\\\": 452}]}}";
	  
	  /*
	   * Lognormal delayDistribution": {
       * "type": "lognormal",
       * "median": 80,
       * "sigma": 0.4
	   */
	  
	  String configCommand  = "{ \"request\": { \"url\": \"/bookstore/"+ inBookStore +"\", \"method\": \"GET\" }, \"response\": { \"status\": 200,\"delayDistribution\": {\"type\": \"lognormal\",\"median\": 8000,\"sigma\": 0.4}, \"headers\": {\"Content-Type\": \"application/json\",\"Cache-Control\": \"no-cache\"},\"body\": \"" + JSONStringToBeLoaded + "\" }}";	  
	 
	  Response responseToWireMockBuilder = RestAssured.given().contentType("application/json")
				.body(configCommand).when().post("http://localhost:8080/__admin/mappings/new");
	  

	  /* Part 2 – Using the configured Wiremock */
	  given().
	  when().
	  get("http://localhost:8080/bookstore/"+inBookStore).
	  then().
	  assertThat().
	  body("store.name", equalTo(inBookStore));
  }
  
  @Ignore
  @Test(dataProvider = "DP4WireMockJSONMessageContents")
  // I have 3 fields in the excel chart - TestCaseName	- BookStore - Address
  public void testWireMockUniformDelayDistribution(String TestCaseName, String inBookStore, String inAddress) {
	  
	  /* Part 1 - Configuring the Wiremock */
	  String JSONStringToBeLoaded = "{\\\"store\\\": {\\\"name\\\": \\\""+inBookStore+"\\\",\\\"Address\\\": \\\""+inAddress+"\\\",\\\"book\\\": [{\\\"category\\\": \\\"reference\\\",\\\"subcategory\\\": \\\"stamps\\\",\\\"author\\\": \\\"Nigel Philatelist\\\",\\\"title\\\": \\\"Stamps of the Europe\\\",\\\"isbn\\\": \\\"0-12-7778899-3=2\\\",\\\"price\\\": 7.99,\\\"itemnumber\\\": 23},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"romance\\\",\\\"author\\\": \\\"Evelyn Waugh\\\",\\\"title\\\": \\\"Bride's Romance\\\",\\\"isbn\\\": \\\"0-12-456311-3\\\",\\\"price\\\": 12.99,\\\"itemnumber\\\": 24},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"romance\\\",\\\"author\\\": \\\"Jane Austen\\\",\\\"title\\\": \\\"Pride and Prejudice\\\",\\\"isbn\\\": \\\"0-12-456311-3\\\",\\\"price\\\": 12.99,\\\"itemnumber\\\": 25},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"action\\\",\\\"author\\\": \\\"J. R. R. Tolkien\\\",\\\"title\\\": \\\"The Lord of the Rings\\\",\\\"isbn\\\": \\\"0-871-92135-8\\\",\\\"price\\\": 27.99,\\\"itemnumber\\\": 31}],\\\"bicycle\\\": [{\\\"model\\\": \\\"MX99S\\\",\\\"brand\\\": \\\"Giant\\\",\\\"color\\\": \\\"red\\\",\\\"price\\\": 599.95,\\\"item number\\\": 451},{\\\"model\\\": \\\"MXS65S\\\",\\\"brand\\\": \\\"Giant\\\",\\\"color\\\": \\\"blue\\\",\\\"price\\\": 607.95,\\\"item number\\\": 452}]}}";
	  
	  /* Uniform delay Distribution
	   * we can build a upper and lower distribution of the delays with uniform delay
	   * 
	   */
	  
	  String configCommand  = "{ \"request\": { \"url\": \"/bookstore/"+ inBookStore +"\", \"method\": \"GET\" }, \"response\": { \"status\": 200,  \"delayDistribution\": {\"type\": \"uniform\",\"lower\": 5000,\"upper\": 10000}, \"headers\": {\"Content-Type\": \"application/json\",\"Cache-Control\": \"no-cache\"},\"body\": \"" + JSONStringToBeLoaded + "\" }}";
	   
	  Response responseToWireMockBuilder = RestAssured.given().contentType("application/json")
				.body(configCommand).when().post("http://localhost:8080/__admin/mappings/new");
	  

	  /* Part 2 – Using the configured Wiremock */
	  given().
	  when().
	  get("http://localhost:8080/bookstore/"+inBookStore).
	  then().
	  assertThat().
	  body("store.name", equalTo(inBookStore));
  }
  
  @Ignore
  @Test(dataProvider = "DP4WireMockJSONMessageContents")
  // I have 3 fields in the excel chart - TestCaseName	- BookStore - Address
  public void testWireMockChunkedDelay(String TestCaseName, String inBookStore, String inAddress) {
	  
	  /* Part 1 - Configuring the Wiremock */
	  String JSONStringToBeLoaded = "{\\\"store\\\": {\\\"name\\\": \\\""+inBookStore+"\\\",\\\"Address\\\": \\\""+inAddress+"\\\",\\\"book\\\": [{\\\"category\\\": \\\"reference\\\",\\\"subcategory\\\": \\\"stamps\\\",\\\"author\\\": \\\"Nigel Philatelist\\\",\\\"title\\\": \\\"Stamps of the Europe\\\",\\\"isbn\\\": \\\"0-12-7778899-3=2\\\",\\\"price\\\": 7.99,\\\"itemnumber\\\": 23},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"romance\\\",\\\"author\\\": \\\"Evelyn Waugh\\\",\\\"title\\\": \\\"Bride's Romance\\\",\\\"isbn\\\": \\\"0-12-456311-3\\\",\\\"price\\\": 12.99,\\\"itemnumber\\\": 24},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"romance\\\",\\\"author\\\": \\\"Jane Austen\\\",\\\"title\\\": \\\"Pride and Prejudice\\\",\\\"isbn\\\": \\\"0-12-456311-3\\\",\\\"price\\\": 12.99,\\\"itemnumber\\\": 25},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"action\\\",\\\"author\\\": \\\"J. R. R. Tolkien\\\",\\\"title\\\": \\\"The Lord of the Rings\\\",\\\"isbn\\\": \\\"0-871-92135-8\\\",\\\"price\\\": 27.99,\\\"itemnumber\\\": 31}],\\\"bicycle\\\": [{\\\"model\\\": \\\"MX99S\\\",\\\"brand\\\": \\\"Giant\\\",\\\"color\\\": \\\"red\\\",\\\"price\\\": 599.95,\\\"item number\\\": 451},{\\\"model\\\": \\\"MXS65S\\\",\\\"brand\\\": \\\"Giant\\\",\\\"color\\\": \\\"blue\\\",\\\"price\\\": 607.95,\\\"item number\\\": 452}]}}";
	  
	  /* chunked delay
	  
	  *            "chunkedDribbleDelay": {
                    "numberOfChunks": 5,
                    "totalDuration": 1000
	  */
	 
	  String configCommand  = "{ \"request\": { \"url\": \"/bookstore/"+ inBookStore +"\", \"method\": \"GET\" }, \"response\": { \"status\": 200,\"chunkedDribbleDelay\": {\"numberOfChunks\": 5,\"totalDuration\": 5000}, \"headers\": {\"Content-Type\": \"application/json\",\"Cache-Control\": \"no-cache\"},\"body\": \"" + JSONStringToBeLoaded + "\" }}"; 
	  
	  Response responseToWireMockBuilder = RestAssured.given().contentType("application/json")
				.body(configCommand).when().post("http://localhost:8080/__admin/mappings/new");  

	  /* Part 2 – Using the configured Wiremock */
	  given().
	  when().
	  get("http://localhost:8080/bookstore/"+inBookStore).
	  then().
	  assertThat().
	  body("store.name", equalTo(inBookStore));
  }
  
  @Ignore
  @Test(dataProvider = "DP4WireMockJSONMessageContents")
  // I have 3 fields in the excel chart - TestCaseName	- BookStore - Address
  public void testWireMockFault(String TestCaseName, String inBookStore, String inAddress) {
	  
	  /* Part 1 - Configuring the Wiremock */
	  String JSONStringToBeLoaded = "{\\\"store\\\": {\\\"name\\\": \\\""+inBookStore+"\\\",\\\"Address\\\": \\\""+inAddress+"\\\",\\\"book\\\": [{\\\"category\\\": \\\"reference\\\",\\\"subcategory\\\": \\\"stamps\\\",\\\"author\\\": \\\"Nigel Philatelist\\\",\\\"title\\\": \\\"Stamps of the Europe\\\",\\\"isbn\\\": \\\"0-12-7778899-3=2\\\",\\\"price\\\": 7.99,\\\"itemnumber\\\": 23},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"romance\\\",\\\"author\\\": \\\"Evelyn Waugh\\\",\\\"title\\\": \\\"Bride's Romance\\\",\\\"isbn\\\": \\\"0-12-456311-3\\\",\\\"price\\\": 12.99,\\\"itemnumber\\\": 24},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"romance\\\",\\\"author\\\": \\\"Jane Austen\\\",\\\"title\\\": \\\"Pride and Prejudice\\\",\\\"isbn\\\": \\\"0-12-456311-3\\\",\\\"price\\\": 12.99,\\\"itemnumber\\\": 25},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"action\\\",\\\"author\\\": \\\"J. R. R. Tolkien\\\",\\\"title\\\": \\\"The Lord of the Rings\\\",\\\"isbn\\\": \\\"0-871-92135-8\\\",\\\"price\\\": 27.99,\\\"itemnumber\\\": 31}],\\\"bicycle\\\": [{\\\"model\\\": \\\"MX99S\\\",\\\"brand\\\": \\\"Giant\\\",\\\"color\\\": \\\"red\\\",\\\"price\\\": 599.95,\\\"item number\\\": 451},{\\\"model\\\": \\\"MXS65S\\\",\\\"brand\\\": \\\"Giant\\\",\\\"color\\\": \\\"blue\\\",\\\"price\\\": 607.95,\\\"item number\\\": 452}]}}";
	  
	  //fault string
	  
	  /*
	   * Choose an error
	   * EMPTY_RESPONSE:
		MALFORMED_RESPONSE_CHUNK: 
		RANDOM_DATA_THEN_CLOSE:
		CONNECTION_RESET_BY_PEER: 
	   */
	  
	  String configCommand  = "{ \"request\": { \"url\": \"/bookstore/"+ inBookStore +"\", \"method\": \"GET\" }, \"response\": { \"fault\": \"MALFORMED_RESPONSE_CHUNK\" }}";
	  
	  Response responseToWireMockBuilder = RestAssured.given().contentType("application/json")
				.body(configCommand).when().post("http://localhost:8080/__admin/mappings/new");
	  
	  /* Part 2 – Using the configured Wiremock */
	  given().
	  when().
	  get("http://localhost:8080/bookstore/"+inBookStore).
	  then().
	  assertThat().
	  body("store.name", equalTo(inBookStore));
  }
  
  @Ignore
  @Test(dataProvider = "DP4WireMockJSONMessageContents")
  // I have 3 fields in the excel chart - TestCaseName	- BookStore - Address
  public void testWireMockDynamicResponse(String TestCaseName, String inBookStore, String inAddress) {
	  
	  /* Part 1 - Configuring the Wiremock */
	  String JSONStringToBeLoaded = "{\\\"store\\\": {\\\"name\\\": \\\""+inBookStore+"\\\",\\\"Address\\\": \\\""+inAddress+"\\\",\\\"book\\\": [{\\\"category\\\": \\\"reference\\\",\\\"subcategory\\\": \\\"stamps\\\",\\\"author\\\": \\\"Nigel Philatelist\\\",\\\"title\\\": \\\"Stamps of the Europe\\\",\\\"isbn\\\": \\\"0-12-7778899-3=2\\\",\\\"price\\\": 7.99,\\\"itemnumber\\\": 23},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"romance\\\",\\\"author\\\": \\\"Evelyn Waugh\\\",\\\"title\\\": \\\"Bride's Romance\\\",\\\"isbn\\\": \\\"0-12-456311-3\\\",\\\"price\\\": 12.99,\\\"itemnumber\\\": 24},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"romance\\\",\\\"author\\\": \\\"Jane Austen\\\",\\\"title\\\": \\\"Pride and Prejudice\\\",\\\"isbn\\\": \\\"0-12-456311-3\\\",\\\"price\\\": 12.99,\\\"itemnumber\\\": 25},{\\\"category\\\": \\\"fiction\\\",\\\"subcategory\\\": \\\"action\\\",\\\"author\\\": \\\"J. R. R. Tolkien\\\",\\\"title\\\": \\\"The Lord of the Rings\\\",\\\"isbn\\\": \\\"0-871-92135-8\\\",\\\"price\\\": 27.99,\\\"itemnumber\\\": 31}],\\\"bicycle\\\": [{\\\"model\\\": \\\"MX99S\\\",\\\"brand\\\": \\\"Giant\\\",\\\"color\\\": \\\"red\\\",\\\"price\\\": 599.95,\\\"item number\\\": 451},{\\\"model\\\": \\\"MXS65S\\\",\\\"brand\\\": \\\"Giant\\\",\\\"color\\\": \\\"blue\\\",\\\"price\\\": 607.95,\\\"item number\\\": 452}]}}";
	  
	 String configCommand  = "{ \"request\": { \"url\": \"/bookstore/"+ inBookStore +"\", \"method\": \"GET\" }, \"response\": {\"transformers\": [\"response-template\"], \"status\": 200, \"headers\": {\"Content-Type\": \"application/json\",\"DateTime\": \"{now},\",\"Cache-Control\": \"no-cache\"},\"body\": \"" + JSONStringToBeLoaded + "\" }}";
				
	  Response responseToWireMockBuilder = RestAssured.given().contentType("application/json")
				.body(configCommand).when().post("http://localhost:8080/__admin/mappings/new");
	  

	  /* Part 2 – Using the configured Wiremock */
	  given().
	  when().
	  get("http://localhost:8080/bookstore/"+inBookStore).
	  then().
	  assertThat().
	  body("store.name", equalTo(inBookStore));
  }
 
  @Test
  // I have 3 fields in the excel chart - TestCaseName	- BookStore - Address
  public void testWireMockStatefulBehaviour() {  
	  
	  
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
	  
	  
  @DataProvider
  public Object[][] DP4WireMockJSONMessageContents() throws Exception{
 
		Object[][] testObjArray = ExcelFileReader.getTableArray("C:/Users/Rob/Userdata/DX Software Testing/Tools/set_of_test_"
				+ "data_for_automation.xlsx","BookStores");	
	    	return (testObjArray);
		}
  
}

/* Debug lines for Mocking Framework */

/*
System.out.println("Body" + responseToWireMockBuilder.getBody().asString());
System.out.println("status code" + responseToWireMockBuilder.getStatusCode());
System.out.println("status code" + responseToWireMockBuilder.getStatusCode());
Headers headers = responseToWireMockBuilder.getHeaders();
for(Header header: headers) {
	  System.out.println("Header Name" + header.getName() + "Header Value" + header.getValue());
}
*/



