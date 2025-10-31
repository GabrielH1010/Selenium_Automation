package pages;

import org.openqa.selenium.*;
import java.util.List;

public class CheckoutPage extends BasePage {

    private By inputFirstName = By.id("first-name");
    private By inputLastName = By.id("last-name");
    private By inputPostalCode = By.id("postal-code");

    private By itensResumo = By.cssSelector(".cart_item");
    private By nomeProdutoResumo = By.cssSelector(".inventory_item_name");
    private By precoProdutoResumo = By.cssSelector(".inventory_item_price");

    private By botaoBackToHome = By.id("back-to-products");

    private By mensagemErro = By.cssSelector("h3[data-test='error']");
    private By mensagemSucesso = By.cssSelector(".complete-header");

    private By formulario = By.cssSelector(".checkout_info");

    public CheckoutPage() {
        super();
    }

    public void aguardarTelaCheckoutSuasInformacoes() {
        aguardarTelaCarregar("checkoutSuasInformacoes", formulario);
    }

    public void clicarBotao(String texto){
        clicarPorTexto(texto);
    }

    public boolean botaoBackToHomeVisivel() {
        return elementoVisivel(botaoBackToHome);
    }

    public void preencherCampos(String nome,String sobreNome,String codPostal) {
        preencherTexto(inputFirstName, nome);
        preencherTexto(inputLastName, sobreNome);
        preencherTexto(inputPostalCode, codPostal);
    }

    public boolean resumoCompraExibeProdutos() {
        List<WebElement> itens = driver.findElements(itensResumo);
        if (itens.isEmpty()) return false;

        for (WebElement item : itens) {
            String nome = item.findElement(nomeProdutoResumo).getText();
            String preco = item.findElement(precoProdutoResumo).getText();
            if (nome.isEmpty() || preco.isEmpty()) return false;
        }
        return true;
    }

    public String capturarMensagemSucesso() {
        return capturarTexto(mensagemSucesso);
    }

    public String capturarMensagemErro() {
        return capturarTexto(mensagemErro);
    }
}