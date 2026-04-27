CREATE DATABASE daligas;

CREATE EXTENSION postgis;

SELECT PostGIS_Version();

CREATE SCHEMA entrega_gas;

create table entrega_gas.usuario(
	id_usuario SERIAL primary key,
	nome Varchar(150) not null,
	cpf varchar(11) not null,
	data_nascimento TIMESTAMP not null,
	telefone varchar(50) not null,
	email varchar(200) not null,
	senha varchar(255) not null,
	ativo BOOLEAN NOT NULL DEFAULT false,
	
	constraint uq_usuario_cpf unique (cpf),
	constraint uq_usuario_telefone unique (telefone),
	constraint uq_usuario_email unique (email)
);

create table entrega_gas.produto(
	id_produto SERIAL primary key,
	nome Varchar(150) not null,
	ncm varchar(8) not null,
	quantidade int not null default 0,
	valor NUMERIC(10,2) not null,
	constraint uq_produto_nome unique (nome)
);

create table entrega_gas.empresa(
	id_empresa SERIAL primary key,
	razao_social Varchar(150) not null,
	cnpj varchar(14) not null,
	telefone varchar(50) not null,
	localizacao GEOMETRY(Point, 4326) not null,
	constraint uq_empresa_telefone unique (telefone),
	constraint uq_empresa_cnpj unique (cnpj),
	constraint uq_empresa_razao_social unique (razao_social)
);
CREATE INDEX idx_entregador_localizacao ON entrega_gas.entregador USING GIST (localizacao);

create table entrega_gas.entregador(
	id_entregador SERIAL primary key,
	nome varchar(150) not null,
	cpf varchar(11) not null,
	senha varchar(255) not null,
	telefone varchar(50) not null,
	ativo BOOLEAN not null default false,
	localizacao GEOMETRY(Point, 4326) not null,
	criado_em TIMESTAMP DEFAULT NOW(),
	ultima_atualizacao TIMESTAMP default NOW(),
	constraint uq_entregador_cpf unique (cpf),
	constraint uq_entregador_telefone unique (telefone)
);
CREATE INDEX idx_entregador_localizacao ON entrega_gas.entregador USING GIST (localizacao);

create table entrega_gas.status_pedido(
	id_status SERIAL primary key,
	tipo_status varchar(30) not null
);
INSERT INTO entrega_gas.status_pedido (tipo_status)
VALUES 
('Rascunho'),
('Disponivel'),
('Aceito'),
('Finalizado'),
('Cancelado');

create table entrega_gas.tipo_movimentacao(
	id_tipo_movimentacao SERIAL primary key,
	tipo_movimentacao varchar(30) not null,
	operacao_gas varchar(1),
	operacao_vazia varchar(1),
	constraint chk_tipo_movimentacao_operacao_gas check (operacao_gas in ('+', '-') or operacao_gas is null),
	constraint chk_tipo_movimentacao_operacao_vazia check (operacao_vazia in ('+', '-') or operacao_vazia is null) 
);

create table entrega_gas.endereco(
	id_endereco SERIAL primary key,
	id_usuario int not null,
	rua varchar(200) not null,
	numero varchar(20) not null,
	complemento varchar(200) not null,
	bairro varchar(200) not null,
	cidade varchar(200) not null,
	estado varchar(200) not null,
	cep varchar(8) not null,
	localizacao GEOMETRY(Point, 4326) not null,
	constraint fk_endereco_id_usuario foreign key (id_usuario) references entrega_gas.usuario (id_usuario)
);

create table entrega_gas.pedido(
	id_pedido SERIAL primary key,
	id_endereco int,
	id_usuario int not null,
	id_empresa int not null,
	id_entregador int,
	id_status int not NULL DEFAULT 1,
	data_pedido TIMESTAMP default NOW(),
	valor_compra NUMERIC(10,2) not null,
	constraint fk_pedido_id_endereco foreign key (id_endereco) references entrega_gas.endereco (id_endereco),
	constraint fk_pedido_id_usuario foreign key (id_usuario) references entrega_gas.usuario (id_usuario),
	constraint fk_pedido_id_empresa foreign key (id_empresa) references entrega_gas.empresa (id_empresa),
	constraint fk_pedido_entregador foreign key (id_entregador) references entrega_gas.entregador (id_entregador),
	constraint fk_pedido_status foreign key (id_status) references entrega_gas.status_pedido (id_status)
);

create table entrega_gas.movimentacao(
	id_movimentacao SERIAL primary key,
	id_entregador int not null,
	id_pedido int,
	id_tipo_movimentacao int not null,
	data_hora TIMESTAMP default NOW(),
	quantidade_botijao_cheio int not null default 0,
	quantidade_botijao_vazio int not null default 0,
	observarcao varchar(350),
	constraint fk_movimentacao_id_entregador foreign key (id_entregador) references entrega_gas.entregador (id_entregador),
	constraint fk_movimentacao_id_pedido foreign key (id_pedido) references entrega_gas.pedido (id_pedido),
	constraint fk_movimentacao_id_tipo_movimentacao foreign key (id_tipo_movimentacao) references entrega_gas.tipo_movimentacao(id_tipo_movimentacao)
);
ALTER TABLE entrega_gas.movimentacao ALTER COLUMN id_pedido DROP NOT NULL;

create table entrega_gas.pedido_produto(
	id_pedido_produto SERIAL primary key,
	id_pedido int not null,
	id_produto int not null,
	quantidade INT NOT null,
	valor_unitario NUMERIC(10,2) NOT null,
	desconto NUMERIC(10,2) NOT null,
	constraint fk_pedido_produto_id_pedido foreign key (id_pedido) references entrega_gas.pedido (id_pedido),
	constraint fk_pedido_produto_id_produto foreign key (id_produto) references entrega_gas.produto (id_produto)
);

