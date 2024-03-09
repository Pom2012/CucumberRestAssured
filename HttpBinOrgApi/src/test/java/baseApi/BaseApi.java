package baseApi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;

public abstract class BaseApi {

    public static Response RESPONSE;
    public static RequestSpecification REQUEST;
    public static Logger log = LoggerFactory.getLogger(BaseApi.class);
    public static List<List<String>> listValue = null;
    public static List<Map<String,String>> mapList =null;
    public JSONObject jsonObject = new JSONObject();
    File file;

}
