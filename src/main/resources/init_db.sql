CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `internet_shop`.`products` (
  `product_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `price` DOUBLE NULL,
  `deleted` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`product_id`));
INSERT INTO `internet_shop`.`products` (`name`, `price`, `deleted`) VALUES ('AuduQ3', '50000', '0');
INSERT INTO `internet_shop`.`products` (`name`, `price`, `deleted`) VALUES ('AudiQ5', '70000', '0');
