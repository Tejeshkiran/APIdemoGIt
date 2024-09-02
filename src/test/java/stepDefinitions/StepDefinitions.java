package stepDefinitions;

import POJO.AddLoaction;
import POJO.AddPlace;
import Resources.ApiResources;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDefinitions extends Utils {
    RequestSpecification res;
    Response response;
    static String place_id;
    TestDataBuild testData = new TestDataBuild();

    @Given("Add payload with {string} {string} {string}")
    public void add_payload_with(String name, String language, String address) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        res = given().spec(requestSpecification()).body(testData.GetPayload(name,language,address));

    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String HttpMethod) {
        // Write code here that turns the phrase above into concrete actions
        ApiResources ApiR =ApiResources.valueOf(resource);
        if(HttpMethod.equalsIgnoreCase("POST"))
        {
            response= res.when().post(ApiR.getResource()).then().spec(responseSpecification())
                    .extract().response();
        } else if (HttpMethod.equalsIgnoreCase("GET")) {

            response= res.when().get(ApiR.getResource()).then().spec(responseSpecification())
                    .extract().response();
        }
        if(place_id == null)
        place_id = getResponseParameterValue("place_id", response);
    }
    @Then("validate the response has the {int} status code")
    public void validate_the_response_has_the_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode,200);
    }
    @Then("validate the response parameter that has {string} is {string}")
    public void validate_the_response_parameter_that_has_is(String KeyParameter, String ExpectedParameter_value) {
        // Write code here that turns the phrase above into concrete actions
         String ActualParameter_value = getResponseParameterValue(KeyParameter, response);
         Assert.assertEquals(ActualParameter_value,ExpectedParameter_value);

    }
    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String Expected_name, String resource) throws IOException {
        // Write code here that turns the phrase above into concrete actions
       // place_id = getResponseParameterValue("place_id", response);
        res = given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_with_http_request(resource, "GET");
        String Actual_name_value = getResponseParameterValue("name",response);
        Assert.assertEquals(Actual_name_value,Expected_name);

    }
    @Given("Add DeletePlace payload")
    public void add_delete_place_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        res = given().spec(requestSpecification()).body(testData.getDeletePayload(place_id));


    }




}
