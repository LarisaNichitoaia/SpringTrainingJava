CREATE TABLE IF NOT EXISTS product (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    description VARCHAR(128),
    price NUMERIC NOT NULL,
    weight DOUBLE PRECISION NOT NULL,
    category_id UUID REFERENCES product_category(id),
    supplier VARCHAR(20) NOT NULL,
    image_url VARCHAR(255)
);