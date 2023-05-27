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
    PRIMARY KEY (replySeq, postSeq)
);

DROP TABLE IF EXISTS ChallengeUser;
CREATE TABLE ChallengeUser (
    userSeq bigint NOT NULL,
    userId varchar(255) NULL,
    challengeContent text NULL,
    adminSeq bigint NOT NULL,
    PRIMARY KEY (userSeq)
);

DROP TABLE IF EXISTS ChallengeAdmin;
CREATE TABLE ChallengeAdmin (
    adminSeq bigint NOT NULL,
    keyword1 varchar(255) NULL,
                                keyword2 varchar(255) NULL,
                                keyword3 varchar(255) NULL,
                                PRIMARY KEY (adminSeq)
);

-- Users 테이블과 Geulgwi 테이블을 연결하는 외래키
ALTER TABLE `Geulgwi` ADD CONSTRAINT `FK_Geulgwi_TO_Users_1` FOREIGN KEY (
                                                                          `userId`
    )
    REFERENCES `Users` (
                        `userId`
        );

-- Users 테이블과 Post 테이블을 연결하는 외래키
ALTER TABLE `Post` ADD CONSTRAINT `FK_Post_TO_Users_1` FOREIGN KEY (
                                                                    `userId`
    )
    REFERENCES `Users` (
                        `userId`
        );

-- Post 테이블과 Comment 테이블을 연결하는 외래키
ALTER TABLE `Comment` ADD CONSTRAINT `FK_Comment_TO_Post_1` FOREIGN KEY (
                                                                         `postSeq`
    )
    REFERENCES `Post` (
                       `postSeq`
        );

-- ChallengeAdmin 테이블과 ChallengeUser 테이블을 연결하는 외래키
ALTER TABLE `ChallengeUser` ADD CONSTRAINT `FK_ChallengeUser_TO_ChallengeAdmin_1` FOREIGN KEY (
                                                                                               `adminSeq`
    )
    REFERENCES `ChallengeAdmin` (
                                 `adminSeq`
        );