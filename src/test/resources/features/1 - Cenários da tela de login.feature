#language: pt
Funcionalidade: Login no Swag Labs

    Contexto:
        Dado que o usuário está na página de login

    Cenário: CT001 - Login com senha inválida
        Quando ele preenche a senha inválida
        E clicar no botão de "Login"
        Entao deve ser apresentado a mensagem de erro na tela

    Cenário: CT002 - Login com sucesso
        Quando ele preenche o usuário e senha válidos
        E clicar no botão de "Login"
        Entao ele deve ser redirecionado para a tela de produtos