DROP DATABASE   IF     EXISTS netlabs;
CREATE DATABASE IF NOT EXISTS netlabs;
USE netlabs;



SET FOREIGN_KEY_CHECKS = 0;
drop table if exists category;
drop table if exists transaction_details;
drop table if exists transaction;
drop table if exists product;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE category (
category_id INT AUTO_INCREMENT PRIMARY KEY,
ciudad varchar(250) not null,
discount_percentage int null
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE product (
id_product INT AUTO_INCREMENT PRIMARY KEY,
name varchar(250) not null,
price int null,
category_id int not null,
stock int not null,
CONSTRAINT FK_category_id FOREIGN KEY (category_id)
REFERENCES category(category_id)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE transaction (
id_transaction INT AUTO_INCREMENT PRIMARY KEY,
date datetime not null,
total_price int not null
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE transaction_detail (
`transaction_detail_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
id_transaction int not null,
id_product int not null,
discount_applied int not null,
quantity int not null,
CONSTRAINT FK_transaction_id FOREIGN KEY (id_transaction)
    REFERENCES transaction(id_transaction),
CONSTRAINT FK_product_id FOREIGN KEY (id_product)
REFERENCES product(id_product)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


ALTER TABLE transaction_detail AUTO_INCREMENT = 1;
ALTER TABLE transaction AUTO_INCREMENT = 1;
ALTER TABLE product AUTO_INCREMENT = 1;
ALTER TABLE category AUTO_INCREMENT = 1;