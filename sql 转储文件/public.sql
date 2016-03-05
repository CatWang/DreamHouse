/*
Navicat PGSQL Data Transfer

Source Server         : localhost_5432
Source Server Version : 90403
Source Host           : localhost:5432
Source Database       : postgres
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90403
File Encoding         : 65001

Date: 2015-06-18 13:07:29
*/


-- ----------------------------
-- Table structure for ad
-- ----------------------------
DROP TABLE IF EXISTS "public"."ad";
CREATE TABLE "public"."ad" (
"propNo" varchar(10) COLLATE "default" NOT NULL,
"adDate" date NOT NULL,
"newsName" varchar(25) COLLATE "default" NOT NULL,
"cost" numeric(5,2)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of ad
-- ----------------------------
INSERT INTO "public"."ad" VALUES ('pr1110001', '2014-11-18', 'Changjiang Jin', '200.00');
INSERT INTO "public"."ad" VALUES ('pr1110001', '2014-12-12', 'Changjiang Jin', '200.00');
INSERT INTO "public"."ad" VALUES ('pr1110001', '2015-03-15', 'Changjiang Jin', '200.00');
INSERT INTO "public"."ad" VALUES ('pr1120001', '2014-12-14', 'Changjiang Jin', '200.00');
INSERT INTO "public"."ad" VALUES ('pr2110001', '2015-04-28', 'Cangzhou Daily', '150.00');
INSERT INTO "public"."ad" VALUES ('pr2110002', '2015-01-25', 'Cangzhou Daily', '150.00');
INSERT INTO "public"."ad" VALUES ('pr2110002', '2015-03-24', 'Cangzhou Daily', '150.00');
INSERT INTO "public"."ad" VALUES ('pr3110001', '2015-01-17', 'Jinling Daily', '300.00');
INSERT INTO "public"."ad" VALUES ('pr3110001', '2015-04-23', 'Jinling Daily', '300.00');
INSERT INTO "public"."ad" VALUES ('pr3120002', '2015-03-14', 'Jinling Daily', '300.00');

-- ----------------------------
-- Table structure for Branch
-- ----------------------------
DROP TABLE IF EXISTS "public"."Branch";
CREATE TABLE "public"."Branch" (
"branchNo" varchar(4) COLLATE "default" NOT NULL,
"street" varchar(15) COLLATE "default",
"city" varchar(15) COLLATE "default",
"postcode" varchar(20) COLLATE "default",
"teleNo" varchar(15) COLLATE "default",
"managerNo" varchar(8) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of Branch
-- ----------------------------
INSERT INTO "public"."Branch" VALUES ('bh01', 'Luoyu', 'Wuhan', '430079', '6363587', 'st100001');
INSERT INTO "public"."Branch" VALUES ('bh02', 'Yingbin', 'Cangzhou', '631000', '6789248', 'st200001');
INSERT INTO "public"."Branch" VALUES ('bh03', 'Zhongshan', 'Nanjing', '486200', '8792486', 'st300001');
INSERT INTO "public"."Branch" VALUES ('bh05', 'lala', 'jadk', '656546', '4582', 'st100001');

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS "public"."client";
CREATE TABLE "public"."client" (
"ClientNo" varchar(8) COLLATE "default" NOT NULL,
"fName" varchar(15) COLLATE "default" NOT NULL,
"lName" varchar(15) COLLATE "default" NOT NULL,
"teleNo" varchar(15) COLLATE "default" NOT NULL,
"email" varchar(25) COLLATE "default",
"type" varchar(15) COLLATE "default",
"maxRent" numeric(7,2),
"staffNo" varchar(8) COLLATE "default" NOT NULL,
"joinDate" date NOT NULL,
"branchNo" varchar(4) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO "public"."client" VALUES ('cl100001', 'Bill', 'Levis', '15479234861', '123@hotmail.com', 'flat', '2000.00', 'st100003', '2015-03-15', 'bh01');
INSERT INTO "public"."client" VALUES ('cl100002', 'Hank', 'Hall', '13578968414', 'abc@gmail.com', 'flat', '1500.00', 'st100003', '2014-12-03', 'bh01');
INSERT INTO "public"."client" VALUES ('cl100003', 'John', 'Young', '15015987315', '125@qq.com', 'house', '3000.00', 'st100002', '2014-11-23', 'bh01');
INSERT INTO "public"."client" VALUES ('cl200001', 'Bobby', 'Walker', '13078928964', 'lls@163.com', 'flat', '1200.00', 'st200003', '2015-05-01', 'bh02');
INSERT INTO "public"."client" VALUES ('cl200002', 'Douglas', 'King', '15078924863', 'dk@126.com', 'house', '2500.00', 'st200002', '2014-12-30', 'bh02');
INSERT INTO "public"."client" VALUES ('cl200003', 'Pheobe', 'Baker', '13513278501', 'ph@vip.qq.com', 'house', '2700.00', 'st200003', '2014-11-18', 'bh02');
INSERT INTO "public"."client" VALUES ('cl300001', 'Jerry', 'Green', '15804863782', 'je@hotmail.com', 'flat', '1500.00', 'st300003', '2014-12-18', 'bh03');
INSERT INTO "public"."client" VALUES ('cl300002', 'Mason', 'Collins', '13948524466', 'mson@gmail.com', 'house', '3500.00', 'st300003', '2015-03-19', 'bh03');
INSERT INTO "public"."client" VALUES ('cl300003', 'Ultraman', 'Cook', '13078519647', 'ultra@qq.com', 'house', '3000.00', 'st300002', '2015-04-16', 'bh03');

-- ----------------------------
-- Table structure for lease
-- ----------------------------
DROP TABLE IF EXISTS "public"."lease";
CREATE TABLE "public"."lease" (
"leaseNo" varchar(12) COLLATE "default" NOT NULL,
"clientNo" varchar(8) COLLATE "default" NOT NULL,
"propNo" varchar(10) COLLATE "default" NOT NULL,
"payMethod" varchar(15) COLLATE "default" NOT NULL,
"depositPaid" bool NOT NULL,
"startDate" date NOT NULL,
"endDate" date NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of lease
-- ----------------------------
INSERT INTO "public"."lease" VALUES ('ls10000001', 'cl100001', 'pr1110001', 'cash', 't', '2015-06-01', '2016-05-31');
INSERT INTO "public"."lease" VALUES ('ls10000002', 'cl100002', 'pr1120001', 'cheque', 'f', '2015-06-01', '2015-12-31');
INSERT INTO "public"."lease" VALUES ('ls20000001', 'cl200001', 'pr2110001', 'cash', 't', '2015-06-01', '2015-10-31');
INSERT INTO "public"."lease" VALUES ('ls20000002', 'cl200001', 'pr2120001', 'cash', 't', '2015-02-01', '2015-05-31');
INSERT INTO "public"."lease" VALUES ('LS30000001', 'cl300001', 'pr3110001', 'cheque', 'f', '2015-04-15', '2015-12-14');

-- ----------------------------
-- Table structure for Newspaper
-- ----------------------------
DROP TABLE IF EXISTS "public"."Newspaper";
CREATE TABLE "public"."Newspaper" (
"newsName" varchar(25) COLLATE "default" NOT NULL,
"street" varchar(15) COLLATE "default",
"city" varchar(15) COLLATE "default" NOT NULL,
"postcode" varchar(20) COLLATE "default",
"teleNo" varchar(15) COLLATE "default" NOT NULL,
"fName" varchar(15) COLLATE "default",
"lName" varchar(15) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of Newspaper
-- ----------------------------
INSERT INTO "public"."Newspaper" VALUES ('Cangzhou Daily', 'Bohan', 'Cangzhou', '631000', '8147962', 'Brant', 'Clark');
INSERT INTO "public"."Newspaper" VALUES ('Changjiang Jin', 'Jianghan', 'Wuhan', '430079', '4831579', 'Billy', 'Davis');
INSERT INTO "public"."Newspaper" VALUES ('Jinling Daily', 'Xinjie', 'Nanjing', '482130', '7186146', 'Duke', 'Lee');

-- ----------------------------
-- Table structure for owner
-- ----------------------------
DROP TABLE IF EXISTS "public"."owner";
CREATE TABLE "public"."owner" (
"ownerNo" varchar(8) COLLATE "default" NOT NULL,
"fName" varchar(10) COLLATE "default" NOT NULL,
"lName" varchar(10) COLLATE "default" NOT NULL,
"street" varchar(15) COLLATE "default",
"city" varchar(15) COLLATE "default" NOT NULL,
"postcode" varchar(20) COLLATE "default",
"teleNo" varchar(15) COLLATE "default" NOT NULL,
"email" varchar(25) COLLATE "default",
"password" varchar(20) COLLATE "default" NOT NULL,
"type" varchar(10) COLLATE "default",
"bName" varchar(15) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of owner
-- ----------------------------
INSERT INTO "public"."owner" VALUES ('on110001', 'Tommy', 'Evans', 'Chuhe', 'Wuhan', '430079', '15034879201', 'tom@gmail,com', '110001', null, null);
INSERT INTO "public"."owner" VALUES ('on120002', 'Thomas', 'Stewart', 'Xiongchu', 'Wuhan', '430079', '8147906', 'Tho@qq.com', '120002', 'business', 'Stewart');
INSERT INTO "public"."owner" VALUES ('on210001', 'Tony', 'Morris', 'XInhua', 'Cangzhou', '631000', '13514890134', 'ton@hotmail.com', '210001', null, null);
INSERT INTO "public"."owner" VALUES ('on220002', 'Ronald', 'Sanchez', 'Yunhe', 'Cangzhou', '631000', '8648126', 'fdjk@hotmail.com', '220002', 'accomdate', 'Sanchez');
INSERT INTO "public"."owner" VALUES ('on310002', 'Terrence', 'Bell', 'zhongshan', 'Nanjing', '486200', '15734896314', 'terren@hotmail.com', '310002', null, null);
INSERT INTO "public"."owner" VALUES ('on320001', 'Adeline', 'Reed', 'Xinjie', 'Nanjing', '486200', '4872169', 'ade@qq.com', '320001', 'business', 'Reed');

-- ----------------------------
-- Table structure for Property
-- ----------------------------
DROP TABLE IF EXISTS "public"."Property";
CREATE TABLE "public"."Property" (
"propNo" varchar(10) COLLATE "default" NOT NULL,
"street" varchar(15) COLLATE "default",
"city" varchar(15) COLLATE "default" NOT NULL,
"postcode" varchar(20) COLLATE "default",
"type" varchar(10) COLLATE "default" NOT NULL,
"roomHave" int2 NOT NULL,
"rentFee" numeric(7,2) NOT NULL,
"ownerNo" varchar(8) COLLATE "default" NOT NULL,
"staffNo" varchar(8) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of Property
-- ----------------------------
INSERT INTO "public"."Property" VALUES ('pr1110001', 'Luojia', 'Wuhan', '430079', 'flat', '2', '1300.00', 'on110001', 'st100003');
INSERT INTO "public"."Property" VALUES ('pr1110002', 'Luojia', 'Wuhan', '430079', 'flat', '1', '800.00', 'on110001', 'st100003');
INSERT INTO "public"."Property" VALUES ('pr1120001', 'Chuhe', 'Wuhan', '430079', 'house', '5', '2500.00', 'on120002', 'st100002');
INSERT INTO "public"."Property" VALUES ('pr2110001', 'Xinhua', 'Cangzhou', '631000', 'flat', '2', '1000.00', 'on210001', 'st200002');
INSERT INTO "public"."Property" VALUES ('pr2110002', 'Yunhe', 'Cangzhou', '631000', 'house', '4', '1800.00', 'on210001', 'st200003');
INSERT INTO "public"."Property" VALUES ('pr2120001', 'Xinhua', 'Cangzhou', '631000', 'house', '5', '2000.00', 'on220002', 'st200003');
INSERT INTO "public"."Property" VALUES ('pr3110001', 'Qingpu', 'Nanjing', '486200', 'house', '3', '1800.00', 'on310002', 'st300002');
INSERT INTO "public"."Property" VALUES ('pr3120001', 'Xinjie', 'Nanjing', '486200', 'flat', '2', '1400.00', 'on320001', 'st300003');
INSERT INTO "public"."Property" VALUES ('pr3120002', 'Xinjie', 'Nanjing', '486200', 'house', '4', '2000.00', 'on320001', 'st300003');

-- ----------------------------
-- Table structure for Staff
-- ----------------------------
DROP TABLE IF EXISTS "public"."Staff";
CREATE TABLE "public"."Staff" (
"staffNo" varchar(8) COLLATE "default" NOT NULL,
"fName" varchar(10) COLLATE "default" NOT NULL,
"lName" varchar(10) COLLATE "default" NOT NULL,
"street" varchar(15) COLLATE "default",
"city" varchar(15) COLLATE "default" NOT NULL,
"postcode" varchar(20) COLLATE "default",
"salary" numeric(10,2),
"superNo" varchar(8) COLLATE "default",
"startDate" date,
"bonus" numeric(10,2),
"branchNo" varchar(4) COLLATE "default",
"position" varchar(15) COLLATE "default",
"gender" varchar(2) COLLATE "default",
"DOB" date
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of Staff
-- ----------------------------
INSERT INTO "public"."Staff" VALUES ('st100001', 'Steven', 'Smith', 'Luojia', 'Wuhan', '430079', '35000.00', null, '2002-01-23', '50000.00', 'bh01', 'manager', 'M', '1972-05-13');
INSERT INTO "public"."Staff" VALUES ('st100002', 'Amily', 'Swift', 'Luojia', 'Wuhan', '430079', '15000.00', 'st100001', '2005-07-20', null, 'bh01', 'supervisor', 'F', '1980-03-14');
INSERT INTO "public"."Staff" VALUES ('st100003', 'Tommy', 'Stack', 'Xiongchu', 'Wuhan', '430079', '8000.00', 'st100002', '2015-05-22', null, 'bh01', 'assitant', 'M', '1983-12-14');
INSERT INTO "public"."Staff" VALUES ('st200001', 'Sue', 'Clinton', 'Binjiang', 'Cangzhou', '631000', '30000.00', null, '2011-06-17', '45000.00', 'bh02', 'manager', 'F', '1968-10-14');
INSERT INTO "public"."Staff" VALUES ('st200002', 'Goerge', 'Bush', 'Xinhua', 'Cangzhou', '631000', '12000.00', null, '2009-04-07', null, 'bh02', 'supervisor', 'M', '1976-03-23');
INSERT INTO "public"."Staff" VALUES ('st200003', 'James', 'Moore', 'Yunhe', 'Cangzhou', '631000', '6000.00', 'st200002', '2013-07-09', null, 'bh02', 'assitant', 'M', '1987-06-14');
INSERT INTO "public"."Staff" VALUES ('st300001', 'Jack', 'Jones', 'Zhongshan', 'Nanjing', '486200', '55000.00', null, '2001-07-15', '80000.00', 'bh03', 'manager', 'M', '1988-05-29');
INSERT INTO "public"."Staff" VALUES ('st300002', 'Cary', 'Wilson', 'Qingpu', 'Nanjing', '486200', '20000.00', 'st300001', '2009-04-08', null, 'bh03', 'supervisor', 'F', '1969-07-28');
INSERT INTO "public"."Staff" VALUES ('st300003', 'Dylan', 'White', 'Shiziqiao', 'Nanjing', '486200', '13000.00', 'st300002', '2014-06-18', null, 'bh03', 'assitant', 'M', '1979-06-27');

-- ----------------------------
-- Table structure for Viewing
-- ----------------------------
DROP TABLE IF EXISTS "public"."Viewing";
CREATE TABLE "public"."Viewing" (
"ClientNo" varchar(8) COLLATE "default" NOT NULL,
"propNo" varchar(10) COLLATE "default" NOT NULL,
"viewDate" date NOT NULL,
"comment" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of Viewing
-- ----------------------------
INSERT INTO "public"."Viewing" VALUES ('cl100001', 'pr1110002', '2015-04-25', 'too expensive');
INSERT INTO "public"."Viewing" VALUES ('cl100002', 'pr1110002', '2015-03-15', 'too small');
INSERT INTO "public"."Viewing" VALUES ('cl100002', 'pr1120001', '2015-03-15', 'good');
INSERT INTO "public"."Viewing" VALUES ('cl100003', 'pr1110002', '2015-04-22', 'good');
INSERT INTO "public"."Viewing" VALUES ('cl200001', 'pr2110002', '2015-03-25', 'far from subway');
INSERT INTO "public"."Viewing" VALUES ('cl200002', 'pr2110001', '2015-02-20', 'too small');
INSERT INTO "public"."Viewing" VALUES ('cl200003', 'pr2120001', '2015-04-26', 'good');
INSERT INTO "public"."Viewing" VALUES ('cl300002', 'pr3110001', '2015-05-15', 'good');
INSERT INTO "public"."Viewing" VALUES ('cl300003', 'pr3110001', '2015-05-15', null);
INSERT INTO "public"."Viewing" VALUES ('cl300003', 'pr3120002', '2015-03-17', 'too small');

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table ad
-- ----------------------------
ALTER TABLE "public"."ad" ADD PRIMARY KEY ("propNo", "adDate", "newsName");

-- ----------------------------
-- Primary Key structure for table Branch
-- ----------------------------
ALTER TABLE "public"."Branch" ADD PRIMARY KEY ("branchNo");

-- ----------------------------
-- Primary Key structure for table client
-- ----------------------------
ALTER TABLE "public"."client" ADD PRIMARY KEY ("ClientNo");

-- ----------------------------
-- Primary Key structure for table lease
-- ----------------------------
ALTER TABLE "public"."lease" ADD PRIMARY KEY ("leaseNo");

-- ----------------------------
-- Primary Key structure for table Newspaper
-- ----------------------------
ALTER TABLE "public"."Newspaper" ADD PRIMARY KEY ("newsName");

-- ----------------------------
-- Primary Key structure for table owner
-- ----------------------------
ALTER TABLE "public"."owner" ADD PRIMARY KEY ("ownerNo");

-- ----------------------------
-- Primary Key structure for table Property
-- ----------------------------
ALTER TABLE "public"."Property" ADD PRIMARY KEY ("propNo");

-- ----------------------------
-- Primary Key structure for table Staff
-- ----------------------------
ALTER TABLE "public"."Staff" ADD PRIMARY KEY ("staffNo");

-- ----------------------------
-- Primary Key structure for table Viewing
-- ----------------------------
ALTER TABLE "public"."Viewing" ADD PRIMARY KEY ("ClientNo", "propNo", "viewDate");

-- ----------------------------
-- Foreign Key structure for table "public"."ad"
-- ----------------------------
ALTER TABLE "public"."ad" ADD FOREIGN KEY ("propNo") REFERENCES "public"."Property" ("propNo") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."ad" ADD FOREIGN KEY ("newsName") REFERENCES "public"."Newspaper" ("newsName") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."Branch"
-- ----------------------------
ALTER TABLE "public"."Branch" ADD FOREIGN KEY ("managerNo") REFERENCES "public"."Staff" ("staffNo") ON DELETE SET NULL ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."client"
-- ----------------------------
ALTER TABLE "public"."client" ADD FOREIGN KEY ("staffNo") REFERENCES "public"."Staff" ("staffNo") ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE "public"."client" ADD FOREIGN KEY ("branchNo") REFERENCES "public"."Branch" ("branchNo") ON DELETE SET NULL ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."lease"
-- ----------------------------
ALTER TABLE "public"."lease" ADD FOREIGN KEY ("clientNo") REFERENCES "public"."client" ("ClientNo") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."lease" ADD FOREIGN KEY ("propNo") REFERENCES "public"."Property" ("propNo") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."Property"
-- ----------------------------
ALTER TABLE "public"."Property" ADD FOREIGN KEY ("ownerNo") REFERENCES "public"."owner" ("ownerNo") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."Property" ADD FOREIGN KEY ("staffNo") REFERENCES "public"."Staff" ("staffNo") ON DELETE SET NULL ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."Staff"
-- ----------------------------
ALTER TABLE "public"."Staff" ADD FOREIGN KEY ("superNo") REFERENCES "public"."Staff" ("staffNo") ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE "public"."Staff" ADD FOREIGN KEY ("branchNo") REFERENCES "public"."Branch" ("branchNo") ON DELETE SET NULL ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."Viewing"
-- ----------------------------
ALTER TABLE "public"."Viewing" ADD FOREIGN KEY ("propNo") REFERENCES "public"."Property" ("propNo") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."Viewing" ADD FOREIGN KEY ("ClientNo") REFERENCES "public"."client" ("ClientNo") ON DELETE CASCADE ON UPDATE CASCADE;
