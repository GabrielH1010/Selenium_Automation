package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import utils.YamlUtils;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By erroMensagem = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acessarPagina() {
        driver.get(YamlUtils.getValorAmbiente("ambientes.login"));
    }

    public void preencherUsuario(String usuario) {
        driver.findElement(usernameField).sendKeys(usuario);
    }

    public void preencherSenha(String senha) {
        driver.findElement(passwordField).sendKeys(senha);
    }

    public boolean validaMensagemAparece() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(erroMensagem));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String mensagemErro() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(erroMensagem));
        return element.getText();
    }

    public void clicarLogin() {
        driver.findElement(loginButton).click();
    }
}