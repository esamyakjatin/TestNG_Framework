package admin;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

public class LotDiamondBidController extends BaseTest {

	@Test
	public void test01LotDiamondNegotiation() {

		System.out.println("--------------------------------");
		System.out.println("test01LotDiamondNegotiation");
		System.out.println();

		// String name = "Jatin" + new
		// java.sql.Timestamp(System.currentTimeMillis()).getTime();
		// System.out.println("Name : " + name);

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);


		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		// Create the JSON body for the PUT request
		String jsonBody = "{" + "\"currentBidAmount\": 0," + "\"lotId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\","
				+ "\"userId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\","
				+ "\"auctionId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"," + "\"acceptStatus\": \"string\"" + "}";

		// Add the JSON body to the request
		request.body(jsonBody);

		Response response = request.put("/lot-diamond/negotiation");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK)
		if (statusCode == 200) {
			System.out.println("Request succeeded: User updated successfully.");
		} else if (statusCode == 400) {
			System.out.println("Bad Request: Invalid input.");
		} else if (statusCode == 403) {
			System.out.println("Forbidden: Access is denied.");
		} else if (statusCode == 404) {
			System.out.println("Not Found: The resource could not be found.");
		} else if (statusCode == 500) {
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
		}

		// Assert that the status code is 200 (OK)
		Assert.assertTrue(statusCode == 200, "Expected 200 OK, but got: " + statusCode);
	}

	@Test
	public void test02LotDiamondSearch() {

		System.out.println("--------------------------------");
		System.out.println("test02LotDiamondSearch");
		System.out.println();
		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		// Create the JSON body for the POST request
		String jsonBody = "{\n" + "  \"diamondName\": \"string\",\n" + "  \"stockNumber\": 0,\n"
				+ "  \"status\": \"string\",\n" + "  \"fancyColor\": \"string\",\n" + "  \"weight\": 0,\n"
				+ "  \"certNo\": \"string\",\n" + "  \"flourColor\": \"string\",\n" + "  \"measurement\": \"string\",\n"
				+ "  \"length\": 0,\n" + "  \"width\": 0,\n" + "  \"height\": 0,\n" + "  \"rap\": 0,\n"
				+ "  \"ppc\": 0,\n" + "  \"gettPrice\": 0,\n" + "  \"startPrice\": 0,\n" + "  \"reservePrice\": 0,\n"
				+ "  \"remarks\": \"string\",\n" + "  \"origin\": \"string\",\n" + "  \"shape\": \"string\",\n"
				+ "  \"color\": \"string\",\n" + "  \"clarity\": \"string\",\n" + "  \"lab\": \"string\",\n"
				+ "  \"cut\": \"string\",\n" + "  \"polish\": \"string\",\n" + "  \"sym\": \"string\",\n"
				+ "  \"flour\": \"string\",\n" + "  \"diamondId\": \"string\",\n" + "  \"shapeList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n" + "  \"colorList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n" + "  \"clarityList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n" + "  \"labList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n" + "  \"cutList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n" + "  \"polishList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n" + "  \"symList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n" + "  \"flourList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n" + "  \"lotIdList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n"
				+ "  \"user\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "  \"from\": 0,\n" + "  \"to\": 0,\n"
				+ "  \"fromRap\": 0,\n" + "  \"toRap\": 0,\n"
				+ "  \"lotId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "  \"endTime\": \"string\",\n"
				+ "  \"serialNo\": 0,\n" + "  \"videoLink\": \"string\",\n" + "  \"currentBidAmt\": 0,\n"
				+ "  \"minimumBid\": 0,\n" + "  \"auctionName\": \"string\",\n" + "  \"boxName\": \"string\",\n"
				+ "  \"supplierId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "  \"userName\": \"string\",\n"
				+ "  \"imageLink\": \"string\",\n" + "  \"sold\": true,\n" + "  \"active\": true,\n"
				+ "  \"fromStart\": 0,\n" + "  \"toStart\": 0,\n" + "  \"fromCurrent\": 0,\n" + "  \"toCurrent\": 0,\n"
				+ "  \"negotiationEndTime\": \"string\",\n" + "  \"supplierName\": \"string\",\n"
				+ "  \"auctionMode\": \"string\",\n" + "  \"isCorrection\": true,\n"
				+ "  \"stockNumberCorrection\": \"string\",\n" + "  \"measurementCorrection\": \"string\",\n"
				+ "  \"remarkCorrection\": \"string\",\n" + "  \"shapeCorrection\": \"string\",\n"
				+ "  \"colorCorrection\": \"string\",\n" + "  \"flourCorrection\": \"string\",\n"
				+ "  \"clarityCorrection\": \"string\",\n" + "  \"weightCorrection\": \"string\",\n"
				+ "  \"labCorrection\": \"string\",\n" + "  \"cutCorrection\": \"string\",\n"
				+ "  \"polishCorrection\": \"string\",\n" + "  \"symCorrection\": \"string\",\n"
				+ "  \"rapCorrection\": \"string\",\n" + "  \"minimumBidCorrection\": \"string\",\n"
				+ "  \"startPriceCorrection\": \"string\",\n" + "  \"reservePriceCorrection\": \"string\",\n"
				+ "  \"videoLinkCorrection\": \"string\",\n" + "  \"endDateTimeCorrection\": \"string\",\n"
				+ "  \"negotiationEndTimeCorrection\": \"string\",\n" + "  \"certNumber\": \"string\"\n" + "}";

		// Add the JSON body to the request
		request.body(jsonBody);

		// Define the auctionId path parameter
		// String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// request.pathParam("auctionId", auctionId);

		// Send the POST request
		Response response = request.post("/lot-diamond/search");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString()); // Added response logging
		System.out.println("---------------Response Details---------------");
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK) or 201 (Created)
		if (statusCode == 200) {
			System.out.println("Request succeeded: Data inserted successfully.");
		} else if (statusCode == 201) {
			System.out.println("Request succeeded: Symmetry created.");
		} else if (statusCode == 400) {
			System.out.println("Bad Request: Invalid input.");
		} else if (statusCode == 403) {
			System.out.println("Forbidden: Access is denied.");
		} else if (statusCode == 500) {
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
		}

		// Assert that the status code is 200 (OK) or 201 (Created)
		Assert.assertTrue(statusCode == 200 || statusCode == 201,
				"Expected 200 OK or 201 Created, but got: " + statusCode);
	}

	@Test
	public void test03LotDiamondSearchPage() {

		System.out.println("--------------------------------");
		System.out.println("test03LotDiamondSearchPage");
		System.out.println();
		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		// Create the JSON body for the POST request
		String jsonBody = "{\n" + "  \"diamondName\": \"string\",\n" + "  \"stockNumber\": 0,\n"
				+ "  \"status\": \"string\",\n" + "  \"fancyColor\": \"string\",\n" + "  \"weight\": 0,\n"
				+ "  \"certNo\": \"string\",\n" + "  \"flourColor\": \"string\",\n" + "  \"measurement\": \"string\",\n"
				+ "  \"length\": 0,\n" + "  \"width\": 0,\n" + "  \"height\": 0,\n" + "  \"rap\": 0,\n"
				+ "  \"ppc\": 0,\n" + "  \"gettPrice\": 0,\n" + "  \"startPrice\": 0,\n" + "  \"reservePrice\": 0,\n"
				+ "  \"remarks\": \"string\",\n" + "  \"origin\": \"string\",\n" + "  \"shape\": \"string\",\n"
				+ "  \"color\": \"string\",\n" + "  \"clarity\": \"string\",\n" + "  \"lab\": \"string\",\n"
				+ "  \"cut\": \"string\",\n" + "  \"polish\": \"string\",\n" + "  \"sym\": \"string\",\n"
				+ "  \"flour\": \"string\",\n" + "  \"diamondId\": \"string\",\n" + "  \"shapeList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n" + "  \"colorList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n" + "  \"clarityList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n" + "  \"labList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n" + "  \"cutList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n" + "  \"polishList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n" + "  \"symList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n" + "  \"flourList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n" + "  \"lotIdList\": [\n"
				+ "    \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" + "  ],\n"
				+ "  \"user\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "  \"from\": 0,\n" + "  \"to\": 0,\n"
				+ "  \"fromRap\": 0,\n" + "  \"toRap\": 0,\n"
				+ "  \"lotId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "  \"endTime\": \"string\",\n"
				+ "  \"serialNo\": 0,\n" + "  \"videoLink\": \"string\",\n" + "  \"currentBidAmt\": 0,\n"
				+ "  \"minimumBid\": 0,\n" + "  \"auctionName\": \"string\",\n" + "  \"boxName\": \"string\",\n"
				+ "  \"supplierId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "  \"userName\": \"string\",\n"
				+ "  \"imageLink\": \"string\",\n" + "  \"sold\": true,\n" + "  \"active\": true,\n"
				+ "  \"fromStart\": 0,\n" + "  \"toStart\": 0,\n" + "  \"fromCurrent\": 0,\n" + "  \"toCurrent\": 0,\n"
				+ "  \"negotiationEndTime\": \"string\",\n" + "  \"supplierName\": \"string\",\n"
				+ "  \"auctionMode\": \"string\",\n" + "  \"isCorrection\": true,\n"
				+ "  \"stockNumberCorrection\": \"string\",\n" + "  \"measurementCorrection\": \"string\",\n"
				+ "  \"remarkCorrection\": \"string\",\n" + "  \"shapeCorrection\": \"string\",\n"
				+ "  \"colorCorrection\": \"string\",\n" + "  \"flourCorrection\": \"string\",\n"
				+ "  \"clarityCorrection\": \"string\",\n" + "  \"weightCorrection\": \"string\",\n"
				+ "  \"labCorrection\": \"string\",\n" + "  \"cutCorrection\": \"string\",\n"
				+ "  \"polishCorrection\": \"string\",\n" + "  \"symCorrection\": \"string\",\n"
				+ "  \"rapCorrection\": \"string\",\n" + "  \"minimumBidCorrection\": \"string\",\n"
				+ "  \"startPriceCorrection\": \"string\",\n" + "  \"reservePriceCorrection\": \"string\",\n"
				+ "  \"videoLinkCorrection\": \"string\",\n" + "  \"endDateTimeCorrection\": \"string\",\n"
				+ "  \"negotiationEndTimeCorrection\": \"string\",\n" + "  \"certNumber\": \"string\"\n" + "}";

		// Add the JSON body to the request
		request.body(jsonBody);

		// Send the POST request
		Response response = request.post("/lot-diamond/searchWithePage");

		// Print the response status and body for debugging
		// System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString()); // Added response logging
		System.out.println("---------------Response Details---------------");
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK) or 201 (Created)
		if (statusCode == 200) {
			System.out.println("Request succeeded: Data inserted successfully.");
		} else if (statusCode == 201) {
			System.out.println("Request succeeded: Symmetry created.");
		} else if (statusCode == 400) {
			System.out.println("Bad Request: Invalid input.");
		} else if (statusCode == 403) {
			System.out.println("Forbidden: Access is denied.");
		} else if (statusCode == 500) {
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
		}

		// Assert that the status code is 200 (OK) or 201 (Created)
		Assert.assertTrue(statusCode == 200 || statusCode == 201,
				"Expected 200 OK or 201 Created, but got: " + statusCode);
	}

	@Test

	public void test04LotDiamondRemark() {
		// GET/api/v1/remarks
		System.out.println("--------------------------------");
		System.out.println("test04LotDiamondRemark");
		System.out.println();

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);


		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		String lotDiamondSearchInfo = "{\n" + "   \"from\":\"\",\n" + "   \"to\":\"\",\n" + "   \"fromRap\":\"\",\n"
				+ "   \"toRap\":\"\",\n" + "   \"fromStart\":\"\",\n" + "   \"toStart\":\"\",\n"
				+ "   \"fromCurrent\":\"\",\n" + "   \"toCurrent\":\"\",\n"
				+ "   \"userId\":\"ae44b00e-0075-4a5e-8911-fe2f5e3ffba2\",\n"
				+ "   \"auctionId\":\"bbc4d7aa-e722-4495-99d7-ec1774ad570f\",\n" + "   \"sortCondition\":\"sortBy\",\n"
				+ "   \"searchTerm\":\"\",\n" + "   \"showOnlyActiveLots\":false\n" + "}";

		// Send the 'lotDiamondSearchInfo' parameter in the query string
		request.queryParam("lotDiamondSearchInfo", lotDiamondSearchInfo);

		// Send the PUT request
		Response response = request.get("/remarks");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK)
		if (statusCode == 200) {
			System.out.println("Request succeeded: User updated successfully.");
		} else if (statusCode == 400) {
			System.out.println("Bad Request: Invalid input.");
		} else if (statusCode == 403) {
			System.out.println("Forbidden: Access is denied.");
		} else if (statusCode == 404) {
			System.out.println("Not Found: The resource could not be found.");
		} else if (statusCode == 500) {
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
		}

		// Assert that the status code is 200 (OK)
		Assert.assertTrue(statusCode == 200, "Expected 200 OK, but got: " + statusCode);
	}

	@Test
	public void test05LotDiamondLotId() {
		// GET/api/v1/lot-diamond/search/{lotId}/{userId}/{auctionId}
		// Set the base URI

		System.out.println("--------------------------------");
		System.out.println("test05LotDiamondLotId");
		System.out.println();

		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);


		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		String lotId = "ca731c4d-b682-464a-8592-fac7c4963b42";
		String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// Add path parameters dynamically
		request.pathParam("userId", userId);
		request.pathParam("auctionId", auctionId);
		request.pathParam("lotId", lotId);

		// Send the GET request with query parameters
		Response response = request.get("/lot-diamond/search/{lotId}/{userId}/{auctionId}");

		if (response.getStatusCode() == 401) {
			System.out.println("Token expired. Please generate a new token.");
			Assert.fail("Request failed due to token expiration.");
		}

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());
		System.out.println("---------------Response Details---------------");
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK) or 403 (Forbidden)
		if (statusCode == 403) {
			System.out.println("Access denied: Invalid credentials or permissions.");
		} else if (statusCode == 200) {
			System.out.println("Request succeeded: Access granted.");
		}

		// Assert that the status code is 200 (OK), or change the expectation if 403 is
		// valid
		Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	}

	@Test
	public void test06LotDiamondLiveBids() {

		System.out.println("--------------------------------");
		System.out.println("test06LotDiamondLiveBids");
		System.out.println();

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		String auctionId = "3fa85f64-5717-4562-b3fc-2c963f66afa6";
//  String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";

		// request.pathParam("userId", userId)
		request.pathParam("auctionId", auctionId);

		// Send the PUT request
		Response response = request.get("/live-bids/{auctionId}");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK)
		if (statusCode == 200) {
			System.out.println("Request succeeded: User updated successfully.");
		} else if (statusCode == 400) {
			System.out.println("Bad Request: Invalid input.");
		} else if (statusCode == 403) {
			System.out.println("Forbidden: Access is denied.");
		} else if (statusCode == 404) {
			System.out.println("Not Found: The resource could not be found.");
		} else if (statusCode == 500) {
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
		}

		// Assert that the status code is 200 (OK)
		Assert.assertTrue(statusCode == 200, "Expected 200 OK, but got: " + statusCode);
	}

	@Test
	public void test07LotDiamondFavourites() {
		// POST/api/v1/favourites

		System.out.println("--------------------------------");
		System.out.println("test07LotDiamondFavourites");
		System.out.println();

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
		String token = Constant.authToken;

		// Add Bearer token in Authorization header
		request.header("Authorization", "Bearer " + token);

		String lotDiamondSearchInfo = "{\n" + "   \"from\":\"\",\n" + "   \"to\":\"\",\n" + "   \"fromRap\":\"\",\n"
				+ "   \"toRap\":\"\",\n" + "   \"fromStart\":\"\",\n" + "   \"toStart\":\"\",\n"
				+ "   \"fromCurrent\":\"\",\n" + "   \"toCurrent\":\"\",\n"
				+ "   \"userId\":\"ae44b00e-0075-4a5e-8911-fe2f5e3ffba2\",\n"
				+ "   \"auctionId\":\"bbc4d7aa-e722-4495-99d7-ec1774ad570f\",\n" + "   \"sortCondition\":\"sortBy\",\n"
				+ "   \"searchTerm\":\"\",\n" + "   \"showOnlyActiveLots\":false\n" + "}";

		// Send the 'lotDiamondSearchInfo' parameter in the query string
		request.queryParam("lotDiamondSearchInfo", lotDiamondSearchInfo);

		// Send the POST request
		Response response = request.get("/favourites");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK)
		if (statusCode == 200) {
			System.out.println("Request succeeded: User updated successfully.");
		} else if (statusCode == 400) {
			System.out.println("Bad Request: Invalid input.");
		} else if (statusCode == 403) {
			System.out.println("Forbidden: Access is denied.");
		} else if (statusCode == 404) {
			System.out.println("Not Found: The resource could not be found.");
		} else if (statusCode == 500) {
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
		}

		// Assert that the status code is 200 (OK)
		Assert.assertTrue(statusCode == 200, "Expected 200 OK, but got: " + statusCode);
	}

	@Test
	public void test08LotDiamondGetWinBids() {
		// GET/api/v1/bid/getWinBids
		System.out.println("--------------------------------");
		System.out.println("test08LotDiamondGetWinBids");
		System.out.println();

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);


		String token = Constant.authToken;

		// Add Bearer token in Authorization header
		request.header("Authorization", "Bearer " + token);

		// Define the 'lotDiamondSearchInfo' as a JSON string
		String lotDiamondSearchInfo = "{\n" + "   \"from\":\"\",\n" + "   \"to\":\"\",\n" + "   \"fromRap\":\"\",\n"
				+ "   \"toRap\":\"\",\n" + "   \"fromStart\":\"\",\n" + "   \"toStart\":\"\",\n"
				+ "   \"fromCurrent\":\"\",\n" + "   \"toCurrent\":\"\",\n"
				+ "   \"userId\":\"b00af367-dc85-4201-bf71-3d0c2db98187\",\n"
				+ "   \"auctionId\":\"bbc4d7aa-e722-4495-99d7-ec1774ad570f\",\n" + "   \"sortCondition\":\"sortBy\",\n"
				+ "   \"searchTerm\":\"\",\n" + "   \"showOnlyActiveLots\":false\n" + "}";

		// Send the 'lotDiamondSearchInfo' parameter in the query string
		request.queryParam("lotDiamondSearchInfo", lotDiamondSearchInfo);

		// Send the GET request
		Response response = request.get("/bid/getWinBids");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK)
		if (statusCode == 200) {
			System.out.println("Request succeeded: User updated successfully.");
		} else if (statusCode == 400) {
			System.out.println("Bad Request: Invalid input.");
		} else if (statusCode == 403) {
			System.out.println("Forbidden: Access is denied.");
		} else if (statusCode == 404) {
			System.out.println("Not Found: The resource could not be found.");
		} else if (statusCode == 500) {
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
		}

		// Assert that the status code is 200 (OK)
		Assert.assertTrue(statusCode == 200, "Expected 200 OK, but got: " + statusCode);
	}

	@Test
	public void test09LotDiamondGetMyStockData() {

		System.out.println("--------------------------------");
		System.out.println("test09LotDiamondGetMyStockData");
		System.out.println();
		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// Add path parameters dynamically
		request.pathParam("userId", userId);
		request.pathParam("auctionId", auctionId);

		// Send the GET request with query parameters
		Response response = request.get("/bid/getMyStockData/{userId}/{auctionId}");

		if (response.getStatusCode() == 401) {
			System.out.println("Token expired. Please generate a new token.");
			Assert.fail("Request failed due to token expiration.");
		}

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());
		System.out.println("---------------Response Details---------------");
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK) or 403 (Forbidden)
		if (statusCode == 403) {
			System.out.println("Access denied: Invalid credentials or permissions.");
		} else if (statusCode == 200) {
			System.out.println("Request succeeded: Access granted.");
		}

		// Assert that the status code is 200 (OK), or change the expectation if 403 is
		// valid
		Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	}

	@Test
	public void test10GetMyBidStatusData() {

		System.out.println("--------------------------------");
		System.out.println("test10GetMyBidStatusData");
		System.out.println();
		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();
		
		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// Add path parameters dynamically
		request.pathParam("userId", userId);
		request.pathParam("auctionId", auctionId);

		// Send the GET request with query parameters
		Response response = request.get("/bid/getMyBidstatusData/{userId}/{auctionId}");

		if (response.getStatusCode() == 401) {
			System.out.println("Token expired. Please generate a new token.");
			Assert.fail("Request failed due to token expiration.");
		}

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());
		System.out.println("---------------Response Details---------------");
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK) or 403 (Forbidden)
		if (statusCode == 403) {
			System.out.println("Access denied: Invalid credentials or permissions.");
		} else if (statusCode == 200) {
			System.out.println("Request succeeded: Access granted.");
		}

		// Assert that the status code is 200 (OK), or change the expectation if 403 is
		// valid
		Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	}

	@Test

	public void test11GetMyBids() {
		// GET/api/v1/bid/getMyBids

		System.out.println("--------------------------------");
		System.out.println("test11GetMyBids");
		System.out.println();

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		String lotDiamondSearchInfo = "{\n" + "   \"from\":\"\",\n" + "   \"to\":\"\",\n" + "   \"fromRap\":\"\",\n"
				+ "   \"toRap\":\"\",\n" + "   \"fromStart\":\"\",\n" + "   \"toStart\":\"\",\n"
				+ "   \"fromCurrent\":\"\",\n" + "   \"toCurrent\":\"\",\n"
				+ "   \"userId\":\"b00af367-dc85-4201-bf71-3d0c2db98187\",\n"
				+ "   \"auctionId\":\"bbc4d7aa-e722-4495-99d7-ec1774ad570f\",\n" + "   \"sortCondition\":\"sortBy\",\n"
				+ "   \"searchTerm\":\"\",\n" + "   \"showOnlyActiveLots\":false\n" + "}";

		// Send the 'lotDiamondSearchInfo' parameter in the query string
		request.queryParam("lotDiamondSearchInfo", lotDiamondSearchInfo);

		// Send the PUT request
		Response response = request.get("/bid/getMyBids");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK)
		if (statusCode == 200) {
			System.out.println("Request succeeded: User updated successfully.");
		} else if (statusCode == 400) {
			System.out.println("Bad Request: Invalid input.");
		} else if (statusCode == 403) {
			System.out.println("Forbidden: Access is denied.");
		} else if (statusCode == 404) {
			System.out.println("Not Found: The resource could not be found.");
		} else if (statusCode == 500) {
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
		}

		// Assert that the status code is 200 (OK)
		Assert.assertTrue(statusCode == 200, "Expected 200 OK, but got: " + statusCode);
	}

	@Test

	public void test12GetLoseBids() {
		// GET/api/v1/bid/getLoseBids

		System.out.println("--------------------------------");
		System.out.println("test12GetLoseBids");
		System.out.println();

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		request.header("Authorization", "Bearer " + token);

		String lotDiamondSearchInfo = "{\n" + "   \"from\":\"\",\n" + "   \"to\":\"\",\n" + "   \"fromRap\":\"\",\n"
				+ "   \"toRap\":\"\",\n" + "   \"fromStart\":\"\",\n" + "   \"toStart\":\"\",\n"
				+ "   \"fromCurrent\":\"\",\n" + "   \"toCurrent\":\"\",\n"
				+ "   \"userId\":\"b00af367-dc85-4201-bf71-3d0c2db98187\",\n"
				+ "   \"auctionId\":\"bbc4d7aa-e722-4495-99d7-ec1774ad570f\",\n" + "   \"sortCondition\":\"sortBy\",\n"
				+ "   \"searchTerm\":\"\",\n" + "   \"showOnlyActiveLots\":false\n" + "}";

		// Send the 'lotDiamondSearchInfo' parameter in the query string
		request.queryParam("lotDiamondSearchInfo", lotDiamondSearchInfo);

		Response response = request.get("/bid/getLoseBids");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK)
		if (statusCode == 200) {
			System.out.println("Request succeeded: User updated successfully.");
		} else if (statusCode == 400) {
			System.out.println("Bad Request: Invalid input.");
		} else if (statusCode == 403) {
			System.out.println("Forbidden: Access is denied.");
		} else if (statusCode == 404) {
			System.out.println("Not Found: The resource could not be found.");
		} else if (statusCode == 500) {
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
		}

		// Assert that the status code is 200 (OK)
		Assert.assertTrue(statusCode == 200, "Expected 200 OK, but got: " + statusCode);
	}

	@Test(enabled = false)
	void test13GetAllWonBids() {
		System.out.println("--------------------------------");
		System.out.println("test13GetAllWonBids");
		System.out.println();

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		String token = Constant.authToken;

		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		// String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";

		// Add path parameters dynamically
		// request.pathParam("userId", userId);
		request.pathParam("auctionId", auctionId);

		// Send the GET request with query parameters
		Response response = request.get("/bid/getAllWonLots/{auctionId");

		if (response.getStatusCode() == 401) {
			System.out.println("Token expired. Please generate a new token.");
			Assert.fail("Request failed due to token expiration.");
		}

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());
		System.out.println("---------------Response Details---------------");
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK) or 403 (Forbidden)
		if (statusCode == 403) {
			System.out.println("Access denied: Invalid credentials or permissions.");
		} else if (statusCode == 200) {
			System.out.println("Request succeeded: Access granted.");
		}

		// Assert that the status code is 200 (OK), or change the expectation if 403 is
		// valid
		Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	}

}