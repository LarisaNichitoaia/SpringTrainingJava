CREATE TABLE IF NOT EXISTS customer (
    id UUID NOT NULL PRIMARY KEY ,
    first_name varchar(20),
    last_name varchar(50),
    username varchar(50),
    password varchar(50),
    email_address varchar(50)
);