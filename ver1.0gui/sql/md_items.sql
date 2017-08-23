-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Июл 23 2017 г., 18:42
-- Версия сервера: 5.5.25
-- Версия PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `minecraft`
--

-- --------------------------------------------------------

--
-- Структура таблицы `md_items`
--

CREATE TABLE IF NOT EXISTS `md_items` (
  `item_id` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  `sub` tinyint(4) NOT NULL,
  `name` text NOT NULL,
  `info` text NOT NULL,
  `limit` int(11) NOT NULL DEFAULT '-1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `md_items`
--

INSERT INTO `md_items` (`item_id`, `count`, `cost`, `sub`, `name`, `info`, `limit`) VALUES
(1, 10, 10, 0, 'Stone', 'Some text', -1),
(2, 10, 15, 0, 'Grass', 'Some another text', 760),
(44, 15, 44, 4, 'brick slab', 'brick slab', -1),
(272, 1, 1, 0, 'stone swrd', 'SWOOOOOOOOOOARD', -1),
(322, 15, 100, 0, 'Apples', 'APPELSPELD', 10),
(295, 10, 1, 0, '??', 'asdasd', -1),
(296, 55, 45, 0, 'wheat', 'NOM NOM NOM', -1),
(306, 1, 60, 0, 'Helmet', 'Helmet, just helmet...', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
