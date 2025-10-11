package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    private WebDriver driver;
    private By titulo = By.className("title");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitulo() {
        return driver.findElement(titulo).getText();
    }
}
