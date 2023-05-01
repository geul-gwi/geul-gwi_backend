#mariadb
DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
    userSeq bigint NOT NULL AUTO_INCREMENT,
    userId varchar(255) NOT NULL,
    userPassword varchar(255) NOT NULL,
    userName varchar(255) NOT NULL,
    userAge int NOT NULL,
    userGender varchar(255) NOT NULL,
    userNickname varchar(255) NOT NULL,
    tag1 varchar(255) NULL,
    tag2 varchar(255) NULL,
    tag3 varchar(255) NULL,
    role varchar(255) NULL,
    userProfile varchar(255) NULL,
    PRIMARY KEY (userSeq)
);

DROP TABLE IF EXISTS Geulgwi;
CREATE TABLE Geulgwi (
    geulgwiSeq bigint NOT NULL,
    userId varchar(255) NOT NULL,
    geulgwiTitle varchar(255) NOT NULL,
    geulgwiContent text NOT NULL,
    tag1 int NULL,
    tag2 int NULL,
    tag3 int NULL,
    regDate datetime NULL,
    userNumber bigint NOT NULL,
    PRIMARY KEY (geulgwiSeq)
);

DROP TABLE IF EXISTS Post;
CREATE TABLE Post (
    postSeq bigint NOT NULL,
    userId varchar(255) NOT NULL,
    postTitle varchar(255) NOT NULL,
    postContent text NOT NULL,
    regDate datetime NULL,
    likes int NULL,
    userNumber bigint NOT NULL,
    PRIMARY KEY (postSeq)
);

DROP TABLE IF EXISTS Comment;
CREATE TABLE Comment (
    replySeq bigint NOT NULL,
    postSeq bigint NOT NULL,
    replyId varchar(255) NOT NULL,
    replyComment text NOT NULL,
    regDate datetime NULL,
    PRIMARY KEY (replySeq, postSeq),
    CONSTRAINT FK_Post_TO_Comment_1 FOREIGN KEY (postSeq)
    REFERENCES Post (postSeq)
);

DROP TABLE IF EXISTS Challenge_User;
CREATE TABLE Challenge_User (
    userSeq bigint NOT NULL,
    userId varchar(255) NOT NULL,
    chellengeContent text NOT NULL,
    PRIMARY KEY (userSeq)
);

DROP TABLE IF EXISTS ChallengeAdmin;
CREATE TABLE ChallengeAdmin (
    adminSeq bigint NOT NULL,
    keyword1 varchar(255) NOT NULL,
    keyword2 varchar(255) NOT NULL,
    keyword3 varchar(255) NOT NULL,
    PRIMARY KEY (adminSeq)
);

DROP TABLE IF EXISTS Challenge;
CREATE TABLE Challenge (
    challengeSeq bigint NOT NULL,
    adminSeq bigint NOT NULL,
    userSeq bigint NOT NULL,
    PRIMARY KEY (challengeSeq)
);

#mysql
DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
                       userSeq bigint NOT NULL,
                       userId varchar(255) NOT NULL,
                       userPassword varchar(255) NOT NULL,
                       userName varchar(255) NOT NULL,
                       userAge int NOT NULL,
                       userGender varchar(255) NOT NULL,
                       userNickname varchar(255) NOT NULL,
                       tag1 varchar(255) NULL,
                       tag2 varchar(255) NULL,
                       tag3 varchar(255) NULL,
                       role varchar(255) NULL,
                       userProfile varchar(255) NULL,
                       PRIMARY KEY (userSeq)
);

DROP TABLE IF EXISTS Geulgwi;
CREATE TABLE Geulgwi (
                         geulgwiSeq bigint NOT NULL,
                         userId varchar(255) NOT NULL,
                         geulgwiTitle varchar(255) NOT NULL,
                         geulgwiContent text NOT NULL,
                         tag1 int NULL,
                         tag2 int NULL,
                         tag3 int NULL,
                         regDate datetime NULL,
                         userNumber bigint NOT NULL,
                         PRIMARY KEY (geulgwiSeq)
);

DROP TABLE IF EXISTS Post;
CREATE TABLE Post (
                      postSeq bigint NOT NULL,
                      userId varchar(255) NOT NULL,
                      postTitle varchar(255) NOT NULL,
                      postContent text NOT NULL,
                      regDate datetime NULL,
                      likes int NULL,
                      userNumber bigint NOT NULL,
                      PRIMARY KEY (postSeq)
);

DROP TABLE IF EXISTS Comment;
CREATE TABLE Comment (
                         replySeq bigint NOT NULL,
                         postSeq bigint NOT NULL,
                         replyId varchar(255) NOT NULL,
                         replyComment text NOT NULL,
                         regDate datetime NULL,
                         PRIMARY KEY (replySeq, postSeq),
                         CONSTRAINT FK_Post_TO_Comment_1 FOREIGN KEY (postSeq)
                             REFERENCES Post (postSeq)
);

DROP TABLE IF EXISTS Challenge_User;
CREATE TABLE Challenge_User (
                                userSeq bigint NOT NULL,
                                userId varchar(255) NOT NULL,
                                chellengeContent text NOT NULL,
                                PRIMARY KEY (userSeq)
);

DROP TABLE IF EXISTS ChallengeAdmin;
CREATE TABLE ChallengeAdmin (
                                adminSeq bigint NOT NULL,
                                keyword1 varchar(255) NOT NULL,
                                keyword2 varchar(255) NOT NULL,
                                keyword3 varchar(255) NOT NULL,
                                PRIMARY KEY (adminSeq)
);

DROP TABLE IF EXISTS Challenge;
CREATE TABLE Challenge (
                           challengeSeq bigint NOT NULL,
                           adminSeq bigint NOT NULL,
                           userSeq bigint NOT NULL,
                           PRIMARY KEY (challengeSeq)
);