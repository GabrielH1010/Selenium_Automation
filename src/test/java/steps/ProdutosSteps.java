package steps;

import io.cucumber.java.pt.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.ProdutosPage;
import utils.DriverFactory;

public class ProdutosSteps {

    private WebDriver driver;
    private ProdutosPage produtosPage;

    public ProdutosSteps() {
        this.driver = DriverFactory.getDriver();
        this.produtosPage = new ProdutosPage();
    }

    @Dado("que o usuário está logado")
    public void queOUsuarioEstaLogado() {
        produtosPage.aguardarTelaProdutosCarregar();
        Assert.assertEquals("Products", produtosPage.getTituloPagina());
    }

    //CT001 - Ordenar produtos de A a Z
    @Quando("o usuário ordenar os produtos de A a Z")
    public void ordenarProdutosAZ() {
        produtosPage.selecionarOrdenacao("az");
    }

    @Entao("os produtos devem estar em ordem alfabética crescente")
    public void validarOrdemAlfabeticaAZ() {
        Assert.assertTrue("Os produtos não estão ordenados de A-Z!", produtosPage.ordenadoAlfabeticoAZ());
    }

    //CT002 - Ordenar produtos de Z a A
    @Quando("o usuário ordenar os produtos de Z a A")
    public void ordenarProdutosZA() {
        produtosPage.selecionarOrdenacao("za");
    }

    @Entao("os produtos devem estar em ordem alfabética decrescente")
    public void validarOrdemAlfabeticaZA() {
        Assert.assertTrue("Os produtos não estão ordenados de Z-A!", produtosPage.ordenadoAlfabeticoZA());
    }

    //CT003 - Ordenar produtos por preço crescente
    @Quando("o usuário ordenar os produtos do menor para o maior preço")
    public void ordenarPorPrecoCrescente() {
        produtosPage.selecionarOrdenacao("lohi");
    }

    @Entao("os produtos devem estar em ordem crescente de preço")
    public void validarPrecoCrescente() {
        Assert.assertTrue("Os preços não estão em ordem crescente!", produtosPage.ordenadoPorPrecoCrescente());
    }

    //CT004 - Ordenar produtos por preço decrescente
    @Quando("o usuário ordenar os produtos do maior para o menor preço")
    public void ordenarPorPrecoDecrescente() {
        produtosPage.selecionarOrdenacao("hilo");
    }

    @Entao("os produtos devem estar em ordem decrescente de preço")
    public void validarPrecoDecrescente() {
        Assert.assertTrue("Os preços não estão em ordem decrescente!", produtosPage.ordenadoPorPrecoDecrescente());
    }

    //CT005 - Adicionar produto ao carrinho
    @Quando("o usuário adiciona um produto ao carrinho")
    public void adicionarProdutoAoCarrinho() {
        produtosPage.adicionarPrimeiroProdutoAoCarrinho();
    }

    @Entao("o ícone do carrinho deve mostrar {string} item")
    public void validarCarrinho(String quantidadeEsperada) {
        Assert.assertEquals("Quantidade incorreta no carrinho!",
                quantidadeEsperada, produtosPage.getQuantidadeCarrinho());
    }

    //CT006 - Remover produto ao carrinho
    @Quando("o usuário remove um produto ao carrinho")
    public void oUsuarioRemoveUmProdutoAoCarrinho() {
        produtosPage.removerProdutoAoCarrinho();
    }

    @Entao("o ícone do carrinho não deve conter mais quantidades")
    public void oIconeDoCarrinhoNaoDeveConterMaisQuantidades() {
        Assert.assertEquals("Quantidade incorreta no carrinho!", "0", produtosPage.getQuantidadeCarrinho());
    }
}