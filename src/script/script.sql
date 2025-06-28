create database bsPizzeria;
use bsPizzeria;

create table cliente(
    idCliente int auto_increment primary key,
    nombre varchar(100) not null
);

create table pedido(
    idPedido int auto_increment primary key,
    idCliente int not null,
    nombrePizza varchar(100) not null,
    estado varchar(25) not null,
    precioTotal int not null,
    foreign key (idCliente) references cliente(idCliente)
);