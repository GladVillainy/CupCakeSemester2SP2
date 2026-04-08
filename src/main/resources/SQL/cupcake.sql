-- Bundene
CREATE TABLE bottoms (
    bottom_id SERIAL PRIMARY KEY,
    name      VARCHAR(50) NOT NULL,
    price     NUMERIC(5,2) NOT NULL
);

INSERT INTO bottoms (name, price) VALUES
    ('Chokolade', 5.00),
    ('Vanilje',   5.00),
    ('Muskat',    5.00),
    ('Pistacie',  6.00),
    ('Mandel',    7.00);


-- Toppings
CREATE TABLE toppings (
    topping_id SERIAL PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    price      NUMERIC(5,2) NOT NULL
);

INSERT INTO toppings (name, price) VALUES
    ('Chokolade', 5.00),
    ('Blåbær',    5.00),
    ('Hindbær',   5.00),
    ('Crispy',    6.00),
    ('Jordbær',   6.00),
    ('Romrosin',  7.00),
    ('Orange',    8.00),
    ('Citron',    8.00),
    ('Blå ost',   9.00);