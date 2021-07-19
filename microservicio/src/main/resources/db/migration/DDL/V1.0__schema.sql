create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);

create table documento (
	id serial not null,
	titulo varchar(50) not null,
	primary key(id)
);

CREATE TABLE categoria (
	id serial not null,
	titulo varchar(1) not null,
	cuota_moderadora decimal(12,2) not null,
	multa decimal(12,2) not null,
	primary key(id)
);

CREATE TABLE paciente (
	id bigint not null,
	nombre varchar(20) not null,
	apellidos varchar(40) not null,
	fecha_nacimiento date not null,
	correo_electronico varchar(50) not null,
	telefono bigint not null,
	id_categoria int(11) not null,
	id_documento int(11) not null,
	primary key(id),
	CONSTRAINT "fk_PACIENTE_CATEGORIA"
    FOREIGN KEY ("id_categoria")
    REFERENCES categoria ("id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
	CONSTRAINT "fk_PACIENTE_DOCUMENTO"
    FOREIGN KEY ("id_documento")
	REFERENCES documento ("id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE cita (
	id serial not null,
	fecha date not null,
	hora time not null,
	costo decimal(12,2) not null,
	id_paciente bigint not null,
	estado varchar(20) not null,
	primary key(id),
	CONSTRAINT "fk_CITA_PACIENTE"
    FOREIGN KEY ("id_paciente")
    REFERENCES paciente ("id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);