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
  `id_shoping_cart` BIGINT(11) NOT NULL,
  `id_user` VARCHAR(11) NULL,
  PRIMARY KEY (`id_shoping_cart`),
  CONSTRAINT `fk_id_user`
    FOREIGN KEY (`id_shoping_cart`)
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

CREATE TABLE `internet_shop`.`shopping_carts_products` (
  `id_shopping_carts_products` BIGINT(11) NOT NULL,
  `shopping_carts_id` BIGINT(11) NULL,
  `product_id` BIGINT(11) NULL,
  PRIMARY KEY (`id_shopping_carts_products`),
  INDEX `fk_shoping_cart_idx` (`shopping_carts_id` ASC) VISIBLE,
  INDEX `fk_product_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_shoping_cart`
    FOREIGN KEY (`shopping_carts_id`)
    REFERENCES `internet_shop`.`shoping_carts` (`id_shoping_cart`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `internet_shop`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
