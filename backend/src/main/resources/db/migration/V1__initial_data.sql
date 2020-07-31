CREATE TABLE ingredient (
  id varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  price double NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_bcuaj97y3iu3t2vj26jg6hijj (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO ingredient (id, name, price)
VALUES
	('1ff3144b-e365-4ac4-8681-d1ff6c60ff26','Queijo',1.5),
	('975cb180-a19e-4f05-b264-8a6d7f799f43','Ovo',0.8),
	('9f6867d5-7bf2-44e7-8d7c-8a596212cda6','Hambúrguer de carne',3),
	('d1b42a23-f122-4def-aff5-a5b6ba2f44b5','Alface',0.4),
	('f21e065e-1642-4efb-8d2f-c985099edd61','Bacon',2);

CREATE TABLE snack (
  id varchar(255) NOT NULL,
  description varchar(255) DEFAULT NULL,
  is_menu_item bit(1) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO snack (id, description, is_menu_item)
VALUES
	('7c99341a-2595-46fc-8301-e99846cb9b71','X-Burger',b'1'),
	('a6b3a3d4-db43-4035-98a5-f0e60ae61645','X-Bacon',b'1'),
	('accb7f4b-3f6d-4511-8e0f-fbb1ddd569c6','X-Egg',b'1'),
	('b3be429b-e665-40b0-9c24-6d69e48535e5','X-Egg Bacon',b'1');

CREATE TABLE snack_item (
  id varchar(255) NOT NULL,
  quantity int(11) NOT NULL,
  ingredient_id varchar(255) NOT NULL,
  snack_id varchar(255) NOT NULL,
  PRIMARY KEY (id),
  KEY FK6aibx0qex2c3l1d3merit1hl9 (ingredient_id),
  KEY FKt8u2iswhuim8imcpwkg1nt08d (snack_id),
  CONSTRAINT FK6aibx0qex2c3l1d3merit1hl9 FOREIGN KEY (ingredient_id) REFERENCES ingredient (id),
  CONSTRAINT FKt8u2iswhuim8imcpwkg1nt08d FOREIGN KEY (snack_id) REFERENCES snack (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO snack_item (id, quantity, ingredient_id, snack_id)
VALUES
	(UUID(),1,'f21e065e-1642-4efb-8d2f-c985099edd61','a6b3a3d4-db43-4035-98a5-f0e60ae61645'),
	(UUID(),1,'9f6867d5-7bf2-44e7-8d7c-8a596212cda6','a6b3a3d4-db43-4035-98a5-f0e60ae61645'),
	(UUID(),1,'1ff3144b-e365-4ac4-8681-d1ff6c60ff26','a6b3a3d4-db43-4035-98a5-f0e60ae61645'),
	(UUID(),1,'9f6867d5-7bf2-44e7-8d7c-8a596212cda6','7c99341a-2595-46fc-8301-e99846cb9b71'),
	(UUID(),1,'1ff3144b-e365-4ac4-8681-d1ff6c60ff26','7c99341a-2595-46fc-8301-e99846cb9b71'),
	(UUID(),1,'975cb180-a19e-4f05-b264-8a6d7f799f43','accb7f4b-3f6d-4511-8e0f-fbb1ddd569c6'),
	(UUID(),1,'9f6867d5-7bf2-44e7-8d7c-8a596212cda6','accb7f4b-3f6d-4511-8e0f-fbb1ddd569c6'),
	(UUID(),1,'1ff3144b-e365-4ac4-8681-d1ff6c60ff26','accb7f4b-3f6d-4511-8e0f-fbb1ddd569c6'),
	(UUID(),1,'975cb180-a19e-4f05-b264-8a6d7f799f43','b3be429b-e665-40b0-9c24-6d69e48535e5'),
	(UUID(),1,'9f6867d5-7bf2-44e7-8d7c-8a596212cda6','b3be429b-e665-40b0-9c24-6d69e48535e5'),
	(UUID(),1,'1ff3144b-e365-4ac4-8681-d1ff6c60ff26','b3be429b-e665-40b0-9c24-6d69e48535e5'),
	(UUID(),1,'f21e065e-1642-4efb-8d2f-c985099edd61','b3be429b-e665-40b0-9c24-6d69e48535e5');

CREATE TABLE snackbar_order (
  id varchar(255) NOT NULL,
  discount double DEFAULT NULL,
  price double NOT NULL,
  snack_id varchar(255) NOT NULL,
  PRIMARY KEY (id),
  KEY FKj6jftt34jtpw9044btsx7wrnd (snack_id),
  CONSTRAINT FKj6jftt34jtpw9044btsx7wrnd FOREIGN KEY (snack_id) REFERENCES snack (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
