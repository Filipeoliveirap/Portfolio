const dataHora = () => {
  let horaP = document.getElementById('hora'); // Seleciona o elemento onde a hora será exibida
  let dataP = document.getElementById('data'); // Seleciona o elemento onde a data será exibida
  let dataHora = new Date(); // Cria um novo objeto de data com a data e hora atuais

  // Atualiza o conteúdo HTML com a hora e data formatadas
  horaP.innerHTML = dataHora.toLocaleTimeString(); // Formata e exibe a hora atual
  dataP.innerHTML = dataHora.toLocaleDateString(); // Formata e exibe a data atual
};

let iniciarButtton = document.getElementById('iniciar'); // Seleciona o botão de iniciar
let idInterval = 0; // Variável que armazenará o ID do intervalo do relógio

// Adiciona um evento ao botão "iniciar" que, quando clicado, começa a atualizar a hora e a data a cada segundo
iniciarButtton.onclick = (event) => {
  idInterval = setInterval(dataHora, 1000); // Inicia um intervalo que chama a função dataHora a cada 1 segundo (1000ms)
};

let pararButton = document.getElementById('parar'); // Seleciona o botão de parar
// Adiciona um evento ao botão "parar" que, quando clicado, interrompe o intervalo de atualização da hora e da data
pararButton.onclick = (event) => {
  console.log(idInterval); // Exibe no console o ID do intervalo atual
  clearInterval(idInterval); // Interrompe o intervalo, parando a atualização da hora e da data
};

let body = document.body; // Seleciona o elemento <body> do documento

// Adiciona um evento ao "onmouseleave" do body (quando o mouse sai da área da página)
// Quando o mouse sai da página, exibe uma mensagem personalizada
body.onmouseleave = (event) => {
  let mensagemP = document.getElementById('mensagem'); // Seleciona o elemento onde a mensagem será exibida
  mensagemP.innerHTML = `
    Por favor, não nos deixe.
    <img src='../imagem/please.png' width='10%'/>
  `; // Define a mensagem a ser exibida com uma imagem de alerta
};

// Adiciona um evento ao "onmouseenter" do body (quando o mouse entra na área da página)
// Quando o mouse volta à página, exibe uma mensagem de boas-vindas
body.onmouseenter = (event) => {
  let mensagemP = document.getElementById('mensagem'); // Seleciona o elemento onde a mensagem será exibida
  mensagemP.innerHTML = `
    Que bom sua volta.
    <img src='../imagem/happy.png' width='10%'/>
  `; // Define a mensagem de boas-vindas com uma imagem de felicidade
};
