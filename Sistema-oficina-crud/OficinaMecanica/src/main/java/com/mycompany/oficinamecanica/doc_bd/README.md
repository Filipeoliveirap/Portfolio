# 📘 Documentação do Banco de Dados – Sistema Oficina CRUD  
# 📘 Database Documentation – Workshop CRUD System

Este sistema utiliza um banco de dados relacional MySQL. Abaixo estão as principais tabelas utilizadas, com suas colunas, chaves e relacionamentos.  
This system uses a MySQL relational database. Below are the main tables used, with their columns, keys, and relationships.

---

## 📑 Tabelas e Estrutura  
## 📑 Tables and Structure

### 🧍 clientes | clients  
Armazena os dados básicos dos clientes.  
Stores basic client data.

| Campo / Field | Tipo / Type       | Restrição / Constraint              |
|---------------|-------------------|-----------------------------------|
| id            | int               | PRIMARY KEY (auto_increment)       |
| nome / name   | varchar(100)      | NOT NULL                         |
| telefone / phone | varchar(15)     | NULLABLE                         |

---

### 📍 enderecos | addresses  
Relaciona um endereço a um cliente.  
Links an address to a client.

| Campo / Field | Tipo / Type       | Restrição / Constraint                |
|---------------|-------------------|-------------------------------------|
| id            | int               | PRIMARY KEY (auto_increment)         |
| rua / street  | varchar(100)      | NULLABLE                           |
| numero / number | varchar(10)      | NULLABLE                           |
| bairro / neighborhood | varchar(100) | NULLABLE                         |
| cidade / city | varchar(100)      | NULLABLE                           |
| estado / state | varchar(50)      | NULLABLE                           |
| cep / zip_code | varchar(10)      | NULLABLE                           |
| complemento / additional_info | text | NULLABLE                        |
| cliente_id / client_id | int       | FOREIGN KEY → clientes(id)           |

---

### 🚗 veiculos | vehicles  
Veículos cadastrados para cada cliente.  
Vehicles registered to each client.

| Campo / Field | Tipo / Type       | Restrição / Constraint                |
|---------------|-------------------|-------------------------------------|
| id            | int               | PRIMARY KEY (auto_increment)         |
| modelo / model | varchar(50)      | NOT NULL                           |
| placa / plate | varchar(10)       | UNIQUE, NOT NULL                   |
| ano / year    | int               | NULLABLE                           |
| cliente_id / client_id | int       | FOREIGN KEY → clientes(id)           |

---

### 🔧 servicos | services  
Serviços realizados em veículos.  
Services performed on vehicles.

| Campo / Field | Tipo / Type       | Restrição / Constraint                |
|---------------|-------------------|-------------------------------------|
| id            | int               | PRIMARY KEY (auto_increment)         |
| descricao / description | text     | NOT NULL                           |
| preco / price | decimal(10,2)     | NOT NULL                           |
| data / date   | date              | NOT NULL                           |
| veiculo_id / vehicle_id | int      | FOREIGN KEY → veiculos(id)           |

---

### 🧾 notas_fiscais | invoices  
Notas fiscais emitidas para serviços realizados.  
Invoices issued for completed services.

| Campo / Field | Tipo / Type       | Restrição / Constraint                |
|---------------|-------------------|-------------------------------------|
| id            | int               | PRIMARY KEY (auto_increment)         |
| numero / number | varchar(20)      | UNIQUE, NOT NULL                   |
| data_emissao / issue_date | date    | NOT NULL                           |
| valor_total / total_value | decimal(10,2) | NOT NULL                      |
| servico_id / service_id | int       | FOREIGN KEY → servicos(id)           |

---

## 🔗 Relacionamentos | Relationships

- Um **cliente** pode ter **múltiplos veículos** e **um endereço**.  
- A **client** can have **multiple vehicles** and **one address**.

- Um **veículo** pode ter **múltiplos serviços**.  
- A **vehicle** can have **multiple services**.

- Cada **serviço** pode gerar uma **nota fiscal**.  
- Each **service** can generate an **invoice**.

- As chaves estrangeiras garantem integridade referencial entre as tabelas.  
- Foreign keys ensure referential integrity between tables.

---

## 📂 Scripts e Diagrama | Scripts and Diagram

- O script completo de criação das tabelas está em: [`estrutura.sql`](./estrutura.sql)  
- The full table creation script is in: [`estrutura.sql`](./estrutura.sql)

- O diagrama entidade-relacionamento está disponível em: [`modelo_ER.png`](./modelo_ER.png)  
- The entity-relationship diagram is available at: [`modelo_ER.png`](./modelo_ER.png)

---

> Para mais detalhes sobre o uso do banco no sistema, consulte o arquivo `README.md` na raiz do projeto.  
> For more details on how the database is used in the system, see the `README.md` file in the project root.
