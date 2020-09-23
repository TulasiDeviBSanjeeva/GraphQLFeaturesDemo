INSERT INTO customer (id, email, name) VALUES (1, 'alia@axe.com', 'Alia');
INSERT INTO customer (id, email, name) VALUES (2, 'bubble@bae.com', 'Bubbly');
INSERT INTO customer (id, email, name) VALUES (3, 'cinta@cigna.com', 'Charlie Cinta');

INSERT INTO product (id, name, price) VALUES (1, 'Brita tumbler', 25);
INSERT INTO product (id, name, price) VALUES (2, 'Bony Jacke', 120);
INSERT INTO product (id, name, price) VALUES (3, 'Bermuda Beauty', 60);
INSERT INTO product (id, name, price) VALUES (4, 'Cassendra Top', 80);
INSERT INTO product (id, name, price) VALUES (5, 'Chameliah Shoes', 130);

INSERT INTO purchase (id, date, quantity, status, customer_id, product_id) VALUES (1, '2020-09-19 10:13:42', 10, 'OK', 1, 1);
INSERT INTO purchase (id, date, quantity, status, customer_id, product_id) VALUES (2, '2020-09-19 10:13:46', 20, 'OK', 1, 2);
INSERT INTO purchase (id, date, quantity, status, customer_id, product_id) VALUES (3, '2020-09-19 14:13:48', 30, 'OK', 2, 3);
INSERT INTO purchase (id, date, quantity, status, customer_id, product_id) VALUES (4, '2020-09-19 14:13:50', 40, 'OK', 2, 4);
INSERT INTO purchase (id, date, quantity, status, customer_id, product_id) VALUES (5, '2020-09-20 18:13:53', 50, 'OK', 3, 5);