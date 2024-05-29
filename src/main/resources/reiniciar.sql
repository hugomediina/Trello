drop table if exists tarjeta;
drop table if exists columna;
drop table if exists tablero;
drop table if exists userdetails;

create table userdetails (
    username varchar(50) primary key,
    email varchar(50) not null,
    password varchar(255) not null,
    tipo integer not null
);
create table tablero (
    id_tablero serial primary key ,
    nombre varchar(255) not null ,
    usuario varchar(50) not null
);
create table columna(
    id_columna serial primary key ,
    nombre varchar(255) not null ,
    id_tablero integer not null ,
    foreign key (id_tablero) references tablero(id_tablero) on delete cascade
);
create table tarjeta (
    id_tarjeta serial primary key ,
    titulo varchar(255) not null,
    descripcion text,
    id_columna integer not null,
    foreign key (id_columna) references columna(id_columna) on delete cascade
);

