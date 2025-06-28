create database bsPizzeria;
use bsPizzeria;

create table clientes(
    idCliente int auto_increment primary key,
    nombre varchar(100) not null
);

create table pedidos(
    idPedido int auto_increment primary key,
    idCliente int not null,
    nombrePizza varchar(100) not null,
    ingredientes varchar(255) not null,
    fecha datetime not null,
    cantidad int not null,
    precioTotal decimal(10,2) not null,
    foreign key (idCliente) references clientes(idCliente)
);