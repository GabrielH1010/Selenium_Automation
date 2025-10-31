package steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.E;
import pages.*;
import utils.DriverFactory;

public class CheckoutSteps {

    private WebDriver driver;
    private ProdutosPage produtosPage;
    private CarrinhoPage carrinhoPage;
    private CheckoutPage checkoutPage;

    public CheckoutSteps() {
        this.driver = DriverFactory.getDriver();
        this.produtosPage = new ProdutosPage();
        this.carrinhoPage = new CarrinhoPage();
        this.checkoutPage = new CheckoutPage();
    }

    //Contexto
    @Dado("que o usuário esteja na tela de checkout")
    public void queOUsuarioEstejaNaTelaDeCheckout() {
        if (carrinhoPage.carrinhoEstaVazio()) {
            System.out.println("[INFO] Carrinho vazio. Adicionando produto para o teste...");
            produtosPage.adicionarProdutoParaCompra();
            carrinhoPage.aguardarTelaCarrinhoCarregar();
        }
        checkoutPage.clicarBotao("Checkout");
        checkoutPage.aguardarTelaCheckoutSuasInformacoes();
    }

    // CT001 - Campos obrigatórios não preenchidos
    @Quando("clicar no botão {string} sem preencher nenhum campo")
    public void clicarNoBotaoSemPreencherOsCampos(String botaoContinue) {
        checkoutPage.clicarBotao(botaoContinue);
    }

    @Entao("a mensagem de erro {string} deve ser exibida")
    public void aMensagemDeErroDeveSerExibida(String mensagemEsperada) {
        Assert.assertEquals(mensagemEsperada, checkoutPage.capturarMensagemErro());
    }

    // CT002 - Preencher apenas o campo First Name
    @Quando("preencher apenas o campo First Name com {string}")
    public void preencherApenasOCampo(String primeiroNome) {
        checkoutPage.preencherCampos(primeiroNome, "", "");
    }

    // CT003 - Visualizar resumo da compra antes de finalizar
    @Quando("preencher com os dados {string}, {string} e {string}")
    public void preencherComOsDadosE(String nome, String sobreNome, String codPostal) {
        checkoutPage.preencherCampos(nome, sobreNome, codPostal);
    }

    @E("clicar em {string}")
    public void clicarEm(String botaoContinue) {
        checkoutPage.clicarBotao(botaoContinue);
    }

    @Entao("o resumo da compra deve exibir os produtos adicionados com nome e valores")
    public void oResumoDaCompraDeveExibirOsProdutosAdicionadosComNomeEValores() {
        Assert.assertTrue("Resumo da compra não exibe os produtos corretamente.",
                checkoutPage.resumoCompraExibeProdutos());
    }

    // CT004 - Finalizar compra com sucesso
    @Entao("a mensagem {string} deve ser exibida")
    public void aMensagemDeveSerExibida(String mensagemSucesso) {
        Assert.assertEquals(mensagemSucesso, checkoutPage.capturarMensagemSucesso());
    }

    @E("o botão {string} deve ser apresentado")
    public void oBotaoDeveSerApresentado(String botaoBackToHome) {
        Assert.assertTrue(checkoutPage.botaoBackToHomeVisivel());
    }
}