CREATE TABLE IF NOT EXISTS product_category (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    description VARCHAR(128)
);