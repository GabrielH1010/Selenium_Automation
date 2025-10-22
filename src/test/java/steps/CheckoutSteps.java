package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.WebDriver;
import pages.CarrinhoPage;
import pages.ProdutosPage;
import utils.DriverFactory;

public class CheckoutSteps {

    private WebDriver driver;
    private ProdutosPage produtosPage;
    private CarrinhoPage carrinhoPage;

    public CheckoutSteps() {
        this.driver = DriverFactory.getDriver();
        this.produtosPage = new ProdutosPage();
        this.carrinhoPage = new CarrinhoPage();
    }

    //CT001 - Campos obrigatórios não preenchidos
    @Dado("que o usuário esteja na tela de checkout")
    public void queOUsuarioEstejaNaTelaDeCheckout() {
    }

    @Quando("clicar no botão {string} sem preencher os campos")
    public void clicarNoBotaoSemPreencherOsCampos(String arg0) {
    }

    @Entao("a mensagem de erro {string} deve ser exibida")
    public void aMensagemDeErroDeveSerExibida(String arg0) {
    }

    //CT002 - Preencher apenas o campo First Name
    @Quando("preencher apenas o campo {string} e clicar no botão {string}")
    public void preencherApenasOCampoEClicarNoBotao(String arg0, String arg1) {
    }

    //CT003 - Visualizar resumo da compra antes de finalizar
    @Dado("que o usuário tenha preenchido corretamente os dados de checkout")
    public void queOUsuarioTenhaPreenchidoCorretamenteOsDadosDeCheckout() {
    }

    @Quando("clicar em {string}")
    public void clicarEm(String arg0) {
    }

    @Entao("o resumo da compra deve exibir os produtos adicionados com nome e valores")
    public void oResumoDaCompraDeveExibirOsProdutosAdicionadosComNomeEValores() {
    }

    //CT004 - Finalizar compra com sucesso
    @Dado("que o usuário esteja na tela de resumo do produto")
    public void queOUsuarioEstejaNaTelaDeResumoDoProduto() {
    }

    @Entao("a mensagem {string} deve ser exibida")
    public void aMensagemDeveSerExibida(String arg0) {
    }
}