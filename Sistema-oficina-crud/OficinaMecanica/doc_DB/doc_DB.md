# Guia de Modelagem do Banco de Dados

Este documento apresenta a estrutura do banco de dados utilizada no sistema de oficina mecânica.

## Entidades e Relacionamentos

### Cliente
- **id_cliente** (INT, PRIMARY KEY, AUTO_INCREMENT) - Identificador único do cliente.
- **nome** (VARCHAR) - Nome do cliente.
- **telefone** (VARCHAR) - Contato telefônico do cliente.
- **endereço** (TEXT) - Endereço do cliente.

### Veículo
- **id_veiculo** (INT, PRIMARY KEY, AUTO_INCREMENT) - Identificador único do veículo.
- **modelo** (VARCHAR) - Modelo do veículo.
- **placa** (VARCHAR, UNIQUE) - Placa do veículo.
- **ano** (INT) - Ano de fabricação do veículo.
- **id_cliente** (INT, FOREIGN KEY) - Cliente proprietário do veículo.

### Serviço
- **id_servico** (INT, PRIMARY KEY, AUTO_INCREMENT) - Identificador único do serviço.
- **descricao** (TEXT) - Descrição do serviço realizado.
- **preco** (DECIMAL) - Preço do serviço prestado.
- **data_servico** (DATE) - Data de realização do serviço.
- **id_veiculo** (INT, FOREIGN KEY) - Veículo ao qual o serviço foi prestado.

### Nota Fiscal
- **id_nota** (INT, PRIMARY KEY, AUTO_INCREMENT) - Identificador único da nota fiscal.
- **numero_nota** (VARCHAR, UNIQUE) - Número da nota fiscal.
- **data_emissao** (DATE) - Data de emissão da nota.
- **valor_total** (DECIMAL) - Valor total da nota fiscal.
- **id_servico** (INT, FOREIGN KEY) - Serviço relacionado à nota fiscal.

### Relatório
- **id_relatorio** (INT, PRIMARY KEY, AUTO_INCREMENT) - Identificador único do relatório.
- **mes_ano** (VARCHAR) - Mês e ano do relatório.
- **total_servicos** (INT) - Total de serviços prestados no período.
- **receita_total** (DECIMAL) - Receita total gerada no período.

## Observações
- Todas as tabelas utilizam **chaves primárias** para garantir a unicidade dos registros.
- **Chaves estrangeiras** garantem a integridade referencial entre os dados.
- O banco de dados foi modelado para otimizar consultas e facilitar a extração de relatórios de gestão.

Este modelo pode ser ajustado conforme necessário para atender a novos requisitos do sistema.

