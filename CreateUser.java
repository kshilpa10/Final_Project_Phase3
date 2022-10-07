package goRest;

import java.util.HashMap;
import java.util.UUID;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class CreateUser {
	
	public static HashMap<String, String> map= new HashMap<String, String>();
	UUID uuid=UUID.randomUUID();
	
	@BeforeMethod
	public void postData() {
		map.put("name", "Shilpa Kshirsagar");
		map.put("email", "shilpa"+uuid+"@gmail.com");
		map.put("gender", "female");
		map.put("status", "active");
		RestAssured.baseURI="https://gorest.co.in/";
		RestAssured.basePath="/public/v2/users";
	}
	
	@Test
	public void createResource() {
		
		RestAssured
			.given()
			.contentType("application/json")
			.body(map)
			.header("Authorization","Bearer 502380835327a18c52c3ae4141c65654e819061f5c450e709e9f3d2b15222721")
			.when()
				.post()
			.then()
				.statusCode(201)
				.log().all();
	}


}
