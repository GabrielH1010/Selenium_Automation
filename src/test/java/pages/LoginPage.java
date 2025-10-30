package pages;

import org.openqa.selenium.By;
import utils.YamlUtils;

public class LoginPage extends BasePage {

    private final By inputUsuario = By.id("user-name");
    private final By inputSenha = By.id("password");
    private final By botaoLogin = By.id("login-button");
    private final By mensagemErro = By.cssSelector("[data-test='error']");
    private ProdutosPage produtosPage;

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
        this.produtosPage = new ProdutosPage();
        String rotaAtual = driver.getCurrentUrl();
        String rotaProduto = YamlUtils.getValorAmbiente("ambientes.produto");

        if (!rotaAtual.equals(rotaProduto)) {
            acessarPagina();
            preencherUsuario("standard_user");
            preencherSenha("secret_sauce");
            clicarLogin();
            produtosPage.aguardarTelaProdutosCarregar();
        }
    }
}