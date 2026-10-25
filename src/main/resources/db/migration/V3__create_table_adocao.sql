CREATE TABLE adocao(
    id BINARY(16) PRIMARY KEY,
    adotante_id BINARY(16) NOT NULL,
    animal_id BINARY(16) NOT NULL,
    data_adocao DATE,
    status_adocao VARCHAR(255) NOT NULL,

    FOREIGN KEY(adotante_id) REFERENCES adotante(id),
    FOREIGN KEY (animal_id) REFERENCES animal(id),
    UNIQUE (animal_id)

);