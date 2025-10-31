#language: pt
Funcionalidade: Tela do checkout

    Contexto:
        Dado que o usuário esteja na tela de checkout

    Cenário: CT001 - Campos obrigatórios não preenchidos
        Quando clicar no botão "Continue" sem preencher nenhum campo
        Entao a mensagem de erro "Error: First Name is required" deve ser exibida

    Cenário: CT002 - Preencher apenas o campo First Name
        Quando preencher apenas o campo First Name com "Gabriel"
        E clicar em "Continue"
        Entao a mensagem de erro "Error: Last Name is required" deve ser exibida

    Cenário: CT003 - Visualizar resumo da compra antes de finalizar
        Quando preencher com os dados "Gabriel", "Henrique" e "12345-7"
        E clicar em "Continue"
        Entao o resumo da compra deve exibir os produtos adicionados com nome e valores

    Cenário: CT004 - Finalizar compra com sucesso
        Quando preencher com os dados "Gabriel", "Henrique" e "12345-7"
        E clicar em "Continue"
        E clicar em "Finish"
        Entao a mensagem "Thank you for your order!" deve ser exibida
        E o botão "Back to home" deve ser apresentado