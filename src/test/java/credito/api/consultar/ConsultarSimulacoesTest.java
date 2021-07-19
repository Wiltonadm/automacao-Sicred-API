package credito.api.consultar;

import credito.api.BaseApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ConsultarSimulacoesTest extends BaseApi {

//    REALIZAR CONSULTA DE TODOS AS SIMULACOES CRIADAS (RETORNO STATUS CODE 200))
    @Test
    public void testDadoConsultarTodasSimulacoesQuandoNaoInformaCPFnoPathEntaoObtenhoStatusCode200(){
        given().
                get("v1/simulacoes").
        then().
                statusCode(200).log().all();
        System.out.println("********** CT01 - RETORNA TODAS AS  SIMULACOES REALIZADO COM SUCESSO **********");
    }

//    REALIZAR CONSULTA ESPECIFICA POR UM CPF, NECESSARIO INFORMA O CPF JA CADASTRADO NA STRING CPF ( RETORNO STATUS CODE 200)
    @Test
    public void testDadoConsultarSimulacaoQuandoInformaCPFEntaoObtenhoStatusCode200(){
        String cpf = "66414919004";

        given().
                get("v1/simulacoes/"+cpf).
        then().
                statusCode(200).log().all();
        System.out.println("********** CT02 - RETORNA CONSULTA ESPECIFICA POR CPF REALIZADA COM SUCESSO **********");
    }

//    REALIZAR TENTATIVA DE CONSULTA COM CPF NAO CADASTRADO, NECESSARIO INFORMA UM CPF NAO CADASTRADO NA STRING CPF ( RETORNO STATUS CODE 404)
    @Test
    public void testDadoConsultarSimulacaoQuandoInformaCPFQuePossuiEntaoObtenhoStatusCode404(){
        String cpf = "1111111111111111";
        given().
                get("v1/simulacoes/"+cpf).
        then().
                statusCode(404).log().all().
        and().
                body("mensagem", is("CPF "+cpf+" não encontrado"));
        System.out.println("********** CT03 - VALIDAR TENTATIVA DE CONSULTAR CPF NAO CADASTRADO REALIZADO **********");
    }
}
