DROP TABLE  IF EXISTS `T_GHXX`;
DROP TABLE  IF EXISTS `T_HZXX`;
DROP TABLE  IF EXISTS `T_KSYS`;
DROP TABLE  IF EXISTS `T_BRXX`;
DROP TABLE  IF EXISTS `T_KSXX`;

CREATE TABLE `T_KSXX` (
  `KSBH` char(6),
  `KSMC` char(10) NOT NULL,
  `PYZS` char(8) NOT NULL,
  PRIMARY KEY  (`KSBH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `T_BRXX` (
  `BRBH` char(6),
  `BRMC` char(10) NOT NULL,
  `DLKL` char(8) NOT NULL,
  `YCJE` decimal(10,2) NOT NULL,
  `DLRQ` timestamp  ,
  PRIMARY KEY  (`BRBH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `T_KSYS` (
  `YSBH` char(6),
  `KSBH` char(6) NOT NULL,
  `YSMC` char(10) NOT NULL,
  `PYZS` char(4),
  `DLKL` char(8) NOT NULL,
  `SFZJ` boolean NOT NULL,
  `DLRQ` timestamp ,
  PRIMARY KEY  (`YSBH`),
  FOREIGN KEY(`KSBH`) references `T_KSXX`(`KSBH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `T_HZXX` (
  `HZBH` char(6),
  `HZMC` char(12) NOT NULL,
  `PYZS` char(4) NOT NULL,
  `KSBH` char(6) NOT NULL,
  `SFZJ` boolean NOT NULL,
  `GHRS` int NOT NULL,
  `GHFY`decimal(10,2) NOT NULL,
  PRIMARY KEY  (`HZBH`),
  FOREIGN KEY(`KSBH`) references `T_KSXX`(`KSBH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `T_GHXX` (
  `GHBH` char(6),
  `HZBH` char(6) NOT NULL,
  `YSBH` char(6) NOT NULL,
  `BRBH` char(6) NOT NULL,
  `GHRC` int NOT NULL,
  `THBZ` boolean NOT NULL,
  `GHFY` decimal(8,2) NOT NULL,
  `RQSJ` timestamp NOT NULL,
  PRIMARY KEY  (`GHBH`),
  FOREIGN KEY(`HZBH`) references `T_HZXX`(`HZBH`),
  FOREIGN KEY(`YSBH`) references `T_KSYS`(`YSBH`),
  FOREIGN KEY(`BRBH`) references `T_BRXX`(`BRBH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `jcoursetest`.`T_KSXX`(`KSBH`, `KSMC`, `PYZS`) VALUES ('000001','科室一','KSY');
INSERT INTO `jcoursetest`.`T_KSXX`(`KSBH`, `KSMC`, `PYZS`) VALUES ('000002','科室二','KSE');
INSERT INTO `jcoursetest`.`T_KSXX`(`KSBH`, `KSMC`, `PYZS`) VALUES ('000003','科室三','KSS');
INSERT INTO `jcoursetest`.`T_KSXX`(`KSBH`, `KSMC`, `PYZS`) VALUES ('000004','科室四','KSSI');
INSERT INTO `jcoursetest`.`T_KSXX`(`KSBH`, `KSMC`, `PYZS`) VALUES ('000005','科室五','KSW');
INSERT INTO `jcoursetest`.`T_KSXX`(`KSBH`, `KSMC`, `PYZS`) VALUES ('000006','科室六','KSL');

INSERT INTO `jcoursetest`.`T_BRXX`(`BRBH`, `BRMC`, `DLKL`, `YCJE`, `DLRQ`) VALUES ('000001','病人一','123456','20','2019-04-18 21:21:00');
INSERT INTO `jcoursetest`.`T_BRXX`(`BRBH`, `BRMC`, `DLKL`, `YCJE`, `DLRQ`) VALUES ('000002','病人二','123456','30','2019-04-18 21:21:00');
INSERT INTO `jcoursetest`.`T_BRXX`(`BRBH`, `BRMC`, `DLKL`, `YCJE`, `DLRQ`) VALUES ('000003','病人三','123456','40','2019-04-18 21:21:00');
INSERT INTO `jcoursetest`.`T_BRXX`(`BRBH`, `BRMC`, `DLKL`, `YCJE`, `DLRQ`) VALUES ('000004','病人四','123456','30','2019-04-18 21:21:00');
INSERT INTO `jcoursetest`.`T_BRXX`(`BRBH`, `BRMC`, `DLKL`, `YCJE`, `DLRQ`) VALUES ('000005','病人五','123456','20','2019-04-18 21:21:00');
INSERT INTO `jcoursetest`.`T_BRXX`(`BRBH`, `BRMC`, `DLKL`, `YCJE`, `DLRQ`) VALUES ('000006','病人六','123456','10','2019-04-18 21:21:00');

INSERT INTO `jcoursetest`.`T_KSYS`(`YSBH`, `KSBH`, `YSMC`, `PYZS`, `DLKL`, `SFZJ`, `DLRQ`) VALUES ('000001','000001','专家医生一','YSY' ,'123',true ,'2019-04-18 21:21:00');
INSERT INTO `jcoursetest`.`T_KSYS`(`YSBH`, `KSBH`, `YSMC`, `PYZS`, `DLKL`, `SFZJ`, `DLRQ`) VALUES ('000002','000002','普通医生二','YSE' ,'123',false,'2019-04-18 21:21:00');
INSERT INTO `jcoursetest`.`T_KSYS`(`YSBH`, `KSBH`, `YSMC`, `PYZS`, `DLKL`, `SFZJ`, `DLRQ`) VALUES ('000003','000003','专家医生三','YSS' ,'123',true ,'2019-04-18 21:21:00');
INSERT INTO `jcoursetest`.`T_KSYS`(`YSBH`, `KSBH`, `YSMC`, `PYZS`, `DLKL`, `SFZJ`, `DLRQ`) VALUES ('000004','000004','专家医生四','YSSI','123',true ,'2019-04-18 21:21:00');
INSERT INTO `jcoursetest`.`T_KSYS`(`YSBH`, `KSBH`, `YSMC`, `PYZS`, `DLKL`, `SFZJ`, `DLRQ`) VALUES ('000005','000005','普通医生五','YSW' ,'123',false,'2019-04-18 21:21:00');
INSERT INTO `jcoursetest`.`T_KSYS`(`YSBH`, `KSBH`, `YSMC`, `PYZS`, `DLKL`, `SFZJ`, `DLRQ`) VALUES ('000006','000006','专家医生六','YSL' ,'123',true ,'2019-04-18 21:21:00');

INSERT INTO `jcoursetest`.`T_HZXX`(`HZBH`, `HZMC`, `PYZS`, `KSBH`, `SFZJ`, `GHRS`, `GHFY`) VALUES ('000001','专家号种一','HZY' ,'000001',true ,20,20.5);
INSERT INTO `jcoursetest`.`T_HZXX`(`HZBH`, `HZMC`, `PYZS`, `KSBH`, `SFZJ`, `GHRS`, `GHFY`) VALUES ('000002','普通号种二','HZE' ,'000002',false,20,10.5);
INSERT INTO `jcoursetest`.`T_HZXX`(`HZBH`, `HZMC`, `PYZS`, `KSBH`, `SFZJ`, `GHRS`, `GHFY`) VALUES ('000003','专家号种三','HZS' ,'000003',true ,20,20.5);
INSERT INTO `jcoursetest`.`T_HZXX`(`HZBH`, `HZMC`, `PYZS`, `KSBH`, `SFZJ`, `GHRS`, `GHFY`) VALUES ('000004','专家号种四','HZSI','000004',true ,20,20.5);
INSERT INTO `jcoursetest`.`T_HZXX`(`HZBH`, `HZMC`, `PYZS`, `KSBH`, `SFZJ`, `GHRS`, `GHFY`) VALUES ('000005','普通号种五','HZW' ,'000005',false,20,10.5);
INSERT INTO `jcoursetest`.`T_HZXX`(`HZBH`, `HZMC`, `PYZS`, `KSBH`, `SFZJ`, `GHRS`, `GHFY`) VALUES ('000006','专家号种六','HZL' ,'000006',true ,20,20.5);