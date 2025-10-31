package steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import pages.*;
import utils.YamlUtils;

public class LoginSteps {


    private WebDriver driver;
    private ProdutosPage produtosPage;
    private LoginPage loginPage;

    public LoginSteps() {
        this.driver = DriverFactory.getDriver();
        this.loginPage = new LoginPage();
        this.produtosPage = new ProdutosPage();
    }

    //Contexto
    @Dado("que o usuário está na página de login")
    public void queOUsuarioEstaNaPaginaDeLogin() {
        loginPage.acessarPagina();
    }

    //CT001 - Login com senha inválida
    @Quando("ele preenche a senha inválida")
    public void elePreencheASenhaInvalida() {
        loginPage.preencherCampos(YamlUtils.getValorAmbiente("massa.usuario"), "senha123");
    }

    @E("clicar no botão de {string}")
    public void clicarEm(String botaoLogin){
        loginPage.clicarBotao(botaoLogin);
    }

    @Entao("deve ser apresentado a mensagem de erro na tela")
    public void deveSerApresentadoAMensagemDeErroNaTela() {
        Assert.assertTrue("Mensagem de erro não apareceu!", loginPage.validaMensagemAparece());
        String expectedText = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(expectedText, loginPage.mensagemErro());
    }

    //CT002 - Login com sucesso
    @Quando("ele preenche o usuário e senha válidos")
    public void elePreencheOUsuarioESenhaValidos() {
        loginPage.preencherCampos(YamlUtils.getValorAmbiente("massa.usuario"), YamlUtils.getValorAmbiente("massa.senha"));
    }

    @Entao("ele deve ser redirecionado para a tela de produtos")
    public void eleDeveSerRedirecionadoParaATelaDeProdutos() {
        Assert.assertEquals("Products", produtosPage.getTituloPagina());
    }
}