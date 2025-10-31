package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class CarrinhoPage extends BasePage {

    private By tituloPagina = By.cssSelector(".title");
    private By listaCarrinho = By.cssSelector(".cart_list");
    private By item = By.className("cart_item");
    private By tituloProduto = By.cssSelector(".inventory_item_name");
    private By descricaoProduto = By.cssSelector(".inventory_item_desc");
    private By precoProduto = By.cssSelector(".inventory_item_price");

    public CarrinhoPage() {
        super();
    }

    public String tituloPagina() {
        return capturarTexto(tituloPagina);
    }

    public void aguardarTelaCarrinhoCarregar() {
        aguardarTelaCarregar("carrinho", listaCarrinho);
    }

    public void clicarBotao(String texto){
        clicarPorTexto(texto);
    }

    public boolean botaoEstaVisivel(String textoBotao) {
        By botao = By.xpath("//button[text()='" + textoBotao + "']");
        return elementoVisivel(botao);
    }

    public boolean carrinhoEstaVazio() {
        aguardarTelaCarrinhoCarregar();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        try {
            // Aguarda até não existirem mais itens visíveis no carrinho
            boolean vazio = wait.until(driver -> {
                List<WebElement> produtos = driver.findElements(item);
                return produtos.isEmpty();
            });

            System.out.println("Carrinho está vazio");
            return vazio;
        } catch (TimeoutException e) {
            System.out.println("Tempo limite atingido — ainda existem itens no carrinho.");
            return false;
        }
    }

    public void verificaDadosProduto() {
        List<WebElement> produtos = driver.findElements(item);
        if (!produtos.isEmpty()) {
            WebElement produto = produtos.get(0);

            String nome = produto.findElement(tituloProduto).getText();
            String descricao = produto.findElement(descricaoProduto).getText();
            String preco = produto.findElement(precoProduto).getText();

            // Valores esperados do produto adicionado no carrinho
            String nomeEsperado = "Sauce Labs Backpack";
            String descricaoEsperada = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
            String precoEsperado = "$29.99";

            Assert.assertEquals(nomeEsperado, nome);
            Assert.assertEquals(descricaoEsperada, descricao);
            Assert.assertEquals(precoEsperado, preco);

        } else {
            Assert.fail("Produto não está na lista do carrinho!");
        }
    }
}