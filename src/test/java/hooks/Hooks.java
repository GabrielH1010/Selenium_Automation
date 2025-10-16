package hooks;

import io.cucumber.java.*;
import utils.DriverFactory;

public class Hooks {
    DriverFactory DriverFactory;

    // Garante que o driver está ativo antes de cada cenário
    @Before
    public void beforeScenario() {
        DriverFactory.getDriver();
    }

    // Fecha apenas ao final de cada cenário
    @After
    public void afterScenario() {
        DriverFactory.quitDriver();
    }
}