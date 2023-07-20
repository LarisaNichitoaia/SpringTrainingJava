CREATE TABLE IF NOT EXISTS location (
    id UUID NOT NULL PRIMARY KEY ,
    name varchar(50),
    address_country varchar(50),
    address_city varchar(50),
    address_county varchar(50),
    address_street varchar(50)
);
