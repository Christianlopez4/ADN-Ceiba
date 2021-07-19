insert into usuario(nombre,clave,fecha_creacion) values('test','1234',now());
insert into documento(titulo) values('test');
insert into categoria(titulo, cuota_moderadora, multa) values('A', 3000.0, 5000.0);
insert into paciente(id, nombre, apellidos, fecha_nacimiento, correo_electronico, telefono, id_categoria, id_documento) values (1, 'Test', 'Test', '2021-7-19', 'Test', 123, 1, 1);
insert into paciente(id, nombre, apellidos, fecha_nacimiento, correo_electronico, telefono, id_categoria, id_documento) values (2, 'Test', 'Test', '1999-5-26', 'Test', 123, 1, 1);
insert into cita(fecha, hora, costo, id_paciente, estado) values ('2021-7-19', '14:00:00', 6000.0, 1, 'test');