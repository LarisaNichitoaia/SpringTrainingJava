CREATE TABLE IF NOT EXISTS product (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(20),
    description VARCHAR(128),
    price double precision,
    weight double precision,
    category_id UUID REFERENCES product_category(id),
    supplier varchar(20),
    image_url VARCHAR(255)
);