package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import utils.YamlUtils;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class ProdutosPage extends BasePage {

    private By tituloProduto = By.cssSelector(".inventory_item_name");
    private By filtro = By.cssSelector(".product_sort_container");
    private By adicionarAoCarrinho = By.id("add-to-cart-sauce-labs-backpack");
    private By removerDoCarrinho = By.id("remove-sauce-labs-backpack");
    private By iconeCarrinho = By.cssSelector(".shopping_cart_badge");
    private By tituloPagina = By.cssSelector(".title");

    public ProdutosPage() {
        super();
    }

    public void aguardarTelaProdutosCarregar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        String urlAtual = driver.getCurrentUrl();
        String urlEsperada = YamlUtils.getValorAmbiente("ambientes.produto");

        if (!urlAtual.equals(urlEsperada)) {
            navegar(urlEsperada);
            wait.until(ExpectedConditions.urlToBe(urlEsperada));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.localStorage.setItem('session-username', 'standard_user');");
            driver.navigate().refresh();
        }

        try {
            driver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException ignored) {}

        wait.until(ExpectedConditions.visibilityOfElementLocated(filtro));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tituloProduto));
    }

    public List<String> getListaDeProdutos() {
        List<WebElement> produtos = driver.findElements(tituloProduto);
        return produtos.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getTituloPagina() {
        return capturarTexto(tituloPagina);
    }

    public void selecionarOrdenacao(String criterio) {
        aguardarTelaProdutosCarregar();
        Select select = new Select(driver.findElement(filtro));
        select.selectByValue(criterio);
    }

    public List<Double> getListaDePrecos() {
        List<WebElement> precos = driver.findElements(By.cssSelector(".inventory_item_price"));
        return precos.stream()
                .map(WebElement::getText)
                .map(p -> Double.parseDouble(p.replace("$", "")))
                .collect(Collectors.toList());
    }

    public boolean ordenadoAlfabeticoAZ() {
        List<String> produtos = getListaDeProdutos();
        List<String> ordenados = new ArrayList<>(produtos);
        Collections.sort(ordenados);
        return produtos.equals(ordenados);
    }

    public boolean ordenadoAlfabeticoZA() {
        List<String> produtos = getListaDeProdutos();
        List<String> ordenados = new ArrayList<>(produtos);
        Collections.sort(ordenados, Collections.reverseOrder());
        return produtos.equals(ordenados);
    }

    public boolean ordenadoPorPrecoCrescente() {
        List<Double> precos = getListaDePrecos();
        List<Double> ordenados = new ArrayList<>(precos);
        Collections.sort(ordenados);
        return precos.equals(ordenados);
    }

    public boolean ordenadoPorPrecoDecrescente() {
        List<Double> precos = getListaDePrecos();
        List<Double> ordenados = new ArrayList<>(precos);
        Collections.sort(ordenados, Collections.reverseOrder());
        return precos.equals(ordenados);
    }

    public void adicionarPrimeiroProdutoAoCarrinho() {
        aguardarTelaProdutosCarregar();
        List<WebElement> botoes = driver.findElements(adicionarAoCarrinho);
        if (!botoes.isEmpty()) {
            botoes.get(0).click();
        }
    }

    public void removerProdutoAoCarrinho() {
        aguardarTelaProdutosCarregar();
        List<WebElement> botoes = driver.findElements(removerDoCarrinho);
        if (!botoes.isEmpty()) {
            botoes.get(0).click();
        }
    }

    //Valida de quando adicionar o produto, deve ser refletido no icone e se for vazio deverá retornar um valor string igual a 0
    public String getQuantidadeCarrinho() {
        aguardarTelaProdutosCarregar();
        List<WebElement> badge = driver.findElements(iconeCarrinho);
        return badge.isEmpty() ? "0" : badge.get(0).getText();
    }

    public void adicionarProdutoParaCompra() {
        aguardarTelaProdutosCarregar();
        List<WebElement> botoes = driver.findElements(adicionarAoCarrinho);
        if (!botoes.isEmpty()) {
            botoes.get(0).click();
            clicar(iconeCarrinho);
        }
    }
}