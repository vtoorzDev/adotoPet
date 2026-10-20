CREATE TABLE adotante (
    id BINARY(16) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    adotante_status VARCHAR(255) NOT NULL
);