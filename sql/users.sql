CREATE TABLE if not exists users (
    userNumber BIGINT NOT NULL,
    userId VARCHAR(30) NOT NULL,
    userPassword VARCHAR(50) NOT NULL,
    userName VARCHAR(100) NOT NULL,
    userAge INT NOT NULL,
    userGender VARCHAR (20) NOT NULL,
    userAddress varchar(40),
    role VARCHAR(20),
    PRIMARY KEY(userNumber)
    );