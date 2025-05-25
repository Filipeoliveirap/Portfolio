# Sistema de Gerenciamento de Oficina Mecânica

Este é um sistema desktop completo para gerenciamento de oficinas mecânicas. O backend é desenvolvido em Java com Spring Boot e o frontend utiliza Electron, oferecendo uma experiência moderna e funcional para usuários.

## Funcionalidades

- Cadastro de clientes, veículos, serviços e produtos
- Controle automático de estoque
- Histórico de serviços realizados por cliente e veículo
- Geração de relatórios em PDF (por data, cliente e estoque baixo)
- Busca e filtros dinâmicos
- Validações e tratamento de erros
- Integração completa entre frontend (Electron) e backend (Spring Boot + SQLite)

## Tecnologias Utilizadas

### Backend
- Java 17
- Spring Boot
- SQLite
- JPA / Hibernate

### Frontend
- Electron
- HTML/CSS/JavaScript
- Fetch API para comunicação com o backend

## Como Executar

1. Clone o repositório
2. No diretório `backend`, execute:
   ```bash
   ./mvnw spring-boot:run

