package factoryRequest;


import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestToken  {


    public static String getToken() {
        String tokenString;
        Response response= given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .log().all()
                .when()
                .get("https://todo.ly/api/authentication/token.json");
       tokenString =response.then().extract().path("TokenString");
        response.then().log().all();
        return tokenString;
    }
}
