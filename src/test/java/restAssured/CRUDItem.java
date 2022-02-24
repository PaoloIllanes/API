package restAssured;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUDItem {

    @Test
    public void CRUDItem(){

        // Create Item
        JSONObject ItemBody = new JSONObject();
        ItemBody.put("Content","PaoloItem");


        Response response=given()
                .auth()
                .preemptive()
                .basic("paolo@api.upb","123")
                .body(ItemBody.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/items.json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("PaoloItem"))
                .log().all();

        int idItem =response.then().extract().path("Id");

        // Read Item
        response=given()
                .auth()
                .preemptive()
                .basic("paolo@api.upb","123")
                .log().all()
                .when()
                .get("https://todo.ly/api/items/"+idItem+".json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("PaoloItem"))
                .log().all();
        // Update Item
        ItemBody.put("Content","PaoloItemUpdate");
        response=given()
                .auth()
                .preemptive()
                .basic("paolo@api.upb","123")
                .body(ItemBody.toString())
                .log().all()
                .when()
                .put("https://todo.ly/api/items/"+idItem+".json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("PaoloItemUpdate"))
                .log().all();
      /* // Delete Item
       response=given()
                .auth()
                .preemptive()
                .basic("paolo@api.upb","123")
                .log().all()
                .when()
                .delete("https://todo.ly/api/items/"+idItem+".json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("PaoloItemUpdate"))
                .body("Deleted",equalTo(true))
                .log().all();*/

    }
}
