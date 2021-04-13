insert into recurso (id, nome, status) values (1, 'EDITAR_PERFIL', b'1');
insert into recurso (id, nome, status) values (2, 'VISUALIZAR_PERFIL', b'1');
insert into recurso (id, nome, status) values (3, 'EDITAR_POST', b'1');
insert into recurso (id, nome, status) values (4, 'VISUALIZAR_POST', b'1');
insert into recurso (id, nome, status) values (5, 'EXCLUIR_POST', b'1');

insert into usuario (id, nome, login, senha, email, data_nascimento, status) values (1, 'Vanilton Filho', 'vanilton-filho', '123', 'vanilton.alv3sfilho@gmail.com', '1996-03-05', b'1');
insert into usuario (id, nome, login, senha, email, data_nascimento, status) values (2, 'George', 'george', '123', 'george@email.com', '1980-01-01', b'1');
insert into usuario (id, nome, login, senha, email, data_nascimento, status) values (3, 'Mary Doe', 'mariadoe', '123', 'mariadoe@email.com', '1995-08-09', b'1');
insert into usuario (id, nome, login, senha, email, data_nascimento, status) values (4, 'Beltrano', 'beltrano', '123', 'beltrano@email.com', '1995-08-09', b'0');
insert into usuario (id, nome, login, senha, email, data_nascimento, status) values (5, 'Manolo', 'manolo', '123', 'manolo@email.com', '1995-08-09', b'1');
insert into usuario (id, nome, login, senha, email, data_nascimento, status) values (6, 'Cicrano', 'cicrano', '123', 'cicrano@email.com', '1995-08-09', b'0');


insert into permissao_usuario (usuario_id, recurso_id) values (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (2, 2), (3, 1), (3, 2), (4, 1), (4, 2), (4, 5), (5, 2), (6, 1), (6, 2), (6, 3), (6, 4), (6, 5);
