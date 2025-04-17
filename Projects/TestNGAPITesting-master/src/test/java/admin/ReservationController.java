package admin;


import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

public class ReservationController extends BaseTest {

		@Test
		public void Test01PostSlotTransaction() {

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
			//String uniqueName = "Japan Auction House" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			String jsonBody = "{\n" +
	                "  \"auctionId\": \"056c2b5e-39fc-4ffd-b018-c3ed87d2fabf\",\n" +
	                "  \"auctionName\": \"Diamond202520250129092452\",\n" +
	                "  \"maxCompaniesPerSlot\": 5,\n" +
	                "  \"maxPeoplePerCompany\": 2,\n" +
	                "  \"slotDetails\": [\n" +
	                "    {\n" +
	                "      \"slotStartTime\": \"2:18 PM\",\n" +
	                "      \"slotEndTime\": \"4:18 PM\"\n" +
	                "    }\n" +
	                "  ],\n" +
	                "  \"slots\":3,\n" +
	                "  \"viewingDates\": [\n" +
	                "    \"2025-05-10 00:00:00\",\n" +
	                "    \"2025-01-11 00:00:00\"\n" +
	                "  ],\n" +
	                "  \"viewingLastDate\": \"2025-01-11 00:00:00\"\n" +
	                "}";



			// Add the JSON body to the request
			request.body(jsonBody);

			// Send the POST request
			Response response = request.post("/slot-transaction");

			// Print the response status and body for debugging
			System.out.println("The status received: " + response.statusLine());
			System.out.println("Response: " + response.getBody().asString());

			int statusCode = response.getStatusCode();
			System.out.println("Status Code: " + statusCode);

			// Check if the status code is 200 (OK) or 201 (Created)
			switch (statusCode) {
			case 200:
				System.out.println("Request succeeded: Slot details updated successfully.");
				break;
			case 201:
				System.out.println("Request succeeded: Slot details created.");
				break;
			case 400:
				System.out.println("Bad Request: Invalid input.");
				break;
			case 403:
				System.out.println("Forbidden: Access is denied.");
				break;
			case 500:
				System.out.println("Internal Server Error: The server encountered an unexpected condition.");
				break;
			default:
				System.out.println("Unexpected status code: " + statusCode);
			}

			// Assert that the status code is 200 (OK) or 201 (Created)
			Assert.assertTrue(statusCode == 200 || statusCode == 201,
					"Expected 200 OK or 201 Created, but got: " + statusCode);
		}
	

	@Test
	public void Test02GetSlots() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
		
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Specify the auctionId as a path parameter
		String auctionId = "21d71c14-a9a4-48f2-bdac-0008fa0d9091";
		request.pathParam("auctionId", auctionId);

		// Send the GET request
		Response response = request.get("/slots/{auctionId}");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK)
		switch (statusCode) {
		case 200:
			System.out.println("Request succeeded: Slot details retrieved successfully.");
			break;
		case 403:
			System.out.println("Forbidden: Access is denied.");
			break;
		case 404:
			System.out.println("Not Found: The specified resource was not found.");
			break;
		case 500:
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
			break;
		default:
			System.out.println("Unexpected status code: " + statusCode);
		}

		// Assert that the status code is 200 (OK)
		Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	}
	
	@Test
	public void Test03PostUpdateSlotTransaction() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
		
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Specify the auctionId as a path parameter
		//String auctionId = "056c2b5e-39fc-4ffd-b018-c3ed87d2fabf";
		//request.pathParam("auctionId", auctionId);

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		// Create the JSON body for the POST request
		//String uniqueName = "Japan Auction House" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		String jsonBody = "{\n" +
			    "  \"auctionId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
			    "  \"maxCompaniesPerSlot\": 0,\n" +
			    "  \"maxPeoplePerCompany\": 0,\n" +
			    "  \"slotDetails\": [\n" +
			    "    {\n" +
			    "      \"slotStartTime\": \"5:15 pm\",\n" +
			    "      \"slotEndTime\": \"string\",\n" +
			    "      \"slotTransactionId\": \"31d902da-500b-4a47-be48-280346b87a0f\"\n" +
			    "    }\n" +
			    "  ],\n" +
			    "  \"viewingDates\": [\n" +
			    "    \"string\"\n" +
			    "  ],\n" +
			    "  \"viewingLastDate\": \"string\",\n" +
			    "  \"slots\": 0,\n" +
			    "  \"slotDate\": \"string\",\n" +
			    "  \"slotStartTime\": \"string\",\n" +
			    "  \"slotEndTime\": \"string\",\n" +
			    "  \"active\": true,\n" +
			    "  \"companyOccupancy\": 0,\n" +
			    "  \"companyOccupied\": 0,\n" +
			    "  \"peopleOccupancy\": 0,\n" +
			    "  \"peopleOccupied\": 0,\n" +
			    "  \"slotTransactionId\": \"31d902da-500b-4a47-be48-280346b87a0f\",\n" +
			    "  \"masterReservationId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" +
			    "}"; 




		// Add the JSON body to the request
		request.body(jsonBody);
		
		// Send the GET request
		Response response = request.put("/update/slot-transaction");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK)
		switch (statusCode) {
		case 200:
			System.out.println("Request succeeded: Slot details retrieved successfully.");
			break;
		case 201:
			System.out.println("Request succeeded: Slot Transaction updated successfully.");
			break;
		case 403:
			System.out.println("Forbidden: Access is denied.");
			break;
		case 404:
			System.out.println("Not Found: The specified resource was not found.");
			break;
		case 500:
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
			break;
		default:
			System.out.println("Unexpected status code: " + statusCode);
		}

		// Assert that the status code is 200 (OK)
		Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	}
	
	
	@Test
	
	public void Test04GetSlotTransactionsSlotDate() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
		
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Specify the auctionId as a path parameter
		String auctionId = "056c2b5e-39fc-4ffd-b018-c3ed87d2fabf";
		String slotDate="2025-01-10 00:00:00.000000";
		request.pathParam("auctionId", auctionId);
		request.pathParam("slotDate", slotDate);
		
		// Send the GET request
		Response response = request.get("/slot/transactions/{auctionId}/{slotDate}");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK)
		switch (statusCode) {
		case 200:
			System.out.println("Request succeeded: Slot details retrieved successfully.");
			break;
		case 403:
			System.out.println("Forbidden: Access is denied.");
			break;
		case 404:
			System.out.println("Not Found: The specified resource was not found.");
			break;
		case 500:
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
			break;
		default:
			System.out.println("Unexpected status code: " + statusCode);
		}

		// Assert that the status code is 200 (OK)
		Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	}
	
	@Test
	public void Test05GetMasterReservation() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);
		
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Specify the auctionId as a path parameter
		String auctionId = "056c2b5e-39fc-4ffd-b018-c3ed87d2fabf";
		request.pathParam("auctionId", auctionId);

		// Send the GET request
		Response response = request.get("/master/reservation/{auctionId}");

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString());

		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);

		// Check if the status code is 200 (OK)
		switch (statusCode) {
		case 200:
			System.out.println("Request succeeded: Slot details retrieved successfully.");
			break;
		case 403:
			System.out.println("Forbidden: Access is denied.");
			break;
		case 404:
			System.out.println("Not Found: The specified resource was not found.");
			break;
		case 500:
			System.out.println("Internal Server Error: The server encountered an unexpected condition.");
			break;
		default:
			System.out.println("Unexpected status code: " + statusCode);
		}

		// Assert that the status code is 200 (OK)
		Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
	}
	
	
	
}
