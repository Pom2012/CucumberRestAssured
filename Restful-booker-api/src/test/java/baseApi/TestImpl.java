package baseApi;

import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import utility.Utility;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static utility.Utility.genStrFromRsrc;

public class TestImpl extends BaseApi {

    public Response makeRequestToEndpoint(RequestSpecification request, String method, String jsFileName, String endpoint) throws IOException {
        String jsonBody = genStrFromRsrc(pth + jsFileName + ".json");
        return switch (method) {
            case "post" -> request.given().contentType("application/json").body(jsonBody).when().post(endpoint);
            case "put", "patch" -> request.given().contentType("application/json").header("Authorization", auth).body(jsonBody).when().request(method, endpoint);
            case "delete" -> request.given().contentType("application/json").header("Authorization", auth).when().delete(endpoint);
            default -> null;
        };
    }

    public ValidatableResponse getResponse(Response response, String statusCode) {
        return response.then().log().all().assertThat().statusCode(Integer.parseInt(statusCode));
    }

    public Response makeRequestToEndpoint(RequestSpecification request, String method, String endpoint, DataTable dataTable) {
        jsonObject.putAll(dataTable.asMap(String.class, String.class));
        return request.given().accept("*/*").contentType("application/json").body(jsonObject.toJSONString()).when().request(method, endpoint).prettyPeek();
    }

    public void responseHasCucumberData(Response response, String type, DataTable dataTable) {
        expectedDetails = dataTable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : expectedDetails.entrySet()) {
            if (type.equalsIgnoreCase("create"))
                response.then().body("booking." + entry.getKey(), equalTo(Utility.convertToAppropriateType(entry.getValue())));
            else
                response.then().body(entry.getKey(), equalTo(Utility.convertToAppropriateType(entry.getValue())));
        }
    }

    public void responseHasData(Response response, String id, String token) {
        response.then().assertThat()
                .body("id", equalTo(Integer.parseInt(id)))
                .body("token", equalTo(token));
    }

    public void retrieveToken(Response response, String token) {
        authToken = response.jsonPath().getString(token);
        log.info("Token value: " + authToken);
    }

}
