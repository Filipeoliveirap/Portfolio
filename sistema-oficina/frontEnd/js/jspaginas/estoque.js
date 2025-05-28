let paginaAtual = 0;
const tamanhoPagina = 10;

function editarProduto(id) {
  window.location.href = `estoqueCadastro.html?id=${id}`;
}

async function excluirProduto(id) {
  const confirmado = await confirmarAcao('Tem certeza que deseja excluir este produto?');
  if (!confirmado) return;

  try {
    const response = await fetch(`http://localhost:8080/produtos/${id}`, {
      method: 'DELETE',
    });

    if (!response.ok) throw new Error('Erro ao excluir o produto');

    const linha = document.querySelector(`tr[data-id="${id}"]`);
    if (linha) linha.remove();

    await alertaSucesso('Produto excluído com sucesso!');
  } catch (error) {
    console.error('Erro:', error.message);
    await alertaErro('Erro ao excluir produto', error.message);
  }
}

document.addEventListener('DOMContentLoaded', () => {
  const tbody = document.querySelector('#tabela-produtos tbody');
  const inputBusca = document.getElementById('pesquisar-estoque');
  const btnBusca = document.getElementById('btn-buscar-estoque');
  const tipoFiltroSelect = document.getElementById('tipo-filtro-estoque');

  let filtroAtual = '';
  let tipoFiltroAtual = 'nome';

  async function carregarProdutos(pagina, filtro = '', tipoFiltro = 'nome') {
    try {
      // Monta URL com paginação e filtro
      let url = `http://localhost:8080/produtos?pagina=${pagina}&tamanho=${tamanhoPagina}`;
      url += `&tipoFiltro=${encodeURIComponent(tipoFiltro)}`;
      if (filtro) {
        url += `&filtro=${encodeURIComponent(filtro)}`;
      }
      console.log('URL da requisição:', url);

      const response = await fetch(url);

      if (!response.ok) throw new Error('Erro ao buscar produtos');

      const dados = await response.json();
      console.log('Dados recebidos:', dados);

      tbody.innerHTML = '';

      // Exibe mensagem caso não haja produtos
      if (!dados.content || dados.content.length === 0) {
        tbody.innerHTML = `
          <tr>
            <td colspan="7" class="text-center p-4">
              Nenhum produto encontrado ou cadastrado.
            </td>
          </tr>
        `;
        return;
      }

      // Popula tabela com produtos
      dados.content.forEach(produto => {
        const precoTotal = (produto.quantidade * produto.precoUnitario).toFixed(2);

        const row = document.createElement('tr');
        row.setAttribute('data-id', produto.id);

        row.innerHTML = `
          <td class="px-5 py-3 border-b text-sm">${produto.nome}</td>
          <td class="px-5 py-3 border-b text-sm">${produto.quantidade}</td>
          <td class="px-5 py-3 border-b text-sm">R$ ${produto.precoUnitario.toFixed(2)}</td>
          <td class="px-5 py-3 border-b text-sm">R$ ${precoTotal}</td>
          <td class="px-5 py-3 border-b text-sm">${produto.categoria ?? 'Não disponível'}</td>
          <td class="px-6 py-4 border-b border-gray-200 text-sm">${produto.observacao || '—'}</td>
          <td class="px-5 py-3 border-b text-sm text-center space-x-2">
            <button onclick="editarProduto(${produto.id})" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded-md text-xs" aria-label="Editar produto ${produto.nome}">
              Editar
            </button>
            <button onclick="excluirProduto(${produto.id})" class="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded-md text-xs" aria-label="Excluir produto ${produto.nome}">
              Excluir
            </button>
          </td>
        `;

        tbody.appendChild(row);
      });

      atualizarPaginacao(dados.totalPages);
    } catch (error) {
      console.error('Erro:', error.message);
      await alertaErro('Erro ao carregar produtos', 'Não foi possível carregar os dados dos produtos.');
    }
  }

  function atualizarPaginacao(totalPaginas) {
    const paginacao = document.getElementById('paginacao-estoque');
    paginacao.innerHTML = '';

    for (let i = 0; i < totalPaginas; i++) {
      const botao = document.createElement('button');
      botao.textContent = i + 1;
      botao.disabled = i === paginaAtual;
      botao.className = 'bg-gray-200 hover:bg-gray-400 text-gray-800 font-bold py-1 px-3 rounded mx-1';

      // Evento para mudar página
      botao.addEventListener('click', () => {
        paginaAtual = i;
        carregarProdutos(paginaAtual, filtroAtual, tipoFiltroAtual);
      });

      paginacao.appendChild(botao);
    }
  }

  // Busca ao clicar no botão
  btnBusca.addEventListener('click', () => {
    filtroAtual = inputBusca.value.trim();
    tipoFiltroAtual = tipoFiltroSelect.value;
    paginaAtual = 0;
    carregarProdutos(paginaAtual, filtroAtual, tipoFiltroAtual);
  });

  // Busca ao apertar Enter no input de busca
  inputBusca.addEventListener('keydown', (event) => {
    if (event.key === 'Enter') {
      btnBusca.click();
    }
  });

  // Carregar produtos inicialmente, sem filtro
  carregarProdutos(paginaAtual);
});
