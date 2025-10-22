package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import utils.YamlUtils;

public class LoginPage extends BasePage {

    private final By inputUsuario = By.id("user-name");
    private final By inputSenha = By.id("password");
    private final By botaoLogin = By.id("login-button");
    private final By mensagemErro = By.cssSelector("[data-test='error']");

    public LoginPage() {
        super();
    }

    public void acessarPagina() {
        navegar(YamlUtils.getValorAmbiente("ambientes.login"));
    }

    public void preencherUsuario(String usuario) {
        preencherTexto(inputUsuario, usuario);
    }

    public void preencherSenha(String senha) {
        preencherTexto(inputSenha, senha);
    }

    public void clicarLogin() {
        clicar(botaoLogin);
    }

    public boolean validaMensagemAparece() {
        return elementoVisivel(mensagemErro);
    }

    public String mensagemErro() {
        return capturarTexto(mensagemErro);
    }

    public void loginComSucesso() {
        acessarPagina();
        preencherUsuario("standard_user");
        preencherSenha("secret_sauce");
        clicarLogin();
    }

    public void loginDireto() {
        navegar(YamlUtils.getValorAmbiente("ambientes.produto"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('session-username', 'standard_user');");
        driver.navigate().refresh();
    }
}