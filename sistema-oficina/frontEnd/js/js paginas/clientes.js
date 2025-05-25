let paginaAtual = 0; 
const tamanhoPagina = 10;

// Essas funções agora estão no escopo global
function editarCliente(id) {
    window.location.href = `clienteCadastro.html?id=${id}`;
}

async function excluirCliente(id) {
    const confirmado = await confirmarAcao('Tem certeza que deseja excluir este cliente?');
    if (!confirmado) return;

    try {
        const response = await fetch(`http://localhost:8080/clientes/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error('Erro ao excluir o cliente');
        }

        const linha = document.querySelector(`tr[data-id="${id}"]`);
        if (linha) {
            linha.remove();
        }

        await alertaSucesso('Cliente excluído com sucesso!');
    } catch (error) {
        console.error('Erro:', error.message);
        await alertaErro('Erro ao excluir cliente', error.message);
    }
}


document.addEventListener('DOMContentLoaded', function () {
    const tbody = document.querySelector('#tabela-clientes tbody');
    const inputBusca = document.getElementById('pesquisar-cliente');
    const btnBusca = document.getElementById('btn-buscar-cliente');
    const tipoFiltroSelect = document.getElementById('tipo-filtro');
    let filtroAtual = '';
    let tipoFiltroAtual = 'nome';

    async function carregarClientes(pagina, filtro = '', tipoFiltro = 'nome') {
        try {
            let url = `http://localhost:8080/clientes?pagina=${pagina}&tamanho=${tamanhoPagina}&tipoFiltro=${encodeURIComponent(tipoFiltro)}`;
            if (filtro) {
                url += `&filtro=${encodeURIComponent(filtro)}`;
            }
            console.log('URL da requisição:', url);

            const response = await fetch(url);

            if (!response.ok) {
                throw new Error('Erro ao buscar clientes');
            }
            const dados = await response.json();
            console.log('Dados recebidos:', dados);
    
            tbody.innerHTML = '';
    
            dados.content.forEach(cliente => {
                const row = document.createElement('tr');
                row.setAttribute('data-id', cliente.id);
                
                row.innerHTML = `
                    <td class="px-5 py-3 border-b text-sm">${cliente.nome}</td>
                    <td class="px-5 py-3 border-b text-sm">${cliente.cpf}</td>
                    <td class="px-5 py-3 border-b text-sm">${cliente.telefone}</td>
                    <td class="px-5 py-3 border-b text-sm">${cliente.email}</td>
                    <td class="px-5 py-3 border-b text-sm">${cliente.endereco ?? 'Não disponível'}</td>
                    <td class="px-5 py-3 border-b text-sm text-center">
                        <button onclick="editarCliente(${cliente.id})" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-md">
                            Editar
                        </button>
                        <button onclick="excluirCliente(${cliente.id})" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded-md">
                            Excluir
                        </button>
                    </td>
                `;
                
                tbody.appendChild(row);
            });
    
            atualizarPaginacao(dados.totalPages);
        } catch (error) {
            console.error('Erro:', error.message);
            await alertaErro('Erro ao carregar cliente', 'Não foi possível carregar os dados do cliente.');
        }
    }

    function atualizarPaginacao(totalPaginas) {
        const paginacao = document.getElementById('paginacao');
        paginacao.innerHTML = '';

        for (let i = 0; i < totalPaginas; i++) {
            const botao = document.createElement('button');
            botao.textContent = i + 1;
            botao.disabled = i === paginaAtual;
            botao.addEventListener('click', () =>{
                paginaAtual = i;
                carregarClientes(paginaAtual, filtroAtual);
            });
            paginacao.appendChild(botao);
        }
    }

    btnBusca.addEventListener('click', () => {
        filtroAtual = inputBusca.value.trim();
        tipoFiltroAtual = tipoFiltroSelect.value;
        paginaAtual = 0; // reseta página para a primeira
        carregarClientes(paginaAtual, filtroAtual, tipoFiltroAtual);
    });

    carregarClientes(paginaAtual);
});
