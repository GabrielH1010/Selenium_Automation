package steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import pages.*;

public class CarrinhoSteps {

    private WebDriver driver;
    private CarrinhoPage carrinhoPage;
    private ProdutosPage produtosPage;

    public CarrinhoSteps() {
        this.driver = DriverFactory.getDriver();
        this.produtosPage = new ProdutosPage();
        this.carrinhoPage = new CarrinhoPage();
    }

    //CT001 - Exibir produtos adicionados ao carrinho
    @Dado("que o usuário tenha adicionado produtos ao carrinho")
    public void queOUsuarioTenhaAdicionadoProdutosAoCarrinho() {
        produtosPage.adicionarProdutoParaCompra();
    }

    @Quando("acessar a tela Your Cart")
    public void acessarATelaYourCart() {
        carrinhoPage.aguardarTelaCarrinhoCarregar();
        Assert.assertEquals("Your Cart", carrinhoPage.tituloPagina());
    }

    @Entao("os produtos adicionados devem ser exibidos com o botão {string} deve estar visível")
    public void osProdutosAdicionadosDevemSerExibidosComOBotaoDeveEstarVisivel(String botaoCheckout) {
        Assert.assertTrue("Botão não está visível!", carrinhoPage.botaoEstaVisivel(botaoCheckout));
    }

    //CT002 - Remover item do carrinho
    @Dado("que o usuário esteja na tela {string} com produtos adicionados")
    public void queOUsuarioEstejaNaTelaComProdutosAdicionados(String nomeTela) {
        carrinhoPage.aguardarTelaCarrinhoCarregar();
        Assert.assertEquals(nomeTela, carrinhoPage.tituloPagina());
    }

    @Quando("clicar no botão {string} de um produto")
    public void clicarNoBotaoDeUmProduto(String removerItem) {
        Assert.assertTrue("Botão não está visível!", carrinhoPage.botaoEstaVisivel(removerItem));
        carrinhoPage.botaoRemove();
    }

    @Entao("o produto deve ser removido da lista do carrinho")
    public void oProdutoDeveSerRemovidoDaListaDoCarrinho() {
        Assert.assertTrue("O produto ainda está listado no carrinho!", carrinhoPage.carrinhoEstaVazio());
    }

    //CT003 - Voltar para a loja
    @Dado("que o usuário esteja na tela {string}")
    public void queOUsuarioEstejaNaTela(String nomeTela) {
        carrinhoPage.aguardarTelaCarrinhoCarregar();
        Assert.assertEquals(nomeTela, carrinhoPage.tituloPagina());
    }

    @Quando("clicar no botão Continue Shopping")
    public void clicarNoBotao() {
        Assert.assertTrue("Botão não está visível!", carrinhoPage.botaoEstaVisivel("Continue Shopping"));
        carrinhoPage.botaoContinueShopping();
    }

    @Entao("o usuário deve ser redirecionado para a tela de {string}")
    public void oUsuarioDeveSerRedirecionadoParaATelaDe(String nomeTela) {
        produtosPage.aguardarTelaProdutosCarregar();
        produtosPage.getListaDeProdutos();
        Assert.assertEquals(nomeTela, produtosPage.getTituloPagina());
    }

    //CT004 - Verificar detalhes do produto no carrinho
    @Dado("que o usuário tenha adicionado o produto ao carrinho")
    public void queOUsuarioTenhaAdicionadoOProdutoAoCarrinho() {
        produtosPage.adicionarProdutoParaCompra();
    }

    @Quando("acessar a tela de carrinho")
    public void acessarATelaDeCarrinho() {
        carrinhoPage.aguardarTelaCarrinhoCarregar();
    }

    @Entao("o produto deve exibir nome, descrição e preço corretamente")
    public void oProdutoDeveExibirNomeDescricaoEPrecoCorretamente() {
        carrinhoPage.verificaDadosProduto();
    }
}