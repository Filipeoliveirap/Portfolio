-- Script de criação das tabelas do sistema oficina CRUD

CREATE DATABASE oficina;
USE oficina;

-- Tabela clientes
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(15)
);

-- Tabela enderecos
CREATE TABLE enderecos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(100),
    numero VARCHAR(10),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    cep VARCHAR(10),
    complemento TEXT,
    cliente_id INT,
    CONSTRAINT fk_endereco_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);

-- Tabela veiculos
CREATE TABLE veiculos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(50) NOT NULL,
    placa VARCHAR(10) NOT NULL UNIQUE,
    ano INT,
    cliente_id INT,
    CONSTRAINT fk_veiculo_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);

-- Tabela servicos
CREATE TABLE servicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    data DATE NOT NULL,
    veiculo_id INT,
    CONSTRAINT fk_servico_veiculo FOREIGN KEY (veiculo_id) REFERENCES veiculos(id) ON DELETE SET NULL
);

-- Tabela notas_fiscais
CREATE TABLE notas_fiscais (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(20) NOT NULL UNIQUE,
    data_emissao DATE NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    servico_id INT,
    CONSTRAINT fk_nota_servico FOREIGN KEY (servico_id) REFERENCES servicos(id) ON DELETE SET NULL
);
