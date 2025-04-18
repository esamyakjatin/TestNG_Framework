package admin;
import org.testng.annotations.Test; // ✅ Correct TestNG import

import java.io.File;

import org.json.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

public class GemStoneController extends BaseTest{

	@Test
	public void testUpdateGemStone() {
	    // Base URI
	    RestAssured.baseURI = "http://localhost:8080/api/v1";

	    // Path variable
	    String gemStoneId = "391609da-1505-11f0-a67a-a08cfdf176e2"; // Replace with valid UUID

	    // Request body
	    JSONObject requestBody = new JSONObject();
	    requestBody.put("name", "Updated GemStone");
	    requestBody.put("desc", "Updated Description");
	    requestBody.put("code", "GEM123");
	    requestBody.put("srNo", 2);
	    requestBody.put("count", 10);
	    requestBody.put("gemstoneId", gemStoneId); // Should match the path variable
	    requestBody.put("createdDate", "2024-01-01T00:00:00"); // Format as per backend requirement

	    // Send PUT request
	    Response response = RestAssured
	        .given()
	        .header("Authorization", "Bearer " + Constant.authToken)
	        .header("Content-Type", "application/json")
	        .body(requestBody.toString())
	        .put("/gem-stone/" + gemStoneId);

	    // Debug
	    System.out.println("Status Code: " + response.getStatusCode());
	    System.out.println("Response Body:\n" + response.getBody().asPrettyString());

	    // Assertions
	    Assert.assertEquals(response.getStatusCode(), 200, "Expected HTTP 200 OK");
	    Assert.assertTrue(response.getBody().asString().contains("Updated"), "Response should reflect updated data");
	}
	
	@Test
	public void testCreateGemStone() {
	    // Base URI
	    RestAssured.baseURI = "http://localhost:8080/api/v1";

	    // Request body using the exact structure from the provided JSON
	    JSONObject requestBody = new JSONObject();
	    String uniqueName = "Gem_" + System.currentTimeMillis();
	    requestBody.put("name", uniqueName);
	    requestBody.put("desc", "New Gem 4");
	    requestBody.put("code", "New Gem 4");
	    requestBody.put("srNo", 40);
	    requestBody.put("count", 40);
	    requestBody.put("gemstoneId", "3fa85f64-5717-4562-b3fc-2c963f66afa6");
	    requestBody.put("createdDate", "2024-01-01T00:00:00");

	    // Send POST request
	    Response response = RestAssured
	        .given()
	        .header("Authorization", "Bearer " + Constant.authToken) // Add token if required
	        .header("Content-Type", "application/json")
	        .body(requestBody.toString())
	        .post("/gem-stone");

	    // Debug output
	    System.out.println("Status Code: " + response.statusCode());
	    System.out.println("Response Body:\n" + response.getBody().asPrettyString());

	    // Assertions
	    Assert.assertEquals(response.getStatusCode(), 201, "Expected HTTP 201 Created");
	    Assert.assertTrue(response.getBody().asString().contains("Gem Stone Created"),
	        "Response should contain 'Gem Stone Created'");
	}
	
	@Test
	public void testUploadGemstoneExcel() {
	    // Base URI
	    RestAssured.baseURI = "http://localhost:8080/api/v1";

	    // Path to Excel file (adjust if needed)
	    File excelFile = new File("C:\\TestNG_Framework\\Projects\\TestNGAPITesting-master\\src\\test\\resources/GemStone_Excel.xlsx");

	    // Send POST request with multipart form-data
	    Response response = RestAssured
	        .given()
	        .header("Authorization", "Bearer " + Constant.authToken)
	        .multiPart("file", excelFile)
	        .post("/gem-stone/excel");

	    // Debug output
	    System.out.println("Status Code: " + response.statusCode());
	    System.out.println("Response Body:\n" + response.getBody().asPrettyString());

	    // Assertions
	    Assert.assertEquals(response.getStatusCode(), 200, "Expected 200 OK");
	    Assert.assertTrue(response.getBody().asString().toLowerCase().contains("success"), "Upload should be successful");
	}
}
