delimiter $$

CREATE DATABASE `proyecto` /*!40100 DEFAULT CHARACTER SET utf8 */$$

CREATE TABLE `sessions` (
  `SESSION_ID` varchar(125) NOT NULL COMMENT 'Identificador de la sesión',
  `USER_ID` varchar(45) DEFAULT NULL COMMENT 'Usuario asociado a la sesión en caso de saberlo',
  `SESSION_BEGINS` datetime DEFAULT NULL COMMENT 'Momento en el que la sesión se crea',
  `SESSION_ENDS` datetime DEFAULT NULL COMMENT 'Momento en el que se abandona la sesion\\n',
  `USER_AGENT` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`SESSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabla en la que se guardan las sesiones que se han registrado en la aplicación'$$

CREATE TABLE `configuration` (
  `CONF_KEY` int(11) NOT NULL,
  `CONF_DESCRIPTION` varchar(45) DEFAULT NULL,
  `CONF_VALUE` varchar(45) NOT NULL,
  PRIMARY KEY (`CONF_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

CREATE TABLE `users` (
  `USER_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(45) NOT NULL,
  `USER_EMAIL` varchar(100) NOT NULL,
  `USER_PWD` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_EMAIL_UNIQUE` (`USER_EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8$$