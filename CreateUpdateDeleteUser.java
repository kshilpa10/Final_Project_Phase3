package goRest;

import static org.hamcrest.Matchers.is;
import java.util.HashMap;
import java.util.UUID;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUpdateDeleteUser extends BaseTest2 {
	
	public static HashMap<String, String> map= new HashMap<String, String>();
	UUID uuid=UUID.randomUUID();
	String id;
	
	@Test(priority=0)
	public void postData() {
		map.put("name", "Shilpa");
		logger.info("Added name");
		map.put("email", "shilpa"+uuid+"@gmail.com");
		logger.info("Added email");
		map.put("gender", "female");
		logger.info("Added gender");
		map.put("status", "active");
		logger.info("Added status");
		RestAssured.baseURI="https://gorest.co.in/";
		RestAssured.basePath="/public/v2/users";
		logger.info("Payload created for creating the resource");
	}
	
	@Test(priority=1)
	
	public void createResource() {
		
		Response response=RestAssured
			.given()
			.contentType("application/json")
			.body(map)
			.header("Authorization","Bearer 502380835327a18c52c3ae4141c65654e819061f5c450e709e9f3d2b15222721")
			.when()
				.post()
			.then()
				.log().all()
				.contentType(ContentType.JSON).extract().response();
		logger.info("Resource created and Response Captured");
		
			JsonPath jsonPath= response.jsonPath();
			logger.info("JSON path created");
			//System.out.println("Response="+jsonPath.get("id"));
			id=jsonPath.get("id").toString();
		logger.info("Resource Captured");
	}
	
	@Test(priority=2)
	
		public void putData() {
			map.put("name", "Shilpa Kshirsagar");
			map.put("email", "shilpa12345789@gmail.com");
			map.put("gender", "female");
			map.put("status", "active");
			RestAssured.baseURI="https://gorest.co.in/";
			RestAssured.basePath="/public/v2/users/"+id;
			logger.info("Payload created for updating the resource");
			
		
				
				RestAssured
					.given()
					.contentType("application/json")
					.body(map)
					.header("Authorization","Bearer 502380835327a18c52c3ae4141c65654e819061f5c450e709e9f3d2b15222721")
					.when()
						.put()
					.then()
						.statusCode(200)
						.log().all()
							.and()
				.body("name", is("Shilpa Kshirsagar"));
				logger.info("Resource Updated");		
		}
	
	
		@Test(priority=3)
		
			public void deleteResource() {
				RestAssured.baseURI="https://gorest.co.in/";
				RestAssured.basePath="public/v2/users/"+id;
				
				RestAssured
					.given()
					.contentType("application/json")
					.header("Authorization","Bearer 502380835327a18c52c3ae4141c65654e819061f5c450e709e9f3d2b15222721")
						.when()
							.delete()
						.then()
						.statusCode(204);
				logger.info("Resource Deleted");
			}
		
}
