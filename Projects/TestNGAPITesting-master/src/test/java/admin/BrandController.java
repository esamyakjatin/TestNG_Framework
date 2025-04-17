package admin;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

public class BrandController extends BaseTest {
	@Test
	public void test01PutBrand() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		// Create the JSON body for the PUT request

		String jsonBody = "{\n" + "  \"desc\": \"string\",\n" + "  \"code\": \"string\",\n" + "  \"srNo\": 0,\n"
				+ "  \"brandId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "  \"name\": \"string\",\n"
				+ "  \"count\": 0,\n" + "  \"createdDate\": \"2024-12-13T09:21:05.197Z\"\n" + "}";

		// Add the JSON body to the request
		request.body(jsonBody);

		// String auctionId = "3fa85f64-5717-4562-b3fc-2c963f66afa6";
		// String userId = "7828500F-5781-40D5-9E61-ADF2A09EB993";
		String brandId = "cd26212c-b131-11ef-a57f-c8d3ffbc6ac6";

		// request.pathParam("userId", userId);
		// request.pathParam("auctionId", auctionId);
		request.pathParam("brandId", brandId);

		// Send the PUT request
		Response response = request.put("brand/{brandId}");

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
	public void test02GetBrand() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		// Send the GET request with query parameters
		Response response = request.get("/brand");

		if (response.getStatusCode() == 401) {
			System.out.println("Token expired. Please generate a new token.");
			Assert.fail("Request failed due to token expiration.");
		}

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString()); // Added response logging
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
	public void test03PostBrand() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");
		
		String uniqueName = "harshit" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		// Create the JSON body for the POST request
		String jsonBody = "{\n" + "  \"name\": \""+uniqueName+"\",\n" + "  \"desc\": \"dipanshu\",\n"
				+ "  \"code\": \"123\",\n" + "\"count\": 0,\n" + "  \"srNo\": 0,\n"
				+ "  \"brandId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" + "  \"createdDate\": \"10/12/2024\"\n"
				+ "}";

		// Add the JSON body to the request
		/*
		 * String jsonBody = "{\n" + "  \"name\": \"dipanshu\",\n" +
		 * "  \"desc\": \"dipanshu\",\n" + "  \"code\": \"123\",\n" + "  \"srNo\": 0,\n"
		 * + "  \"jewelleryMaterialId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\"\n" +
		 * "  \"createdDate\": \"10/12/2024\",\n" +// Removed the trailing comma here
		 * "}";
		 */
		request.body(jsonBody);

		// Send the POST request
		Response response = request.post("/brand");

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

	@Test(enabled=false)
	public void test04PostBrandExcel() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		
		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header


		// Set the Content-Type header to application/json
		request.header("Content-Type", "application/json");

		// Set the Content-Type header to multipart/form-data
		request.header("Content-Type", "multipart/form-data");

		// Create the multipart body for the POST request
		File file = new File("C:\\Users\\ESAMYAK121\\Desktop\\Testing\\.xlsx");

		// Add the file to the request
		request.multiPart("file", file);
		

		// Create the JSON body for the POST request
		String jsonBody = "{\n" + "  \"file\": \"polish_Masters_Excel.xlsx\",\n" +

				"}";
		

		request.body(jsonBody);

		// Send the POST request
		Response response = request.post("/brand/excel");

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
	public void test05GetBrands() {

		// Set the base URI
		RestAssured.baseURI = "http://localhost:8080/api/v1";
		RequestSpecification request = RestAssured.given();

		String encryptedCredentials = Constant.adminCredentials; 

		request.body(encryptedCredentials);

		// Add Authorization header for Bearer Token Authentication
		String token = Constant.authToken;
		request.header("Authorization", "Bearer " + token); // Add Bearer token in Authorization header

		// Optional: Set headers if required
		request.header("Content-Type", "application/json");

		// Send the GET request with query parameters
		Response response = request.get("/brands");

		if (response.getStatusCode() == 401) {
			System.out.println("Token expired. Please generate a new token.");
			Assert.fail("Request failed due to token expiration.");
		}

		// Print the response status and body for debugging
		System.out.println("The status received: " + response.statusLine());
		System.out.println("Response: " + response.getBody().asString()); // Added response logging
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