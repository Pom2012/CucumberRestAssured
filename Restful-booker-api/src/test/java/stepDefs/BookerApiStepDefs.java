package stepDefs;

import baseApi.BaseApi;
import baseApi.TestImpl;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import utility.ConfigReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BookerApiStepDefs extends BaseApi {

    TestImpl testImpl = new TestImpl();

    @Given("the base URI")
    public void the_base_URI() {
        REQUEST = given().baseUri(ConfigReader.getProperty("url"));
    }

    @When("a {string} request with the {string} is made to {string}")
    public void a_request_with_the_is_made_to(String method, String jsFileName, String endpoint) throws IOException {
        RESPONSE = testImpl.makeRequestToEndpoint(REQUEST, method, jsFileName, endpoint);
    }

    @Then("the {string} is generated")
    public void the_is_generated(String token) {
        testImpl.retrieveToken(RESPONSE, token);
    }

    @Then("the response {string}")
    public void the_response(String statusCode) {
        RESPONSE.then().log().all().assertThat().statusCode(Integer.parseInt(statusCode));
        log.info("Status code is: " + RESPONSE.then().extract().statusCode());
    }

    @Then("the {string} body has the below data:")
    public void the_body_has_the_below_data(String type, DataTable dataTable) {
        testImpl.responseHasCucumberData(RESPONSE,type, dataTable);
    }














    @When("a {string} request is made to {string} with the below data:")
    public void a_request_is_made_to_with_the_below_data(String method, String endpoint, DataTable dataTable) {
        RESPONSE = testImpl.makeRequestToEndpoint(REQUEST, method, endpoint, dataTable);
    }



    @And("the body has {string} and {string}")
    public void has_body_has_and(String string, String string2) {
        testImpl.responseHasData(RESPONSE, string, string2);
    }

}
