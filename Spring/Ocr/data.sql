USE spring;

DROP TABLE IF EXISTS `books`;

CREATE TABLE `books`(
    `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT 'book id',
    `name` VARCHAR(100) NOT NULL COMMENT 'book name',
    `counts` INT(11) NOT NULL COMMENT 'book quantity',
    `detail` VARCHAR(200) NOT NULL COMMENT 'book detail',
    KEY `id`(`id`)
);

INSERT INTO `books`(`name`, `counts`, `detail`) VALUES ('Java', 1, 'this is java book');
INSERT INTO `books`(`name`, `counts`, `detail`) VALUES ('C', 2, 'this is c book');
INSERT INTO `books`(`name`, `counts`, `detail`) VALUES ('Python', 3, 'this is python book');