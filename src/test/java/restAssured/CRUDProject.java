package restAssured;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class CRUDProject {

    @Test
    public void crudProject(){
        // Create
        JSONObject body = new JSONObject();
        body.put("Content","EynarJSON");
        body.put("Icon",4);

        Response response=given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/projects.json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("EynarJSON"))
                .body("Icon",equalTo(4))
                .log().all();

        int idProject =response.then().extract().path("Id");

        // Read
        response=given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .log().all()
                .when()
                .get("https://todo.ly/api/projects/"+idProject+".json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("EynarJSON"))
                .body("Icon",equalTo(4))
                .log().all();
        // Update
        body.put("Content","EynarUpdate");
        response=given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .body(body.toString())
                .log().all()
                .when()
                .put("https://todo.ly/api/projects/"+idProject+".json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("EynarUpdate"))
                .body("Icon",equalTo(4))
                .log().all();
        // Delete
        response=given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .log().all()
                .when()
                .delete("https://todo.ly/api/projects/"+idProject+".json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("EynarUpdate"))
                .body("Icon",equalTo(4))
                .body("Deleted",equalTo(true))
                .log().all();

    }

}
