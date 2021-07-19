package credito.api.atualizar;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import credito.api.BaseApi;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AtulizarSimulacoesTest extends BaseApi {
    String cpf = "77889988";

    //    REALIZAR TENTATIVA DE ATUALIZACAO INFORMANDO OS BODY CORRETO POREM ENVIANDO UM PUT COM CPF INCORRETO ( RETORNO STATUS CODE 404)
    @Test
    public void testDadoAtualizarSimualcaoQuandoInformaCPFNaoCadastradoEntaoObtenhoStatusCode404() {
        given().
                contentType(ContentType.JSON).body(" {\n" +
                "        \"nome\": \"Wilton Henriquedd\",\n" +
                "        \"cpf\": \""+cpf+"\",\n" +
                "        \"email\": \"deltrano@gmail.com\",\n" +
                "        \"valor\": 20000.00,\n" +
                "        \"parcelas\": 5,\n" +
                "        \"seguro\": false\n" +
                "    }").
                put("/v1/simulacoes/"+cpf+"1").
                then().
                statusCode(404).
                and().body("mensagem", is("CPF "+cpf+"1 não encontrado"));
        System.out.println("********** CT01 - REALIZAR TENTATIVA DE ATUALIZACAO COM CPF INVALIDO REALIZADO COM SUCESSO **********");
    }


    //    REALIZAR A TESTE DE ATUALIZACAO DA SIMULACAO,
//    ALTERAR OS CAMPOS QUE DESEJA NO BODY ( RETORNO STATUS CODE 200)
    @Test
    public void testDadoJaExisteUmaSimulacaoQuandoAtualizarInformacaoEntaoObtenhoStatusCode200() {
        given().
                contentType(ContentType.JSON).body(" {\n" +
                "        \"nome\": \"Nascimento\",\n" +
                "        \"cpf\": \""+cpf+"\",\n" +
                "        \"email\": \"wilton@email.com\",\n" +
                "        \"valor\": 800.00,\n" +
                "        \"parcelas\": 5,\n" +
                "        \"seguro\": true\n" +
                "    }").
                put("/v1/simulacoes/"+cpf).
        then().
                statusCode(200).log().all();
        System.out.println("********** CT02 - ATUALIZAR SIMULACAO REALIZADO COM SUCESSO **********");
    }


//    REALIZAR TENTATIVA COM CPF INVALIDO SEM ENVIAR O BODY E COM PUT INCORRETO( RETORNO STATUS CODE 400)
    @Test
    public void testDadoQueroAtulizarSimulacaoEntaoInformaCPFInvalidoEntaoObtenhoStatusCode400() {
        given().
                put("v1/simulacoes/0").
        then().
                statusCode(400);
        System.out.println("********** CT03 - VALIDAR TENTATIVA DE ATULIZACAO SEM BODY E COM CPF INVALIDO REALIZADO COM SUCESSO **********");
    }
}