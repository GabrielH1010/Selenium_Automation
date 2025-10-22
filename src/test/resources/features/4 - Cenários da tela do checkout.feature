#language: pt
Funcionalidade: Tela do checkout

    Cenário: CT001 - Campos obrigatórios não preenchidos
        Dado que o usuário esteja na tela de checkout
        Quando clicar no botão "Continue" sem preencher os campos
        Entao a mensagem de erro "Error: First Name is required" deve ser exibida

    Cenário: CT002 - Preencher apenas o campo First Name
        Dado que o usuário esteja na tela de checkout
        Quando preencher apenas o campo "First Name" e clicar no botão "Continue"
        Entao a mensagem de erro "Error: Last Name is required" deve ser exibida

    Cenário: CT003 - Visualizar resumo da compra antes de finalizar
        Dado que o usuário tenha preenchido corretamente os dados de checkout
        Quando clicar em "Continue"
        Entao o resumo da compra deve exibir os produtos adicionados com nome e valores

    Cenário: CT004 - Finalizar compra com sucesso
        Dado que o usuário esteja na tela de resumo do produto
        Quando clicar no botão "Finish"
        Entao a mensagem "Thank you for your order!" deve ser exibida