DROP database IF exists rodriguez;
CREATE DATABASE  IF NOT EXISTS rodriguez;
USE rodriguez;


DROP TABLE IF EXISTS alumno;
CREATE TABLE IF NOT EXISTS alumno (
  alumno_dni int NOT NULL,
  alumno_nombre varchar(45) DEFAULT NULL,
  alumno_apellido varchar(45) DEFAULT NULL,
  alumno_fec_nac date DEFAULT NULL, 
  alu_insc_cod int DEFAULT NULL,
  PRIMARY KEY (alumno_dni),  
  FOREIGN KEY (alu_insc_cod) REFERENCES inscripcion (insc_cod) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO alumno VALUES (36859261,'Arturo','Rodriguez','1992-06-16',1123),(36555444,'Agustin','Canovas','1999-01-03',4432),(35444888,'Carla','Bustamante','1997-01-08',null);



DROP TABLE IF EXISTS profesor;
CREATE TABLE IF NOT EXISTS profesor (
  prof_dni int NOT NULL,
  prof_nombre varchar(45) DEFAULT NULL,
  prof_apellido varchar(45) DEFAULT NULL,
  prof_fec_nac varchar(45) DEFAULT NULL,
  PRIMARY KEY (prof_dni)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO profesor VALUES (11222333,'Javier','Lopez','1958-01-17'),(222444888,'German','Rago','1984-11-28');



DROP TABLE IF EXISTS materia;
CREATE TABLE IF NOT EXISTS materia (
  mat_cod int NOT NULL,
  mat_nombre varchar(45) DEFAULT NULL,
  mat_prof_dni int DEFAULT NULL,
  PRIMARY KEY (mat_cod),
  FOREIGN KEY (mat_prof_dni) REFERENCES profesor (prof_dni) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO materia VALUES (101,'Matematica',11222333),(102,'Economia',11222333),(103,'Estadistica',222444888),(104,'Contabilidad',222444888);


DROP TABLE IF EXISTS carrera;
CREATE TABLE IF NOT EXISTS carrera (
  codigo_carrera int NOT NULL,
  nombre_carrera varchar(30) DEFAULT NULL,
  duracion_carrera_anios int(5) DEFAULT NULL,
  PRIMARY KEY (codigo_carrera)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO carrera VALUES (3,'Programacion','5'),(2,'Contador','5');



DROP TABLE IF EXISTS cursado;

CREATE TABLE IF NOT EXISTS cursado (
  codigo int NOT NULL,
  cur_alu_dni int NOT NULL,
  cur_mat_cod int DEFAULT NULL,
  cur_nota double DEFAULT NULL,
  PRIMARY KEY (codigo),
  FOREIGN KEY (cur_mat_cod) REFERENCES materia (mat_cod) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO cursado VALUES (3342615,36859261,101,8),(4659385,36555444,102,6);



DROP TABLE IF EXISTS inscripcion;
CREATE TABLE IF NOT EXISTS inscripcion (
  insc_cod int NOT NULL,
  insc_nombre varchar(45) DEFAULT NULL,
  insc_fecha date DEFAULT NULL,
  insc_car_cod int DEFAULT NULL,
  PRIMARY KEY (insc_cod),
  FOREIGN KEY (insc_car_cod) REFERENCES carrera (codigo_carrera) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO inscripcion VALUES (1123,'Arturo Rodriguez','2020-04-10',2),(4432,'Agustin Canovas','2020-06-12',3);

