CREATE DATABASE IF NOT EXISTS decompositor_db;
USE decompositor_db;

CREATE TABLE IF NOT EXISTS registros_decomposicao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    qtd_lixo_organico DOUBLE NOT NULL,
    descricao_lixo TEXT NOT NULL,
    qtd_adubo DOUBLE NOT NULL,
    qtd_chorume DOUBLE NOT NULL,
    tempo_decomposicao_dias INT NOT NULL,
    data_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);