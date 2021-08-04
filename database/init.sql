CREATE DATABASE backend CHARACTER SET utf8mb4;
CREATE USER 'dev'@'%' IDENTIFIED BY 'pass';
GRANT ALL ON backend.* TO 'dev'@'%';
ALTER USER 'root'@'localhost' IDENTIFIED BY 'secret';
