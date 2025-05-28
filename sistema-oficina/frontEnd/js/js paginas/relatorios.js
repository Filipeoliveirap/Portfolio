const tabelaFinalizados = document.querySelector('#tabela-servicos-finalizados tbody');
const inputPesquisa = document.getElementById('pesquisar-servico-finalizado');
const btnBuscar = document.getElementById('btn-buscar-servico-finalizado');
const btnGerarRelatorioGeral = document.getElementById('btn-gerar-relatorio-geral');

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

// Carrega os serviços com os filtros atuais (inicialmente vazios)
buscarServicosFinalizados();

async function buscarServicosFinalizados() {
  filtrosAtuais.termo = inputPesquisa.value.trim();
  filtrosAtuais.inicio = formatarDataParaEnvio(document.getElementById('data-inicio').value);
  filtrosAtuais.fim = formatarDataParaEnvio(document.getElementById('data-fim').value);
  filtrosAtuais.periodo = document.getElementById('filtro-periodo').value;

  const url = new URL("http://localhost:8080/servicos-finalizados");

  if (filtrosAtuais.termo) url.searchParams.append("termo", filtrosAtuais.termo);
  if (filtrosAtuais.inicio) url.searchParams.append("inicio", filtrosAtuais.inicio);
  if (filtrosAtuais.fim) url.searchParams.append("fim", filtrosAtuais.fim);
  if (filtrosAtuais.periodo) url.searchParams.append("periodo", filtrosAtuais.periodo);

  console.log("🔍 URL da requisição:", url.toString());

  try {
    const response = await fetch(url.toString());
    if (!response.ok) throw new Error('Erro ao buscar serviços finalizados');

    const servicos = await response.json();
    carregarTabelaComDados(servicos);
    console.log("📦 Resposta do back-end:", servicos);
  } catch (error) {
    console.error('Erro:', error);
    alert('Erro ao buscar serviços finalizados');
  }
}

function carregarTabelaComDados(servicos) {
  tabelaFinalizados.innerHTML = '';

  if (servicos.length === 0) {
    tabelaFinalizados.innerHTML = `<tr><td colspan="8" class="text-center p-4">Nenhum serviço finalizado encontrado.</td></tr>`;
    return;
  }

  servicos.forEach(servico => {
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td class="px-5 py-3 border-b border-gray-200">${servico.descricao}</td>
      <td class="px-5 py-3 border-b border-gray-200">R$ ${servico.preco.toFixed(2)}</td>
      <td class="px-5 py-3 border-b border-gray-200">${formatarDataBrasileira(servico.dataInicio)}</td>
      <td class="px-5 py-3 border-b border-gray-200">${formatarDataBrasileira(servico.dataFinalizacao)}</td>
      <td class="px-5 py-3 border-b border-gray-200">${servico.nomeCliente}</td>
      <td class="px-5 py-3 border-b border-gray-200">${servico.cpfCliente}</td>
      <td class="px-5 py-3 border-b border-gray-200">${servico.observacoes || ''}</td>
      <td class="flex gap-1">
          <button onclick="gerarRelatorio(${servico.id})" class="bg-blue-600 hover:bg-blue-700 text-white py-1 px-2 rounded" title="Gerar Relatório">
              <i class="fas fa-file-pdf"></i>
          </button>
          <button onclick="adicionarObservacao(${servico.id})" class="bg-purple-600 hover:bg-purple-700 text-white py-1 px-2 rounded" title="Adicionar Observação">
              <i class="fas fa-sticky-note"></i>
          </button>
          <button onclick="excluirServico(${servico.id})" class="bg-red-500 hover:bg-red-600 text-white py-1 px-2 rounded" title="Excluir">
              <i class="fas fa-trash"></i>
          </button>
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

    console.log("📝 Gerando relatório com URL:", url.toString());

    const response = await fetch(url.toString(), {
      method: 'GET',
      headers: { 'Content-Type': 'application/pdf' }
    });

    if (!response.ok) throw new Error('Erro ao gerar relatório geral');

    const blob = await response.blob();
    const urlBlob = window.URL.createObjectURL(blob);

    const a = document.createElement('a');
    a.href = urlBlob;
    a.download = 'relatorio-servicos-finalizados.pdf';
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
      return;
    }

    const response = await fetch(`http://localhost:8080/servicos-finalizados/${id}`, {
      method: 'PATCH',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ observacoes: observacao }),
    });

    if (!response.ok) throw new Error('Erro ao adicionar observação');

    alert('Observação adicionada com sucesso!');
    buscarServicosFinalizados(); // atualiza tabela
  } catch (error) {
    alert(error.message);
  }
}

async function excluirServico(id) {
  if (!confirm('Deseja realmente excluir esse serviço finalizado?')) return;

  try {
    const response = await fetch(`http://localhost:8080/servicos-finalizados/${id}`, {
      method: 'DELETE',
    });

    if (!response.ok) throw new Error('Erro ao excluir serviço');

    alert('Serviço excluído com sucesso!');
    buscarServicosFinalizados(); // Atualiza a tabela com filtros atuais
  } catch (error) {
    alert(error.message);
  }
}
