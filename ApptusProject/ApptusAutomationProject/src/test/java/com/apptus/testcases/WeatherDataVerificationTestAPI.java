package com.apptus.testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.apptus.base.TestBase;
import io.restassured.http.ContentType;

public class WeatherDataVerificationTestAPI extends TestBase {

	private final String URL = "https://api.openweathermap.org/data/3.0/stations";
	private final String API_KEY = "6115952cca9b4abce243139257527a21";
	private final String URLWithAPIKey = URL + "?appid=" + API_KEY;
	private final String INVALID_POST_CALL_MESSAGE="Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
	private final String INVALID_DELETE_STATION_MESSAGE="Station not found";
	private String id1, id2;
	private JSONObject request0 ,request1, request2;

	@SuppressWarnings("unchecked")
	@Test(priority = 1, invocationCount = 1, groups= "APITest")
	void test_Post_Invalid() {

		request0 = new JSONObject();
		request0.put("external_id", "DEMO_TEST001");
		request0.put("name", "InterviewStation001");
		request0.put("latitude", 33.33);
		request0.put("longitude", -111.43);
		request0.put("altitude", 444);

		given()
				.headers("Content-Type", "application/json")
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(request0.toJSONString()).
	    when()
	            .post(URL).
	    then()
	    		.assertThat()
				.statusCode(401)
				.body("message", equalTo(INVALID_POST_CALL_MESSAGE))
				.log().all();

	}

	@SuppressWarnings("unchecked")
	@Test(priority = 2, invocationCount = 1, groups= "APITest")
	void test_Post_Valid() {

		request1 = new JSONObject();
		request1.put("external_id", "DEMO_TEST001");
		request1.put("name", "InterviewStation001");
		request1.put("latitude",33.33);
		request1.put("longitude",-111.43);
		request1.put("altitude",444);

		given()
				.headers("Content-Type", "application/json")
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(request1.toJSONString()).
		when()
				.post(URLWithAPIKey).
		then()
				.assertThat()
				.statusCode(201)
				.log().all();

		request2 = new JSONObject();
		request2.put("external_id", "Interview1");
		request2.put("name", "InterviewStation002");
		request2.put("latitude",33.44);
		request2.put("longitude",-12.44);
		request2.put("altitude",444);

		given()
				.headers("Content-Type", "application/json")
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(request2.toJSONString()).
		when()
				.post(URLWithAPIKey).
	    then()
				.statusCode(201)
				.log().all();

	}

	@Test(priority = 3, invocationCount = 1, groups= "APITest")
	void test_Get_Valid() {

		given()
			    .get(URLWithAPIKey).
		then()
				.assertThat()
				.statusCode(200)
				.body("external_id[0]", equalTo("DEMO_TEST001"))
				.body("external_id[1]", equalTo("Interview1"))
				.body("name[0]", equalTo("InterviewStation001"))
				.body("name[1]", equalTo("InterviewStation002"))
//				.body("latitude[1]", equalTo("33.44"))
//				.body("longitude[0]", equalTo("-111.43"))
//				.body("longitude[1]", equalTo("-12.44"))
//				.body("altitude[0]", equalTo("444"))
//				.body("altitude[1]", equalTo("444"))
				.log().all();
	}

	@Test(priority = 4, invocationCount = 1, groups= "APITest")
	void test_Delete_Valid() {
		
		id1 = given().get(URLWithAPIKey).jsonPath().get("id[0]").toString();
		id2 = given().get(URLWithAPIKey).jsonPath().get("id[1]").toString();
		
		given().delete(URL + "/" + id1 + "?appid=" + API_KEY).then().statusCode(204).log().all();
		given().delete(URL + "/" + id2 + "?appid=" + API_KEY).then().statusCode(204).log().all();
	}
	
	@Test(priority = 5, invocationCount = 1, groups= "APITest")
	void test_Delete_Invalid() {
		
		given()
		        .delete(URL + "/" + id1 + "?appid=" + API_KEY).
		then()
				.assertThat()
				.statusCode(404)
				.body("message", equalTo(INVALID_DELETE_STATION_MESSAGE))
				.log().all();
		
		given()
				.delete(URL + "/" + id2 + "?appid=" + API_KEY).
        then()
				.assertThat()
				.statusCode(404)
				.body("message", equalTo(INVALID_DELETE_STATION_MESSAGE))
				.log().all();
	}
}

