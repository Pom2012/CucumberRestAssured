package stepDefs;

import baseApi.BaseApi;
import baseApi.TestImpl;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import utility.ConfigReader;

import static io.restassured.RestAssured.given;

public class RegresApiStepDefs extends BaseApi {

    TestImpl testImpl =new TestImpl();

    @Given("the base URI")
    public void the_base_URI() {
        REQUEST= given().baseUri(ConfigReader.getProperty("url"));
    }

    @When("a {string} request is made to {string}")
    public void a_request_is_made_to(String method, String endpoint) {
        RESPONSE=testImpl.makeRequestToEndpoint(REQUEST,RESPONSE, method,endpoint);
    }

    @Then("get the response {string}")
    public void get_the_response(String statusCode) {
        RESPONSE.then().log().all().assertThat().statusCode(Integer.parseInt(statusCode));
        log.info("Status code is: "+RESPONSE.then().extract().statusCode());
    }
}
