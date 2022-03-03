package runner;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInformation;
import helpers.Configuration;
import helpers.JSONCompare;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class MyStepDefsClean {

    Response response;
    RequestInformation requestInformation = new RequestInformation();
    Map<String,String> dynamicVar= new HashMap<>();
    @Given("yo uso la authenticacion por Token")
    public void yoUsoLaAuthenticacionPorToken() {
    }

    @When("envio {} request a la {} con el body")
    public void envioPOSTRequestALaApiProjectsJsonConElBody(String method,String url,String body) {
        requestInformation.setUrl(Configuration.host+replaceVar(url)).setBody(replaceVar(body));
        response= FactoryRequest.make(method).send(requestInformation);
    }

    @Then("el codigo de respuesta deberia ser {int}")
    public void elCodigoDeRespuestaDeberiaSer(int expectedResult) {
        response.then()
                .statusCode(expectedResult);
    }

    @And("el expected body deberia ser")
    public void elExpectedBodyDeberiaSer(String expectedJsonBody) throws Exception {
        JSONCompare.areEqualJson(replaceVar(expectedJsonBody),response.body().asPrettyString(),"Los Jsons son diferentes");
    }

    @And("guardo el {} de la respuesta en {}")
    public void guardoElIdDeLaRespuestaEnID_PROJ(String attribute,String nameVariable) {
        dynamicVar.put(nameVariable,response.then().extract().path(attribute)+"");
    }

    @And("el atributo {} deberia ser {}")
    public void elAtributoContentDeberiaSerNAME(String attribute,String expectedValue) {
        response.then()
                .body(attribute,equalTo(expectedValue));
    }

    private String replaceVar(String value){
        for (String attribute: dynamicVar.keySet() ) {
            value=value.replace(attribute,dynamicVar.get(attribute));
        }
        return value;
    }

}
