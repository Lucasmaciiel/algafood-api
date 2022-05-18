alter table restaurante
add column ativo tinyint(1) not null;

update restaurante set restaurante.ativo = true;