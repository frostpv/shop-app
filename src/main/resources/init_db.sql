DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `deleted` tinyint DEFAULT '0',
  PRIMARY KEY (`order_id`),
  KEY `fk_users_id_idx` (`user_id`),
  CONSTRAINT `fk_users_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
LOCK TABLES `orders` WRITE;
INSERT INTO `orders` VALUES (1,2,1),(2,2,1),(3,2,1),(4,2,1),(5,4,1),(6,4,1),(7,4,1),(8,4,1),(9,4,1),(10,4,1),(11,2,1),(12,4,1),(13,4,1),(14,4,1),(15,5,1),(16,4,1),(17,4,1),(18,4,1),(19,4,1),(20,4,1),(21,6,1),(22,1,1),(23,6,1),(24,6,1),(25,6,0);
UNLOCK TABLES;
DROP TABLE IF EXISTS `orders_products`;
CREATE TABLE `orders_products` (
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  KEY `fk_productc_id_idx` (`product_id`),
  KEY `fk_orders_id_idx` (`order_id`),
  CONSTRAINT `fk_orders_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `fk_productc_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
LOCK TABLES `orders_products` WRITE;
INSERT INTO `orders_products` VALUES (3,1),(3,1),(3,1),(3,1),(4,1),(5,1),(5,1),(6,1),(6,1),(7,1),(7,2),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,2),(15,2),(16,1),(17,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1);
UNLOCK TABLES;
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `price` double NOT NULL,
  `deleted` tinyint DEFAULT '0',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
LOCK TABLES `products` WRITE;
INSERT INTO `products` VALUES (1,'Audi Q5',45686,0),(2,'Audi Q5',51515,1);
UNLOCK TABLES;
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(256) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
LOCK TABLES `roles` WRITE;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER');
UNLOCK TABLES;
DROP TABLE IF EXISTS `shoping_cart`;
CREATE TABLE `shoping_cart` (
  `id_shoping_cart` bigint NOT NULL AUTO_INCREMENT,
  `id_user` bigint DEFAULT NULL,
  `deleted` tinyint DEFAULT '0',
  PRIMARY KEY (`id_shoping_cart`),
  KEY `fk_userss_ids_idx` (`id_user`),
  CONSTRAINT `fk_userss_ids` FOREIGN KEY (`id_user`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
LOCK TABLES `shoping_cart` WRITE;
INSERT INTO `shoping_cart` VALUES (1,1,1),(2,3,1),(3,4,0),(4,5,0),(5,6,0),(6,7,1);
UNLOCK TABLES;
DROP TABLE IF EXISTS `shoping_cart_products`;
CREATE TABLE `shoping_cart_products` (
  `id_cart` bigint DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  KEY `fk_cart_id_idx` (`id_cart`),
  KEY `fk_product_id_idx` (`id_product`),
  CONSTRAINT `fk_cart_id` FOREIGN KEY (`id_cart`) REFERENCES `shoping_cart` (`id_shoping_cart`),
  CONSTRAINT `fk_product_id` FOREIGN KEY (`id_product`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
LOCK TABLES `shoping_cart_products` WRITE;
INSERT INTO `shoping_cart_products` VALUES (4,2),(2,1),(3,1),(1,1),(1,1);
UNLOCK TABLES;
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `user_id` bigint DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  KEY `fk_role_id_idx` (`role_id`),
  KEY `user_fk_id_idx` (`user_id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `user_fk_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
LOCK TABLES `user_roles` WRITE;
INSERT INTO `user_roles` VALUES (1,2),(2,1),(3,2),(4,2),(2,1),(5,2),(6,2),(7,2);
UNLOCK TABLES;
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(226) DEFAULT NULL,
  `user_login` varchar(226) DEFAULT NULL,
  `user_pass` varchar(226) DEFAULT NULL,
  `deleted` tinyint DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES (1,'Igor','Igor','123',1),(2,'Admin','Admin','123',0),(3,'Laci','Laci','123',1),(4,'Max','Max','123',0),(5,'Dima','Dima','123',0),(6,'Mika','Mika','123',0),(7,'Igor','Igor','123',1);
UNLOCK TABLES;
ALTER TABLE `shop`.`users`
ADD COLUMN `salt` VARBINARY(16) NULL AFTER `deleted`;