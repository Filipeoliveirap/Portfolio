// Função chamada quando o botão é clicado
const incrementarOnclick = (event) => {
  console.log('Clicou no botão'); // Exibe uma mensagem no console indicando que o botão foi clicado
};

// Função chamada quando o mouse passa sobre o botão
const incrementarOnmouseover = (event) => {
  console.log('Passou o mouse no botão'); // Exibe uma mensagem no console indicando que o mouse passou sobre o botão
};

// Seleciona o elemento do botão com o ID 'incrementar'
let incrementarButton = document.getElementById('incrementar');

// Define a função incrementarOnclick para ser chamada quando o botão é clicado
incrementarButton.onclick = incrementarOnclick;

// Define a função incrementarOnmouseover para ser chamada quando o mouse passa sobre o botão
incrementarButton.onmouseover = incrementarOnmouseover;
