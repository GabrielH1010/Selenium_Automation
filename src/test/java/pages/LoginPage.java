package pages;

import org.openqa.selenium.By;
import utils.YamlUtils;

public class LoginPage extends BasePage {

    private final By inputUsuario = By.id("user-name");
    private final By inputSenha = By.id("password");
    private final By mensagemErro = By.cssSelector("[data-test='error']");

    public LoginPage() {
        super();
    }

    public void acessarPagina() {
        navegar(YamlUtils.getValorAmbiente("ambientes.login"));
    }

    public void preencherCampos(String usuario,String senha) {
        preencherTexto(inputUsuario, usuario);
        preencherTexto(inputSenha, senha);
    }

    public void clicarBotao(String login) {
        clicarPorTexto(login);
    }

    public boolean validaMensagemAparece() {
        return elementoVisivel(mensagemErro);
    }

    public String mensagemErro() {
        return capturarTexto(mensagemErro);
    }

    public void loginComSucesso() {
        String rotaAtual = driver.getCurrentUrl();
        String rotaProduto = YamlUtils.getValorAmbiente("ambientes.produto");

        if (!rotaAtual.equals(rotaProduto)) {
            acessarPagina();
            preencherCampos(YamlUtils.getValorAmbiente("massa.usuario"), YamlUtils.getValorAmbiente("massa.senha"));
            clicarBotao("Login");
        }
    }
}