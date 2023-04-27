### 유저 테이블
CREATE TABLE IF NOT EXISTS Users (
    userNumber BIGINT UNSIGNED NOT NULL,
    userId VARCHAR(255) NOT NULL,
    userPassword VARCHAR(255) NOT NULL,
    userName VARCHAR(255) NOT NULL,
    userAge INT NOT NULL,
    userGender VARCHAR(255) NOT NULL,
    userNickname VARCHAR(255) NOT NULL,
    tag1 VARCHAR(255) NULL,
    tag2 VARCHAR(255) NULL,
    tag3 VARCHAR(255) NULL,
    role VARCHAR(255) NULL,
    userProfile VARCHAR(255) NULL,
    PRIMARY KEY (`userNumber`)
);
### 태그 테이블
CREATE TABLE IF NOT EXISTS Tag (
    sentimental VARCHAR(255) COMMENT '감성적인',
    motivated VARCHAR(255) COMMENT '동기부여',
    console VARCHAR(255) COMMENT '위로',
    realize VARCHAR(255) COMMENT '깨달음',
    poem VARCHAR(255) COMMENT '시',
    realistic VARCHAR(255) COMMENT '현실직시',
    nature VARCHAR(255) COMMENT '자연',
    wisesaying VARCHAR(255) COMMENT '명언',
    love VARCHAR(255) COMMENT '사랑',
    quotesInNovels VARCHAR(255) COMMENT '소설 속 명언',
    userNumber BIGINT UNSIGNED COMMENT 'auto increment',
    geulgwiNumber BIGINT UNSIGNED,
    PRIMARY KEY (userNumber, geulgwiNumber),
    FOREIGN KEY (userNumber) REFERENCES Users(userNumber),
    FOREIGN KEY (geulgwiNumber) REFERENCES Geulgwi(geulgwiNumber)
);
### 글귀 테이블
CREATE TABLE IF NOT EXISTS Geulgwi (
    geulgwiNumber BIGINT UNSIGNED NOT NULL,
    userId VARCHAR(255) NOT NULL,
    userNickname VARCHAR(255) NOT NULL,
    geulgwiTitle VARCHAR(255) NOT NULL,
    geulgwiContent TEXT NOT NULL,
    tag1 VARCHAR(255) NULL,
    tag2 VARCHAR(255) NULL,
    tag3 VARCHAR(255) NULL,
    regDate DATETIME NULL,
    updateDate DATETIME NULL,
    userNumber BIGINT UNSIGNED,
    PRIMARY KEY (geulgwiNumber)
);
#
# ### 자유게시판 테이블
# CREATE TABLE IF NOT EXISTS `Post` (
#     `postNumber` BIGINT UNSIGNED NOT NULL,
#     `userId` VARCHAR(255) NOT NULL,
#     `userNickname` VARCHAR(255) NOT NULL,
#     `postTitle` VARCHAR(255) NOT NULL,
#     `postContent` TEXT NOT NULL,
#     `regDate` DATETIME NULL,
#     `updateDate` DATETIME NULL,
#     `likes` INT NULL,
#     `userNumber` BIGINT UNSIGNED NULL,
#     PRIMARY KEY (`postNumber`)
# );

#
# ### 댓글 테이블
# CREATE TABLE if not exists `Comment` (
#     `replyNumber` BIGINT UNSIGNED NOT NULL,
#     `postNumber` BIGINT UNSIGNED NOT NULL,
#     `replyId` VARCHAR(255) NOT NULL,
#     `replyComment`	VARCHAR(255) NOT NULL,
#     `regDate` DATETIME NULL,
#     `updateDate` DATETIME NULL,
#     PRIMARY KEY (`replyNumber`),
#     FOREIGN KEY (`postNumber`) REFERENCES `Post`(`postNumber`)
# );
#
# ### 글귀챌린지_유저
# CREATE TABLE if not exists `Challenge_User` (
#     `challengeNumber` BIGINT UNSIGNED NOT NULL,
#     `userId` VARCHAR(255) NOT NULL,
#     `challengeContent` VARCHAR(255) NOT NULL,
#     `userNumber` BIGINT UNSIGNED NOT NULL,
#     PRIMARY KEY (`challengeNumber`),
#     FOREIGN KEY (`userNumber`) REFERENCES `Users`(`userNumber`)
# );
#
# ### 글귀챌린지_관리자
# CREATE TABLE if not exists `ChallengeAdmin` (
#     `challengeNumber` BIGINT UNSIGNED NOT NULL,
#     `keyword1` VARCHAR(255) NULL,
#     `keyword2` VARCHAR(255) NULL,
#     `keyword3` VARCHAR(255) NULL
# );
#
# ALTER TABLE `Users` ADD CONSTRAINT `PK_USERS` PRIMARY KEY (
#     `userNumber`
# );
#
# ALTER TABLE `Geulgwi` ADD CONSTRAINT `PK_GEULGWI` PRIMARY KEY (
#     `geulgwiNumber`
# );
#
# ALTER TABLE `Post` ADD CONSTRAINT `PK_POST` PRIMARY KEY (
#     `postNumber`
# );
#
# ALTER TABLE `Comment` ADD CONSTRAINT `PK_COMMENT` PRIMARY KEY (
#     `replyNumber`,
#     `postNumber`
# );
#
# ALTER TABLE `Challenge_User` ADD CONSTRAINT `PK_CHALLENGE_USER` PRIMARY KEY (
#     `challengeNumber`
# );
#
# ALTER TABLE `ChallengeAdmin` ADD CONSTRAINT `PK_CHALLENGEADMIN` PRIMARY KEY (
#     `challengeNumber`
# );
#
# ALTER TABLE `Comment` ADD CONSTRAINT `FK_Post_TO_Comment_1` FOREIGN KEY (
#     `postNumber`
#     )
#     REFERENCES `Post` (
#     postNumber
#     );
#
# ALTER TABLE `Challenge_User` ADD CONSTRAINT `FK_ChallengeAdmin_TO_Challenge_User_1` FOREIGN KEY (
#     `challengeNumber`
#     )
#     REFERENCES `ChallengeAdmin` (
#     `challengeNumber`
# );