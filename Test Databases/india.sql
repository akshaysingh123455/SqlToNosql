-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 11, 2015 at 02:14 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `india`
--
CREATE DATABASE IF NOT EXISTS `india` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `india`;

-- --------------------------------------------------------

--
-- Table structure for table `articles`
--

CREATE TABLE IF NOT EXISTS `articles` (
  `article_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(300) NOT NULL,
  `link` varchar(500) NOT NULL,
  `content` text NOT NULL,
  `thumbnail` varchar(200) NOT NULL,
  `big_image` varchar(500) NOT NULL,
  `parent_website_id` bigint(20) NOT NULL,
  `add_date` datetime NOT NULL,
  `num_views` int(11) NOT NULL DEFAULT '0',
  `num_ups` int(11) NOT NULL DEFAULT '0',
  `num_downs` int(11) NOT NULL DEFAULT '0',
  `num_saved` int(11) NOT NULL DEFAULT '0',
  `num_comments` int(11) NOT NULL DEFAULT '0',
  `one_page_article` tinyint(1) NOT NULL,
  `primary_link` bigint(20) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`article_id`),
  UNIQUE KEY `link` (`link`),
  KEY `parent_website_id` (`parent_website_id`),
  KEY `primary_link` (`primary_link`),
  KEY `tag_id` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `article_folder_link`
--

CREATE TABLE IF NOT EXISTS `article_folder_link` (
  `article_id` bigint(20) NOT NULL,
  `folder_id` bigint(20) NOT NULL,
  `description` varchar(1000) NOT NULL,
  KEY `article_id` (`article_id`,`folder_id`),
  KEY `folder_id` (`folder_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `article_tags`
--

CREATE TABLE IF NOT EXISTS `article_tags` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `num_followers` int(11) NOT NULL,
  PRIMARY KEY (`tag_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `category_main_index`
--

CREATE TABLE IF NOT EXISTS `category_main_index` (
  `category_main_index_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_index_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`category_main_index_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `category_main_index`
--

INSERT INTO `category_main_index` (`category_main_index_id`, `category_index_name`) VALUES
(1, 'Personal Blog'),
(2, 'forum'),
(3, 'Govt. websites'),
(4, 'Service providers'),
(5, 'News'),
(6, 'Search engine'),
(7, 'Private Firm Websites'),
(8, 'E. commerce websites'),
(9, 'Ad. websites'),
(10, 'Databases/ datasets'),
(11, 'Tutorial/ how to do '),
(12, 'fun'),
(13, 'Other'),
(14, 'Social Networks'),
(15, 'Content Sharing');

-- --------------------------------------------------------

--
-- Table structure for table `folder_details`
--

CREATE TABLE IF NOT EXISTS `folder_details` (
  `folder_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `folder_name` varchar(30) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `parent_folder_id` bigint(20) NOT NULL,
  `num_articles` int(11) NOT NULL DEFAULT '0',
  `num_followers` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`folder_id`),
  KEY `owner_id` (`owner_id`,`parent_folder_id`),
  KEY `parent_folder_id` (`parent_folder_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `folder_followers`
--

CREATE TABLE IF NOT EXISTS `folder_followers` (
  `folder_id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `folder_id` (`folder_id`,`user_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `languages`
--

CREATE TABLE IF NOT EXISTS `languages` (
  `name` varchar(40) NOT NULL DEFAULT '',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `languages`
--

INSERT INTO `languages` (`name`) VALUES
('Assamese'),
('bengali'),
('bodo'),
('dogri'),
('english'),
('gujrati'),
('hindi'),
('kannada'),
('kashmiri'),
('konkani'),
('malyalam'),
('manipuri'),
('marathi'),
('nepali'),
('oriya'),
('punjabi'),
('sanskrit'),
('santali'),
('sindhi'),
('tamil'),
('telugu'),
('urdu');

-- --------------------------------------------------------

--
-- Table structure for table `languages_index`
--

CREATE TABLE IF NOT EXISTS `languages_index` (
  `website_id` bigint(20) NOT NULL,
  `language` varchar(40) NOT NULL,
  KEY `website_id` (`website_id`,`language`),
  KEY `language` (`language`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `social_links_index`
--

CREATE TABLE IF NOT EXISTS `social_links_index` (
  `site_id` bigint(20) NOT NULL,
  `social_link_type_id` int(11) NOT NULL,
  `link` varchar(400) NOT NULL,
  UNIQUE KEY `link` (`link`),
  KEY `site_id` (`site_id`,`social_link_type_id`),
  KEY `social_link_type_id` (`social_link_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `social_link_type`
--

CREATE TABLE IF NOT EXISTS `social_link_type` (
  `social_link_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `link_name` varchar(40) NOT NULL,
  `link` varchar(100) NOT NULL,
  PRIMARY KEY (`social_link_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `subcategory_index`
--

CREATE TABLE IF NOT EXISTS `subcategory_index` (
  `subcategory_index_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `subcategory_name` varchar(30) NOT NULL,
  PRIMARY KEY (`subcategory_index_id`),
  KEY `category_id` (`category_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `subcategory_index`
--

INSERT INTO `subcategory_index` (`subcategory_index_id`, `category_id`, `subcategory_name`) VALUES
(1, 1, 'test');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(240) NOT NULL,
  `username` varchar(20) NOT NULL,
  `email` varchar(240) NOT NULL,
  `password` varchar(240) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ip` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `website_index`
--

CREATE TABLE IF NOT EXISTS `website_index` (
  `site_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `title` varchar(200) NOT NULL,
  `link` varchar(200) DEFAULT NULL,
  `description` varchar(200) NOT NULL DEFAULT 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis',
  `site_logo` varchar(300) NOT NULL,
  `category_main_index_id` int(11) NOT NULL,
  `subcategory_index_id` int(11) NOT NULL,
  `add_date` datetime NOT NULL,
  `num_ups` int(11) NOT NULL,
  `num_downs` int(11) NOT NULL,
  `views` bigint(20) NOT NULL,
  `no_articles_registered` int(11) NOT NULL,
  `made_in_india` smallint(6) NOT NULL,
  `num_followers` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`site_id`),
  KEY `category_main_index_id` (`category_main_index_id`,`subcategory_index_id`),
  KEY `subcategory_index_id` (`subcategory_index_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=61 ;

--
-- Dumping data for table `website_index`
--

INSERT INTO `website_index` (`site_id`, `name`, `title`, `link`, `description`, `site_logo`, `category_main_index_id`, `subcategory_index_id`, `add_date`, `num_ups`, `num_downs`, `views`, `no_articles_registered`, `made_in_india`, `num_followers`) VALUES
(1, 'No Match', 'No Match', 'Blank', '', 'abc.png', 1, 1, '0000-00-00 00:00:00', 0, 0, 0, 0, 0, 0),
(16, 'Digital Inspiration', '', 'http://www.labnol.org', '', 'abc.png', 1, 1, '2015-05-16 12:52:56', 0, 0, 0, 9, 0, 0),
(18, 'National Protal Of India', '', 'http://india.gov.in', '', 'abc.png', 1, 1, '2015-05-17 17:46:46', 0, 0, 0, 0, 0, 0),
(20, 'Android Hive', '', 'http://www.androidhive.info', '', 'abc.png', 1, 1, '2015-05-19 19:19:09', 0, 0, 0, 0, 0, 0),
(21, 'Scoop', '', 'http://www.scoopwhoop.com', '', 'abc.png', 1, 1, '2015-05-24 10:14:30', 0, 0, 0, 2, 0, 0),
(28, 'ministry of corporate affairs', '', 'http://www.mca.gov.in', '', 'abc.png', 1, 1, '2015-05-27 22:25:42', 0, 0, 0, 0, 0, 0),
(29, 'press information bureau', '', 'http://pib.nic.in', '', 'abc.png', 1, 1, '2015-05-27 22:33:38', 0, 0, 0, 0, 0, 0),
(30, 'Indian Institute of Technology Ropar', '', 'http://www.iitrpr.ac.in', '', 'abc.png', 1, 1, '2015-05-27 22:36:21', 0, 0, 0, 0, 0, 0),
(31, ' Ministry of Drinking Water and Sanitation', '', 'http://sbm.gov.in', '', 'abc.png', 1, 1, '2015-05-27 22:36:56', 0, 0, 0, 0, 0, 0),
(32, 'CENTRAL BOARD OF SECONDARY EDUCATION', '', 'http://cbse.nic.in', '', 'abc.png', 1, 1, '2015-05-27 22:38:21', 0, 0, 0, 0, 0, 0),
(33, 'national information center Himachal pradesh', '', 'http://admis.hp.nic.in', '', 'abc.png', 1, 1, '2015-05-27 22:38:51', 0, 0, 0, 0, 0, 0),
(34, 'Bansal Classes', '', 'http://bansal.ac.in', '', 'abc.png', 1, 1, '2015-05-27 22:40:13', 0, 0, 0, 0, 0, 0),
(35, 'Income Tax Department', '', 'http://www.incometaxindia.gov.in', '', 'abc.png', 1, 1, '2015-05-27 22:41:00', 0, 0, 0, 0, 0, 0),
(36, 'Enactus India', '', 'http://www.enactusindia.org', '', 'abc.png', 1, 1, '2015-05-27 22:42:04', 0, 0, 0, 0, 0, 0),
(37, 'Enactus IIT Ropar', '', 'http://enactusiitropar.blogspot.in', '', 'abc.png', 1, 1, '2015-05-27 22:42:51', 0, 0, 0, 0, 0, 0),
(38, 'SantaBanta', '', 'http://www.santabanta.com', '', 'abc.png', 1, 1, '2015-05-27 22:49:18', 0, 0, 0, 0, 0, 0),
(39, 'Funtoosh.com', '', 'http://www.funtoosh.com', '', 'abc.png', 1, 1, '2015-05-27 22:49:48', 0, 0, 0, 0, 0, 0),
(40, 'comparebuyhatke.com', '', 'www.comparebuyhatke.com', '', 'abc.png', 1, 1, '2015-05-27 23:13:40', 0, 0, 0, 0, 0, 0),
(41, 'facebook', '', 'www.facebook.com', '', 'abc.png', 1, 1, '2015-05-27 23:14:11', 0, 0, 0, 0, 1, 0),
(42, 'Blog', '', 'sajeedmhb.blogspot.com', '', 'abc.png', 1, 1, '2015-05-27 23:14:47', 0, 0, 0, 0, 0, 0),
(43, 'youtube', '', 'www.youtube.com', '', 'abc.png', 1, 1, '2015-05-27 23:15:12', 0, 0, 0, 0, 1, 0),
(44, 'flipkart.com', '', 'www.flipkart.com', '', 'abc.png', 1, 1, '2015-05-27 23:15:30', 0, 0, 0, 0, 0, 0),
(45, 'Life and Lies of Ishan', '', 'http://ishanjajoo.blogspot.in', '', 'abc.png', 1, 1, '2015-05-27 23:28:03', 0, 0, 0, 0, 0, 0),
(46, 'Info Tuts', '', 'http://www.infotuts.com', '', 'abc.png', 1, 1, '2015-05-31 23:21:27', 0, 0, 0, 0, 0, 0),
(48, 'Love in my life', '', 'http://aaryan111.blogspot.in', '', 'abc.png', 1, 1, '2015-06-06 23:21:08', 0, 0, 0, 0, 0, 0),
(49, 'Desi Martini', '', 'http://www.desimartini.com', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis', 'abc.png', 1, 1, '2015-06-10 17:35:24', 0, 0, 0, 0, 0, 0),
(50, 'HolidayIQ', '', 'http://www.holidayiq.com', '', 'abc.png', 1, 1, '2015-06-14 22:46:35', 0, 0, 0, 0, 0, 0),
(51, 'Geeks for Geeks', '', 'http://geeksforgeeks.org', '', 'abc.png', 1, 1, '2015-06-15 06:26:21', 0, 0, 0, 0, 0, 0),
(52, 'W3 Schools', '', 'http://www.w3schools.com', 'Fast and easy tutorials website for learning web technologies', 'abc.png', 1, 1, '2015-06-16 19:54:38', 0, 0, 0, 0, 1, 0),
(53, 'Tutorials Point', '', 'http://www.tutorialspoint.com', 'Website to learn programming in simpler and understandable syntax', 'abc.png', 1, 1, '2015-06-16 20:01:56', 0, 0, 0, 0, 1, 0),
(55, 'Meritnation', '', 'http://www.meritnation.com', 'The No.1 Education Site with Study Material & Live Classes for CBSE, ICSE & more', 'abc.png', 1, 1, '2015-06-16 22:26:02', 0, 0, 0, 0, 0, 0),
(58, 'Scroll In', '', 'http://scroll.in', 'Scroll.in is an independent news, information, and entertainment venture. They bring into sharp focus the most important political and cultural stories that are shaping contemporary India', 'abc.png', 1, 1, '2015-06-25 19:14:17', 0, 0, 0, 0, 0, 0),
(59, 'PopXo', '', 'http://www.popxo.com', 'Popxo is a content platform for Indian women and girls. It is about relationships and friendships, the clothes you wear, the brands you love, and all your health and beauty questions.', 'abc.png', 1, 1, '2015-07-04 21:55:53', 0, 0, 0, 0, 0, 0),
(60, 'Youth Connect', '', 'http://www.youthconnect.in', 'Youth Connect is India’s media house for the youth. It is run by a group of passionate and enthusiastic college students based in India. ', 'abc.png', 1, 1, '2015-07-06 12:53:49', 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `website_index_followers`
--

CREATE TABLE IF NOT EXISTS `website_index_followers` (
  `website_index_followers_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `website_id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`website_index_followers_id`),
  KEY `website_id` (`website_id`,`user_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `articles`
--
ALTER TABLE `articles`
  ADD CONSTRAINT `articles_ibfk_1` FOREIGN KEY (`parent_website_id`) REFERENCES `website_index` (`site_id`),
  ADD CONSTRAINT `articles_ibfk_2` FOREIGN KEY (`primary_link`) REFERENCES `articles` (`article_id`),
  ADD CONSTRAINT `articles_ibfk_3` FOREIGN KEY (`tag_id`) REFERENCES `article_tags` (`tag_id`);

--
-- Constraints for table `article_folder_link`
--
ALTER TABLE `article_folder_link`
  ADD CONSTRAINT `article_folder_link_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `articles` (`article_id`),
  ADD CONSTRAINT `article_folder_link_ibfk_2` FOREIGN KEY (`folder_id`) REFERENCES `folder_details` (`folder_id`);

--
-- Constraints for table `folder_details`
--
ALTER TABLE `folder_details`
  ADD CONSTRAINT `folder_details_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `folder_details_ibfk_2` FOREIGN KEY (`parent_folder_id`) REFERENCES `folder_details` (`folder_id`);

--
-- Constraints for table `folder_followers`
--
ALTER TABLE `folder_followers`
  ADD CONSTRAINT `folder_followers_ibfk_1` FOREIGN KEY (`folder_id`) REFERENCES `folder_details` (`folder_id`),
  ADD CONSTRAINT `folder_followers_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `languages_index`
--
ALTER TABLE `languages_index`
  ADD CONSTRAINT `languages_index_ibfk_1` FOREIGN KEY (`website_id`) REFERENCES `website_index` (`site_id`),
  ADD CONSTRAINT `languages_index_ibfk_2` FOREIGN KEY (`language`) REFERENCES `languages` (`name`);

--
-- Constraints for table `social_links_index`
--
ALTER TABLE `social_links_index`
  ADD CONSTRAINT `social_links_index_ibfk_1` FOREIGN KEY (`site_id`) REFERENCES `website_index` (`site_id`),
  ADD CONSTRAINT `social_links_index_ibfk_2` FOREIGN KEY (`social_link_type_id`) REFERENCES `social_link_type` (`social_link_type_id`);

--
-- Constraints for table `subcategory_index`
--
ALTER TABLE `subcategory_index`
  ADD CONSTRAINT `subcategory_index_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category_main_index` (`category_main_index_id`);

--
-- Constraints for table `website_index`
--
ALTER TABLE `website_index`
  ADD CONSTRAINT `website_index_ibfk_1` FOREIGN KEY (`category_main_index_id`) REFERENCES `category_main_index` (`category_main_index_id`),
  ADD CONSTRAINT `website_index_ibfk_2` FOREIGN KEY (`subcategory_index_id`) REFERENCES `subcategory_index` (`subcategory_index_id`);

--
-- Constraints for table `website_index_followers`
--
ALTER TABLE `website_index_followers`
  ADD CONSTRAINT `website_index_followers_ibfk_1` FOREIGN KEY (`website_id`) REFERENCES `website_index` (`site_id`),
  ADD CONSTRAINT `website_index_followers_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
