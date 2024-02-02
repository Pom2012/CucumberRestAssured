package baseApi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class BaseApi {

    public static Response RESPONSE;
    public static RequestSpecification REQUEST;
    public static Logger log = LoggerFactory.getLogger(BaseApi.class);
    public static List<List<String>> listValue = null;
    public static List<Map<String,String>> mapList =null;
}
