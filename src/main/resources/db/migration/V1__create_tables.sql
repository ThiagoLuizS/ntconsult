create table if not exists tb_pauta (
    id_pauta int8 not null,
    nome varchar(150) not null,
    descricao varchar(250),
    data_cadastro timestamp,
    expiracao_sessao timestamp,
    calculado bool
);

alter table tb_pauta ADD CONSTRAINT tb_pauta_pkey PRIMARY KEY (id_pauta);

create table if not exists tb_voto (
    id_voto int8 not null,
    pauta_id int8 not null,
    opcao varchar(3) not null,
    cpf varchar(11) not null
);

alter table tb_voto ADD CONSTRAINT tb_voto_pkey PRIMARY KEY (id_voto);
alter table tb_voto ADD CONSTRAINT tb_voto_pauta_fkey FOREIGN KEY (pauta_id) REFERENCES tb_pauta (id_pauta);

CREATE SEQUENCE seq_id_pauta
    INCREMENT 1
    START 1;

CREATE SEQUENCE seq_id_voto
    INCREMENT 1
    START 1;