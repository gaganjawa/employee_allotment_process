-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.15


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema employee_allotment
--

CREATE DATABASE IF NOT EXISTS employee_allotment;
USE employee_allotment;

--
-- Definition of table `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `employee_id` varchar(10) NOT NULL,
  `street` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `pincode` varchar(45) NOT NULL,
  `landmark` varchar(45) NOT NULL,
  PRIMARY KEY (`employee_id`),
  CONSTRAINT `employee_address_fk` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` (`employee_id`,`street`,`city`,`state`,`country`,`pincode`,`landmark`) VALUES 
 ('E00001','ABC','Kalyan','Maharashtra','India','421301','ABC'),
 ('E00002','XYZ','Kolkata','West Bengal','India','000301','XYZ'),
 ('E00003','PQR','Panipat','Hariyana','India','111000','PQR'),
 ('E00004','SDF','Pune','Maharashtra','India','444666','SDF'),
 ('E00005','QWERTY','Satara','Maharashtra','India','234567','QWERTY'),
 ('E00006','ghj','Pune','Maharashtra','India','000100','ghj'),
 ('E00007','poi','Pune','Maharashtra','India','000100','poi');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;


--
-- Definition of table `business_unit`
--

DROP TABLE IF EXISTS `business_unit`;
CREATE TABLE `business_unit` (
  `unit_id` varchar(10) NOT NULL,
  `unit_name` varchar(45) NOT NULL,
  `unit_head_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`unit_id`),
  KEY `unit_head_fk` (`unit_head_id`),
  CONSTRAINT `unit_head_fk` FOREIGN KEY (`unit_head_id`) REFERENCES `employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `business_unit`
--

/*!40000 ALTER TABLE `business_unit` DISABLE KEYS */;
INSERT INTO `business_unit` (`unit_id`,`unit_name`,`unit_head_id`) VALUES 
 ('U00000','Common Pool',NULL),
 ('U00001','Microsoft Business Unit','E00007');
/*!40000 ALTER TABLE `business_unit` ENABLE KEYS */;


--
-- Definition of table `credentials`
--

DROP TABLE IF EXISTS `credentials`;
CREATE TABLE IF NOT EXISTS `credentials` (
  `employee_id` varchar(10) NOT NULL,
  `password` varchar(45) NOT NULL,
  `designation` enum('Employee','HR','UnitHead') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Dumping data for table `credentials`
--

/*!40000 ALTER TABLE `credentials` DISABLE KEYS */;
INSERT INTO `credentials` (`employee_id`, `password`, `designation`) VALUES
('E00001', '5ff2aedbccf86eda8bb9338f86b1c308', 'Employee'),
('E00002', 'e2fc714c4727ee9395f324cd2e7f331f', 'Employee'),
('E00003', 'e2fc714c4727ee9395f324cd2e7f331f', 'Employee'),
('E00004', 'e2fc714c4727ee9395f324cd2e7f331f', 'Employee'),
('E00005', 'e2fc714c4727ee9395f324cd2e7f331f', 'Employee'),
('E00006', '81dc9bdb52d04dc20036dbd8313ed055', 'HR'),
('E00007', '674f3c2c1a8a6f90461e8a66fb5550ba', 'UnitHead');
/*!40000 ALTER TABLE `credentials` ENABLE KEYS */;
ALTER TABLE `credentials`
 ADD PRIMARY KEY (`employee_id`);
ALTER TABLE `credentials`
ADD CONSTRAINT `employee_credentials_fk` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE;


--
-- Definition of table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_id` varchar(10) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `gender` enum('MALE','FEMALE') NOT NULL,
  `date_of_birth` date NOT NULL,
  `contact_no` varchar(13) NOT NULL,
  `email_id` varchar(45) NOT NULL,
  `salary` decimal(10,0) unsigned NOT NULL,
  `system_id` varchar(10) DEFAULT NULL,
  `unit_id` varchar(10) DEFAULT NULL,
  `project_id` varchar(10) DEFAULT NULL,
  `skill` enum('Java','CPP','DotNet') NOT NULL,
  `interview_feedback` varchar(500) DEFAULT NULL,
  `date_of_joining` date NOT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `contact_no` (`contact_no`),
  UNIQUE KEY `email_id` (`email_id`),
  KEY `employee_system_fk` (`system_id`),
  KEY `employee_unit_fk` (`unit_id`),
  KEY `employee_employee_fk` (`project_id`),
  CONSTRAINT `employee_employee_fk` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee_system_fk` FOREIGN KEY (`system_id`) REFERENCES `system` (`system_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee_unit_fk` FOREIGN KEY (`unit_id`) REFERENCES `business_unit` (`unit_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`employee_id`,`first_name`,`last_name`,`gender`,`date_of_birth`,`contact_no`,`email_id`,`salary`,`system_id`,`unit_id`,`project_id`,`skill`,`interview_feedback`,`date_of_joining`) VALUES 
 ('E00001','Aalhad','Kulkarni','MALE','1993-12-24','8879621486','aalhad_kulkarni@persistent.co.in','1000000',NULL,NULL,NULL,'Java',NULL,'2016-01-01'),
 ('E00002','Abu','Rahan','MALE','1993-01-01','8983473055','abu_rahan@persistent.co.in','1000000',NULL,NULL,NULL,'Java',NULL,'2015-01-01'),
 ('E00003','Gagan','Jawa','MALE','1993-04-04','8983275669','gagan_jawa@persistent.co.in','1000000',NULL,NULL,NULL,'CPP',NULL,'2015-01-01'),
 ('E00004','Abhishek','Ingale','MALE','1992-05-05','9123456780','abhishek_ingale@persistent.co.in','1000000',NULL,NULL,NULL,'DotNet',NULL,'2015-01-01'),
 ('E00005','Dhiraj','Sutar','MALE','1993-07-07','9876543210','dhiraj_sutar@persistent.co.in','1000000',NULL,NULL,NULL,'Java',NULL,'2015-01-01'),
 ('E00006','Abc','Pqr','MALE','1980-02-02','8812345679','abc_pqr@persistent.co.in','2000000',NULL,NULL,NULL,'CPP',NULL,'2015-01-01'),
 ('E00007','Qwe','Rty','MALE','1980-01-01','8876654321','qwe_rty@persistent.co.in','3000000',NULL,'U00001',NULL,'Java',NULL,'2015-01-01');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;


--
-- Definition of table `project`
--

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `project_id` varchar(10) NOT NULL,
  `project_name` varchar(45) NOT NULL,
  `manager_id` varchar(10) DEFAULT NULL,
  `unit_id` varchar(10) DEFAULT NULL,
  `vacancies` int(10) unsigned NOT NULL DEFAULT '0',
  `skill` enum('Java','CPP','DotNet') NOT NULL,
  PRIMARY KEY (`project_id`),
  KEY `manager_project_fk` (`manager_id`),
  KEY `unit_id` (`unit_id`),
  CONSTRAINT `manager_project_fk` FOREIGN KEY (`manager_id`) REFERENCES `employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`unit_id`) REFERENCES `business_unit` (`unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project`
--

/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` (`project_id`,`project_name`,`manager_id`,`unit_id`,`vacancies`,`skill`) VALUES 
 ('PRJ001','Microsoft Edge Browser','E00007','U00001',6,'DotNet'),
 ('PRJ002','Microsoft Bing Search','E00007','U00001',2,'Java');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;


--
-- Definition of table `project_software_requirement`
--

DROP TABLE IF EXISTS `project_software_requirement`;
CREATE TABLE `project_software_requirement` (
  `project_id` varchar(10) NOT NULL,
  `software_id` varchar(10) NOT NULL,
  PRIMARY KEY (`project_id`,`software_id`),
  KEY `project_software_fk2` (`software_id`),
  CONSTRAINT `project_software_fk1` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_software_fk2` FOREIGN KEY (`software_id`) REFERENCES `software` (`software_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project_software_requirement`
--

/*!40000 ALTER TABLE `project_software_requirement` DISABLE KEYS */;
INSERT INTO `project_software_requirement` (`project_id`,`software_id`) VALUES 
 ('PRJ001','SFT001'),
 ('PRJ001','SFT002'),
 ('PRJ002','SFT002'),
 ('PRJ001','SFT003'),
 ('PRJ002','SFT004'),
 ('PRJ002','SFT005');
/*!40000 ALTER TABLE `project_software_requirement` ENABLE KEYS */;


--
-- Definition of table `software`
--

DROP TABLE IF EXISTS `software`;
CREATE TABLE `software` (
  `software_id` varchar(10) NOT NULL,
  `software_name` varchar(45) NOT NULL,
  PRIMARY KEY (`software_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `software`
--

/*!40000 ALTER TABLE `software` DISABLE KEYS */;
INSERT INTO `software` (`software_id`,`software_name`) VALUES 
 ('jdafhldf','jdafhldf'),
 ('SFT001','Eclipse Luna'),
 ('SFT002','Eclipse Helios'),
 ('SFT003','MySQL'),
 ('SFT004','JDK 1.8'),
 ('SFT005','Apache Tomcat');
/*!40000 ALTER TABLE `software` ENABLE KEYS */;


--
-- Definition of table `system`
--

DROP TABLE IF EXISTS `system`;
CREATE TABLE `system` (
  `system_id` varchar(10) NOT NULL,
  `system_status` enum('Available','NotAvailable') NOT NULL,
  `operating_system` varchar(45) NOT NULL,
  `ip_address` varchar(45) NOT NULL,
  PRIMARY KEY (`system_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `system`
--

/*!40000 ALTER TABLE `system` DISABLE KEYS */;
INSERT INTO `system` (`system_id`,`system_status`,`operating_system`,`ip_address`) VALUES 
 ('SYS001','NotAvailable','Windows 8 - 32 bit','10.44.204.218'),
 ('SYS002','Available','Windows 8 - 64 bit','10.44.214.215'),
 ('SYS003','Available','Windows 8 - 64 bit','10.44.208.216'),
 ('SYS004','Available','Windows 8 - 32 bit','10.44.203.219'),
 ('SYS005','Available','Windows 8 - 64 bit','10.44.206.212');
/*!40000 ALTER TABLE `system` ENABLE KEYS */;


--
-- Definition of table `system_software_installation`
--

DROP TABLE IF EXISTS `system_software_installation`;
CREATE TABLE `system_software_installation` (
  `system_id` varchar(10) NOT NULL,
  `software_id` varchar(10) NOT NULL,
  PRIMARY KEY (`system_id`,`software_id`),
  KEY `system_software_fk2` (`software_id`),
  CONSTRAINT `system_software_fk1` FOREIGN KEY (`system_id`) REFERENCES `system` (`system_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `system_software_fk2` FOREIGN KEY (`software_id`) REFERENCES `software` (`software_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `system_software_installation`
--

/*!40000 ALTER TABLE `system_software_installation` DISABLE KEYS */;
INSERT INTO `system_software_installation` (`system_id`,`software_id`) VALUES 
 ('SYS001','SFT001'),
 ('SYS001','SFT002'),
 ('SYS002','SFT002'),
 ('SYS002','SFT003'),
 ('SYS001','SFT004'),
 ('SYS002','SFT004'),
 ('SYS001','SFT005');
/*!40000 ALTER TABLE `system_software_installation` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
