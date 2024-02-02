package baseApi;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class TestImpl {

    public Response makeRequestToEndpoint(RequestSpecification request, Response response, String method, String endpoint) {
        if(method.equalsIgnoreCase("get")){
            response=request.given().when().get(endpoint);
        }
        return response;
    }

    public ValidatableResponse getResponse(Response response, String statusCode) {
        return response.then().log().all().assertThat().statusCode(Integer.parseInt(statusCode));
    }
}
