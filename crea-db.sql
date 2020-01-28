DROP DATABASE IF EXISTS tiendaCuchillos;
-- REVOKE ALL PRIVILEGES , GRANT OPTION ON tiendaCuchillos.* FROM tomcat@localhost;
-- delete from mysql.db where user = 'tomcat';
-- DROP USER tomcat@localhost;
FLUSH PRIVILEGES;
CREATE DATABASE tiendaCuchillos;




CREATE USER IF NOT EXISTS tomcat@localhost IDENTIFIED BY 'tomcat';
GRANT ALL PRIVILEGES ON tiendaCuchillos.* TO 'tomcat'@'localhost';
FLUSH PRIVILEGES;

USE tiendaCuchillos ;

CREATE TABLE producto (
 _idcuchillo INT NOT NULL AUTO_INCREMENT,
 nombre VARCHAR(255) NOT NULL,
 precio  INT NOT NULL,
 imagen VARCHAR(255) NOT NULL,
 PRIMARY KEY (_idcuchillo));

INSERT INTO producto VALUES (NULL, "cuchilloJapones",1,"cuchillo.png");
INSERT INTO producto VALUES (NULL, "cuchilloSierra",2,"cuchillo2.png");
INSERT INTO producto VALUES (NULL, "cuchilloItaliano",2,"cuchillo3.png");

CREATE TABLE pedidos (
 _idcompra INT NOT NULL AUTO_INCREMENT,
 nombre VARCHAR(255) NOT NULL,
 cantidad INT NOT NULL,
 total INT NOT NULL,
 PRIMARY KEY (_idcompra));

