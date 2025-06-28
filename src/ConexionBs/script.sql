
create database bsPizzeria;
use bsPizzeria;

create table clientes(
    idCliente int(12) not null auto_increment primary key,
    nombre varchar(100) not null,
    telefono int(15) not null,
    direccion varchar(100) not null,
    email varchar(50),
);

create table ingredientes(
    idIngrediente int(12) not null auto_increment primary key,
    nombre varchar(100) not null
);

create table pizzas(
    id int(12) not null auto_increment primary key,
    nombre varchar(100) not null,
    precio decimal(8,5) not null
);

create table PizzaIngredientes(
    idPizza int(12),
    idIngrediente int(12),
    cantidad int(3) not null,
    primary key(idPizza, idIngrediente),
    foreign key(idPizza) references pizzas(id),
    foreign key(idIngrediente) references ingredientes(idIngrediente)
);

