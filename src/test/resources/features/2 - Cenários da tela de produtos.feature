#language: pt
Funcionalidade: Tela de Produtos

    Contexto:
        Dado que o usuário está logado

    Cenário: CT001 - Ordenar produtos de A a Z
        Quando o usuário ordenar os produtos de A a Z
        Entao os produtos devem estar em ordem alfabética crescente

    Cenário: CT002 - Ordenar produtos de Z a A
        Quando o usuário ordenar os produtos de Z a A
        Entao os produtos devem estar em ordem alfabética decrescente

    Cenário: CT003 - Ordenar produtos por preço crescente
        Quando o usuário ordenar os produtos do menor para o maior preço
        Entao os produtos devem estar em ordem crescente de preço

    Cenário: CT004 - Ordenar produtos por preço decrescente
        Quando o usuário ordenar os produtos do maior para o menor preço
        Entao os produtos devem estar em ordem decrescente de preço

    Cenário: CT005 - Adicionar produto ao carrinho
        Quando o usuário adiciona um produto ao carrinho
        Entao o ícone do carrinho deve mostrar "1" item

    Cenário: CT006 - Remover  produto ao carrinho
        Quando o usuário remove um produto ao carrinho
        Entao o ícone do carrinho não deve conter mais quantidades