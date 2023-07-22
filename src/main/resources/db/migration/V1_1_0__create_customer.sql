CREATE TABLE IF NOT EXISTS customer (
    id UUID NOT NULL PRIMARY KEY ,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email_address VARCHAR(50) NOT NULL
);