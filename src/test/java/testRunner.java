import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

public class testRunner {

    public static void main(String[] args){
        System.out.println("hello");

        //TEST CASE 001 -
        //get user by id and display user payload
        System.out.println("running TEST CASE 001 - get user by id and display user json,validate correct user fetched");

        String path = "http://qainterview.merchante-solutions.com:3030/users";
        String param = "4";

        JSONArray respObj= new JSONArray(RestUtils.getById(path,param));
        System.out.println("Response "+respObj);
        //validate correct id value from response
        for(Object currObj:respObj){
            JSONObject currJson = new JSONObject(String.valueOf(currObj));
            String currId = String.valueOf(currJson.get("id"));
            if(!currId.equals(param)){
                Assert.assertTrue(false);
            }
            System.out.println("PASSED: id value from user response json is "+param+" as expected");
        }



        //TEST CASE 002 -
        //post comment to comments resource validate post success
        System.out.println("running TEST CASE 002 - post comment to comments resource validate post success");

        String pathForPost = "http://qainterview.merchante-solutions.com:3030/comments";
        JSONObject jOb = new JSONObject();
        ValueGenerator valueGenerator = new ValueGenerator();
        valueGenerator.generateRandomValue("id");
        jOb.put("id",valueGenerator.generateRandomValue("id"));
        jOb.put("comment","I posted a comment here");

        Response r = RestUtils.postRequest(pathForPost,jOb);
        if(!(r.getStatusCode()==201)){Assert.assertTrue("status code <>201 hence failing",false);}
        System.out.println("PASSED: comment posted succuessfully");
        System.out.println("Status Code: "+r.getStatusCode());
        System.out.println("Response: "+r.getBody().asString());


        //TEST CASE 003 -
        //fetch from post resource update and validate update success
        System.out.println("running TEST CASE 003 - fetch from post resource update and validate update success");

            //post a value to post resource,then update the resource
            String pathForPost2 = "http://qainterview.merchante-solutions.com:3030/posts";
            JSONObject jOb2 = new JSONObject();
            ValueGenerator valueGenerator2 = new ValueGenerator();
            valueGenerator.generateRandomValue("id");
            jOb2.put("id",valueGenerator.generateRandomValue("id"));
            jOb2.put("post","I posted a post here");

            Response r2 = RestUtils.postRequest(pathForPost2,jOb2);
            if(!(r2.getStatusCode()==201)){Assert.assertTrue("status code <>201 hence failing",false);}
            System.out.println("PASSED: comment posted succuessfully");
            System.out.println("Status Code: "+r2.getStatusCode());
            System.out.println("Response: "+r2.getBody().asString());


            String jObResponseStr = r2.getBody().asString();
            JSONObject jObResponse = new JSONObject(jObResponseStr);
            String endpointValForPut = String.valueOf(jObResponse.get("id"));
                //update the resource using put
                String pathForPut = "http://qainterview.merchante-solutions.com:3030/posts/"+endpointValForPut;
                JSONObject newjObForPut = new JSONObject();
                newjObForPut.put("post","I updated the post here");

                Response r3 = RestUtils.putRequest(pathForPut,newjObForPut);
                if(!(r3.getStatusCode()==200)){Assert.assertTrue("status code <>201 hence failing",false);}
                System.out.println("PASSED: comment updated succuessfully");
                System.out.println("Status Code: "+r3.getStatusCode());
                System.out.println("Response: "+r3.getBody().asString());



    }
}
