document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('cadastro-servico-form');
  const buscaClienteInput = document.getElementById('busca-cliente');
  const sugestoesClientes = document.getElementById('sugestoes-clientes');
  const clienteIdInput = document.getElementById('cliente-id');
  const cancelarBtn = document.getElementById('cancelar-btn');

  let todosClientes = [];
  let servicoId = null;
  let nomeCliente = '';
  let cpfCliente = '';

  const urlParams = new URLSearchParams(window.location.search);
  servicoId = urlParams.get('id');

  async function carregarClientesDoBackend() {
    try {
      const response = await fetch('http://localhost:8080/clientes');
      if (!response.ok) throw new Error('Erro ao buscar clientes');

      const dados = await response.json();
      if (Array.isArray(dados.content)) {
        todosClientes = dados.content;
      } else {
        console.error('Resposta do backend não é um array:', dados);
        todosClientes = [];
      }
    } catch (erro) {
      console.error('Erro ao carregar clientes:', erro);
      todosClientes = [];
    }
  }

  async function carregarServicoParaEdicao(id) {
    try {
      const response = await fetch(`http://localhost:8080/servicos/${id}`);
      if (!response.ok) {
        const errorText = await response.text();
        console.error('Erro do backend:', errorText);
        throw new Error('Erro ao buscar serviço');
      }

      const servico = await response.json();
      document.getElementById('descricao').value = servico.descricao;
      document.getElementById('preco').value = servico.preco;
      document.getElementById('data').value = servico.data.split('T')[0];
      buscaClienteInput.value = `${servico.cliente.nome} - ${servico.cliente.cpf}`;
      clienteIdInput.value = servico.cliente.id;
      nomeCliente = servico.cliente.nome;
      cpfCliente = servico.cliente.cpf;
    } catch (error) {
      console.error('Erro ao carregar serviço:', error);
      alert('Erro ao carregar serviço. Verifique os dados e tente novamente.');
    }
  }

  if (servicoId) {
    carregarServicoParaEdicao(servicoId);
  }

  function mostrarSugestoes(filtro) {
    const filtrados = todosClientes.filter(cliente =>
      cliente.nome.toLowerCase().includes(filtro.toLowerCase())
    );

    sugestoesClientes.innerHTML = '';

    if (filtrados.length === 0) {
      sugestoesClientes.classList.add('hidden');
      return;
    }

    filtrados.forEach(cliente => {
      const li = document.createElement('li');
      li.textContent = `${cliente.nome} - ${cliente.cpf}`;
      li.classList.add('cursor-pointer', 'p-2', 'hover:bg-gray-200');

      li.addEventListener('click', () => {
        buscaClienteInput.value = cliente.nome;
        clienteIdInput.value = cliente.id;
        nomeCliente = cliente.nome;
        cpfCliente = cliente.cpf;
        sugestoesClientes.classList.add('hidden');
      });

      sugestoesClientes.appendChild(li);
    });

    sugestoesClientes.classList.remove('hidden');
  }

  buscaClienteInput.addEventListener('input', () => {
    const valor = buscaClienteInput.value.trim();
    if (valor.length > 0) {
      mostrarSugestoes(valor);
    } else {
      sugestoesClientes.classList.add('hidden');
      clienteIdInput.value = '';
    }
  });

  document.addEventListener('click', (e) => {
    if (!buscaClienteInput.contains(e.target) && !sugestoesClientes.contains(e.target)) {
      sugestoesClientes.classList.add('hidden');
    }
  });

  buscaClienteInput.addEventListener('focus', () => {
    mostrarSugestoes(buscaClienteInput.value.trim());
  });

  function validarFormulario() {
    let valido = true;
    if (!clienteIdInput.value) {
      document.getElementById('cliente-error').textContent = 'Selecione um cliente da lista.';
      valido = false;
    } else {
      document.getElementById('cliente-error').textContent = '';
    }
    return valido;
  }

  form.addEventListener('submit', async (e) => {
    e.preventDefault();

    if (!validarFormulario()) return;

    const descricao = document.getElementById('descricao').value.trim();
    const preco = parseFloat(document.getElementById('preco').value.trim());
    const data = document.getElementById('data').value;
    const idCliente = clienteIdInput.value;

    const dadosServico = {
      descricao,
      preco,
      data: `${data}T00:00:00`, // ← corrigido aqui
      cliente: {
        id: parseInt(idCliente),
        nome: nomeCliente,
        cpf: cpfCliente
      }
    };


    console.log('Dados que serão enviados:', JSON.stringify(dadosServico, null, 2));

    try {
      let response;
      if (servicoId) {
        response = await fetch(`http://localhost:8080/servicos/${servicoId}`, {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(dadosServico),
        });
      } else {
        response = await fetch('http://localhost:8080/servicos', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(dadosServico),
        });
      }

      if (!response.ok) {
        const erroTexto = await response.text();
        throw new Error(erroTexto || 'Erro ao salvar serviço');
      }

      await alertaSucesso('Sucesso!', 'Serviço salvo com sucesso!');
      window.location.href = 'servicos.html';
    } catch (error) {
      console.error('Erro ao salvar serviço:', error.message);
      await alertaErro('Erro ao salvar serviço', error.message);
      if (typeof formErrorResponse === 'function') {
        formErrorResponse('form-error', error.message);
      }
    }
  });

  cancelarBtn.addEventListener('click', () => {
    window.location.href = 'servicos.html';
  });

  carregarClientesDoBackend();

  // ✅ Função que estava faltando
  function formErrorResponse(elementId = 'form-error', msg = 'Erro ao processar formulário') {
    const el = document.getElementById(elementId);
    if (el) {
      el.textContent = msg;
      el.classList.remove('hidden');
    } else {
      alert(msg);
    }
  }
});
