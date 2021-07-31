ALTER USER 'root'@'localhost' IDENTIFIED BY 'secret';
CREATE DATABASE IF NOT EXISTS backend;
USE backend;
CREATE USER 'dev'@'%' IDENTIFIED BY 'pass';
GRANT ALL ON backend.* TO 'dev'@'%';
