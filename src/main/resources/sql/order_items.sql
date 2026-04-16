CREATE TABLE order_items (
  order_item_id SERIAL PRIMARY KEY,
  order_id  INT REFERENCES orders(order_id) ON DELETE CASCADE,
  bottom_id INT REFERENCES bottoms(bottom_id),
  topping_id INT REFERENCES toppings(topping_id)
);