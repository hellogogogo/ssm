-- create database ssm character set utf8;

drop table if exists tb_user;
drop table if exists tb_customer;

create table tb_user(
  id int primary key auto_increment,
  username varchar(100),
  password varchar(100)
) default charset = utf8;

create table tb_customer(
  id int primary key auto_increment,
  name varchar(100),
  telephone varchar(100),
  address varchar(100),
  remark varchar(100)
) default charset = utf8;

insert into tb_user values(1,'admin','admin');

insert into tb_customer values(1,'涂陌','123456789','你猜','不想写备注');
insert into tb_customer values(2,'逗瓜','123456789','你猜','不想写备注');
insert into tb_customer values(3,'愤青','123456789','你猜','不想写备注');
insert into tb_customer values(4,'咸鱼','123456789','你猜','不想写备注');
insert into tb_customer values(5,'小白','123456789','你猜','不想写备注');
insert into tb_customer values(6,'菜鸡','123456789','你猜','不想写备注');

CREATE TABLE `logs1` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `logtype` VARCHAR(255) DEFAULT NULL,
  `logurl` VARCHAR(255) DEFAULT NULL,
  `logip` VARCHAR(255) DEFAULT NULL,
  `logdz` VARCHAR(255) DEFAULT NULL,
  `ladduser` VARCHAR(255) DEFAULT NULL,
  `lfadduser` VARCHAR(255) DEFAULT NULL,
  `laddtime` DATETIME DEFAULT NULL,
  `htmlname` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MYISAM  AUTO_INCREMENT=1811 DEFAULT CHARSET=utf8 COMMENT='日志表';

CREATE TABLE `upload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`,`path`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;