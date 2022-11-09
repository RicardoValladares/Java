create database agenda_telefonica;

use agenda_telefonica;

create table contactos(
id_contactos int not null auto_increment,
fotos mediumblob not null,
nombres varchar(50) not null,
primary key(id_contactos)
);

create table tipos(
id_tipos int not null auto_increment,
tipos varchar(30) not null,
primary key(id_tipos)
);

create table numeros(
id_numeros int not null auto_increment,
numeros int not null,
id_contactos int not null,
id_tipos int not null,
primary key(id_numeros)
);

select contactos.id_contactos, contactos.fotos, contactos.nombres, numeros.numeros, tipos.tipos 
from contactos 
left outer join numeros 
on contactos.id_contactos=numeros.id_contactos 
left outer join tipos 
on tipos.id_tipos=numeros.id_tipos;