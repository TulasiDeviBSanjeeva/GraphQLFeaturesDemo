INSERT INTO customer (id, email, name) VALUES (1, 'a@a.com', 'customer A');
INSERT INTO customer (id, email, name) VALUES (2, 'b@b.com', 'customer B');
INSERT INTO customer (id, email, name) VALUES (3, 'c@c.com', 'customer C');

INSERT INTO product (id, name, price) VALUES (1, 'Brita', 25);
INSERT INTO product (id, name, price) VALUES (2, 'Bony', 120);
INSERT INTO product (id, name, price) VALUES (3, 'Bermuda', 60);
INSERT INTO product (id, name, price) VALUES (4, 'Cassendra', 80);
INSERT INTO product (id, name, price) VALUES (5, 'Chameliah', 130);

INSERT INTO order (id, date, quantity, status, customer_id, product_id) VALUES (1, '2020-01-09 11:13:42', 10, 'OK', 1, 1);
INSERT INTO order (id, date, quantity, status, customer_id, product_id) VALUES (2, '2020-01-09 11:13:46', 20, 'OK', 1, 2);
INSERT INTO order (id, date, quantity, status, customer_id, product_id) VALUES (3, '2020-01-09 11:13:48', 30, 'OK', 1, 3);
INSERT INTO order (id, date, quantity, status, customer_id, product_id) VALUES (4, '2020-01-09 11:13:50', 40, 'OK', 1, 4);
INSERT INTO order (id, date, quantity, status, customer_id, product_id) VALUES (5, '2020-01-09 11:13:53', 50, 'OK', 1, 5);