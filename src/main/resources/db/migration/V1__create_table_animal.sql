CREATE TABLE animal(
    id BINARY(16) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL ,
    descricao VARCHAR(255) NOT NULL,
    idade INTEGER NOT NULL ,
    data_registro DATE NOT NULL,
    especie_animal VARCHAR(255) NOT NULL,
    porte_animal VARCHAR(255) NOT NULL ,
    sexo_animal VARCHAR(255) NOT NULL ,
    status_animal VARCHAR(255) NOT NULL
);