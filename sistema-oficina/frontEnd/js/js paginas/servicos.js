document.addEventListener('DOMContentLoaded', () => {
    const tabelaServicos = document.querySelector('#tabela-servicos tbody');
    const filtroInput = document.getElementById('pesquisar-servico');
    const tipoFiltroSelect = document.getElementById('tipo-filtro');
    const btnBuscar = document.getElementById('btn-buscar-servico');

    const API_BASE_URL = 'http://localhost:8080'; 

    function corrigirFusoHorario(dataISO) {
        const data = new Date(dataISO);
        data.setMinutes(data.getMinutes() + data.getTimezoneOffset());
        return data;
    }

    function formatarData(dataISO) {
        const dataCorrigida = corrigirFusoHorario(dataISO);
        const dia = String(dataCorrigida.getDate()).padStart(2, '0');
        const mes = String(dataCorrigida.getMonth() + 1).padStart(2, '0');
        const ano = dataCorrigida.getFullYear();
        return `${dia}/${mes}/${ano}`;
    }

    async function carregarServicos(servicos = null) {
        if (!servicos) {
            servicos = await buscarServicosBackend();
        }

        tabelaServicos.innerHTML = '';
        if (servicos.length === 0) {
            tabelaServicos.innerHTML = '<tr><td colspan="6" class="text-center p-4">Nenhum serviço cadastrado.</td></tr>';
            return;
        }


        servicos.forEach((servico) => {
            const tr = document.createElement('tr');
            tr.id = `servico-${servico.id}`;  // <-- id para remover linha após finalizar/excluir
            tr.innerHTML = `
                <td class="px-5 py-3 border-b border-gray-200">${servico.descricao}</td>
                <td class="px-5 py-3 border-b border-gray-200">R$ ${servico.preco}</td>
                <td class="px-5 py-3 border-b border-gray-200">${formatarData(servico.data)}</td>
                <td class="px-5 py-3 border-b border-gray-200">${servico.cliente?.nome || ''}</td>
                <td class="px-5 py-3 border-b border-gray-200">${servico.cliente?.cpf || ''}</td>
                <td class="px-5 py-3 border-b border-gray-200 space-x-2">
                    <button onclick="editarServico(${servico.id})" class="bg-yellow-400 hover:bg-yellow-500 text-white py-1 px-3 rounded"><i class="fas fa-edit"></i></button>
                    <button onclick="excluirServico(${servico.id})" class="bg-red-500 hover:bg-red-600 text-white py-1 px-3 rounded"><i class="fas fa-trash"></i></button>
                    <button onclick="finalizarServico(${servico.id})" class="bg-green-600 hover:bg-green-700 text-white py-1 px-3 rounded"><i class="fas fa-check"></i></button>
                </td>
            `;
            tabelaServicos.appendChild(tr);
        });
    }

    async function buscarServicos() {
        const termo = filtroInput.value.trim();
        const tipoFiltro = tipoFiltroSelect.value;
        console.log('Botão pesquisar clicado');

        try {
            const response = await fetch(`${API_BASE_URL}/servicos/buscar-tudo?termo=${encodeURIComponent(termo)}`);
            if (!response.ok) throw new Error('Erro ao buscar serviços');
            const servicos = await response.json();
            console.log('Resposta do fetch:', servicos);
            carregarServicos(servicos);
        } catch (error) {
            console.error('Erro:', error);
            alert('Erro ao buscar serviços');
        }
    }

    async function buscarServicosBackend() {
        try {
            const response = await fetch(`${API_BASE_URL}/servicos`);
            if (!response.ok) throw new Error('Erro ao carregar serviços');
            const servicos = await response.json();
            return servicos;
        } catch (error) {
            console.error('Erro:', error);
            return [];
        }
    }

    window.editarServico = (id) => {
        window.location.href = `servicosCadastro.html?id=${id}`;
    };

    window.excluirServico = async (id) => {
        const confirmado = await confirmarAcao('Tem certeza que deseja excluir este serviço?');
        if (!confirmado) return;

        try {
            const response = await fetch(`${API_BASE_URL}/servicos/${id}`, {
                method: 'DELETE'
            });
            if (!response.ok) throw new Error('Erro ao excluir serviço');

            alertaSucesso('Excluído!', 'Serviço excluído com sucesso!');
            carregarServicos();
        } catch (error) {
            console.error('Erro:', error);
            alert('Erro ao excluir serviço');
        }
    };

    // Função corrigida e única para finalizar serviço
    window.finalizarServico = async (id) => {
        const confirmado = confirm("Deseja realmente finalizar este serviço?");
        if (!confirmado) return;

        try {
            const response = await fetch(`${API_BASE_URL}/servicos-finalizados/${id}/finalizar`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                const msgErro = await response.text();
                throw new Error(msgErro || 'Erro ao finalizar serviço.');
            }

            alertaSucesso('Finalizado!', 'Serviço finalizado com sucesso!');

            // Remove a linha correspondente da tabela
            const linha = document.getElementById(`servico-${id}`);
            if (linha) linha.remove();

        } catch (error) {
            console.error('Erro ao finalizar serviço:', error);
            alert(`Erro ao finalizar serviço: ${error.message}`);
        }
    };

    btnBuscar.addEventListener('click', buscarServicos);
    carregarServicos();
});
