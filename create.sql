CREATE DATABASE api;
-- \c api;
CREATE TABLE departments(
id SERIAL PRIMARY KEY,
name VARCHAR,
description VARCHAR,
employeesNo INTEGER
);

CREATE TABLE users(
id SERIAL PRIMARY KEY,
departmentId INTEGER,
name VARCHAR,
position VARCHAR,
role VARCHAR
);

CREATE TABLE news(
id SERIAL PRIMARY KEY,
departmentId INTEGER,
description VARCHAR
);

CREATE TABLE departments_users(
id SERIAL PRIMARY KEY,
departmentId INTEGER,
userId INTEGER
);

CREATE TABLE departments_news(
id SERIAL PRIMARY KEY,
departmentId INTEGER,
newsId INTEGER
);

CREATE DATABASE api_test WITH TEMPLATE api;