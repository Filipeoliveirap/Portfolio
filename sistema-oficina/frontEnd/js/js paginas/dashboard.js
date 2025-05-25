const ctx = document.getElementById('graficoServicos').getContext('2d');

const meuGrafico = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['Jan', 'Fev', 'Mar', 'Abr'], 
        datasets: [{
            label: 'Serviços Realizados',
            data: [5, 7, 3, 8],
            backgroundColor: '#1abc9c'
        }]
    },
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true 
            }
        }
    }
});

fetch('http://localhost:8080/dashboard/servicos-por-mes')
  .then(response => response.json())
  .then(data => {
    const labels = Object.keys(data);
    const valores = Object.values(data);

    // Atualiza o gráfico com os novos dados
    meuGrafico.data.labels = labels;
    meuGrafico.data.datasets[0].data = valores;
    meuGrafico.update();
  })
  .catch(error => console.error('Erro ao buscar dados:', error));

  //função para carregar o histórico de serviços
  async function carregarHistoricoServicos() {
    try {
        const response = await fetch('http://localhost:8080/dashboard/historico');
        if (!response.ok) {
            throw new Error('Erro ao carregar histórico de serviços');
        }
        const dados = await response.json();

        // Preenche a tabela com os dados recebidos
        const tbody = document.querySelector('#historicoServicos tbody');
        tbody.innerHTML = ''; 
        Servicos.forEach(servico => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${servico.clienteNome}</td>
                <td>${servico.descricao}</td>
                <td>${formatarData(servico.dataRealizacao)}</td> <!-- Formatar a data -->
            `;
            tbody.appendChild(row);
        });
    } catch (error) {
        console.error('Erro:', error.message);
        alert('Não foi possível carregar o histórico de serviços.');
    }
}

// Função para formatar a data 
function formatarData(dataISO) {
    if (!dataISO) return 'Data não disponível';
    const data = new Date(dataISO);
    const dia = String(data.getDate()).padStart(2, '0');
    const mes = String(data.getMonth() + 1).padStart(2, '0'); 
    const ano = data.getFullYear();
    return `${dia}/${mes}/${ano}`;
}
            
    
