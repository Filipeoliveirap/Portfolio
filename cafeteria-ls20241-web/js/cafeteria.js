//Array
let itens = [
  {
    titulo: 'Café Expresso',
    descricao:
      'Café expresso feito com grãos selecionados com máquina profissional.',
    alt: 'Café Expresso',
    imagemUrl:
      'https://images.unsplash.com/photo-1495774856032-8b90bbb32b32?q=80&w=1740&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  },
  {
    titulo: 'Prensa Francesa',
    descricao: 'Sabor suave do café selecionado.',
    alt: 'Prensa Francesa',
    imagemUrl:
      'https://images.unsplash.com/photo-1444594975920-e69885b357d5?q=80&w=1712&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  },
  {
    titulo: 'Café Filtrado',
    descricao: 'Café filtrado v60 com todas as notas sensoriais.',
    alt: 'Café Filtrado',
    imagemUrl:
      'https://images.unsplash.com/photo-1498603536246-15572faa67a6?q=80&w=2000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  },
  {
    titulo: 'Café Verde',
    descricao: 'Café filtrado v60 com todas as notas sensoriais.',
    alt: 'Café Filtrado',
    imagemUrl:
      'https://plus.unsplash.com/premium_photo-1674327105074-46dd8319164b?q=80&w=1740&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  },
];

// Armazena os itens no localStorage se ainda não existirem
if (!localStorage.getItem('produtos')) {
  localStorage.setItem('produtos', JSON.stringify(itens));
}

// Função para criar os cards na página inicial
const createCards = () => {
  let produtos = JSON.parse(localStorage.getItem('produtos')); // Obtém os produtos do localStorage
  for (let produto of produtos) {
    let card = createCardItem(produto); // Cria o HTML para cada produto
    let cardsDiv = document.getElementById('cards'); // Localiza a div onde os cards serão inseridos
    if (cardsDiv) {
      cardsDiv.insertAdjacentHTML('beforeend', card); // Insere o card na página
    }
  }
};

// Função para criar um item de card
const createCardItem = (item) => {
  return `<div class="col m-2">
      <div class="card">
        <img src="${item.imagemUrl}" class="card-img-top" alt="${item.alt}">
        <div class="card-body">
          <h5 class="card-title">${item.titulo}</h5>
          <p class="card-text">${item.descricao}</p>
          <a href="#" class="btn btn-primary">Comprar</a>
        </div>
      </div>
    </div>`;
};

// Função para criar linhas da tabela na segunda página
const createTableRows = () => {
  const tableBody = document.getElementById('TableItens'); // Localiza o corpo da tabela onde os itens serão inseridos
  let produtos = JSON.parse(localStorage.getItem('produtos')); // Obtém os produtos do localStorage
  if (tableBody) {
    produtos.forEach((item, index) => {
      const row = `
        <tr>
          <th scope="row">${index + 1}</th>
          <td>${item.titulo}</td>
          <td>${item.descricao}</td>
          <td>${item.alt}</td>
          <td><img src="${item.imagemUrl}" alt="${item.alt}" width="150"></td>
        </tr>
      `;
      tableBody.insertAdjacentHTML('beforeend', row); // Insere a linha correspondente ao produto na tabela
    });
  }
};

// Função para adicionar itens (atualmente vazia)
const additens = () => {};

// Chama a função para criar os cards ao carregar a página inicial
createCards();

// Chama a função para preencher a tabela ao carregar a segunda página
if (document.getElementById('TableItens')) {
  createTableRows();
}

// Função para adicionar itens à lista e atualizar a tabela
const addItens = (event) => {
  event.preventDefault(); // Previne o comportamento padrão do formulário (recarregar a página)

  // Obtém os valores dos campos do formulário
  const titulo = document.getElementById('titulo').value;
  const descricao = document.getElementById('descricao').value;
  const alt = document.getElementById('alt').value;
  const imagemUrl = document.getElementById('imagemUrl').value;

  // Cria um novo item
  const novoItem = {
    titulo,
    descricao,
    alt,
    imagemUrl,
  };

  // Adiciona o novo item ao array e atualiza o localStorage
  let produtos = JSON.parse(localStorage.getItem('produtos')); // Recupera os itens armazenados
  produtos.push(novoItem); // Adiciona o novo item à lista
  localStorage.setItem('produtos', JSON.stringify(produtos)); // Atualiza o localStorage com o novo item

  // Atualiza as exibições (caso necessário)
  createCards(); // Atualiza a exibição dos cards (caso essa função seja chamada)
  creatItensTable(); // Atualiza a exibição da tabela (deve-se assegurar que essa função exista e funcione)

  // Limpa o formulário
  document.getElementById('formulario').reset(); // Reseta os campos do formulário após a submissão
};

// Adiciona o evento de submit ao formulário
const form = document.getElementById('formulario');
if (form) {
  form.addEventListener('submit', addItens); // Associa a função `addItens` ao evento de submissão do formulário
}
