package StepDefinition;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataPayload;
import resources.Utils;

public class AddPlaceStepDefinition extends Utils {
	TestDataPayload data=new TestDataPayload();
	//ResponseSpecification res;
	RequestSpecification reqSpe;
	ResponseSpecification resspec;
	Response response;
	static String place_id;
	@Given("Associated add place payload {string} {string} {string}")
	public void associated_add_place_payload(String name, String language, String address) throws IOException {
		 reqSpe=given().spec(addRequestSpecification())
				.body(data.addPlacePayload(name,language,address));
    }

	@When("providing {string} {string} http request")
	public void providing_http_request(String addplace, String httpMethod) {
		APIResources addPlaceResources=APIResources.valueOf(addplace);
    	System.out.println(addPlaceResources.getResources());
    	if(httpMethod.equalsIgnoreCase("POST"))
    		response=reqSpe.when().post(addPlaceResources.getResources()).then().spec(addResponseSpecification()).extract().response();
    		//System.out.println(response.toString());
    	else if(httpMethod.equalsIgnoreCase("DELETE"))
    		response=reqSpe.when().delete(addPlaceResources.getResources()).then().spec(addResponseSpecification()).extract().response();
    	else if(httpMethod.equalsIgnoreCase("GET"))
    		response=reqSpe.when().get(addPlaceResources.getResources()).then().spec(addResponseSpecification()).extract().response();
    }

    @Then("the responce with status code {int} is received")
    public void the_responce_with_status_code_200_is_received(Integer responsecode)
    {
        assertEquals(response.getStatusCode(),200);
    }

    @Then("the reasonse {string} is received as {string}")
    public void the_reasonse_status_is_received_as_ok(String key, String value)
    {
        assertEquals(getJsonPath(response,key),value);
    }
    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_Id_created_maps_to_using(String name, String resources) throws IOException
    {
    	place_id=getJsonPath(response,"place_id");
    	reqSpe=given().spec(addRequestSpecification()).queryParam("place_id", place_id);
    	providing_http_request(resources, "GET");
    	assertEquals(name, getJsonPath(response,"name"));
    	
    }
    @Given("Deletepayload")
    public void deletepayload() throws IOException {
     reqSpe=given().spec(addRequestSpecification()).body(data.deletePayload(place_id));
    }


}
