# 🧱 Tech Stack

This document outlines the technologies used in the development of the **Digital Menu** system, along with their justifications. The choices were made focusing on productivity, best practices, maintainability, and performance.

---

## 🎨 Frontend

| Item                    | Tool / Library                     | Justification                                                                 |
|-------------------------|------------------------------------|-------------------------------------------------------------------------------|
| Language                | TypeScript                         | Strong typing, robust, industry standard                                     |
| Framework               | React Native (with Expo)           | Fast, cross-platform, easy integration                                       |
| Navigation              | `react-navigation`                 | Full route control (stack, tabs, drawer etc.)                                |
| Styling                 | Tailwind (via `nativewind`)        | Modern and productive styling with utility classes                           |
| HTTP Requests           | `fetch` or `axios`                 | Communication with the API                                                   |
| State Management        | `Zustand`                          | Simple and scalable global state                                             |
| Local Storage           | `AsyncStorage`                     | Store login, cache and local configurations                                  |
| UI Library              | `react-native-paper`               | Ready-to-use, responsive and accessible components                           |
| PDF viewer / download   | `expo-print` + `expo-sharing`      | Generate, view and share PDF reports                                         |
| Forms                  | `react-hook-form` + `zod`           | Typed validation and form control                                            |
| Internationalization    | `react-i18next`                    | Support for multiple languages                                               |
| Animations              | `moti` + `react-native-reanimated` | Modern microinteractions and animations                                      |
| Theming                 | `nativewind` + Context API         | Light/dark theme support                                                     |

---

## ⚙️ Backend

| Item              | Tool / Library            | Justification                                                   |
|-------------------|---------------------------|------------------------------------------------------------------|
| Language          | Kotlin                    | Modern, concise, used by companies for Android and backend       |
| Framework         | Spring Boot               | Industry standard for REST APIs                                 |
| ORM               | JPA (Hibernate)           | Robust object-relational mapping                                |
| Database          | PostgreSQL                | Robust, relational, widely used in production                   |
| Authentication    | Spring Security + JWT     | Secure token-based authentication                               |
| Validation        | Bean Validation           | Simple annotations for validations                              |
| PDF Generator     | OpenPDF                   | For generating PDF reports                                      |
| API Documentation | Swagger                   | Interactive API interface                                       |
| Migrations        | Flyway                    | Database version control                                        |
| Testing           | JUnit 5 + MockK           | Modern unit and integration testing                             |

---

## 🧪 General Tools

| Item                | Tool                                 | Justification                              |
|---------------------|--------------------------------------|--------------------------------------------|
| Backend Testing     | JUnit, Mockito                       | Unit and mocked testing                    |
| Frontend Testing    | Jest + React Native Testing Library  | Ensure app stability                       |
| Linting             | ESLint + Prettier                    | Clean code standard                        |
| Version Control     | Git + GitHub                         | Versioning and portfolio exposure          |




---



# 🧱 Stack Tecnológica

Este documento descreve as tecnologias utilizadas no desenvolvimento do sistema **Digital Menu**, com suas justificativas. A escolha foi feita com foco em produtividade, boas práticas de mercado, manutenibilidade e performance.

---

## 🎨 Frontend

| Item                   | Ferramenta / Lib                    | Justificativa                                                                 |
|------------------------|-------------------------------------|-------------------------------------------------------------------------------|
| Linguagem              | TypeScript                          | Tipagem forte, robusta, padrão do mercado                                    |
| Framework              | React Native (com Expo)             | Rápido, multiplataforma, fácil integração                                    |
| Navegação              | `react-navigation`                  | Controle completo de rotas (pilha, tabs, drawer etc.)                        |
| Estilização            | Tailwind (via `nativewind`)         | Estilo moderno e produtivo com classes utilitárias                           |
| Requisições HTTP       | `fetch` ou `axios`                  | Comunicação com a API                                                        |
| Gerenciamento de estado| `Zustand`                           | Estado global simples e escalável                                            |
| Armazenamento local    | `AsyncStorage`                      | Guardar login, cache e configs locais                                        |
| Biblioteca UI          | `react-native-paper`                | Componentes prontos, responsivos e acessíveis                                |
| PDF viewer / download  | `expo-print` + `expo-sharing`       | Gerar, visualizar e compartilhar relatórios em PDF                           |
| Formulários            | `react-hook-form` + `zod`           | Validação e controle de formulários tipados                                  |
| Internacionalização    | `react-i18next`                     | Suporte a múltiplos idiomas                                                  |
| Animações              | `moti` + `react-native-reanimated`  | Microinterações e animações modernas                                         |
| Temas                  | `nativewind` + Context API          | Suporte a tema claro/escuro                                                  |

---

## ⚙️ Backend

| Item             | Ferramenta / Lib       | Justificativa                                                   |
|------------------|------------------------|------------------------------------------------------------------|
| Linguagem        | Kotlin                 | Moderna, concisa e usada em empresas para Android e backend     |
| Framework        | Spring Boot            | Padrão de mercado para APIs REST                                |
| ORM              | JPA (Hibernate)        | Mapeamento objeto-relacional, robusto                           |
| Banco de dados   | PostgreSQL             | Robusto, relacional, muito usado em produção                   |
| Autenticação     | Spring Security + JWT  | Autenticação segura baseada em token                            |
| Validação        | Bean Validation        | Validações com anotações simples                                |
| Gerador de PDF   | OpenPDF                | Para gerar relatórios em PDF                                    |
| Documentação API | Swagger                | Interface interativa da API                                     |
| Migrations       | Flyway                 | Controle de versões do banco de dados                           |
| Testes           | JUnit 5 + MockK        | Testes unitários e de integração modernos                       |

---

## 🧪 Ferramentas Gerais

| Item                | Ferramenta                             | Justificativa                              |
|---------------------|----------------------------------------|--------------------------------------------|
| Testes Backend      | JUnit, Mockito                         | Testes unitários e mockados                |
| Testes Frontend     | Jest + React Native Testing Library    | Garantir estabilidade no app               |
| Lint                | ESLint + Prettier                      | Padrão de código limpo                     |
| Controle de Versão  | Git + GitHub                           | Versionamento e portfólio                  |
