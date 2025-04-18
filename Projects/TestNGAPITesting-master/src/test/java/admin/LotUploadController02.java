package admin;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseTest;
import utils.Constant;

public class LotUploadController02 extends BaseTest {
	
	
	
	@Test
	public void Test01LotDiamondExcelUpload() {
	    RestAssured.baseURI = "http://localhost:8080/api/v1";
	    RequestSpecification request = RestAssured.given();

	    // Set token
	    String token = Constant.authToken;
	    request.header("Authorization", "Bearer " + token);

	    // Set content type for file upload
	    request.header("Content-Type", "multipart/form-data");

	    // File setup
	    File file = null;
	    try {
	        String filePath = Constant.FILE_PATH + "/src/test/resources/Diamond_Excel_Upload.xlsx";
	        System.out.println("filePath: " + filePath);
	        file = new File(filePath);
	        System.out.println("File exists: " + file.exists());
	        System.out.println("Is file empty: " + (file.length() == 0));
	    } catch (Exception e) {
	        System.out.println("Exception due to " + e.toString());
	    }

	    request.multiPart("file", file); // Ensure field name is exactly "file"

	    // Set path parameters
	    String userId = "7b6e4bdc-c310-4c0a-a587-29839da36de0";   
	    String auctionId = "d7a28125-9ba3-4ee5-a737-b634b9c7cbc5"; 
	    request.pathParam("userId", userId);
	    request.pathParam("auctionId", auctionId);

	    // Send the POST request
	    Response response = request.post("/lot-diamond/uploadLotDiamond/{userId}/{auctionId}");

	    // Log and assert
	    System.out.println("The status received: " + response.statusLine());
	    System.out.println("Response: " + response.getBody().asString());
	    System.out.println("---------------Response Details---------------");
	    int statusCode = response.getStatusCode();
	    System.out.println("Status Code: " + statusCode);

	    Assert.assertTrue(statusCode == 200 || statusCode == 201,
	            "Expected 200 OK or 201 Created, but got: " + statusCode);
	}


	
	@Test
	public void Test02LotDiamondExcelUpdate() {
	    RestAssured.baseURI = "http://localhost:8080/api/v1";
	    RequestSpecification request = RestAssured.given();

	    String encryptedCredentials = Constant.adminCredentials; 
	    request.body(encryptedCredentials);

	    String token = Constant.authToken;
	    request.header("Authorization", "Bearer " + token);
	    request.header("Content-Type", "multipart/form-data");

	    // File setup
	    File file = null;
	    try {
	        String filePath = Constant.FILE_PATH + "/src/test/resources/shape_Masters_Excel.xlsx";
	        System.out.println("filePath: " + filePath);
	        file = new File(filePath);
	    } catch (Exception e) {
	        System.out.println("Exception due to " + e.toString());
	    }

	    request.multiPart("file", file);

	    // Set path parameters
	    String userId = "";    
	    String auctionId = ""; 
	    request.pathParam("userId", userId);
	    request.pathParam("auctionId", auctionId);

	    // Send the PUT request with path parameters
	    Response response = request.put("/lot-diamond/excel-update/{userId}/{auctionId}");

	    // Log and assert
	    System.out.println("The status received: " + response.statusLine());
	    System.out.println("Response: " + response.getBody().asString());
	    System.out.println("---------------Response Details---------------");
	    int statusCode = response.getStatusCode();
	    System.out.println("Status Code: " + statusCode);

	    Assert.assertTrue(statusCode == 200 || statusCode == 201,
	            "Expected 200 OK or 201 Created, but got: " + statusCode);
	}
	
	@Test
	public void Test03LotDiamondExcelUpdateAdmin() {
	    RestAssured.baseURI = "http://localhost:8080/api/v1";
	    RequestSpecification request = RestAssured.given();

	    String encryptedCredentials = Constant.adminCredentials; 
	    request.body(encryptedCredentials);

	    String token = Constant.authToken;
	    request.header("Authorization", "Bearer " + token);
	    request.header("Content-Type", "multipart/form-data");

	    // File setup
	    File file = null;
	    try {
	        String filePath = Constant.FILE_PATH + "/src/test/resources/shape_Masters_Excel.xlsx";
	        System.out.println("filePath: " + filePath);
	        file = new File(filePath);
	    } catch (Exception e) {
	        System.out.println("Exception due to " + e.toString());
	    }

	    request.multiPart("file", file);

	    // Set path parameters
	    String userId = "";    
	    String auctionId = ""; 
	    request.pathParam("userId", userId);
	    request.pathParam("auctionId", auctionId);

	    // Send the PUT request with path parameters
	    Response response = request.put("lot-diamond/excel-update/admin/{userId}/{auctionId}");

	    // Log and assert
	    System.out.println("The status received: " + response.statusLine());
	    System.out.println("Response: " + response.getBody().asString());
	    System.out.println("---------------Response Details---------------");
	    int statusCode = response.getStatusCode();
	    System.out.println("Status Code: " + statusCode);

	    Assert.assertTrue(statusCode == 200 || statusCode == 201,
	            "Expected 200 OK or 201 Created, but got: " + statusCode);
	}




}