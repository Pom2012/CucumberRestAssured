package baseApi;

import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;

public class TestImpl extends BaseApi {

    public Response makeRequestToEndpoint(RequestSpecification request, String method, String endpoint) {
        return request.given().when().request(method, endpoint);
    }

    public ValidatableResponse getResponse(Response response, String statusCode) {
        return response.then().log().all().assertThat().statusCode(Integer.parseInt(statusCode));
    }

    public Response makeRequestToEndpoint(RequestSpecification request, String method, String endpoint, DataTable dataTable) {
        jsonObject.putAll(dataTable.asMap(String.class, String.class));
//        return response=request.given().accept("*/*")
//        .body(new File("src/test/resources/testData/newUser.json")).when().post(endpoint).andReturn().prettyPeek();
        return request.given().accept("*/*").contentType("application/json").body(jsonObject.toJSONString()).when().request(method, endpoint).prettyPeek();
    }

    public void responseHasData(Response response, DataTable dataTable) {
        dataTable.asLists().forEach(p -> response.then().assertThat().body(p.get(0), equalTo(p.get(1))));
    }

    public void responseHasData(Response response, String string, String string2) {
        response.then().assertThat().body("id", equalTo(Integer.parseInt(string)));
        response.then().assertThat().body("token", equalTo(string2));
    }

    public static void main(String[] args) {

        ArrayList col=new ArrayList(Arrays.asList("asdasd","asd"));
    }
}
