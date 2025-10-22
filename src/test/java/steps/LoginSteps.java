package steps;

import io.cucumber.java.pt.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProdutosPage;
import utils.DriverFactory;

public class LoginSteps {
    private String usuario = "standard_user";
    private String senha = "secret_sauce";

    private WebDriver driver;
    private ProdutosPage produtosPage;
    private LoginPage loginPage;

    public LoginSteps() {
        this.driver = DriverFactory.getDriver();
        this.loginPage = new LoginPage();
        this.produtosPage = new ProdutosPage();
    }

    @Dado("que o usuário está na página de login do Swag Labs")
    public void acessarPaginaLogin() {
        loginPage.acessarPagina();
    }

    @Quando("ele preenche o usuário e senha válidos")
    public void preencherCamposValidos() {
        loginPage.preencherUsuario(usuario);
        loginPage.preencherSenha(senha);
        loginPage.clicarLogin();
    }

    @Entao("ele deve ser redirecionado para a tela de produtos")
    public void validarTelaProdutos() {
        Assert.assertEquals("Products", produtosPage.getTituloPagina());
    }

    @Dado("que o usuário está na página de login")
    public void queOUsuarioEstaNaPaginaDeLogin() {
        loginPage.acessarPagina();
    }

    @Quando("ele preenche a senha inválida")
    public void elePreencheASenhaInvalida() {
        loginPage.preencherUsuario(usuario);
        loginPage.preencherSenha("senha inválida");
        loginPage.clicarLogin();
    }

    @Entao("deve ser apresentado a mensagem de erro na tela")
    public void deveSerApresentadoAMensagemDeErroNaTela() {
        Assert.assertTrue("Mensagem de erro não apareceu!", loginPage.validaMensagemAparece());
        String expectedText = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(expectedText, loginPage.mensagemErro());    }
}