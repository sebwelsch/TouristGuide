CREATE DATABASE IF NOT EXISTS prod_db;

USE prod_db;

CREATE TABLE IF NOT EXISTS attractions (
name VARCHAR(50) NOT NULL UNIQUE,
description TEXT NOT NULL,
city VARCHAR(50) NOT NULL,
tags VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS cities (
    name VARCHAR(255) NOT NULL UNIQUE
);
