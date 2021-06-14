insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (1, Argentina
insert into cozinha (id, nome) values (2, 'Brasileira');

insert into estado (id, nome) values (1, 'São Paulo');
insert into estado (id, nome) values (2, 'Paraná');
insert into estado (id, nome) values (3, 'Rio de Janeiro');

insert into cidade (id, nome, estado_id) values (1, 'Paraguaçu Paulista', 1);
insert into cidade (id, nome, estado_id) values (2, 'Assis', 1);
insert into cidade (id, nome, estado_id) values (3, 'Borá', 1);
insert into cidade (id, nome, estado_id) values (4, 'Guaratuba', 2);
insert into cidade (id, nome, estado_id) values (5, 'Rio de Janeiro', 3);

insert into restaurante (id, nome, taxa_frete, cozinha_codigo, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id, data_cadastro, data_atualizacao ) values (1, 'Thai Gourmet', 10.00, 1, 'Jardim América', '18500-000', 'Casa', 'Avenida Rui Barbosa', '1851', 1, utc_timestamp, utc_timestamp );
insert into restaurante (id, nome, taxa_frete, cozinha_codigo, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_codigo, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15.00, 2, utc_timestamp, utc_timestamp);

insert into produto (id, ativo, descricao, nome, preco, restaurante_id) values (1, true, 'Pizza de Calabresa', 'Pizza', 59.90, 1);
insert into produto (id, ativo, descricao, nome, preco, restaurante_id) values (2, true, 'Pizza de Bacon', 'Pizza', 69.90, 2);
insert into produto (id, ativo, descricao, nome, preco, restaurante_id) values (3, true, 'Pizza de Mussarela', 'Pizza', 79.90, 3);
insert into produto (id, ativo, descricao, nome, preco, restaurante_id) values (4, true, 'Pizza de Marguerita', 'Pizza', 89.90, 1);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de Crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de Débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 1), (2, 2), (2, 3),(3, 1), (3, 3);