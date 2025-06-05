# 🛣️ Project Roadmap

This document outlines the steps followed for the complete and professional development of the **Digital Menu** system, including objectives and features.

---

## 1. 📌 Planning and Specification

- Project name definition  
- Target audience identification (restaurants, diners, etc.)  
- Listing of main and secondary features  
- Definition of functional and non-functional requirements  
- Full stack selection (frontend, backend, database, tools)

---

## 2. 🧩 Design and Architecture

- Use case diagram  
- Class diagram (system entities)  
- Visual screen prototype (Figma or hand-drawn)  
- System architecture (MVC, RESTful API, well-defined layers)  
- Organized folder structure (backend and frontend)

---

## 3. 🗃️ Database Modeling

- Creation of the ER Diagram (Entity-Relationship)  
- Table documentation: name, description, data type, primary/foreign keys  
- Database creation using migrations (structure automation)

---

## 4. 🛠️ Backend Implementation (Spring Boot)

- RESTful API with controllers, services, and repositories  
- Validations, use of DTOs, and global error handling  
- Automatic route documentation using Swagger  
- Basic tests with JUnit

---

## 5. 📱 Frontend Implementation (React Native + Tailwind)

- Modern, responsive design with dark mode support  
- Screens to be developed:
  - Digital menu  
  - Customer ordering  
  - Attendant dashboard  
  - Login/admin panel  
- Communication with the backend via fetch or axios  
- Visual feedback: toasts, alerts, and loaders

---

## 6. 🚀 Extra Features

- PDF generation of order reports  
- Filters by category, status, and date  
- Local SQLite database or support for PostgreSQL

---

## 7. 📄 Professional Documentation

- Complete README on GitHub (EN/PT)  
- User manual (how to use the system)  
- Technical documentation (API routes, database structure, architecture)  
- Inclusion of demo screenshots or GIFs  
- Final version packaged (.apk/.exe via Electron or mobile build)

---

## ✅ Planned Features

### 👨‍🍳 1. Menu Management (Admin Mode)

- Add, edit, and delete dishes  
- Organize dishes by category: Starters, Pasta, Drinks, etc.  
- Add photo, description, price, and availability  
- Enable/disable dishes

---

### 🍽️ 2. Menu Visualization (Customer Mode)

- Clean and intuitive screen to view dishes  
- Filter by category  
- Search by dish name  
- Responsive layout (tablet or monitor)

---

### 🧾 3. Order Registration (Attendant Mode)

- Select dishes to build the order  
- Add item notes (e.g., “no onions”)  
- Link order to a table or customer name  
- Order status: In preparation, Ready, Delivered  
- Finalize order and store in history

---

### 📊 4. Reports and History

- View orders by day  
- See number of dishes sold  
- Generate PDF reports (filtered by day/month)  
- Display total revenue by period

---

### 🔐 5. Access Control

- Login screen with access levels  
- Admin has full access  
- Attendant can only register orders and view menu

---

### ⚙️ Extras (If Time Allows)

- Self-service mode for customers  
- Print/send order to the kitchen  
- Dashboard with charts (e.g., daily revenue, top-selling dishes)  
- Light/dark theme toggle



---



# 🛣️ Project Roadmap

Este documento descreve as etapas seguidas para o desenvolvimento completo e profissional do sistema **Digital Menu**, incluindo objetivos e funcionalidades.

---

## 1. 📌 Planejamento e Especificação

- Definição do nome do projeto  
- Identificação do público-alvo (restaurantes, lanchonetes, etc.)  
- Levantamento das funcionalidades principais e secundárias  
- Elaboração dos requisitos funcionais e não funcionais  
- Escolha da stack completa (frontend, backend, banco de dados, ferramentas)

---

## 2. 🧩 Design e Arquitetura

- Diagrama de caso de uso  
- Diagrama de classes (entidades do sistema)  
- Protótipo visual das telas (Figma ou esboço manual)  
- Arquitetura do sistema (MVC, API RESTful, camadas bem definidas)  
- Estrutura de pastas organizada (backend e frontend)

---

## 3. 🗃️ Modelagem de Banco de Dados

- Criação do Diagrama ER (Entidade-Relacionamento)  
- Documentação das tabelas: nome, descrição, tipo de dado, chaves primárias/estrangeiras  
- Criação do banco com uso de migrations (automatização de estrutura)

---

## 4. 🛠️ Implementação Backend (Spring Boot)

- API RESTful com controllers, services e repositories  
- Validações, uso de DTOs e tratamento global de erros  
- Documentação automática das rotas com Swagger  
- Testes básicos com JUnit

---

## 5. 📱 Implementação Frontend (React Native + Tailwind)

- Design moderno, responsivo e suporte a dark mode  
- Telas a serem desenvolvidas:
  - Menu digital
  - Pedido do cliente
  - Painel do atendente
  - Tela de login/admin  
- Comunicação com o backend via fetch ou axios  
- Feedback visual: toasts, alertas e carregamentos

---

## 6. 🚀 Recursos Extras

- Geração de relatórios de pedidos em PDF  
- Filtros por categoria, status e data  
- Banco local SQLite ou suporte ao PostgreSQL

---

## 7. 📄 Documentação Profissional

- README completo no GitHub (EN/PT)  
- Manual do usuário (como utilizar o sistema)  
- Documentação técnica (rotas da API, estrutura do banco de dados, arquitetura)  
- Inclusão de prints ou GIFs demonstrativos  
- Versão final empacotada (.apk/.exe via Electron ou build mobile)

---

## ✅ Funcionalidades

### 👨‍🍳 1. Administração do Cardápio

- Cadastro, edição e exclusão de pratos  
- Organização por categorias: Entradas, Massas, Bebidas etc.  
- Adição de foto, descrição, preço e disponibilidade  
- Ativação/desativação de pratos

---

### 🍽️ 2. Visualização do Cardápio (Modo Cliente)

- Tela limpa e intuitiva para visualização  
- Filtros por categoria  
- Busca por nome de prato  
- Visual responsivo (tablet ou monitor)

---

### 🧾 3. Registro de Pedidos (Modo Atendente)

- Seleção de pratos para montar o pedido  
- Observações por item (ex: “sem cebola”)  
- Associação a uma mesa ou nome do cliente  
- Status do pedido: Em preparo, Pronto, Entregue  
- Finalização e histórico de pedidos

---

### 📊 4. Relatórios e Histórico

- Visualização de pedidos por dia  
- Quantidade vendida por prato  
- Geração de relatórios em PDF (filtros por dia/mês)  
- Exibição do total faturado por período

---

### 🔐 5. Controle de Acesso

- Tela de login com níveis de acesso  
- Admin com acesso completo  
- Atendente apenas registra e visualiza pedidos/cardápio

---

### ⚙️ Extras (Se houver tempo)

- Modo autoatendimento para o cliente  
- Impressão/envio do pedido para cozinha  
- Dashboard com gráficos de faturamento e estatísticas  
- Alternância entre tema claro e escuro

