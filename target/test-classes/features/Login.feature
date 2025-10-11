#language: pt
Funcionalidade: Login no Swag Labs

  Cenário: Login com senha inválida
    Dado que o usuário está na página de login
    Quando ele preenche a senha inválida
    Então deve ser apresentado a mensagem de erro na tela

  Cenário: Login com sucesso
    Dado que o usuário está na página de login do Swag Labs
    Quando ele preenche o usuário e senha válidos
    Então ele deve ser redirecionado para a tela de produtos