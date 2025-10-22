#language: pt
Funcionalidade: Tela do carrinho de compras

    Cenário: CT001 - Exibir produtos adicionados ao carrinho
        Dado que o usuário tenha adicionado produtos ao carrinho
        Quando acessar a tela "Your Cart"
        Entao os produtos adicionados devem ser exibidos com o botão "Checkout" deve estar visível

    Cenário: CT002 - Remover item do carrinho
        Dado que o usuário esteja na tela "Your Cart" com produtos adicionados
        Quando clicar no botão "Remove" de um produto
        Entao o produto deve ser removido da lista do carrinho

    Cenário: CT003 - Voltar para a loja
        Dado que o usuário esteja na tela "Your Cart"
        Quando clicar no botão "Continue Shopping"
        Entao o usuário deve ser redirecionado para a tela de "Products"

    Cenário: CT004 - Verificar detalhes do produto no carrinho
        Dado que o usuário tenha adicionado o produto ao carrinho
        Quando acessar a tela "Your Cart"
        Entao o produto deve exibir nome, descrição e preço corretamente