-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 13 Février 2013 à 03:28
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `comtrek_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `comment` varchar(30) NOT NULL,
  `event_id` int(11) NOT NULL,
  `participant_id` int(11) NOT NULL,
  `trek_id` int(11) NOT NULL,
  `writing_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK38A5EE5F2AFAACD1` (`event_id`),
  KEY `FK38A5EE5F207E1CF1` (`participant_id`),
  KEY `FK38A5EE5F56229F03` (`trek_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE IF NOT EXISTS `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `average_note` int(11) NOT NULL,
  `effective_time` datetime NOT NULL,
  `trek_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK5C6729A8CC159A3` (`user_id`),
  KEY `FK5C6729A56229F03` (`trek_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `event`
--

INSERT INTO `event` (`id`, `version`, `average_note`, `effective_time`, `trek_id`, `user_id`) VALUES
(4, 0, 1, '1913-01-01 00:00:00', 4, 8);

-- --------------------------------------------------------

--
-- Structure de la table `participant`
--

CREATE TABLE IF NOT EXISTS `participant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `events_id` int(11) NOT NULL,
  `note` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2DBDEF331C5439F2` (`events_id`),
  KEY `FK2DBDEF338CC159A3` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `participant`
--

INSERT INTO `participant` (`id`, `version`, `events_id`, `note`, `user_id`) VALUES
(6, 1, 4, 8, 8);

-- --------------------------------------------------------

--
-- Structure de la table `treck`
--

CREATE TABLE IF NOT EXISTS `treck` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `attitude_combined` int(11) NOT NULL,
  `average_note` int(11) NOT NULL,
  `average_time` int(11) NOT NULL,
  `distance` float NOT NULL,
  `level` int(11) NOT NULL,
  `max_altitude` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `weather_link` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4D5102F8CC159A3` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Contenu de la table `treck`
--

INSERT INTO `treck` (`id`, `version`, `attitude_combined`, `average_note`, `average_time`, `distance`, `level`, `max_altitude`, `name`, `user_id`, `weather_link`) VALUES
(4, 1, 452, 2, 200, 20, 3, 0, 'trek', 8, '');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `birthDate` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `login` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `version`, `birthDate`, `email`, `firstName`, `gender`, `lastName`, `login`, `password`) VALUES
(8, 0, '2002-02-03 00:00:00', 'toto@toto.to', 'steven', 'Male', 'bernard', 'toto@toto.to', 'toto@toto.to'),
(13, 1, '2013-01-08 00:00:00', '', 'steven', 'Male', 'bernard', 'toto2@toto.to', 'toto@toto.to');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK38A5EE5F207E1CF1` FOREIGN KEY (`participant_id`) REFERENCES `participant` (`id`),
  ADD CONSTRAINT `FK38A5EE5F2AFAACD1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  ADD CONSTRAINT `FK38A5EE5F56229F03` FOREIGN KEY (`trek_id`) REFERENCES `treck` (`id`);

--
-- Contraintes pour la table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `FK5C6729A56229F03` FOREIGN KEY (`trek_id`) REFERENCES `treck` (`id`),
  ADD CONSTRAINT `FK5C6729A8CC159A3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `participant`
--
ALTER TABLE `participant`
  ADD CONSTRAINT `FK2DBDEF331C5439F2` FOREIGN KEY (`events_id`) REFERENCES `event` (`id`),
  ADD CONSTRAINT `FK2DBDEF338CC159A3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `treck`
--
ALTER TABLE `treck`
  ADD CONSTRAINT `FK4D5102F8CC159A3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
