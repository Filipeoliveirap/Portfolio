document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("cadastro-veiculo-form");
  const modeloInput = document.getElementById("modelo");
  const placaInput = document.getElementById("placa");
  const anoInput = document.getElementById("ano");
  const corInput = document.getElementById("cor");
  const buscaClienteInput = document.getElementById("busca-cliente");
  const sugestoesClientes = document.getElementById("sugestoes-clientes");
  const clienteIdInput = document.getElementById("cliente-id");
  const cancelarBtn = document.getElementById("cancelar-btn");

  let todosClientes = [];
  let nomeCliente = "";
  let cpfCliente = "";

  // Carrega todos os clientes do backend
  async function carregarClientesDoBackend() {
    try {
      const response = await fetch("http://localhost:8080/clientes");
      if (!response.ok) throw new Error("Erro ao buscar clientes");
      const dados = await response.json();

      // Se o backend retorna uma lista direta ou paginada
      if (Array.isArray(dados.content)) {
        todosClientes = dados.content;
      } else if (Array.isArray(dados)) {
        todosClientes = dados;
      } else {
        todosClientes = [];
      }
    } catch (erro) {
      console.error("Erro ao carregar clientes:", erro);
      todosClientes = [];
    }
  }

  // Função para mostrar sugestões de clientes
  function mostrarSugestoes(filtro) {
    const filtrados = todosClientes.filter((c) =>
      c.nome.toLowerCase().includes(filtro.toLowerCase())
    );

    sugestoesClientes.innerHTML = "";

    if (filtrados.length === 0) {
      sugestoesClientes.classList.add("hidden");
      return;
    }

    filtrados.forEach((cliente) => {
      const li = document.createElement("li");
      li.textContent = `${cliente.nome} - ${cliente.cpf}`;
      li.classList.add("cursor-pointer", "p-2", "hover:bg-gray-700");

      li.addEventListener("click", () => {
        buscaClienteInput.value = cliente.nome;
        clienteIdInput.value = cliente.id;
        nomeCliente = cliente.nome;
        cpfCliente = cliente.cpf;
        sugestoesClientes.classList.add("hidden");
      });

      sugestoesClientes.appendChild(li);
    });

    sugestoesClientes.classList.remove("hidden");
  }

  // Eventos do input do cliente
  buscaClienteInput.addEventListener("input", () => {
    const valor = buscaClienteInput.value.trim();
    clienteIdInput.value = "";

    if (valor.length > 0) {
      mostrarSugestoes(valor);
    } else {
      sugestoesClientes.classList.add("hidden");
    }
  });

  buscaClienteInput.addEventListener("focus", () => {
    mostrarSugestoes(buscaClienteInput.value.trim() || "");
  });

  document.addEventListener("click", (e) => {
    if (
      !buscaClienteInput.contains(e.target) &&
      !sugestoesClientes.contains(e.target)
    ) {
      sugestoesClientes.classList.add("hidden");
    }
  });

  // Validação do formulário
  function validarFormulario() {
    let valido = true;

    if (!clienteIdInput.value) {
      document.getElementById("cliente-error").textContent =
        "Selecione um cliente da lista.";
      valido = false;
    } else {
      document.getElementById("cliente-error").textContent = "";
    }

    if (!modeloInput.value.trim()) {
      document.getElementById("modelo-error").textContent =
        "Informe o modelo do veículo.";
      valido = false;
    } else {
      document.getElementById("modelo-error").textContent = "";
    }

    if (!placaInput.value.trim()) {
      document.getElementById("placa-error").textContent =
        "Informe a placa do veículo.";
      valido = false;
    } else {
      document.getElementById("placa-error").textContent = "";
    }

    if (!anoInput.value.trim()) {
      document.getElementById("ano-error").textContent =
        "Informe o ano do veículo.";
      valido = false;
    } else {
      document.getElementById("ano-error").textContent = "";
    }

    if (!corInput.value.trim()) {
      document.getElementById("cor-error").textContent =
        "Informe a cor do veículo.";
      valido = false;
    } else {
      document.getElementById("cor-error").textContent = "";
    }

    return valido;
  }

  // Submissão do formulário
  form.addEventListener("submit", async (e) => {
    e.preventDefault();
    if (!validarFormulario()) return;

    const dadosVeiculo = {
      modelo: modeloInput.value.trim(),
      placa: placaInput.value.trim(),
      ano: parseInt(anoInput.value),
      cor: corInput.value.trim(),
      clienteId: parseInt(clienteIdInput.value),
    };

    try {
      const response = await fetch("http://localhost:8080/veiculos", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dadosVeiculo),
      });

      if (!response.ok) {
        const erroTexto = await response.text();
        throw new Error(erroTexto || "Erro ao salvar veículo");
      }

      Swal.fire("Sucesso!", "Veículo cadastrado com sucesso!", "success");
      form.reset();
      clienteIdInput.value = "";
    } catch (error) {
      console.error("Erro ao salvar veículo:", error);
      Swal.fire("Erro!", error.message, "error");
    }
  });

  // Botão cancelar
  cancelarBtn.addEventListener("click", () => {
    window.location.href = "veiculos.html";
  });

  // Inicializa carregamento de clientes
  carregarClientesDoBackend();
});
