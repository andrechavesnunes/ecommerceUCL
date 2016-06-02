# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table categoria (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  constraint pk_categoria primary key (id))
;

create table fornecedor (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  cnpj                      varchar(255),
  ie                        varchar(255),
  endereco                  varchar(255),
  cidade                    varchar(255),
  uf                        varchar(255),
  bairro                    varchar(255),
  constraint pk_fornecedor primary key (id))
;

create table produto (
  id                        bigint auto_increment not null,
  descricao                 varchar(255),
  sobre                     varchar(255),
  preco                     double,
  imagem                    varchar(255),
  categoria_id              bigint,
  fornecedor_id             bigint,
  estoque                   varchar(255),
  constraint pk_produto primary key (id))
;

alter table produto add constraint fk_produto_categoria_1 foreign key (categoria_id) references categoria (id) on delete restrict on update restrict;
create index ix_produto_categoria_1 on produto (categoria_id);
alter table produto add constraint fk_produto_fornecedor_2 foreign key (fornecedor_id) references fornecedor (id) on delete restrict on update restrict;
create index ix_produto_fornecedor_2 on produto (fornecedor_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table categoria;

drop table fornecedor;

drop table produto;

SET FOREIGN_KEY_CHECKS=1;

