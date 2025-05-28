# Sistema de Gerenciamento de Oficina Mecânica

Este é um sistema desktop para gerenciamento de oficina mecânica. O backend é desenvolvido em Java com Spring Boot e o frontend utiliza Electron, oferecendo uma experiência moderna e funcional para usuários.

## Funcionalidades

- Cadastro de clientes, serviços e produtos
- Controle de estoque
- Histórico de serviços realizados por cliente
- Geração de relatórios em PDF
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

# Instruções para Executar o Projeto Completo

## Como Executar

### 1. Rodando o Backend (Spring Boot)

1. Abra o projeto no terminal


2. Execute o backend com o Maven Wrapper:

- No Linux/macOS:
```bash
./mvnw spring-boot:run
```

- No Windows (Prompt de Comando ou PowerShell):
```bash
mvnw.cmd spring-boot:run
```

3. Aguarde até o backend iniciar. Por padrão, ele estará disponível em [http://localhost:8080](http://localhost:8080).

---

### 2. Rodando o Frontend (React/Node.js)

1. Abra um novo terminal e navegue até o diretório do frontend (por exemplo, `frontEnd`):

```bash
cd frontEnd
```

2. Instale as dependências do frontend (só precisa rodar uma vez):

```bash
npm install
```

3. Inicie o servidor de desenvolvimento do frontend:

```bash
npm start
```

4. O frontend abrirá automaticamente no navegador, normalmente em [http://localhost:3000](http://localhost:3000).

---

### Observações

- Certifique-se de que o backend esteja rodando antes de iniciar o frontend, para que a API esteja disponível para as requisições.
- Caso precise alterar a porta ou outras configurações, veja os arquivos `application.properties` no backend e os arquivos de configuração do frontend.

