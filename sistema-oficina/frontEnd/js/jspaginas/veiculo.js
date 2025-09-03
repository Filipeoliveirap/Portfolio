
document.addEventListener("DOMContentLoaded", () => {
  const tabelaVeiculos = document.querySelector("#tabela-veiculos tbody");
  const filtroInput = document.getElementById("pesquisar-veiculo");
  const tipoFiltroSelect = document.getElementById("tipo-filtro");
  const btnBuscar = document.getElementById("btn-buscar-veiculo");

  const API_BASE_URL = "http://localhost:8080";

  // Carregar veículos 
  async function carregarVeiculos(veiculos = null) {
    if (!veiculos) veiculos = await buscarVeiculosBackend();

    tabelaVeiculos.innerHTML = "";
    if (!veiculos || veiculos.length === 0) {
      tabelaVeiculos.innerHTML =
        '<tr><td colspan="7" class="text-center p-4 text-white bg-black bg-opacity-50 rounded">Nenhum veículo encontrado ou cadastrado.</td></tr>';
      return;
    }

    veiculos.forEach((veiculo) => {
      const tr = document.createElement("tr");
      tr.id = `veiculo-${veiculo.id}`;
      tr.innerHTML = `
        <td class="px-5 py-3 border-b border-gray-700 text-white">${veiculo.modelo}</td>
        <td class="px-5 py-3 border-b border-gray-700 text-white">${veiculo.placa}</td>
        <td class="px-5 py-3 border-b border-gray-700 text-white">${veiculo.ano}</td>
        <td class="px-5 py-3 border-b border-gray-700 text-white">${veiculo.cor}</td>
        <td class="px-5 py-3 border-b border-gray-700 text-white">${veiculo.cliente?.nome || ""}</td>
        <td class="px-5 py-3 border-b border-gray-700 text-white">${veiculo.cliente?.cpf || ""}</td>
        <td class="px-5 py-3 border-b border-gray-700 space-x-2">
            <button onclick="editarVeiculo(${veiculo.id})" class="bg-yellow-500 hover:bg-yellow-600 text-black font-semibold py-1 px-3 rounded shadow"><i class="fas fa-edit"></i></button>
            <button onclick="excluirVeiculo(${veiculo.id})" class="bg-red-600 hover:bg-red-700 text-white font-semibold py-1 px-3 rounded shadow"><i class="fas fa-trash"></i></button>
        </td>
      `;
      tabelaVeiculos.appendChild(tr);
    });
  }

  // Buscar veículos 
  async function buscarVeiculos() {
    const termo = filtroInput.value.trim();
    const tipoFiltro = tipoFiltroSelect.value;

    try {
      const response = await fetch(
        `${API_BASE_URL}/veiculos/buscar?campo=${tipoFiltro}&termo=${encodeURIComponent(termo)}`
      );
      if (!response.ok) throw new Error("Erro ao buscar veículos");
      const veiculos = await response.json();
      carregarVeiculos(veiculos);
    } catch (error) {
      console.error("Erro:", error);
      alert("Erro ao buscar veículos");
    }
  }

  // Buscar todos os veículos
  async function buscarVeiculosBackend() {
    try {
      const response = await fetch(`${API_BASE_URL}/veiculos`);
      if (!response.ok) throw new Error("Erro ao carregar veículos");
      return await response.json();
    } catch (error) {
      console.error("Erro:", error);
      return [];
    }
  }

  //  Editar 
  window.editarVeiculo = (id) => {
    window.location.href = `veiculosCadastro.html?id=${id}`;
  };

  // Excluir 
  window.excluirVeiculo = async (id) => {
    const confirmado = await confirmarAcao(
      "Tem certeza que deseja excluir este veículo?"
    );
    if (!confirmado) return;

    try {
      const response = await fetch(`${API_BASE_URL}/veiculos/${id}`, {
        method: "DELETE",
      });
      if (!response.ok) throw new Error("Erro ao excluir veículo");

      alertaSucesso("Excluído!", "Veículo excluído com sucesso!");
      const linha = document.getElementById(`veiculo-${id}`);
      if (linha) linha.remove();
    } catch (error) {
      console.error("Erro:", error);
      alert("Erro ao excluir veículo");
    }
  };

  btnBuscar.addEventListener("click", buscarVeiculos);

  // Carregar todos inicialmente
  carregarVeiculos();
});
