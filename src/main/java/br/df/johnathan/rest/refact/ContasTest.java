package br.df.johnathan.rest.refact;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import br.df.johnathan.rest.core.BaseTest;
import br.df.johnathan.rest.utils.BarrigaUtils;

public class ContasTest extends BaseTest {
	
	
	
	
	@Test
	public void deveIncluirContaComSucesso() {	
		given()
			.body("{\"nome\": \"Conta Inserida\"}")
		.when()
			.post("/contas")
		.then()
			.statusCode(201)
		;
		
	}
	
	@Test
	public void DeveAlterarContaComSucesso() {
		Integer CONTA_ID = BarrigaUtils.getIdContaPeloNome("Conta para alterar");
		given()
			.body("{\"nome\": \"Conta alterada\"}")
			.pathParam("id", CONTA_ID)
		.when()
			.put("/contas/{id}")
		.then()
		.log().all()
			.statusCode(200)
			.body("nome", is("Conta alterada"))
		;
		
	}
	
	@Test
	public void naoDeveInserirContaComMesmoNome() {
		given()
			.body("{\"nome\": \"Conta mesmo nome\"}")
		.when()
			.post("/contas")
		.then()
			.statusCode(400)
			.body("error", is("Já existe uma conta com esse nome!"))		
		;
		
	}
	

}
