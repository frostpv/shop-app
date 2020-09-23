CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `internet_shop`.`products` (
  `product_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `price` DOUBLE NULL,
  `deleted` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`product_id`));
INSERT INTO `internet_shop`.`products` (`name`, `price`, `deleted`) VALUES ('AudiQ3', '50000', '0');
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

ALTER TABLE `internet_shop`.`users`
CHANGE COLUMN `role_id` `deleted` TINYINT NULL DEFAULT 0 ;

CREATE TABLE `internet_shop`.`user_roles` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(11) NULL,
  `role_id` BIGINT(11) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_User_role_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet_shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `internet_shop`.`roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

 CREATE TABLE `internet_shop`.`shoping_carts` (
  `id_shoping_carts` INT NOT NULL AUTO_INCREMENT,
  `id_user` BIGINT(11) NULL,
  PRIMARY KEY (`id_shoping_carts`),
  INDEX `is_user_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `is_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `internet_shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `internet_shop`.`orders` (
  `order_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(11) NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `fk_order_user_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `internet_shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `internet_shop`.`orders_products` (
  `id_orders_products` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `order_id` BIGINT(11) NULL,
  `product_id` BIGINT(11) NULL,
  PRIMARY KEY (`id_orders_products`),
  INDEX `fk_order_id_idx` (`order_id` ASC) VISIBLE,
  INDEX `fk_product_id_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `internet_shop`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `internet_shop`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `internet_shop`.`shoping_cart_products` (
  `id_shoping_cart_products` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `id_cart` BIGINT(11) NULL,
  `id_product` BIGINT(11) NULL,
  PRIMARY KEY (`id_shoping_cart_products`),
  INDEX `fk_shoping_cart_idx` (`id_cart` ASC) VISIBLE,
  INDEX `fk_product_idx` (`id_product` ASC) VISIBLE,
  CONSTRAINT `fk_shoping_cart`
    FOREIGN KEY (`id_cart`)
    REFERENCES `internet_shop`.`shoping_carts` (`id_shoping_carts`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product`
    FOREIGN KEY (`id_product`)
    REFERENCES `internet_shop`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    ALTER TABLE `internet_shop`.`orders`
ADD COLUMN `deleted` TINYINT NULL DEFAULT 0 AFTER `user_id`;


