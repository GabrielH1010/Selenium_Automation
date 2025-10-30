package hooks;

import io.cucumber.java.*;
import pages.LoginPage;
import pages.ProdutosPage;
import utils.DriverFactory;

public class Hooks {

    private static boolean loginInicial = false;

    @BeforeAll
    public static void setupOnce() {
        DriverFactory.getDriver();
    }

    @Before(order = 0)
    public void beforeScenario() {
        LoginPage loginPage = new LoginPage();
        ProdutosPage produtosPage = new ProdutosPage();

        if (!loginInicial) {
            System.out.println("----------Fazendo login inicial----------");
            loginPage.loginComSucesso();
            loginInicial = true;
        } else {
            produtosPage.aguardarTelaProdutosCarregar();
        }
    }

    @AfterAll
    public static void afterAll() {
        DriverFactory.quitDriver();
        loginInicial = false;
    }
}