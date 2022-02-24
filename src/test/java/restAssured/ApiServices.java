package restAssured;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ApiServices {

    /*
     * codigos de respuesta
     *  1XX ----> informacion
     *  2XX ----> satisfactorias
     *  3XX ----> redirecciones
     *  4XX ----> mensaje para el usuario
     *  5XX ----> error en el servidor
     *
     *  given() ----> configuracion: body / header/ parametros / authentication
     *  when() ---> method (post,get,delete,etc) / url
     *  then() ---> ya tenemos informacion de la respuesta body/codigo respuesta/ msg respuesta /header respuesta
     * */


    @Test
    public void createProject(){
        given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .body("{\n" +
                        "  \"Content\":\"PaoloRestAssured\",\n" +
                        "  \"Icon\":\"4\"\n" +
                        "}")
                .log().all()
                .when()
                .post("https://todo.ly/api/projects.json")
                .then()
                .log().all();
    }


    @Test
    public void createProjectJsonObject(){

        JSONObject body = new JSONObject();
        body.put("Content","PaoloJSON");
        body.put("Icon",7);

        given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/projects.json")
                .then()
                .log().all();
    }


    @Test
    public void createProjectJsonObjectFile(){
        String pathProject=new File("").getAbsolutePath();

        given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .body(new File(pathProject+"/src/test/resources/projectBody.json"))
                .log().all()
                .when()
                .post("https://todo.ly/api/projects.json")
                .then()
                .log().all();

    }

}
