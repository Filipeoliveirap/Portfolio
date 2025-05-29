let paginaAtual = 0;
let totalPaginas = 0;
const tamanhoPagina = 10; // ou o que você quiser

const tabelaFinalizados = document.querySelector('#tabela-servicos-finalizados tbody');
const inputPesquisa = document.getElementById('pesquisar-servico-finalizado');
const btnBuscar = document.getElementById('btn-buscar-servico-finalizado');
const btnGerarRelatorioGeral = document.getElementById('btn-gerar-relatorio-geral');
const filtroPeriodo = document.getElementById('filtro-periodo');

filtroPeriodo.addEventListener('change', (e) => {
  if (e.target.value === 'todos') {
    document.getElementById('data-inicio').value = '';
    document.getElementById('data-fim').value = '';
  }
  paginaAtual = 0; // volta para primeira página
});

filtroPeriodo.addEventListener('change', (e) => {
  if (e.target.value === 'semana') {
    document.getElementById('data-inicio').value = '';
    document.getElementById('data-fim').value = '';
  }
  paginaAtual = 0; // volta para primeira página
});

filtroPeriodo.addEventListener('change', (e) => {
  if (e.target.value === 'mes') {
    document.getElementById('data-inicio').value = '';
    document.getElementById('data-fim').value = '';
  }
  paginaAtual = 0; // volta para primeira página
});

filtroPeriodo.addEventListener('change', (e) => {
  if (e.target.value === 'ano') {
    document.getElementById('data-inicio').value = '';
    document.getElementById('data-fim').value = '';
  }
  paginaAtual = 0; // volta para primeira página
});

function formatarDataBrasileira(dataIso) {
  const data = new Date(dataIso);
  return data.toLocaleDateString('pt-BR', {
    timeZone: 'America/Sao_Paulo',
  });
}

function formatarDataParaEnvio(dataString) {
  if (!dataString) return "";
  const [ano, mes, dia] = dataString.split("-");
  return `${ano}-${mes}-${dia}`;
}


let filtrosAtuais = {
  termo: '',
  inicio: '',
  fim: '',
  periodo: ''
};

btnBuscar.addEventListener('click', buscarServicosFinalizados);
btnGerarRelatorioGeral.addEventListener('click', baixarRelatorioGeral);

function renderizarPaginacao() {
  const container = document.getElementById("paginacao");
  container.innerHTML = '';

  if (totalPaginas <= 1) return;

  for (let i = 0; i < totalPaginas; i++) {
    const btn = document.createElement("button");
    btn.textContent = i + 1;
    btn.className = `mx-1 px-3 py-1 rounded ${i === paginaAtual ? 'bg-orange-600 text-white' : 'bg-gray-500'}`;
    btn.addEventListener("click", () => {
      paginaAtual = i;
      buscarServicosFinalizados();
    });
    container.appendChild(btn);
  }
}


// Carrega os serviços com os filtros atuais (inicialmente vazios)
buscarServicosFinalizados();

async function buscarServicosFinalizados() {
  filtrosAtuais.termo = inputPesquisa.value.trim();
  filtrosAtuais.periodo = document.getElementById('filtro-periodo').value;

  const url = new URL("http://localhost:8080/servicos-finalizados");

  // Quando o filtro for "todos", desativa paginação e ignora data
  let pagina = paginaAtual;
  let tamanho = tamanhoPagina;

  if (filtrosAtuais.periodo === "todos") {
    // Limpa campos de data
    document.getElementById('data-inicio').value = '';
    document.getElementById('data-fim').value = '';

    filtrosAtuais.inicio = '';
    filtrosAtuais.fim = '';

    pagina = 0;
    tamanho = 9999; // Número alto para trazer tudo

    // Esconde paginação, se existir
    const paginacao = document.getElementById("paginacao");
    if (paginacao) paginacao.style.display = "none";
  } else {
    // Caso contrário, coleta datas normalmente
    filtrosAtuais.inicio = formatarDataParaEnvio(document.getElementById('data-inicio').value);
    filtrosAtuais.fim = formatarDataParaEnvio(document.getElementById('data-fim').value);

    // Mostra paginação
    const paginacao = document.getElementById("paginacao");
    if (paginacao) paginacao.style.display = "flex";
  }

  if (filtrosAtuais.termo) url.searchParams.append("termo", filtrosAtuais.termo);
  if (filtrosAtuais.inicio) url.searchParams.append("inicio", filtrosAtuais.inicio);
  if (filtrosAtuais.fim) url.searchParams.append("fim", filtrosAtuais.fim);
  if (filtrosAtuais.periodo) url.searchParams.append("periodo", filtrosAtuais.periodo);

  url.searchParams.append("pagina", pagina);
  url.searchParams.append("tamanho", tamanho);

  console.log("🔍 URL da requisição:", url.toString());

  try {
    const response = await fetch(url.toString());
    if (!response.ok) throw new Error('Erro ao buscar serviços finalizados');

    const dados = await response.json();
    const servicos = dados.content || dados; // Se for lista pura sem paginação
    totalPaginas = dados.totalPages || 1;

    carregarTabelaComDados(servicos);
    console.log("📦 Resposta do back-end:", servicos);
  } catch (error) {
    console.error('Erro:', error);
    alert('Erro ao buscar serviços finalizados');
  }

  if (filtrosAtuais.periodo !== "todos") {
    renderizarPaginacao(); // Só renderiza paginação se não for "todos"
  }
}


function carregarTabelaComDados(servicos) {
  tabelaFinalizados.innerHTML = '';

  if (servicos.length === 0) {
    tabelaFinalizados.innerHTML = `<tr><td colspan="8" class="text-center p-4 text-white bg-black bg-opacity-50 rounded">Nenhum serviço finalizado encontrado.</td></tr>`;
    return;
  }

  servicos.forEach(servico => {
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td class="px-5 py-3 border-b border-gray-700">${servico.descricao}</td>
      <td class="px-5 py-3 border-b border-gray-700">R$ ${servico.preco.toFixed(2)}</td>
      <td class="px-5 py-3 border-b border-gray-700">${formatarDataBrasileira(servico.dataInicio)}</td>
      <td class="px-5 py-3 border-b border-gray-700">${formatarDataBrasileira(servico.dataFinalizacao)}</td>
      <td class="px-5 py-3 border-b border-gray-700">${servico.nomeCliente}</td>
      <td class="px-5 py-3 border-b border-gray-700">${servico.cpfCliente}</td>
      <td class="px-5 py-3 border-b border-gray-700">${servico.observacoes || ''}</td>
      <td class="px-5 py-3 border-b border-gray-700">
        <div class="flex gap-2">
          <button onclick="gerarRelatorio(${servico.id})" 
                  class="bg-orange-600 hover:bg-orange-700 text-white py-1 px-2 rounded shadow-sm" 
                  title="Gerar Relatório">
            <i class="fas fa-file-pdf"></i>
          </button>
          <button onclick="adicionarObservacao(${servico.id})" 
                  class="bg-purple-600 hover:bg-purple-700 text-white py-1 px-2 rounded shadow-sm" 
                  title="Adicionar Observação">
            <i class="fas fa-sticky-note"></i>
          </button>
          <button onclick="excluirServico(${servico.id})" 
                  class="bg-red-600 hover:bg-red-700 text-white py-1 px-2 rounded shadow-sm" 
                  title="Excluir">
            <i class="fas fa-trash"></i>
          </button>
        </div>
      </td>
    `;

    tabelaFinalizados.appendChild(tr);
  });
}

async function gerarRelatorio(id) {
  try {
    const response = await fetch(`http://localhost:8080/relatorios/servico-finalizado/${id}`, {
      method: 'GET',
      headers: { 'Content-Type': 'application/pdf' }
    });

    if (!response.ok) throw new Error('Erro ao gerar relatório');

    const blob = await response.blob();
    const url = window.URL.createObjectURL(blob);

    const a = document.createElement('a');
    a.href = url;
    a.download = `relatorio-servico-finalizado-${id}.pdf`;
    document.body.appendChild(a);
    a.click();
    a.remove();

    window.URL.revokeObjectURL(url);
  } catch (error) {
    alert('Erro: ' + error.message);
  }
}

async function baixarRelatorioGeral() {
  try {
    const url = new URL('http://localhost:8080/relatorios/servicos-finalizados');

    if (filtrosAtuais.termo) url.searchParams.append("termo", filtrosAtuais.termo);
    if (filtrosAtuais.inicio) url.searchParams.append("inicio", filtrosAtuais.inicio);
    if (filtrosAtuais.fim) url.searchParams.append("fim", filtrosAtuais.fim);
    if (filtrosAtuais.periodo) url.searchParams.append("periodo", filtrosAtuais.periodo);

    // ESSA PARTE GARANTE QUE VAI PEGAR APENAS OS DADOS DA PÁGINA ATUAL
    url.searchParams.append("pagina", paginaAtual);
    url.searchParams.append("tamanho", tamanhoPagina);

    console.log(" Gerando relatório da página atual com URL:", url.toString());

    const response = await fetch(url.toString(), {
      method: 'GET',
      headers: { 'Content-Type': 'application/pdf' }
    });

    if (!response.ok) throw new Error('Erro ao gerar relatório geral');

    const blob = await response.blob();
    const urlBlob = window.URL.createObjectURL(blob);

    const a = document.createElement('a');
    a.href = urlBlob;
    a.download = `relatorio-servicos-finalizados-pagina-${paginaAtual + 1}.pdf`;
    document.body.appendChild(a);
    a.click();
    a.remove();

    window.URL.revokeObjectURL(urlBlob);
  } catch (error) {
    alert('Erro: ' + error.message);
  }
}


function abrirModalObservacao() {
  return new Promise((resolve) => {
    const modal = document.getElementById('modalObservacao');
    const btnCancelar = document.getElementById('btnCancelar');
    const btnSalvar = document.getElementById('btnSalvar');
    const inputObservacao = document.getElementById('inputObservacao');

    modal.classList.remove('hidden');
    inputObservacao.value = '';
    btnSalvar.disabled = true;
    inputObservacao.focus();

    function onInput() {
      btnSalvar.disabled = inputObservacao.value.trim() === '';
    }

    function limpar() {
      btnCancelar.removeEventListener('click', onCancelar);
      btnSalvar.removeEventListener('click', onSalvar);
      inputObservacao.removeEventListener('input', onInput);
      modal.classList.add('hidden');
    }

    function onCancelar() {
      limpar();
      resolve(null);
    }

    function onSalvar() {
      const valor = inputObservacao.value.trim();
      limpar();
      resolve(valor || null);
    }

    inputObservacao.addEventListener('input', onInput);
    btnCancelar.addEventListener('click', onCancelar);
    btnSalvar.addEventListener('click', onSalvar);
  });
}

async function adicionarObservacao(id) {
  try {
    const observacao = await abrirModalObservacao();

    if (!observacao) {
      console.log('Usuário cancelou ou não digitou nada.');
      // Aqui também pode usar uma função de alerta informativo, se quiser:
      // mostrarAlertaInfo('Operação cancelada pelo usuário.');
      return;
    }

    const response = await fetch(`http://localhost:8080/servicos-finalizados/${id}`, {
      method: 'PATCH',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ observacoes: observacao }),
    });

    if (!response.ok) throw new Error('Erro ao adicionar observação');

    alertaSucesso('Observação adicionada com sucesso!');
    buscarServicosFinalizados(); // atualiza tabela
  } catch (error) {
    alertaErro(error.message);
  }
}

async function excluirServico(id) {
  const confirmado = await confirmarAcao('Deseja realmente excluir esse serviço finalizado?');
  if (!confirmado) return;

  try {
    const response = await fetch(`http://localhost:8080/servicos-finalizados/${id}`, {
      method: 'DELETE',
    });

    if (!response.ok) throw new Error('Erro ao excluir serviço');

    alertaSucesso('Serviço excluído com sucesso!');
    buscarServicosFinalizados(); // Atualiza a tabela com filtros atuais
  } catch (error) {
    alert(error.message);
  }
}