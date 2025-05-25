# cafeteria-ls20241-web
# Projeto Cafeteria Web

Este é um projeto simples de uma cafeteria feita com HTML, CSS, JavaScript e Bootstrap. O objetivo principal deste projeto é criar uma interface interativa para exibir diferentes tipos de café, com funcionalidades como a exibição de cards de produtos, armazenamento local dos mesmos no navegador, e interações dinâmicas com o usuário.

## Funcionalidades

- **Exibição de Produtos em Cards**: Cada produto (como Café Expresso, Prensa Francesa, etc.) é exibido em um card com uma imagem, título, descrição e um botão de "Comprar".
- **Armazenamento Local**: Os dados dos produtos são armazenados no `localStorage`, permitindo que eles sejam persistidos mesmo após o recarregamento da página.
- **Interatividade**: 
  - Ao clicar em um botão, a página interage e exibe mensagens no console.
  - O mouse sobre os botões gera mensagens dinâmicas, indicando interação.
  - O relógio digital na página pode ser iniciado e pausado com botões de controle.
- **Adição de Produtos**: Através de um formulário, novos produtos podem ser adicionados à lista, atualizando os cards e a tabela de produtos.
- **Mensagem Personalizada**: Quando o usuário sai da página, uma mensagem de alerta é exibida. Quando retorna, uma mensagem de boas-vindas aparece.

## Tecnologias Usadas

- **HTML**: Estruturação da página.
- **CSS**: Estilização dos elementos.
- **Bootstrap**: Framework para criação de layouts responsivos.
- **JavaScript**: Interatividade e manipulação de DOM.
- **localStorage**: Armazenamento persistente de dados.

## Como Funciona
Página Inicial:

- Ao carregar a página, os produtos armazenados no localStorage são exibidos como cards.

- O usuário pode adicionar novos produtos por meio de um formulário que irá atualizar os cards e o armazenamento local.

- Relógio e Interação de Mouse:

- O relógio digital na parte superior da página pode ser iniciado e pausado.

- Há interações com o mouse sobre os botões, que exibem mensagens no console e mudanças no texto da página.

- Persistência de Dados:

- Os produtos são armazenados no localStorage, o que significa que eles permanecem acessíveis entre as sessões do navegador. faça o codigo dessa parte

