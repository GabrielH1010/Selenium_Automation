package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.E;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
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

    private void garantirProdutoNoCarrinho() {
        if (carrinhoPage.carrinhoEstaVazio()) {
            System.out.println("[INFO] Carrinho vazio. Adicionando produto para o teste...");
            produtosPage.adicionarProdutoParaCompra();
            carrinhoPage.aguardarTelaCarrinhoCarregar();
        }
        checkoutPage.botaoCheckout();
        checkoutPage.aguardarTelaCheckoutSuasInformacoes();
    }

    // CT001 - Campos obrigatórios não preenchidos
    @Dado("que o usuário esteja na tela de checkout")
    public void queOUsuarioEstejaNaTelaDeCheckout() {
        garantirProdutoNoCarrinho();
    }

    @Quando("clicar no botão {string} sem preencher nenhum campo")
    public void clicarNoBotaoSemPreencherOsCampos(String botaoCheckout) {
        checkoutPage.botaoContinue();
    }

    @Entao("a mensagem de erro {string} deve ser exibida")
    public void aMensagemDeErroDeveSerExibida(String mensagemEsperada) {
        Assert.assertEquals(mensagemEsperada, checkoutPage.capturarMensagemErro());
    }

    // CT002 - Preencher apenas o campo First Name
    @Quando("preencher apenas o campo {string} e clicar no botão {string}")
    public void preencherApenasOCampoEClicarNoBotao(String campo, String botao) {
        checkoutPage.preencherCampo(campo, "Gabriel");
        checkoutPage.botaoContinue();
    }

    // CT003 - Visualizar resumo da compra antes de finalizar
    @Dado("que o usuário tenha preenchido corretamente os dados de checkout")
    public void queOUsuarioTenhaPreenchidoCorretamenteOsDadosDeCheckout() {
        garantirProdutoNoCarrinho();
        checkoutPage.aguardarTelaCheckoutSuasInformacoes();
        checkoutPage.preencherCamposCorretamente();
    }

    @Quando("clicar em {string}")
    public void clicarEm(String botao) {
        checkoutPage.botaoContinue();
    }

    @Entao("o resumo da compra deve exibir os produtos adicionados com nome e valores")
    public void oResumoDaCompraDeveExibirOsProdutosAdicionadosComNomeEValores() {
        Assert.assertTrue("Resumo da compra não exibe os produtos corretamente.",
                checkoutPage.resumoCompraExibeProdutos());
    }

    // CT004 - Finalizar compra com sucesso
    @Dado("que o usuário esteja na tela de resumo do produto")
    public void queOUsuarioEstejaNaTelaDeResumoDoProduto() {
        garantirProdutoNoCarrinho();
        checkoutPage.aguardarTelaCheckoutSuasInformacoes();
        checkoutPage.preencherCamposCorretamente();
        checkoutPage.botaoContinue();
    }

    @Quando("clicar no botão {string}")
    public void clicarNoBotao(String arg0) {
        checkoutPage.clicarFinish();
    }

    @Entao("a mensagem {string} deve ser exibida")
    public void aMensagemDeveSerExibida(String mensagemSucesso) {
        Assert.assertEquals(mensagemSucesso, checkoutPage.capturarMensagemSucesso());
    }

    @E("o botão {string} deve ser apresentado")
    public void oBotaoDeveSerApresentado(String botaoBackToHome) {
        Assert.assertTrue(checkoutPage.botaoBackToHomeVisivel());
    }
}
