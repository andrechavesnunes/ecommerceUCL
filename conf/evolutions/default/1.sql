# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table carrinho (
  id                        bigint auto_increment not null,
  valor_total               double,
  usuario_id                bigint,
  transportadora_id         bigint,
  forma_pagamento_id        bigint,
  data                      datetime(6),
  quantidade_total          double,
  constraint pk_carrinho primary key (id))
;

create table categoria (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  constraint pk_categoria primary key (id))
;

create table forma_pagamento (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  parcelas                  integer,
  constraint pk_forma_pagamento primary key (id))
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

create table itens_carrinho (
  id                        bigint auto_increment not null,
  valor                     double,
  qtd                       double,
  desconto                  double,
  constraint pk_itens_carrinho primary key (id))
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

create table transportadora (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  cnpj                      varchar(255),
  constraint pk_transportadora primary key (id))
;

create table usuario (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  cnpjcpf                   varchar(255),
  endereco                  varchar(255),
  cidade                    varchar(255),
  uf                        varchar(255),
  cep                       varchar(255),
  bairro                    varchar(255),
  email                     varchar(255),
  senha                     varchar(255),
  constraint pk_usuario primary key (id))
;

alter table carrinho add constraint fk_carrinho_Usuario_1 foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;
create index ix_carrinho_Usuario_1 on carrinho (usuario_id);
alter table carrinho add constraint fk_carrinho_Transportadora_2 foreign key (transportadora_id) references transportadora (id) on delete restrict on update restrict;
create index ix_carrinho_Transportadora_2 on carrinho (transportadora_id);
alter table carrinho add constraint fk_carrinho_FormaPagamento_3 foreign key (forma_pagamento_id) references forma_pagamento (id) on delete restrict on update restrict;
create index ix_carrinho_FormaPagamento_3 on carrinho (forma_pagamento_id);
alter table produto add constraint fk_produto_categoria_4 foreign key (categoria_id) references categoria (id) on delete restrict on update restrict;
create index ix_produto_categoria_4 on produto (categoria_id);
alter table produto add constraint fk_produto_fornecedor_5 foreign key (fornecedor_id) references fornecedor (id) on delete restrict on update restrict;
create index ix_produto_fornecedor_5 on produto (fornecedor_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table carrinho;

drop table categoria;

drop table forma_pagamento;

drop table fornecedor;

drop table itens_carrinho;

drop table produto;

drop table transportadora;

drop table usuario;

SET FOREIGN_KEY_CHECKS=1;

