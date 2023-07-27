CREATE TABLE IF NOT EXISTS location (
    id UUID NOT NULL PRIMARY KEY ,
    name VARCHAR(50) NOT NULL,
    address_country VARCHAR(50) NOT NULL,
    address_city VARCHAR(50) NOT NULL,
    address_county VARCHAR(50) NOT NULL,
    address_street VARCHAR(50) NOT NULL
);
