package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class ProdutosPage extends BasePage {

    private By productTitle = By.cssSelector(".inventory_item_name");
    private By sortDropdown = By.cssSelector(".product_sort_container");
    private By addToCartButton = By.cssSelector(".btn_inventory");
    private By cartBadge = By.cssSelector(".shopping_cart_badge");
    private By tituloPagina = By.cssSelector(".title");

    public ProdutosPage() {
        super();
    }

    public String getTitulo() {
        return driver.findElement(tituloPagina).getText();
    }

    public void aguardarTelaProdutosCarregar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.switchTo().alert().dismiss(); // Ignora popups, se existirem
        } catch (NoAlertPresentException ignored) {}

        wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));
        wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));
    }

    public List<String> getListaDeProdutos() {
        List<WebElement> produtos = driver.findElements(productTitle);
        return produtos.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void selecionarOrdenacao(String criterio) {
        aguardarTelaProdutosCarregar();
        Select select = new Select(driver.findElement(sortDropdown));
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
        List<WebElement> botoes = driver.findElements(addToCartButton);
        if (!botoes.isEmpty()) {
            botoes.get(0).click();
        }
    }

    public String getQuantidadeCarrinho() {
        aguardarTelaProdutosCarregar();
        return driver.findElement(cartBadge).getText();
    }
}