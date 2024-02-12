package stepDefs;

import baseApi.BaseApi;
import baseApi.TestImpl;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import utility.ConfigReader;

import static io.restassured.RestAssured.given;

public class RegresApiStepDefs extends BaseApi {

    TestImpl testImpl = new TestImpl();

    @Given("the base URI")
    public void the_base_URI() {
        REQUEST = given().baseUri(ConfigReader.getProperty("url"));
    }

    @When("a {string} request is made to {string}")
    public void a_request_is_made_to(String method, String endpoint) {
        RESPONSE = testImpl.makeRequestToEndpoint(REQUEST, method, endpoint);
    }

    @Then("the response {string}")
    public void the_response(String statusCode) {
        RESPONSE.then().log().all().assertThat().statusCode(Integer.parseInt(statusCode));
        log.info("Status code is: " + RESPONSE.then().extract().statusCode());
    }

    @When("a {string} request is made to {string} with the below data:")
    public void a_request_is_made_to_with_the_below_data(String method, String endpoint, DataTable dataTable) {
        RESPONSE = testImpl.makeRequestToEndpoint(REQUEST, method, endpoint, dataTable);
    }

    @Then("the response body has the below data:")
    public void the_response_body_has_the_below_data(DataTable dataTable) {
        testImpl.responseHasData(RESPONSE, dataTable);
    }

    @And("the body has {string} and {string}")
    public void has_body_has_and(String string, String string2) {
        testImpl.responseHasData(RESPONSE, string, string2);
    }

}
