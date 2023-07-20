CREATE TABLE IF NOT EXISTS stock (
    product_id UUID REFERENCES product(id),
    location_id UUID REFERENCES location(id),
    quantity integer,
    PRIMARY KEY (product_id, location_id)
);