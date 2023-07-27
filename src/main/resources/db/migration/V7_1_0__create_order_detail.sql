CREATE TABLE IF NOT EXISTS order_detail (
    order_id UUID REFERENCES orderr(id),
    product_id UUID REFERENCES product(id),
    shipped_from UUID REFERENCES location(id),
    quantity INTEGER NOT NULL,
    PRIMARY KEY (order_id, product_id)
);