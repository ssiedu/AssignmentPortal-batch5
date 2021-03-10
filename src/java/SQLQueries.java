/*
    CREATE DATABASE ProjectData;
    
    USE ProjectData;

    CREATE TABLE student   
    (userid varchar(10) primary key, password varchar(10), name varchar(10), address varchar(10), 
    email varchar(20), mobile varchar(12));

    CREATE TABLE faculty
    (userid varchar(10) primary key, password varchar(10), name varchar(10), address varchar(10), 
    email varchar(20), mobile varchar(12), status varchar(10));

    CREATE TABLE QueBank
    (code integer primary key AUTO_INCREMENT,
    question varchar(150),
    sdate    date,
    fid      varchar(10),
    subject  varchar(10));

*/