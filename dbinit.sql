DROP DATABASE IF EXISTS `myorm`;

CREATE DATABASE `myorm` DEFAULT CHARACTER SET utf8;
USE `myorm`;

CREATE TABLE `books` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `year` INTEGER NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `authors` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `surname` VARCHAR(255) NOT NULL,
  `year` INTEGER NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

INSERT INTO `books`
(`id`, `name`, `year`)
VALUES
  (1,    "Азбука", 2000),
  (2,    "Сказки", 1900),
  (3,    "Сталкер", 2000);

INSERT INTO `authors`
(`id`, `name`, `surname`, `year`)
VALUES
  (1,    "Лев",   "Толстой", 1800),
  (2,    "Александр",   "Пушкин", 1850),
  (3,    "Янка",   "Купала", 1900);
