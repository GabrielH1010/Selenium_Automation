# 🧪 Projeto de Automação Web – Swag Labs

Automação de testes end-to-end desenvolvida em **Java** utilizando **Selenium WebDriver**, **Cucumber (BDD)** e **JUnit**, com arquitetura limpa, reutilizável e voltada à escalabilidade.

[Site Swag Labs](https://www.saucedemo.com/)

---

## 🚀 Tecnologias Utilizadas

| Categoria | Ferramenta |
|------------|------------|
| Linguagem | Java 17+ |
| Automação Web | Selenium WebDriver |
| Framework de Testes | JUnit |
| BDD | Cucumber |
| Gerenciador de Dependências | Maven |
| Design Pattern | Page Object Model (POM) |
| Relatórios | Cucumber HTML Report |
| Gerenciamento de Driver | WebDriverManager |

---

## ⚙️ Configuração do Projeto

Clone o repositório:

```text
git clone https://github.com/GabrielH1010/Selenium_Automation.git
```

Instale as dependências:

```text
mvn install
```

Execute os testes localmente:

```text
mvn test
```
---

## 🧱 Estrutura do Projeto

```text
API_Automation/
├── .github/
│   └── workflows/
│       └── ci.yml          
├── src/                
│   └── test/
│       ├── java/
│       │   ├── hooks/
│       │   │   └── Hooks.java
│       │   ├── pages/
│       │   │   └── BasePage.java
│       │   │   └── LoginPage.java      
│       │   │   └── ProdutosPage.java      
│       │   │   └── CarrinhoPage.java      
│       │   │   └── CheckoutPage.java      
│       │   ├── steps/
│       │   │   └── LoginSteps.java
│       │   │   └── ProdutosSteps.java
│       │   │   └── CarrinhoSteps.java
│       │   │   └── CheckoutSteps.java
│       │   └── runners/
│       │       └── RunCucumberTest.java
│       │   └── utils/
│       │       └── DriverFactory.java
│       │       └── YamlUtils.java
│       └── resources/
│           ├── features/            
│           │   └── Regressivos.feature
│           └── fixtures/            
│               ├── criarReserva.json
│               ├── alterarReserva.json
│               └── gerarToken.json
├── pom.xml                         
└── README.md
```

## 📂 Descrição das Pastas
- `hooks/` → **Hooks.java**: Configurações executadas antes e depois dos cenários, como por exemplo o login inicial e o fechamento do driver.
- `pages/` → Classes de Page Object. Cada classe representa uma tela do sistema e contém os elementos e ações possíveis nessa página.
- `pages/` → **BasePage.java**: Classe abstrata base que contém os métodos genéricos para interação com elementos (ex: clicar, preencherTexto, capturarTexto, etc.)
- `steps/` → Implementa as definições dos passos **(@Dado, @Quando, @Entao)** usados nos cenários BDD..
- `runners/` → executável do Cucumber (`RunCucumberTest.java`) com `@CucumberOptions`.
- `utils/` → **DriverFactory.java**: Gerencia a criação e finalização do WebDriver.
- `utils/` → **YamlUtils.java**: esponsável por ler e interpretar o arquivo env.yaml ou qualquer outro YAML do projeto.
- `resources/features/` → cenários de testes em Gherkin `.feature`.
- `.github/workflows/` → workflow(s) do GitHub Actions (`ci.yml`).
- `pom.xml` → dependências (Cucumber, Selenium, JUnit, etc.)

---

## 🧠 Boas Práticas Implementadas

- ✅ Reutilização máxima de código via Page Object
- ✅ Métodos genéricos para interação com elementos (clicarPorTexto, elementoVisivelPorTexto)
- ✅ Separação clara entre lógica de teste e lógica de automação
- ✅ Estrutura modular e escalável
- ✅ Padronização de asserts e mensagens de erro
- ✅ Fácil integração com pipelines CI/CD

---

## 🔁 Integração Contínua (CI)
O projeto está configurado com GitHub Actions para rodar automaticamente a cada commit no branch principal.
Confira as execuções recentes no link abaixo:

[Acessar workflow](https://github.com/GabrielH1010/Selenium_Automation/actions/runs/18982704297)

---

## 📊 Relatórios de Execução

Após a execução dos testes, um relatório HTML é gerado automaticamente na pasta target/, contendo o resumo dos cenários, steps e resultados.

📸 Exemplo de saída do report:

<img width="1491" height="907" alt="image" src="https://github.com/user-attachments/assets/59bb58fa-730e-4ffe-b706-ccc6f1e0cde3" />

---

## 💬 Considerações Finais

Este projeto foi desenvolvido com o objetivo de demonstrar domínio prático em automação de testes Web utilizando Selenium, aplicando os princípios de BDD (Behavior Driven Development) e integrando boas práticas de CI/CD.

A estrutura foi planejada com base em boas práticas de engenharia de testes, priorizando a reutilização de código, organização e legibilidade dos cenários, clareza e manutenção facilitado. O objetivo é evidenciar uma automação profissional, escalável e sustentável, alinhada aos padrões modernos de qualidade de software.

---

## 👨‍💻 Autor

<p>Gabriel Henrique de Oliveira</p>
<p>Quality Assurance (QA) | QA Engineer | Cypress | RestAssured | Selenium | Testes Manuais & Automatizados</p>

- [📧 Gmail](gabrielhdeoliveira17@gmail.com)
- [💼 LinkedIn](https://www.linkedin.com/in/gabriel-henrique-de-oliveira)
- [🐙 GitHub](https://github.com/GabrielH1010)

---
