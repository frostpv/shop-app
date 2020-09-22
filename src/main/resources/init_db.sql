CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `internet_shop`.`products` (
  `product_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `price` DOUBLE NULL,
  `deleted` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`product_id`));
INSERT INTO `internet_shop`.`products` (`name`, `price`, `deleted`) VALUES ('AuduQ3', '50000', '0');
INSERT INTO `internet_shop`.`products` (`name`, `price`, `deleted`) VALUES ('AudiQ5', '70000', '0');
CREATE TABLE `internet_shop`.`users` (
  `user_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(225) NULL,
  `user_login` VARCHAR(225) NULL,
  `user_pass` VARCHAR(225) NULL,
  `role_id` BIGINT(11) NULL,
  PRIMARY KEY (`user_id`));

  CREATE TABLE `internet_shop`.`roles` (
  `role_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(225) NULL,
  PRIMARY KEY (`role_id`));

INSERT INTO `internet_shop`.`roles` (`role_name`) VALUES ('ADMIN');
INSERT INTO `internet_shop`.`roles` (`role_name`) VALUES ('USER');
