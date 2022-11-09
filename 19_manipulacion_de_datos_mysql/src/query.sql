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

create table borrados(
id int not null,
nombre varchar(50) not null,
apellido varchar(50) not null,
primary key(id)
);

delimiter //
create trigger eliminador 
after delete on datos
for each row
begin
   insert into borrados(id,nombre,apellido) 
   values(OLD.id,OLD.nombre,OLD.apellido);
end;//
delimiter ;

delimiter //
create procedure restaurardor(i int)
begin
	insert into datos(id,nombre,apellido) select id,nombre,apellido from borrados where id=i;
	delete from borrados where id=i;
end//
delimiter ;
