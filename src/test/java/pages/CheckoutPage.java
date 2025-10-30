package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import utils.YamlUtils;
import java.time.Duration;
import java.util.List;

public class CheckoutPage extends BasePage {

    private By inputFirstName = By.id("first-name");
    private By inputLastName = By.id("last-name");
    private By inputPostalCode = By.id("postal-code");

    private By mensagemErro = By.cssSelector("h3[data-test='error']");
    private By itensResumo = By.cssSelector(".cart_item");
    private By nomeProdutoResumo = By.cssSelector(".inventory_item_name");
    private By precoProdutoResumo = By.cssSelector(".inventory_item_price");

    private By botaoCheckout = By.id("checkout");
    private By botaoContinue = By.id("continue");
    private By botaoFinish = By.id("finish");
    private By botaoBackToHome = By.id("back-to-products");

    private By mensagemSucesso = By.cssSelector(".complete-header");
    private By formulario = By.cssSelector(".checkout_info");

    public CheckoutPage() {
        super();
    }

    // Aguardando telas
    public void aguardarTelaCheckoutSuasInformacoes() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        String urlAtual = driver.getCurrentUrl();
        String urlEsperada = YamlUtils.getValorAmbiente("ambientes.checkoutSuasInformacoes");

        if (!urlAtual.equals(urlEsperada)) {
            navegar(urlEsperada);
            wait.until(ExpectedConditions.urlToBe(urlEsperada));
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(formulario));
    }


    // Métodos de botões
    public void botaoCheckout() {
        clicar(botaoCheckout);
    }

    public void botaoContinue() {
        clicar(botaoContinue);
    }

    public void clicarFinish() {
        clicar(botaoFinish);
    }

    public boolean botaoBackToHomeVisivel() {
        return elementoVisivel(botaoBackToHome);
    }


    //Métodos de preencher campo
    public void preencherCampo(String campo, String valor) {
        switch (campo) {
            case "First Name":
                preencherTexto(inputFirstName, valor);
                break;
            case "Last Name":
                preencherTexto(inputLastName, valor);
                break;
            case "Postal Code":
                preencherTexto(inputPostalCode, valor);
                break;
        }
    }

    public String capturarMensagemErro() {
        return capturarTexto(mensagemErro);
    }

    public void preencherCamposCorretamente() {
        preencherTexto(inputFirstName, "Gabriel");
        preencherTexto(inputLastName, "Henrique");
        preencherTexto(inputPostalCode, "12345-678");
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
}
