INSERT INTO Pacote_Tarifas (nome, valor_minimo, valor_maximo) VALUES ('Pacote padronizado I', '0.00', '1.00');
INSERT INTO Pacote_Tarifas (nome, valor_minimo, valor_maximo) VALUES ('Pacote padronizado II', '0.00', '1.00');

INSERT INTO cliente (name, cpf, data_Nasc, pacote_tarifas_id) VALUES ('João', '123.456.789-10', '1978-02-23', 1);