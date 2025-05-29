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
            <td colspan="7" class="text-center p-4 text-white bg-black bg-opacity-50 rounded">
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
          <td class="px-5 py-3 border-b border-gray-700 text-sm text-white">${produto.nome}</td>
          <td class="px-5 py-3 border-b border-gray-700 text-sm text-white">${produto.quantidade}</td>
          <td class="px-5 py-3 border-b border-gray-700 text-sm text-white">R$ ${produto.precoUnitario.toFixed(2)}</td>
          <td class="px-5 py-3 border-b border-gray-700 text-sm text-white">R$ ${precoTotal}</td>
          <td class="px-5 py-3 border-b border-gray-700 text-sm text-white">${produto.categoria ?? 'Não disponível'}</td>
          <td class="px-6 py-4 border-b border-gray-700 text-sm text-white">${produto.observacao || '—'}</td>
          <td class="px-5 py-3 border-b border-gray-700 text-sm text-white">
            <div class="flex items-center space-x-2 justify-center h-full">
              <button onclick="editarProduto(${produto.id})" class="bg-yellow-500 hover:bg-yellow-600 text-black font-semibold py-1 px-3 rounded shadow">
                <i class="fas fa-edit"></i>
              </button>
              <button onclick="excluirProduto(${produto.id})" class="bg-red-600 hover:bg-red-700 text-white font-semibold py-1 px-3 rounded shadow">
                <i class="fas fa-trash"></i>
              </button>
            </div>
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

    if (totalPaginas <= 1) {
      return; // Não mostra paginação se só tiver uma página
    }

    for (let i = 0; i < totalPaginas; i++) {
      const botao = document.createElement('button');
      botao.textContent = i + 1;
      botao.disabled = i === paginaAtual;
      botao.className = `mx-1 px-3 py-1 rounded ${i === paginaAtual ? 'bg-orange-600 text-white' : 'bg-gray-500'}`;

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
