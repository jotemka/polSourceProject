-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 25 Sty 2021, 11:21
-- Wersja serwera: 10.4.17-MariaDB
-- Wersja PHP: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `notes`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `note`
--

DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id` int(11) NOT NULL,
  `note_title` varchar(255) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `created` datetime NOT NULL,
  `modified` datetime NOT NULL,
  `thread_id` bigint(20) NOT NULL,
  `note_version` int(11) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `note`
--

INSERT INTO `note` (`id`, `note_title`, `content`, `created`, `modified`, `thread_id`, `note_version`, `is_deleted`) VALUES
(1, 'Test', 'Some content', '2021-01-24 12:19:54', '2021-01-24 12:19:54', 1, 1, 0),
(2, 'Title', 'Some content 1', '2021-01-24 12:19:54', '2021-01-24 12:24:54', 1, 2, 0),
(3, 'Other title', 'asdf', '2021-01-24 13:41:05', '2021-01-24 13:41:05', 2, 1, 0),
(4, 'Other title', 'qwerrewq', '2021-01-24 13:41:05', '2021-01-24 13:41:55', 2, 2, 1),
(5, 'Other title', 'qwerrewq fafds', '2021-01-24 13:41:05', '2021-01-24 13:42:55', 2, 3, 1),
(6, 'Something else 0', 'aalllal', '2021-01-24 17:43:17', '2021-01-24 17:43:17', 415815237663, 1, 0),
(7, 'Other title', 'qwerrewq aaaaaaaaaa', '2021-01-24 13:41:05', '2021-01-24 19:37:11', 2, 4, 0),
(8, 'Other title', 'qwerrewq xxxxxxx', '2021-01-24 13:41:05', '2021-01-24 22:50:56', 2, 5, 1),
(9, 'Something else 01', 'aalllal bbbbbbb', '2021-01-24 17:43:17', '2021-01-25 03:39:18', 415815237663, 2, 1);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `note`
--
ALTER TABLE `note`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
