package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.*;
import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(3));
    }

    //Método de navegação
    protected void navegar(String url) {
        driver.get(url);
    }

    //Método para aguardar a tela carregar
    protected void aguardarTelaCarregar(String chaveAmbiente, By elementoChave) {
        String urlEsperada = YamlUtils.getValorAmbiente("ambientes." + chaveAmbiente);
        String urlAtual = driver.getCurrentUrl();

        if (!urlAtual.equals(urlEsperada)) {
            navegar(urlEsperada);
            wait.until(ExpectedConditions.urlToBe(urlEsperada));
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementoChave));
    }

    //Métodos de clique de ícones/botões
    protected void clicar(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            WebElement elemento = wait.until(ExpectedConditions.elementToBeClickable(locator));
            elemento.click();
        } catch (Exception e) {
            System.err.println("Não foi possível clicar no elemento: " + locator);
            throw e;
        }
    }

    protected void clicarPorTexto(String textoDoBotao) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            By locator = By.xpath("//*[normalize-space(text())='" + textoDoBotao + "' or @value='" + textoDoBotao + "']");
            WebElement elemento = wait.until(ExpectedConditions.elementToBeClickable(locator));
            elemento.click();
        } catch (Exception e) {
            System.err.println("Não foi possível clicar no botão com texto: " + textoDoBotao);
            throw e;
        }
    }

    //Métodos de visibilidade, preenchimentos e captura de textos
    protected void preencherTexto(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    protected String capturarTexto(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    protected boolean elementoVisivel(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}