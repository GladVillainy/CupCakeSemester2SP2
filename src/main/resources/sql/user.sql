-- Users
CREATE TABLE users (
user_id  SERIAL PRIMARY KEY,
email    VARCHAR(255) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
role     VARCHAR(20)  NOT NULL DEFAULT 'customer',
balance  NUMERIC(8,2) NOT NULL DEFAULT 0.00
);

INSERT INTO users (email, password, role, balance) VALUES
('admin@olskercupcakes.dk', 'admin123', 'admin',    500.00);