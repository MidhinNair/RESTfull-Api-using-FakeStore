CREATE TABLE categories
(
    id   VARCHAR(255) NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id       VARCHAR(255) NOT NULL,
    price_id VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE orders_product
(
    orders_id  VARCHAR(255) NOT NULL,
    product_id VARCHAR(255) NOT NULL
);

CREATE TABLE price
(
    price DOUBLE NULL,
    id       VARCHAR(255) NOT NULL,
    currency VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE product
(
    price DOUBLE NOT NULL,
    category_id   VARCHAR(255) NULL,
    id            VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    image         VARCHAR(255) NULL,
    title         VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE product_order
(
    order_id   VARCHAR(255) NOT NULL,
    product_id VARCHAR(255) NOT NULL
);

ALTER TABLE orders
    ADD CONSTRAINT UK_dt8tejvhbe4eccbs4sm22pou0 UNIQUE (price_id);

ALTER TABLE orders_product
    ADD CONSTRAINT FK7h8a805aormf2mxryf70m1n7a FOREIGN KEY (orders_id) REFERENCES orders (id) ON DELETE NO ACTION;

CREATE INDEX FK7h8a805aormf2mxryf70m1n7a ON orders_product (orders_id);

ALTER TABLE product_order
    ADD CONSTRAINT FKh73acsd9s5wp6l0e55td6jr1m FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE NO ACTION;

CREATE INDEX FKh73acsd9s5wp6l0e55td6jr1m ON product_order (product_id);

ALTER TABLE orders_product
    ADD CONSTRAINT FKhqvk1lcdpap9qym2o94b45mcs FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE NO ACTION;

CREATE INDEX FKhqvk1lcdpap9qym2o94b45mcs ON orders_product (product_id);

ALTER TABLE product_order
    ADD CONSTRAINT FKjwsik4uvq2sdqtb7x6h1o5f0v FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE NO ACTION;

CREATE INDEX FKjwsik4uvq2sdqtb7x6h1o5f0v ON product_order (order_id);

ALTER TABLE orders
    ADD CONSTRAINT FKmrfw8fspgk6h4wtsvq9y8fsaw FOREIGN KEY (price_id) REFERENCES price (id) ON DELETE NO ACTION;

ALTER TABLE product
    ADD CONSTRAINT FKowomku74u72o6h8q0khj7id8q FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE NO ACTION;

CREATE INDEX FKowomku74u72o6h8q0khj7id8q ON product (category_id);