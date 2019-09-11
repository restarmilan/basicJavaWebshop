DROP TABLE IF EXISTS ProductCategory CASCADE;
ALTER TABLE IF EXISTS ONLY Products DROP CONSTRAINT IF EXISTS productCategory_id CASCADE;
ALTER TABLE IF EXISTS ONLY Products DROP CONSTRAINT IF EXISTS supplier_id CASCADE;
DROP TABLE IF EXISTS Suppliers CASCADE;

CREATE TABLE Suppliers (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           description VARCHAR(255)
);

CREATE TABLE ProductCategory (
                                 id SERIAL PRIMARY KEY,
                                 name VARCHAR(255) NOT NULL,
                                 department VARCHAR(255) NOT NULL,
                                 description VARCHAR(255)
);

CREATE TABLE Product (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         default_price DECIMAL(10, 2), -- 10 számjegy összesen, két tizedesjegyig
                         default_currency VARCHAR(255),
                         description VARCHAR(255),
                         productCategory_id INTEGER REFERENCES ProductCategory(id),
                         supplier_id INTEGER REFERENCES Suppliers(id)
);
