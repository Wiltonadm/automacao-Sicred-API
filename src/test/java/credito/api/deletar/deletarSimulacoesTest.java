package credito.api.deletar;

import org.testng.annotations.Test;

import credito.api.BaseApi;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class deletarSimulacoesTest extends BaseApi {
    String id;
    String nome = "Wilton qu";
    String cpf = "741852963";

    //    DELETAR UM SIMULACAO,
    //    PARA DELETAR UM SIMULACAO NECESSARIO ALTERAR AS VARIAVES NOME E CPF(RETORNO STATUS CODE 200)
    @Test
    public void testDadoDeletarSimulacaoQuandoInformaIdEntaoObtenhoStatusCode200() {
        InserirSimulacao();
        pegarId();
        given().
                pathParam("id", id);
        delete("v1/simulacoes/"+id).
                then().
                statusCode(200).log().all().
                and().body(equalTo("OK"));
        System.out.println("********* CT01 - DELETAR SIMULACAO REALIZADO COM SUCESSO *********");
    }

//    CRIAR UMA SIMULACAO PARA TESTAR O DELETE
    @Test
    public void InserirSimulacao(){
        given().
                contentType("application/json")
                .body("{\n" +
                        "  \"nome\": \""+nome+"\",\n" +
                        "  \"cpf\": "+cpf+",\n" +
                        "  \"email\": \"wilton@email.com\",\n" +
                        "  \"valor\": 1500,\n" +
                        "  \"parcelas\": 5,\n" +
                        "  \"seguro\": true\n" +
                        "}").
        when().
                post("v1/simulacoes");
    }

//    PEGAR O PRIMEIRO ID O ULTIMO CRIADO
    @Test
    public void pegarId() {

        Response response =
                given().
                        get("v1/simulacoes");
        response.then();
        id = response.jsonPath().getString("id[1]");
    }
}