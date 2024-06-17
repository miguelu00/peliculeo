SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

DROP DATABASE IF EXISTS bdpeliculas;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdpeliculas`
--
CREATE DATABASE IF NOT EXISTS `bdpeliculas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bdpeliculas`;
GRANT ALL PRIVILEGES ON bdpeliculas TO root;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
                                          `NIF` varchar(15) NOT NULL,
                                          `nombre` varchar(255) NOT NULL,
                                          `apellidos` varchar(255) NOT NULL,
                                          `password` varchar(255) NOT NULL,
                                          `fecha_Alta` varchar(255) NOT NULL,
                                          `provincia` varchar(255) NOT NULL,
                                          PRIMARY KEY (`NIF`),
                                          UNIQUE KEY `NIF` (`NIF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`NIF`, `nombre`, `apellidos`, `fecha_Alta`, `provincia`, `password`) VALUES
('14789321G', 'Meghan', 'Brunie', '28/01/24 22:35', 'Asturias', 'contraUser'),
('default_user', 'Marc', 'Apelldiso', '27/01/24 00:55', 'Leon', 'contraUser'),
('20555468E', 'Pepote', 'Juliana', '27/01/24 10:24', 'Leon', 'contraUser'),
('20932123F', 'Mauricio', 'Colmenero', '25/01/24 15:20', 'Leon', 'contraUser'),
('21145756M', 'Johnny', 'Moore', '28/01/24 10:39', 'Cantabria', 'contraUser'),
('23444321F', 'Somali', 'Tonacho', '26/01/24 14:52', 'Cantabria', 'contrasenio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fotoperfils`
--

DROP TABLE IF EXISTS `fotoperfils`;
CREATE TABLE IF NOT EXISTS `fotoperfils` (
                                             `NIF_cliente` varchar(15) NOT NULL,
                                             `uri_foto` varchar(255) NOT NULL DEFAULT 'src/GUI/imgs/fotoPerfil.png',
                                             FOREIGN KEY `FK_fotoperfil_cliente` (`NIF_cliente`) REFERENCES clientes(NIF)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `fotoperfils`
--

INSERT INTO `fotoperfils` (`NIF_cliente`, `uri_foto`) VALUES
    ('default_user', 'src/GUI/imgs/fotoPerfil.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peliculas`
--

DROP TABLE IF EXISTS `peliculas`;
CREATE TABLE IF NOT EXISTS `peliculas` (
                                           `ID` int(11) NOT NULL AUTO_INCREMENT,
                                           `titulo` varchar(255) NOT NULL DEFAULT 'Super Mario Bros.',
                                           `genero` varchar(255) NOT NULL,
                                           `fechaEstreno` varchar(255) NOT NULL,
                                           `anio` int(4) NOT NULL DEFAULT 1993,
                                           PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `peliculas`
--

INSERT INTO `peliculas` (`ID`, `titulo`, `genero`, `fechaEstreno`, `anio`) VALUES
    (21, 'asdf', 'Ciencia Ficción', '2024-06-15, 22:49:00', 2000);

-- --------------------------------------------------------

-- TRIGGER para que siempre al insertar una fecha sea la fecha por defecto actual en MySQL
DELIMITER //

CREATE TRIGGER before_insert_peliculas
    BEFORE INSERT ON peliculas
    FOR EACH ROW
BEGIN
    IF NEW.fechaEstreno IS NULL THEN
        SET NEW.fechaEstreno = DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s');
    END IF;
END //

DELIMITER ;

--
-- Estructura de tabla para la tabla `profile_pics`
--

DROP TABLE IF EXISTS `profile_pics`;
CREATE TABLE IF NOT EXISTS `profile_pics` (
                                              `user_NIF` varchar(15) NOT NULL,
                                              `img` blob NOT NULL,
                                              FOREIGN KEY `FK_pics_usuarios` (`user_NIF`) REFERENCES clientes(NIF)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tickets`
--

DROP TABLE IF EXISTS `tickets`;
CREATE TABLE IF NOT EXISTS `tickets` (
                                         `ID` int(11) NOT NULL AUTO_INCREMENT,
                                         `NIF_cliente` varchar(15) NOT NULL,
                                         `codPelicula` int(11) NOT NULL,
                                         `fecha_peli` varchar(255) NOT NULL,
                                         PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

--
-- Reflejar cambios de 'peliculas' en la tabla 'Tickets'
--
ALTER TABLE `tickets`
    ADD CONSTRAINT `FK_cliente_tickets` FOREIGN KEY (`NIF_cliente`) REFERENCES `clientes` (`NIF`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `FK_codPelicula_tickets` FOREIGN KEY (`codPelicula`) REFERENCES `peliculas` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;


DROP PROCEDURE IF EXISTS DeleteOldMovies;

DELIMITER //

CREATE PROCEDURE DeleteOldMovies()
BEGIN
    DELETE FROM peliculas
    WHERE STR_TO_DATE(fechaEstreno, '%Y-%m-%d %H:%i:%s') < CURDATE();
END //

DELIMITER ;

-- Insertar datos en la tabla peliculas
INSERT INTO `peliculas` (`ID`, `titulo`, `genero`, `fechaEstreno`, `anio`) VALUES
                                                               (1, 'The Matrix', STR_TO_DATE('31/03/1999 00:00', '%d/%m/%Y %H:%i'), 'Sci-Fi', 1999),
                                                               (2, 'Inception', STR_TO_DATE('16/07/2010 00:00', '%d/%m/%Y %H:%i'), 'Sci-Fi', 2010),
                                                               (3, 'Interstellar', STR_TO_DATE('07/11/2014 00:00', '%d/%m/%Y %H:%i'), 'Sci-Fi', 2014),
                                                               (4, 'The Dark Knight', STR_TO_DATE('18/07/2008 00:00', '%d/%m/%Y %H:%i'), 'Action', 2008),
                                                               (5, 'Pulp Fiction', STR_TO_DATE('14/10/1994 00:00', '%d/%m/%Y %H:%i'), 'Crime', 1994);

-- Insertar datos en la tabla usuarios
INSERT INTO `clientes` (`NIF`, `nombre`, `apellidos`, `password`, `fecha_Alta`, `provincia`) VALUES
                                                                                 ('12345678A', 'Juan', 'Pérez', 'password123', STR_TO_DATE('01/01/2020 00:00', '%d/%m/%Y %H:%i'), 'Madrid'),
                                                                                 ('87654321B', 'María', 'García', 'password456', STR_TO_DATE('15/03/2021 00:00', '%d/%m/%Y %H:%i'), 'Barcelona'),
                                                                                 ('11223344C', 'Ana', 'Martínez', 'password789', STR_TO_DATE('20/05/2019 00:00', '%d/%m/%Y %H:%i'), 'Valencia'),
                                                                                 ('44332211D', 'Carlos', 'López', 'password101', STR_TO_DATE('10/10/2022 00:00', '%d/%m/%Y %H:%i'), 'Sevilla'),
                                                                                 ('55667788E', 'Lucía', 'Rodríguez', 'password202', STR_TO_DATE('25/12/2018 00:00', '%d/%m/%Y %H:%i'), 'Bilbao');

-- Insertar datos en la tabla tickets
INSERT INTO `tickets` (`codPelicula`, `NIF_cliente`) VALUES
                                                  (1, '12345678A'),
                                                  (2, '87654321B'),
                                                  (3, '11223344C'),
                                                  (4, '44332211D'),
                                                  (5, '55667788E');


COMMIT;

-- Restore original character set and collation settings
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;