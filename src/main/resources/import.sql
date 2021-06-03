insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into restaurante (nome, taxa_frete, cozinha_codigo ) values ('Thai Gourmet', 10.00, 1);
insert into restaurante (nome, taxa_frete, cozinha_codigo) values ('Thai Delivery', 9.50, 1);
insert into restaurante (nome, taxa_frete, cozinha_codigo) values ('Tuk Tuk Comida Indiana', 15.00, 2);

insert into forma_pagamento (id, descricao) values (1, 'Dinheiro - Á vista');
insert into forma_pagamento (id, descricao) values (2, 'Cheque - Parcelado');
insert into forma_pagamento (id, descricao) values (3, 'Cartão de Crédito');
insert into forma_pagamento (id, descricao) values (4, 'Cartão de Débito');

insert into estado (id, nome) values (1, 'São Paulo');
insert into estado (id, nome) values (2, 'Paraná');
insert into estado (id, nome) values (3, 'Rio de Janeiro');

insert into cidade (id, nome, estado_id) values (1, 'Paraguaçu Paulista', 1);
insert into cidade (id, nome, estado_id) values (2, 'Assis', 1);
insert into cidade (id, nome, estado_id) values (3, 'Borá', 1);
insert into cidade (id, nome, estado_id) values (4, 'Guaratuba', 2);
insert into cidade (id, nome, estado_id) values (5, 'Rio de Janeiro', 3);
