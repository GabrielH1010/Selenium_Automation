package pages;
import org.openqa.selenium.By;
import utils.YamlUtils;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By erroMensagem = By.cssSelector("[data-test='error']");

    public LoginPage() {
        super();
    }

    public void acessarPagina() {
        navegar(YamlUtils.getValorAmbiente("ambientes.login"));
    }

    public void preencherUsuario(String usuario) {
        preencherTexto(usernameField, usuario);
    }

    public void preencherSenha(String senha) {
        preencherTexto(passwordField, senha);
    }

    public void clicarLogin() {
        clicar(loginButton);
    }

    public boolean validaMensagemAparece() {
        return elementoVisivel(erroMensagem);
    }

    public String mensagemErro() {
        return capturarTexto(erroMensagem);
    }

    public void loginComSucesso() {
        acessarPagina();
        preencherUsuario("standard_user");
        preencherSenha("secret_sauce");
        clicarLogin();
    }
}