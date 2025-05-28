const apiUrl = "http://localhost:8080/produtos"; // Ajuste conforme seu backend

const form = document.getElementById('cadastro-estoque-form');
const idInput = document.getElementById('id');
const produtoInput = document.getElementById('produto');
const quantidadeInput = document.getElementById('quantidade');
const precoUnitarioInput = document.getElementById('precoUnitario');
const categoriaInput = document.getElementById('categoria');
const observacoesInput = document.getElementById('observacoes');
const cancelarBtn = document.getElementById('cancelar-btn');

function limparFormulario() {
    idInput.value = '';
    produtoInput.value = '';
    quantidadeInput.value = '';
    precoUnitarioInput.value = '';
    categoriaInput.value = '';
    observacoesInput.value = '';
}

async function salvarProduto(event) {
    event.preventDefault();

    const id = idInput.value;
    const produtoData = {
        nome: produtoInput.value.trim(),
        quantidade: Number(quantidadeInput.value),
        precoUnitario: Number(precoUnitarioInput.value),
        categoria: categoriaInput.value.trim(),
        observacao: observacoesInput.value.trim()
    };

    try {
        let res;
        if (id) {
            // Editar
            res = await fetch(`${apiUrl}/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(produtoData)
            });
        } else {
            // Criar novo
            res = await fetch(apiUrl, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(produtoData)
            });
        }

        if (!res.ok) throw new Error('Erro ao salvar produto');

        await res.json(); // Produto salvo, não precisa usar o retorno aqui
        limparFormulario();

        alert('Produto salvo com sucesso!');
        window.location.href = 'estoque.html'; 
    } catch (error) {
        console.error(error);
        alert("Erro ao salvar produto: " + error.message);
    }
}


function editarProduto(id) {
    fetch(`${apiUrl}/${id}`)
        .then(res => {
            if (!res.ok) throw new Error('Produto não encontrado');
            return res.json();
        })
        .then(produto => {
            idInput.value = produto.id;
            produtoInput.value = produto.nome;
            quantidadeInput.value = produto.quantidade;
            precoUnitarioInput.value = produto.precoUnitario;
            categoriaInput.value = produto.categoria || '';
            observacoesInput.value = produto.observacao || '';
            window.scrollTo({ top: 0, behavior: 'smooth' });
        })
        .catch(err => {
            console.error(err);
            alert("Erro ao carregar produto para edição.");
        });
}

async function excluirProduto(id) {
    if (!confirm("Tem certeza que deseja excluir este produto?")) return;

    try {
        const res = await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
        if (!res.ok) throw new Error('Erro ao excluir produto');

        alert('Produto excluído com sucesso!');
    } catch (error) {
        console.error(error);
        alert("Erro ao excluir produto: " + error.message);
    }
}

// Função auxiliar para obter parâmetros da URL
function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

// Se houver um ID na URL, carregar o produto para edição
window.addEventListener('DOMContentLoaded', async () => {
    const id = getQueryParam('id');
    if (id) {
        try {
            const res = await fetch(`${apiUrl}/${id}`);
            if (!res.ok) throw new Error('Produto não encontrado');
            const produto = await res.json();

            idInput.value = produto.id;
            produtoInput.value = produto.nome;
            quantidadeInput.value = produto.quantidade;
            precoUnitarioInput.value = produto.precoUnitario;
            categoriaInput.value = produto.categoria || '';
            observacoesInput.value = produto.observacao || '';
        } catch (error) {
            console.error(error);
            alert("Erro ao carregar dados do produto para edição.");
        }
    }
});


// Eventos
form.addEventListener('submit', salvarProduto);
cancelarBtn.addEventListener('click', (event) => {
    event.preventDefault();
    window.location.href = 'estoque.html';
});

