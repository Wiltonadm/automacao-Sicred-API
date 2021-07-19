package credito.api.consultar;

import credito.api.BaseApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ConsultarRestricoes extends BaseApi {

    @Test
    public void testDadoConsultarRestricoessQuandoInformaCPFSemRestricoesEntaoObtenhoStatusCode204(){
        String cpf ="66414919004";
        given().
                get("v1/restricoes/"+cpf).
        then().
                statusCode(204).log().all();
        System.out.println("********** CT01 - VALIDAR CPF SEM RESTRICOES REALIZADO COM SUCESSO **********");
    }

    @Test
    public void testDadoConsultarRestricoessQuandoInformaCPFComRestricoesEntaoObtenhoStatusCode200(){
        String cpf ="97093236014";
        given().
                get("v1/restricoes/"+cpf).
            then().
                statusCode(200).
        and().body("mensagem", is("O CPF 97093236014 tem problema")).log().all();
        System.out.println("********** CT01 - VALIDAR CPF SEM RESTRICOES REALIZADO COM SUCESSO **********");
    }
}
