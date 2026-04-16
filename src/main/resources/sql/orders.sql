CREATE TABLE orders (
  order_id SERIAL PRIMARY KEY,
  user_id  INT REFERENCES users(user_id)
);