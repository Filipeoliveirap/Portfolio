document.addEventListener('DOMContentLoaded', () => {
    const tabelaClientes = document.getElementById('tabela-clientes');
    const adicionarClienteBtn = document.getElementById('adicionar-cliente-btn');
    const modal = document.getElementById('cadastro-cliente-modal');
    const closeBtn = document.querySelector('.close-button');
    const cancelarBtn = document.getElementById('cancelar-btn');
    const cadastroClienteForm = document.getElementById('cadastro-cliente-form');
    const pesquisarBtn = document.getElementById('pesquisar-btn');
    const params = new URLSearchParams(window.location.search);
    const clienteId = params.get('id');

    if (adicionarClienteBtn) {
        adicionarClienteBtn.addEventListener('click', () => {
            modal.style.display = "block";
            document.getElementById('cadastro-cliente-form').reset();
            document.getElementById('id').value = '';
            document.getElementById('titulo-formulario').innerText = 'Cadastro de Cliente';
        });
    }

    if (closeBtn) {
        closeBtn.addEventListener('click', () => {
            modal.style.display = "none";
        });
    }

    if (cancelarBtn) {
        cancelarBtn.addEventListener('click', () => {
            window.location.href = 'clientes.html';
        });
    }

    window.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });

    if (cadastroClienteForm) {
        cadastroClienteForm.addEventListener('submit', async function (event) {
            event.preventDefault();
            
            const id = document.getElementById('id').value;
            const nome = document.getElementById('nome').value;
            const cpf = document.getElementById('cpf').value;
            const telefone = document.getElementById('telefone').value;
            const email = document.getElementById('email').value;
            const endereco = document.getElementById('endereco').value;


            let hasErrors = false;

            if (!nome) {
                formErrorResponse('nome-error', 'Nome é obrigatório');
                hasErrors = true;
            } else {
                 document.getElementById('nome-error').textContent = '';
            }
            if (!cpf) {
                formErrorResponse('cpf-error', 'CPF é obrigatório');
                hasErrors = true;
            } else {
                 document.getElementById('cpf-error').textContent = '';
            }
            if (!telefone) {
                formErrorResponse('telefone-error', 'Telefone é obrigatório');
                hasErrors = true;
            } else {
                 document.getElementById('telefone-error').textContent = '';
            }
            if (!email) {
                formErrorResponse('email-error', 'Email é obrigatório');
                hasErrors = true;
            } else if (!isValidEmail(email)) {
                formErrorResponse('email-error', 'Email inválido');
                hasErrors = true;
            } else {
                 document.getElementById('email-error').textContent = '';
            }

            if (!endereco) {
                formErrorResponse('endereco-error', 'Endereço é obrigatório');
                hasErrors = true;
            } else {
                document.getElementById('endereco-error').textContent = '';
            }

            if (hasErrors) {
                return;
            }

            const cliente = {
                nome,
                cpf,
                telefone,
                email,
                endereco: endereco || null
            };

            try {
                let response;
                if (id) {
                    response = await fetch(`http://localhost:8080/clientes/${id}`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(cliente)
                    });
                } else {
                    response = await fetch('http://localhost:8080/clientes', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(cliente)
                    });
                }

                if (!response.ok) {
                    const errorData = await response.json();
                    throw new Error(errorData.erro || 'Erro ao salvar/atualizar o cliente');
                }

                const clienteSalvo = await response.json();
                await alertaSucesso('Cliente atualizado com sucesso!');
                

                document.getElementById('cadastro-cliente-form').reset();
                modal.style.display = "none";
                window.location.href = 'clientes.html';

            } catch (error) {
                console.error('Erro:', error.message);
                alert(error.message);

                // Se o erro for relacionado ao CPF duplicado, exiba a mensagem no campo de CPF
                if (error.message.includes("CPF duplicado")) {
                    formErrorResponse('cpf-error', error.message);
                } else {
                    await alertaErro('Erro ao salvar cliente', error.message);
                    formErrorResponse('form-error', error.message); // Para outros erros genéricos
                }
            }
        });
    }

    if(pesquisarBtn){
        pesquisarBtn.addEventListener('click', () => {
            const termoPesquisa = document.getElementById('pesquisar-cliente').value.toLowerCase();
            const linhas = tabelaClientes.querySelectorAll('tbody tr');

            linhas.forEach(linha => {
                const nome = linha.querySelector('td:nth-child(1)').textContent.toLowerCase();
                const email = linha.querySelector('td:nth-child(2)').textContent.toLowerCase();
                const telefone = linha.querySelector('td:nth-child(3)').textContent.toLowerCase();

                if (nome.includes(termoPesquisa) || email.includes(termoPesquisa) || telefone.includes(termoPesquisa)) {
                    linha.style.display = '';
                } else {
                    linha.style.display = 'none';
                }
            });
        });
    }

    if (clienteId) {
        // Modo de edição
        document.getElementById('titulo-formulario').innerText = 'Editar Cliente';

        fetch(`http://localhost:8080/clientes/${clienteId}`)
            .then(res => res.json())
            .then(cliente => {
                document.getElementById('id').value = cliente.id;
                document.getElementById('nome').value = cliente.nome;
                document.getElementById('cpf').value = cliente.cpf;
                document.getElementById('telefone').value = cliente.telefone;
                document.getElementById('email').value = cliente.email;
                document.getElementById('endereco').value = cliente.endereco;
            })
            .catch(async err => {
                console.error('Erro ao carregar cliente:', err);
                await alertaErro('Erro ao carregar cliente', 'Não foi possível carregar os dados do cliente.');
            });
    }

});

// Função para mostrar a mensagem de erro no campo
function formErrorResponse(inputID, response){
    const inputElement = document.getElementById(inputID);
    if (inputElement) {
        inputElement.textContent = response;
    }
}

// Função para validar o e-mail
function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

// Função para adicionar cliente na tabela
function adicionarClienteNaTabela(cliente) {
    const tabelaElement = document.getElementById('tabela-clientes')
    if(!tabelaElement) return;
    const tabela = tabelaElement.querySelector('tbody');
    const novaLinha = tabela.insertRow();
    novaLinha.setAttribute('data-id', cliente.id);
    novaLinha.innerHTML = `
        <td class="px-5 py-5 border-b border-gray-200 text-sm">${cliente.nome}</td>
        <td class="px-5 py-5 border-b border-gray-200 text-sm">${cliente.cpf}</td>
        <td class="px-5 py-5 border-b border-gray-200 text-sm">${cliente.telefone}</td>
        <td class="px-5 py-5 border-b border-gray-200 text-sm">${cliente.email}</td>
        <td class="px-5 py-5 border-b border-gray-200 text-sm">
            ${cliente.endereco && cliente.endereco.trim() !== "" ? cliente.endereco : 'Não disponível'}
        </td>
        <td class="px-5 py-5 border-b border-gray-200 text-sm space-x-2">
            <button
                class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded-md shadow-md focus:outline-none focus:shadow-outline text-xs"
                onclick="editarCliente(${cliente.id})"
            >
                <i class="fas fa-edit"></i> Editar
            </button>
            <button
                class="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded-md shadow-md focus:outline-none focus:shadow-outline text-xs"
                onclick="excluirCliente(${cliente.id})"
            >
                <i class="fas fa-trash-alt"></i> Excluir
            </button>
        </td>
    `;
}
