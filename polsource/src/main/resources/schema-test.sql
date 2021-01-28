
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `note_title` varchar(255) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `created` datetime NOT NULL,
  `modified` datetime NOT NULL,
  `thread_id` bigint(20) NOT NULL,
  `note_version` int(11) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (id)
);


--INSERT INTO note (`id`, `note_title`, `content`, `created`, `modified`, `thread_id`, `note_version`, `is_deleted`) VALUES
--(1, 'Test', 'Some content', '2021-01-24 12:19:54', '2021-01-24 12:19:54', 1, 1, 0),
--(2, 'Title', 'Some content 1', '2021-01-24 12:19:54', '2021-01-24 12:24:54', 1, 2, 0),
--(3, 'Other title', 'asdf', '2021-01-24 13:41:05', '2021-01-24 13:41:05', 2, 1, 0),
--(4, 'Other title', 'qwerrewq', '2021-01-24 13:41:05', '2021-01-24 13:41:55', 2, 2, 1),
--(5, 'Other title', 'qwerrewq fafds', '2021-01-24 13:41:05', '2021-01-24 13:42:55', 2, 3, 1),
--(6, 'Something else 0', 'aalllal', '2021-01-24 17:43:17', '2021-01-24 17:43:17', 415815237663, 1, 0),
--(7, 'Other title', 'qwerrewq aaaaaaaaaa', '2021-01-24 13:41:05', '2021-01-24 19:37:11', 2, 4, 0),
--(8, 'Other title', 'qwerrewq xxxxxxx', '2021-01-24 13:41:05', '2021-01-24 22:50:56', 2, 5, 1),
--(9, 'Something else 01', 'aalllal bbbbbbb', '2021-01-24 17:43:17', '2021-01-25 03:39:18', 415815237663, 2, 1);

COMMIT;