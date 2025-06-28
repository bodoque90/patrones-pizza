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
    cantidad int not null,
    estado varchar(25) not null,
    precioTotal int not null,
    foreign key (idCliente) references clientes(idCliente)
);