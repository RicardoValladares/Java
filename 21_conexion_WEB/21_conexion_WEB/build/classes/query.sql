create database usuarios;

use usuarios;

create table datos(
id int not null auto_increment,
nombre varchar(50) not null,
apellido varchar(50) not null,
primary key(id)
);

insert into datos(nombre,apellido)
values('Ricardo','Valladares'),('Selena','Renderos');

select * from datos;
