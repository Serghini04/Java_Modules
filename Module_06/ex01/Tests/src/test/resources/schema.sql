DROP TABLE IF EXISTS product;

CREATE TABLE product (
    identifier INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);
