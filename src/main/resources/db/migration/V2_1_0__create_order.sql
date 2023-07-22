CREATE TABLE IF NOT EXISTS orderr (
    id UUID NOT NULL PRIMARY KEY ,
    customer_id UUID REFERENCES customer(id),
    created_at TIMESTAMP,
    address_country VARCHAR(50) NOT NULL,
    address_city VARCHAR(50) NOT NULL,
    address_county VARCHAR(50) NOT NULL,
    address_street VARCHAR(50) NOT NULL
);
