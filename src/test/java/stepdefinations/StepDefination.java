
	package stepdefinations;

	import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

	
	
	
	
	
	public class StepDefination {
		RequestSpecification res;
		ResponseSpecification resspec;
		Response response;
		@Given("Add Place Payload")
		public void add_Place_Payload() {
		    // Write code here that turns the phrase above into concrete actions
			RestAssured.baseURI="https://rahulshettyacademy.com";

			AddPlace p =new AddPlace();
			p.setAccuracy(50);
			p.setAddress("29, side layout, cohen 09");
			p.setLanguage("French-IN");
			p.setPhone_number("(+91) 983 893 3937");
			p.setWebsite("https://rahulshettyacademy.com");
			p.setName("Frontline house");
			List<String> myList =new ArrayList<String>();
			myList.add("shoe park");
			myList.add("shop");

			p.setTypes(myList);
			Location l =new Location();
			l.setLat(-38.383494);
			l.setLng(33.427362);
			p.setLocation(l);

			 RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
			.setContentType(ContentType.JSON).build();
			 
			 
			resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			res=given().spec(req)
			.body(p);
		}

		@When("user calls {string} with Post httprequest")
		public void user_calls_with_Post_httprequest(String string) {
		    // Write code here that turns the phrase above into concrete actions
			 response =res.when().post("/maps/api/place/add/json").
					then().spec(resspec).extract().response();
		}

		@Then("the API call is success with status code {int}")
		public void the_API_call_is_success_with_status_code(Integer int1) {
		    // Write code here that turns the phrase above into concrete actions
		    assertEquals(response.getStatusCode(),200);
		}

		@Then("{string} in response body is {string}")
		public void in_response_body_is(String keyValue, String expectedValue) {
		    // Write code here that turns the phrase above into concrete actions
		    String resp = response.asString();
		    JsonPath js = new JsonPath(resp);
		    assertEquals(js.get(keyValue).toString(),expectedValue);
		}
		
	}


