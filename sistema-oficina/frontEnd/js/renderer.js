// Quando o botão for clicado, chama a função exposta no preload.js
document.getElementById('btnFechar').addEventListener('click', () => {
    window.electron.sairDoApp(); // Chama a função para sair do app
  });
  
  function carregarPagina(pagina) {
    const iframe = document.getElementById('conteudo-frame');
    switch (pagina) {
        case 'dashboard':
            iframe.src = '../html/dashboard.html';
            break;
        case 'clientes':
            iframe.src = '../html/clientes.html'; 
            break;
        case 'servicos':
            iframe.src = '../html/servicos.html'; 
            break;
        case 'estoque':
            iframe.src = '../html/estoque.html'; 
            break;
        case 'relatorios':
            iframe.src = '../html/relatorios.html'; 
            break;
        default:
            console.error(`Página '${pagina}' não encontrada.`);
    }
  }
  