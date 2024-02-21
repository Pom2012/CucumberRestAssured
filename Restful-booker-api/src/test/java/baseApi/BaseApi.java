package baseApi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.ConfigReader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class BaseApi {

    public static Response RESPONSE;
    public static RequestSpecification REQUEST;
    public static Logger log = LoggerFactory.getLogger(BaseApi.class);
    public static List<List<String>> listValue;
    public static List<Map<String,String>> mapList;
    Map<String, String> expectedDetails;
    public JSONObject jsonObject = new JSONObject();
    String auth=ConfigReader.getProperty("basAuth"), authToken, bookingid, pth = "src\\test\\resources\\testData\\";
    Object document, key, value=null;
    File file;

}
