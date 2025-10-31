package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import java.util.*;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            //Configuração do perfil
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("profile.exit_type", "Normal");
            prefs.put("profile.exited_cleanly", true);
            options.setExperimentalOption("prefs", prefs);

            //Configuração das notificações/popup
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-password-generation");
            options.addArguments("--disable-translate");
            options.addArguments("--disable-features=PasswordCheck,PasswordManagerOnboarding,AutofillKeyReleaseHandling");
            options.addArguments("--start-maximized");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--guest");

            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}