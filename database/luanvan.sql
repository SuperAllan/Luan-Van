-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 14, 2015 at 05:49 PM
-- Server version: 5.6.14
-- PHP Version: 5.5.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `luanvan`
--

-- --------------------------------------------------------

--
-- Table structure for table `actor`
--

CREATE TABLE IF NOT EXISTS `actor` (
  `ACTORID` int(11) NOT NULL AUTO_INCREMENT,
  `LOAIACTORID` int(11) NOT NULL,
  `GHICHU` varchar(255) DEFAULT NULL,
  `MOTAACTOR` varchar(255) DEFAULT NULL,
  `NAMEOFACTOR` varchar(255) NOT NULL,
  `PROJECTID` int(11) NOT NULL,
  PRIMARY KEY (`ACTORID`),
  KEY `FK_ACTOR_LOAI` (`LOAIACTORID`),
  KEY `PROJECTID` (`PROJECTID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `actor`
--

INSERT INTO `actor` (`ACTORID`, `LOAIACTORID`, `GHICHU`, `MOTAACTOR`, `NAMEOFACTOR`, `PROJECTID`) VALUES
(15, 1, NULL, '', 'Actor', 1),
(16, 1, NULL, '', 'Actor', 1);

-- --------------------------------------------------------

--
-- Table structure for table `bmt`
--

CREATE TABLE IF NOT EXISTS `bmt` (
  `BMTID` int(11) NOT NULL AUTO_INCREMENT,
  `TEN` varchar(100) DEFAULT NULL,
  `TRONGSO` int(11) DEFAULT NULL,
  `HESOBMT` float DEFAULT NULL,
  `MOTABMT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`BMTID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `bmt`
--

INSERT INTO `bmt` (`BMTID`, `TEN`, `TRONGSO`, `HESOBMT`, `MOTABMT`) VALUES
(1, 'B - Đơn giản', 5, 1, NULL),
(2, 'B - Trung bình', 10, 1, NULL),
(3, 'B - Phức tạp', 15, 1, NULL),
(4, 'M - Đơn giản', 5, 1.2, NULL),
(5, 'M - Trung bình', 10, 1.2, NULL),
(6, 'M - Phức tạp', 15, 1.2, NULL),
(7, 'T - Đơn giản', 5, 1.5, NULL),
(8, 'T - Trung bình', 10, 1.5, NULL),
(9, 'T - Phức tạp', 15, 1.5, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `chucnang`
--

CREATE TABLE IF NOT EXISTS `chucnang` (
  `MAYEUCAU` int(11) NOT NULL AUTO_INCREMENT,
  `MUCDOID` int(11) NOT NULL,
  `MALOAI` int(11) NOT NULL,
  `PROJECTID` int(11) NOT NULL,
  `NHOMID` int(11) NOT NULL,
  `USECASEID` varchar(255) DEFAULT NULL,
  `MOTAYEUCAU` text,
  `GHICHU` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MAYEUCAU`),
  KEY `FK_CHUCNANG_PHANLOAI` (`MALOAI`),
  KEY `FK_MUCDO_CHUCNANG` (`MUCDOID`),
  KEY `FK_NHONCHUCNANG_CHUCNANG` (`NHOMID`),
  KEY `FK_PROJECT_YEUCAU` (`PROJECTID`),
  KEY `FK_USECASE_ChucNang` (`USECASEID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=84 ;

--
-- Dumping data for table `chucnang`
--

INSERT INTO `chucnang` (`MAYEUCAU`, `MUCDOID`, `MALOAI`, `PROJECTID`, `NHOMID`, `USECASEID`, `MOTAYEUCAU`, `GHICHU`) VALUES
(83, 1, 1, 1, 75, NULL, 'chúc', 'Ghi chú');

-- --------------------------------------------------------

--
-- Table structure for table `file`
--

CREATE TABLE IF NOT EXISTS `file` (
  `FILEID` int(11) NOT NULL AUTO_INCREMENT,
  `USECASEID` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `LINK` varchar(255) DEFAULT NULL,
  `GHICHU` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`FILEID`),
  KEY `FK_FILE_USECASE` (`USECASEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `giatriluong`
--

CREATE TABLE IF NOT EXISTS `giatriluong` (
  `PROJECTID` int(100) NOT NULL,
  `LUONGID` int(100) NOT NULL,
  `LUONGTANGTHEM` int(100) NOT NULL,
  `PCKHUVUC` int(100) NOT NULL,
  `PCLUUDONG` int(100) NOT NULL,
  PRIMARY KEY (`PROJECTID`,`LUONGID`),
  KEY `FK_GIATRI_LUONG` (`LUONGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `giatriluong`
--

INSERT INTO `giatriluong` (`PROJECTID`, `LUONGID`, `LUONGTANGTHEM`, `PCKHUVUC`, `PCLUUDONG`) VALUES
(1, 1, 0, 0, 0),
(1, 2, 0, 0, 0),
(1, 3, 0, 0, 200000),
(1, 4, 0, 0, 0),
(1, 5, 0, 0, 0),
(1, 6, 0, 0, 0),
(1, 7, 0, 0, 0),
(1, 8, 200000, 0, 0),
(2, 1, 0, 0, 200000),
(2, 2, 0, 0, 0),
(2, 3, 0, 0, 0),
(2, 4, 0, 0, 0),
(2, 5, 150000, 150000, 200000),
(2, 6, 0, 0, 0),
(2, 7, 0, 0, 0),
(2, 8, 0, 0, 0),
(3, 1, 0, 0, 0),
(3, 2, 0, 0, 0),
(3, 3, 0, 0, 0),
(3, 4, 0, 0, 0),
(3, 5, 0, 0, 0),
(3, 6, 0, 0, 0),
(3, 7, 0, 0, 0),
(3, 8, 0, 0, 0),
(4, 1, 0, 0, 0),
(4, 2, 0, 0, 0),
(4, 3, 0, 0, 0),
(4, 4, 0, 0, 0),
(4, 5, 0, 0, 0),
(4, 6, 0, 0, 0),
(4, 7, 0, 0, 0),
(4, 8, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `hesokythuat`
--

CREATE TABLE IF NOT EXISTS `hesokythuat` (
  `HESOKYTHUATID` int(11) NOT NULL AUTO_INCREMENT,
  `TENHESO` varchar(255) NOT NULL,
  `TRONGSO` int(11) NOT NULL,
  PRIMARY KEY (`HESOKYTHUATID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `hesokythuat`
--

INSERT INTO `hesokythuat` (`HESOKYTHUATID`, `TENHESO`, `TRONGSO`) VALUES
(1, 'Hệ thống phân tán (distributed system)', 2),
(2, 'Tính chất đáp ứng tức thời hoặc yêu cầu đảm bảo thông lượng (response throughput performance objectives)', 1),
(3, 'Hiệu quả sử dụng trực tuyến (end user efficiency online)', 1),
(4, 'Độ phức tạp của xử lý bên trong (Complex internal processing)', 1),
(5, 'Mã nguồn tái sử dụng (Code must be re-usable)', 1),
(6, 'Dễ cài đặt (Easy to install)', 1),
(7, 'Dễ sử dụng (Easy to use)', 1),
(8, 'Khả năng chuyển đổi (Portable)', 2),
(9, 'Khả năng dễ thay đổi (Easy to change)', 1),
(10, 'Sử dụng đồng thời (Concurrent)', 1),
(11, 'Có các tính năng bảo mật đặc biệt (Include special security features)', 1),
(12, 'Cung cấp truy cập trực tiếp với các phần mềm (Provide direct access for third parties)', 1),
(13, 'Yêu cầu phương tiện đào tạo đặc biệt cho người sử dụng (Special user training faciities are required)', 1);

-- --------------------------------------------------------

--
-- Table structure for table `hesomoitruong`
--

CREATE TABLE IF NOT EXISTS `hesomoitruong` (
  `HESOMOITRUONGID` int(11) NOT NULL AUTO_INCREMENT,
  `TRONGSO` float NOT NULL,
  `MOTAHESOMT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`HESOMOITRUONGID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `hesomoitruong`
--

INSERT INTO `hesomoitruong` (`HESOMOITRUONGID`, `TRONGSO`, `MOTAHESOMT`) VALUES
(1, 1.5, 'Có áp dụng quy trình phát triển phần mềm theo mẫu RUP và có hiểu biết về RUP (Familiar with Rational Unified Process)'),
(2, 0.5, 'Có kinh nghiệm về ứng dụng tương tự (application experiences)'),
(3, 1, 'Có kinh nghiệm  hướng đối tượng (Object-oriented experience)'),
(4, 0.5, 'Có khả năng lãnh đạo nhóm (Lead analyst capability)'),
(5, 1, 'Tính chất năng động (The dynamic)'),
(6, 2, 'Độ ổn định Yêu cầu (Stable requirements)'),
(7, -1, 'Có sử dụng nhân viên làm bán thời gian (Part-time workers)'),
(8, -1, 'Ngôn ngữ lập trình khó (Difficult programming language)');

-- --------------------------------------------------------

--
-- Table structure for table `loaiactor`
--

CREATE TABLE IF NOT EXISTS `loaiactor` (
  `LOAIACTORID` int(11) NOT NULL AUTO_INCREMENT,
  `LOAI` varchar(255) NOT NULL,
  `TRONGSO` int(11) DEFAULT NULL,
  `GHICHU` varchar(255) DEFAULT NULL,
  `MOTALOAIACTOR` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LOAIACTORID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `loaiactor`
--

INSERT INTO `loaiactor` (`LOAIACTORID`, `LOAI`, `TRONGSO`, `GHICHU`, `MOTALOAIACTOR`) VALUES
(1, 'Đơn giản', 1, NULL, 'Thuộc loại giao diện của chương trình'),
(2, 'Trung bình', 2, NULL, 'Giao diện tương tác hoặc phục vụ một giao thức họat động'),
(3, 'Phức tạp', 3, NULL, 'Giao diện đồ họa');

-- --------------------------------------------------------

--
-- Table structure for table `luong`
--

CREATE TABLE IF NOT EXISTS `luong` (
  `LUONGID` int(11) NOT NULL AUTO_INCREMENT,
  `BAC` int(11) DEFAULT NULL,
  `HESO` float DEFAULT NULL,
  PRIMARY KEY (`LUONGID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `luong`
--

INSERT INTO `luong` (`LUONGID`, `BAC`, `HESO`) VALUES
(1, 1, 2.34),
(2, 2, 2.67),
(3, 3, 3),
(4, 4, 3.33),
(5, 5, 3.66),
(6, 6, 3.99),
(7, 7, 4.32),
(8, 8, 4.65);

-- --------------------------------------------------------

--
-- Table structure for table `mucdo`
--

CREATE TABLE IF NOT EXISTS `mucdo` (
  `MUCDOID` int(11) NOT NULL AUTO_INCREMENT,
  `MOTAMUCDO` varchar(255) NOT NULL,
  PRIMARY KEY (`MUCDOID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `mucdo`
--

INSERT INTO `mucdo` (`MUCDOID`, `MOTAMUCDO`) VALUES
(1, 'Đơn giản'),
(2, 'Trung bình'),
(3, 'Phức tạp');

-- --------------------------------------------------------

--
-- Table structure for table `mucluongnhanuoc`
--

CREATE TABLE IF NOT EXISTS `mucluongnhanuoc` (
  `MUC` int(11) NOT NULL,
  PRIMARY KEY (`MUC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mucluongnhanuoc`
--

INSERT INTO `mucluongnhanuoc` (`MUC`) VALUES
(1050000);

-- --------------------------------------------------------

--
-- Table structure for table `nhomchucnang`
--

CREATE TABLE IF NOT EXISTS `nhomchucnang` (
  `NHOMID` int(11) NOT NULL AUTO_INCREMENT,
  `TENNHOM` varchar(255) NOT NULL,
  `PROJECTID` int(11) NOT NULL,
  PRIMARY KEY (`NHOMID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=76 ;

--
-- Dumping data for table `nhomchucnang`
--

INSERT INTO `nhomchucnang` (`NHOMID`, `TENNHOM`, `PROJECTID`) VALUES
(75, 'hnom 1', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhomuc`
--

CREATE TABLE IF NOT EXISTS `nhomuc` (
  `NHOMUCID` int(11) NOT NULL AUTO_INCREMENT,
  `TENNHOM` varchar(255) NOT NULL,
  PRIMARY KEY (`NHOMUCID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `phanloai`
--

CREATE TABLE IF NOT EXISTS `phanloai` (
  `ACTORID` int(11) NOT NULL AUTO_INCREMENT,
  `USECASEID` varchar(255) NOT NULL,
  `VAITRO` int(11) NOT NULL,
  PRIMARY KEY (`ACTORID`,`USECASEID`),
  KEY `FK_PHANLOAI_USECASE` (`USECASEID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `phanloai`
--

INSERT INTO `phanloai` (`ACTORID`, `USECASEID`, `VAITRO`) VALUES
(15, 'e0474f36-3dec-47f4-a6bd-b9ca9b78297a', 0),
(16, '22f6f34c-99bc-4899-9d62-06afa36c1488', 0),
(16, 'bb388d16-a49d-4db0-8b61-f0e1b0403037', 0);

-- --------------------------------------------------------

--
-- Table structure for table `phanloaichucnang`
--

CREATE TABLE IF NOT EXISTS `phanloaichucnang` (
  `MALOAI` int(11) NOT NULL AUTO_INCREMENT,
  `TENLOAI` varchar(255) NOT NULL,
  PRIMARY KEY (`MALOAI`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `phanloaichucnang`
--

INSERT INTO `phanloaichucnang` (`MALOAI`, `TENLOAI`) VALUES
(1, 'Dữ liệu đầu vào'),
(2, 'Dữ liệu đầu ra'),
(3, 'Yêu cầu truy vấn'),
(4, 'Cơ sở dữ liệu'),
(5, 'Dữ liệu tra cứu');

-- --------------------------------------------------------

--
-- Table structure for table `phichucnang`
--

CREATE TABLE IF NOT EXISTS `phichucnang` (
  `MAYEUCAU` int(11) NOT NULL AUTO_INCREMENT,
  `PROJECTID` int(11) NOT NULL,
  `MOTAYEUCAU` text,
  PRIMARY KEY (`MAYEUCAU`),
  KEY `FK_PROJECT_YEUCAU2` (`PROJECTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE IF NOT EXISTS `project` (
  `PROJECTID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) NOT NULL,
  `TRANGTHAI` int(11) NOT NULL,
  `NGAYTAO` date NOT NULL,
  `TENPROJECT` varchar(255) NOT NULL,
  `motaproject` varchar(255) DEFAULT NULL,
  `SODO` longtext,
  `luongcoban` int(100) DEFAULT NULL,
  `TRONGSONOLUC` double DEFAULT NULL,
  `BACLUONG` int(100) DEFAULT NULL,
  PRIMARY KEY (`PROJECTID`),
  KEY `FK_CREATE` (`USERNAME`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`PROJECTID`, `USERNAME`, `TRANGTHAI`, `NGAYTAO`, `TENPROJECT`, `motaproject`, `SODO`, `luongcoban`, `TRONGSONOLUC`, `BACLUONG`) VALUES
(1, 'phuong', 0, '2015-02-01', 'du an 3', 'lam cho truong dai hoc can tho 20135', '{"cells":[{"type":"uml.Actor","size":{"width":43,"height":78},"name":"Actor","role":"0_e0474f36-3dec-47f4-a6bd-b9ca9b78297a/","level":"1","description":"","shapes":{"circle":{"cx":21,"cy":15,"fill":"#fcfcfc","r":10,"stroke":"black","stroke-dasharray":"none","stroke-width":2},"line":[{"fill":"#fcfcfc","stroke":"black","stroke-dasharray":"none","stroke-width":2,"x1":21,"x2":21,"y1":25,"y2":50},{"fill":"#fcfcfc","stroke":"black","stroke-dasharray":"none","stroke-width":2,"x1":21,"x2":6,"y1":50,"y2":70},{"fill":"#fcfcfc","stroke":"black","stroke-dasharray":"none","stroke-width":2,"x1":21,"x2":36,"y1":50,"y2":70},{"fill":"#fcfcfc","stroke":"black","stroke-dasharray":"none","stroke-width":2,"x1":6,"x2":36,"y1":35,"y2":35}],"text":{"fill":"black","y":98,"text-anchor":"middle","x":23}},"position":{"x":175,"y":224},"angle":0,"id":"04b9d8eb-0ccd-4865-9f5c-37f85d4c0dcc","z":1,"attrs":{}},{"type":"uml.Usecase","size":{"width":130,"height":60},"name":"Usecase","level":"9","description":"","payMoney":true,"shapes":{"ellipse":{"stroke":"black","stroke-width":2,"rx":60,"ry":25,"fill":"white","cx":65,"cy":30},"text":{"fill":"black","y":82,"text-anchor":"middle","x":66}},"position":{"x":472,"y":201},"angle":0,"id":"e0474f36-3dec-47f4-a6bd-b9ca9b78297a","z":2,"attrs":{}},{"type":"uml.Association","shapes":{".connection":{"stroke-width":2,"stroke":"#aaa","fill":"transparent"}},"source":{"id":"04b9d8eb-0ccd-4865-9f5c-37f85d4c0dcc"},"target":{"id":"e0474f36-3dec-47f4-a6bd-b9ca9b78297a"},"id":"20824a4f-fa62-4cb4-95be-fed6ceeced5a","z":3,"embeds":"","attrs":{}},{"type":"uml.Usecase","size":{"width":130,"height":60},"name":"Usecase","level":"8","description":"saadssada","payMoney":true,"shapes":{"ellipse":{"stroke":"black","stroke-width":2,"rx":60,"ry":25,"fill":"white","cx":65,"cy":30},"text":{"fill":"black","y":82,"text-anchor":"middle","x":66}},"position":{"x":194,"y":159},"angle":0,"id":"78a3a8b0-500c-4284-9da6-a66bcb7d08cf","z":4,"attrs":{}},{"type":"uml.Actor","size":{"width":43,"height":78},"name":"Actor","role":"0_22f6f34c-99bc-4899-9d62-06afa36c1488/0_bb388d16-a49d-4db0-8b61-f0e1b0403037/","level":"1","description":"","shapes":{"circle":{"cx":21,"cy":15,"fill":"#fcfcfc","r":10,"stroke":"black","stroke-dasharray":"none","stroke-width":2},"line":[{"fill":"#fcfcfc","stroke":"black","stroke-dasharray":"none","stroke-width":2,"x1":21,"x2":21,"y1":25,"y2":50},{"fill":"#fcfcfc","stroke":"black","stroke-dasharray":"none","stroke-width":2,"x1":21,"x2":6,"y1":50,"y2":70},{"fill":"#fcfcfc","stroke":"black","stroke-dasharray":"none","stroke-width":2,"x1":21,"x2":36,"y1":50,"y2":70},{"fill":"#fcfcfc","stroke":"black","stroke-dasharray":"none","stroke-width":2,"x1":6,"x2":36,"y1":35,"y2":35}],"text":{"fill":"black","y":98,"text-anchor":"middle","x":23}},"position":{"x":745,"y":151},"angle":0,"id":"bd01b28f-f83e-4ef7-bc35-af1e881d0bbf","z":5,"attrs":{}},{"type":"uml.Usecase","size":{"width":130,"height":60},"name":"Usecase","level":"1","description":"","payMoney":true,"shapes":{"ellipse":{"stroke":"black","stroke-width":2,"rx":60,"ry":25,"fill":"white","cx":65,"cy":30},"text":{"fill":"black","y":82,"text-anchor":"middle","x":66}},"position":{"x":712,"y":389},"angle":0,"id":"22f6f34c-99bc-4899-9d62-06afa36c1488","z":6,"attrs":{}},{"type":"uml.Association","shapes":{".connection":{"stroke-width":2,"stroke":"#aaa","fill":"transparent"}},"source":{"id":"bd01b28f-f83e-4ef7-bc35-af1e881d0bbf"},"target":{"id":"22f6f34c-99bc-4899-9d62-06afa36c1488"},"id":"a8dbc355-95a1-4bd1-a317-087335a5794d","z":12,"embeds":"","attrs":{}},{"type":"uml.Usecase","size":{"width":130,"height":60},"name":"Usecase","level":"1","description":"","payMoney":true,"shapes":{"ellipse":{"stroke":"black","stroke-width":2,"rx":60,"ry":25,"fill":"white","cx":65,"cy":30},"text":{"fill":"black","y":82,"text-anchor":"middle","x":66}},"position":{"x":443,"y":94},"angle":0,"id":"bb388d16-a49d-4db0-8b61-f0e1b0403037","z":13,"attrs":{}},{"type":"uml.Association","shapes":{".connection":{"stroke-width":2,"stroke":"#aaa","fill":"transparent"}},"source":{"id":"bb388d16-a49d-4db0-8b61-f0e1b0403037"},"target":{"id":"bd01b28f-f83e-4ef7-bc35-af1e881d0bbf"},"id":"736892a1-4b20-4155-9b0b-91842b9299b6","z":15,"embeds":"","attrs":{}},{"type":"uml.Usecase","size":{"width":130,"height":60},"name":"Usecase","level":"1","description":"","payMoney":false,"shapes":{"ellipse":{"stroke":"black","stroke-width":2,"rx":60,"ry":25,"fill":"white","cx":65,"cy":30},"text":{"fill":"black","y":82,"text-anchor":"middle","x":66}},"position":{"x":467,"y":343},"angle":0,"id":"0eeaaaa4-6313-4410-a1ef-a690906a7496","z":16,"attrs":{}}]}', 1050000, 1, 24839),
(2, 'phuong', 0, '2015-02-05', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', NULL, 1050000, 0, 37389),
(3, 'phuong', 0, '2015-02-06', 'du an 1', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', NULL, 1050000, 0, 0),
(4, 'phuong', 1, '2015-02-06', 'du an 2', '123', NULL, 1050000, 0, 0),
(5, 'phuong', 0, '2015-02-12', 'du an 4', '123', NULL, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `trongsonoluc`
--

CREATE TABLE IF NOT EXISTS `trongsonoluc` (
  `trongsoid` int(11) NOT NULL AUTO_INCREMENT,
  `mota` varchar(255) DEFAULT NULL,
  `giatri` double DEFAULT NULL,
  PRIMARY KEY (`trongsoid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `trongsonoluc`
--

INSERT INTO `trongsonoluc` (`trongsoid`, `mota`, `giatri`) VALUES
(1, '6/6', 1),
(2, '7/6', 1.16666666666667),
(3, '8/6', 1.33333333333333),
(4, '9/6', 1.5),
(5, '10/6', 1.66666666666667);

-- --------------------------------------------------------

--
-- Table structure for table `usecase`
--

CREATE TABLE IF NOT EXISTS `usecase` (
  `USECASEID` varchar(255) NOT NULL,
  `NAMEOFUC` varchar(255) NOT NULL,
  `BMTID` int(11) NOT NULL,
  `NHOMUCID` int(11) DEFAULT NULL,
  `PROJECTID` int(11) NOT NULL,
  `MOTAUC` text NOT NULL,
  `CAUHOI` text,
  `TINHTIEN` int(11) NOT NULL,
  PRIMARY KEY (`USECASEID`),
  KEY `FK_PROJECT_USECASE` (`PROJECTID`),
  KEY `FK_USECASE_BMT` (`BMTID`),
  KEY `FK_USECASE_NHOMUC` (`NHOMUCID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usecase`
--

INSERT INTO `usecase` (`USECASEID`, `NAMEOFUC`, `BMTID`, `NHOMUCID`, `PROJECTID`, `MOTAUC`, `CAUHOI`, `TINHTIEN`) VALUES
('0eeaaaa4-6313-4410-a1ef-a690906a7496', 'Usecase', 1, NULL, 1, '', NULL, 0),
('22f6f34c-99bc-4899-9d62-06afa36c1488', 'Usecase', 1, NULL, 1, '', NULL, 1),
('78a3a8b0-500c-4284-9da6-a66bcb7d08cf', 'Usecase', 8, NULL, 1, 'saadssada', NULL, 1),
('bb388d16-a49d-4db0-8b61-f0e1b0403037', 'Usecase', 1, NULL, 1, '', NULL, 1),
('e0474f36-3dec-47f4-a6bd-b9ca9b78297a', 'Usecase', 9, NULL, 1, '', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `ENABLED` decimal(1,0) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PHONE` varchar(12) DEFAULT NULL,
  `ADDRESS` varchar(999) DEFAULT NULL,
  `IMAGE` longtext,
  `FULLNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`USERNAME`, `PASSWORD`, `ENABLED`, `EMAIL`, `PHONE`, `ADDRESS`, `IMAGE`, `FULLNAME`) VALUES
('phuong', '123', '1', 'phuong111327@gmail.com', '123123', '', 'data:image/gif;base64,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', 'phuong');

-- --------------------------------------------------------

--
-- Table structure for table `userrole`
--

CREATE TABLE IF NOT EXISTS `userrole` (
  `USERROLEID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) NOT NULL,
  `ROLE` varchar(45) NOT NULL,
  PRIMARY KEY (`USERROLEID`),
  KEY `FK_USER_USERROLES` (`USERNAME`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `userrole`
--

INSERT INTO `userrole` (`USERROLEID`, `USERNAME`, `ROLE`) VALUES
(1, 'phuong', 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `xephangkythuat`
--

CREATE TABLE IF NOT EXISTS `xephangkythuat` (
  `PROJECTID` int(11) NOT NULL,
  `HESOKYTHUATID` int(11) NOT NULL,
  `GIATRIXEPHANG` int(11) NOT NULL,
  `GHICHU` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PROJECTID`,`HESOKYTHUATID`),
  KEY `FK_XEPHANG_HESO` (`HESOKYTHUATID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `xephangkythuat`
--

INSERT INTO `xephangkythuat` (`PROJECTID`, `HESOKYTHUATID`, `GIATRIXEPHANG`, `GHICHU`) VALUES
(1, 1, 2, ''),
(1, 2, 2, ''),
(1, 3, 1, ''),
(1, 4, 0, ''),
(1, 5, 2, ''),
(1, 6, 1, ''),
(1, 7, 1, ''),
(1, 8, 3, ''),
(1, 9, 1, ''),
(1, 10, 1, ''),
(1, 11, 3, ''),
(1, 12, 1, ''),
(1, 13, 1, ''),
(2, 1, 2, ''),
(2, 2, 1, ''),
(2, 3, 1, ''),
(2, 4, 2, ''),
(2, 5, 2, ''),
(2, 6, 0, ''),
(2, 7, 3, ''),
(2, 8, 3, ''),
(2, 9, 2, ''),
(2, 10, 2, ''),
(2, 11, 3, ''),
(2, 12, 3, ''),
(2, 13, 5, '');

-- --------------------------------------------------------

--
-- Table structure for table `xephangmoitruong`
--

CREATE TABLE IF NOT EXISTS `xephangmoitruong` (
  `PROJECTID` int(11) NOT NULL,
  `HESOMOITRUONGID` int(11) NOT NULL,
  `GIATRIXEPHANG` int(11) DEFAULT NULL,
  `ONDINH` float DEFAULT NULL,
  PRIMARY KEY (`PROJECTID`,`HESOMOITRUONGID`),
  KEY `FK_PROJECT_DSNHOM` (`HESOMOITRUONGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `xephangmoitruong`
--

INSERT INTO `xephangmoitruong` (`PROJECTID`, `HESOMOITRUONGID`, `GIATRIXEPHANG`, `ONDINH`) VALUES
(1, 1, 2, 0.6),
(1, 2, 4, 0.1),
(1, 3, 5, 1),
(1, 4, 4, 0.1),
(1, 5, 4, 1),
(1, 6, 4, 1),
(1, 7, 3, 0),
(1, 8, 2, 0),
(2, 1, 2, 0),
(2, 2, 3, 0.3),
(2, 3, 2, 0),
(2, 4, 1, 0.3),
(2, 5, 1, 0.4),
(2, 6, 0, 0.3),
(2, 7, 3, 0.4),
(2, 8, 2, 0.3);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `actor`
--
ALTER TABLE `actor`
  ADD CONSTRAINT `FK_ACTOR_LOAI` FOREIGN KEY (`LOAIACTORID`) REFERENCES `loaiactor` (`LOAIACTORID`),
  ADD CONSTRAINT `FK_ACTOR_Project` FOREIGN KEY (`PROJECTID`) REFERENCES `project` (`PROJECTID`);

--
-- Constraints for table `chucnang`
--
ALTER TABLE `chucnang`
  ADD CONSTRAINT `FK_CHUCNANG_PHANLOAI` FOREIGN KEY (`MALOAI`) REFERENCES `phanloaichucnang` (`MALOAI`),
  ADD CONSTRAINT `FK_MUCDO_CHUCNANG` FOREIGN KEY (`MUCDOID`) REFERENCES `mucdo` (`MUCDOID`),
  ADD CONSTRAINT `FK_NHONCHUCNANG_CHUCNANG` FOREIGN KEY (`NHOMID`) REFERENCES `nhomchucnang` (`NHOMID`),
  ADD CONSTRAINT `FK_PROJECT_YEUCAU` FOREIGN KEY (`PROJECTID`) REFERENCES `project` (`PROJECTID`),
  ADD CONSTRAINT `FK_USECASE_ChucNang` FOREIGN KEY (`USECASEID`) REFERENCES `usecase` (`USECASEID`);

--
-- Constraints for table `file`
--
ALTER TABLE `file`
  ADD CONSTRAINT `FK_FILE_USECASE` FOREIGN KEY (`USECASEID`) REFERENCES `usecase` (`USECASEID`);

--
-- Constraints for table `giatriluong`
--
ALTER TABLE `giatriluong`
  ADD CONSTRAINT `FK_GIATRI_LUONG` FOREIGN KEY (`LUONGID`) REFERENCES `luong` (`LUONGID`),
  ADD CONSTRAINT `FK_GIATRI_PROJECT` FOREIGN KEY (`PROJECTID`) REFERENCES `project` (`PROJECTID`);

--
-- Constraints for table `phanloai`
--
ALTER TABLE `phanloai`
  ADD CONSTRAINT `FK_ACTOR_PHANLOAI` FOREIGN KEY (`ACTORID`) REFERENCES `actor` (`ACTORID`),
  ADD CONSTRAINT `FK_PHANLOAI_USECASE` FOREIGN KEY (`USECASEID`) REFERENCES `usecase` (`USECASEID`);

--
-- Constraints for table `phichucnang`
--
ALTER TABLE `phichucnang`
  ADD CONSTRAINT `FK_PROJECT_YEUCAU2` FOREIGN KEY (`PROJECTID`) REFERENCES `project` (`PROJECTID`);

--
-- Constraints for table `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `FK_CREATE` FOREIGN KEY (`USERNAME`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `usecase`
--
ALTER TABLE `usecase`
  ADD CONSTRAINT `FK_PROJECT_USECASE` FOREIGN KEY (`PROJECTID`) REFERENCES `project` (`PROJECTID`),
  ADD CONSTRAINT `FK_USECASE_BMT` FOREIGN KEY (`BMTID`) REFERENCES `bmt` (`BMTID`),
  ADD CONSTRAINT `FK_USECASE_NHOMUC` FOREIGN KEY (`NHOMUCID`) REFERENCES `nhomuc` (`NHOMUCID`);

--
-- Constraints for table `userrole`
--
ALTER TABLE `userrole`
  ADD CONSTRAINT `FK_USER_USERROLES` FOREIGN KEY (`USERNAME`) REFERENCES `user` (`USERNAME`);

--
-- Constraints for table `xephangkythuat`
--
ALTER TABLE `xephangkythuat`
  ADD CONSTRAINT `FK_PROJECT_XEPHANG` FOREIGN KEY (`PROJECTID`) REFERENCES `project` (`PROJECTID`),
  ADD CONSTRAINT `FK_XEPHANG_HESO` FOREIGN KEY (`HESOKYTHUATID`) REFERENCES `hesokythuat` (`HESOKYTHUATID`);

--
-- Constraints for table `xephangmoitruong`
--
ALTER TABLE `xephangmoitruong`
  ADD CONSTRAINT `FK_PROJECT_DSNHOM` FOREIGN KEY (`HESOMOITRUONGID`) REFERENCES `hesomoitruong` (`HESOMOITRUONGID`),
  ADD CONSTRAINT `FK_PROJECT_NHOM` FOREIGN KEY (`PROJECTID`) REFERENCES `project` (`PROJECTID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
