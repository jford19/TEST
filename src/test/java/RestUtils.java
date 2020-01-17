import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RestUtils {

    static RestAssured r = new RestAssured();

    public static String getById(String path,String idVal){
        System.out.println("get path: "+path);
        System.out.println("query Param: "+"id="+idVal);

        String responseStr = "";
        Map<String,Object> j = new HashMap<String, Object>();

        Response response = null;
                try {
                    response = (Response) r.given().when().queryParam("id", idVal).get(path);
                }
                catch(Exception e){

                }

            responseStr = response.asString();

          return responseStr;
    }



    public static Response putRequest(String path, JSONObject requestBody) {
        System.out.println("update request path : " + path);
        Response response = null;

        try {
            response = (Response)r.given().contentType(ContentType.JSON).body(requestBody.toString()).put(path, new Object[0]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    public static Response postRequest(String path, JSONObject requestBody) {
        System.out.println("post path : " + path);
        Response response = null;

        try {
            response = (Response)r.given().contentType(ContentType.JSON).body(requestBody.toString()).post(path, new Object[0]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }
}
