package goRest;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;

public class GETRequestData {
	
	@Test
	public void getDataList () {
		RestAssured
		.given()
			.contentType("application/json")
			.header("Authorization","Bearer 502380835327a18c52c3ae4141c65654e819061f5c450e709e9f3d2b15222721")
			.when()
			.get("https://gorest.co.in/public/v2/users/5607")
			.then()
				.statusCode(200)
				.log().all()
				.and()
				.body("email", is("shilpakshir123@gmail.com"));
	}


}
