package credito.api.inserir;

import credito.api.BaseApi;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class InserirSimulacoesTest extends BaseApi {

//    PARA REALIZAR O CADASTRO DE UM NOVA SIMULACAO,
//    NECESSARIO ALTERAR OS CAMPOS NAME E CPF PARA UM NAO QUE NAO ESTEJA JA CADASTRADO( RETORNO STATUS CODE 200)
    @Test
    public void testDadoRealizarCadastroSimualacaoQuandoInformaoOsCamposCorretamenteEntaoObtenhoStatusCode200(){
        given().
                contentType("application/json")
                .body("{\n" +
                        "  \"nome\": \"MICHELE \",\n" +
                        "  \"cpf\": 78988888889,\n" +
                        "  \"email\": \"wilton@email.com\",\n" +
                        "  \"valor\": 1500,\n" +
                        "  \"parcelas\": 5,\n" +
                        "  \"seguro\": true\n" +
                        "}").
        when().log().all().
                post("v1/simulacoes").
        then().statusCode(201);
        System.out.println("********** CT01 - SIMULACAO CRIADA COM SUCESSO **********");
    }

//    REALIZAR TENTATIVA DE TENTAR CADASTRAR SIMULACAO FALTANDO CAMPOS ( RETORNO STATUS CODE 400)
    @Test
    public void testDadoRealizarCadastroSimualacaoQuandoInformaoOsCamposFaltandoInformacoesEntaoObtenhoStatusCode400(){
        given().
                contentType("application/json")
                .body("{\n" +
                        "  \"nome\": \"MAICON DA SILVA\",\n" +
                        "  \"email\": \"wilton@email.com\",\n" +
                        "  \"valor\": 1500,\n" +
                        "  \"parcelas\": 5,\n" +
                        "  \"seguro\": true\n" +
                        "}").
                when().log().all().
                post("v1/simulacoes").
                then().statusCode(400);
        System.out.println("********** CT02 - VALIDAR ERRO NAO COLOCAR TODAS INFORMACOES CORRETA RELAIZADO COM SUCESSO **********");
    }

//    REALIZAR TENTATIVA INSERIR UM CPF JA EXISTENTE, NECESSARIO INFORMA UM CPF JA CADASTRADO ( RETORNO STATUS CODE: 400)
    @Test
    public void testDadoRealizarCadastroSimualacaoQuandoInformaoCPFJaCadastradoEntaoObtenhoStatusCode400(){
        given().
                contentType("application/json")
                .body("{\n" +
                        "        \"nome\": \"Fulano\",\n" +
                        "        \"cpf\": \"66414919004\",\n" +
                        "        \"email\": \"fulano@gmail.com\",\n" +
                        "        \"valor\": 11000.00,\n" +
                        "        \"parcelas\": 3,\n" +
                        "        \"seguro\": true\n" +
                        "    }").
        when().log().all().
                post("v1/simulacoes").
        then().statusCode(400);
        System.out.println("********** CT03 - VALIDAR ERRO TENTAR INSERIR UMA SIMULACAO COM CPF EXISTENTE REALIZADO COM SUCESSO **********");
    }


}
