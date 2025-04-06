SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

DROP DATABASE IF EXISTS bdpeliculas;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- Database creation
CREATE DATABASE IF NOT EXISTS `bdpeliculas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE bdpeliculas;

GRANT ALL PRIVILEGES ON bdpeliculas.* TO 'root'@'localhost';

-- --------------------------------------------------------
-- Table structure for `clientes`
DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
                                          `NIF` varchar(15) NOT NULL,
                                          `nombre` varchar(255) NOT NULL,
                                          `apellidos` varchar(255) NOT NULL,
                                          `password` varchar(255) NOT NULL,
                                          `fecha_Alta` VARCHAR(255),
                                          `provincia` varchar(255) NOT NULL,
                                          PRIMARY KEY (`NIF`),
                                          UNIQUE KEY `NIF` (`NIF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- contras default: "contraUser"
-- Insert data into `clientes`
INSERT INTO `clientes` (`NIF`, `nombre`, `apellidos`, `fecha_Alta`, `provincia`, `password`) VALUES
                                                                                                 ('14789321G', 'Meghan', 'Brunie', '28/01/24 22:35', 'Asturias', '$2a$10$eT/9KW0BhHt45owr2U6ZKOO4iGQDxOmFiMi1IOr8a.YDfJxWp95Pi'),
                                                                                                 ('default_user', 'Marc', 'Apelldiso', '27/01/24 00:55', 'Leon', '$2a$10$eT/9KW0BhHt45owr2U6ZKOO4iGQDxOmFiMi1IOr8a.YDfJxWp95Pi'),
                                                                                                 ('20555468E', 'Pepote', 'Juliana', '27/01/24 10:24', 'Leon', '$2a$10$eT/9KW0BhHt45owr2U6ZKOO4iGQDxOmFiMi1IOr8a.YDfJxWp95Pi'),
                                                                                                 ('20932123F', 'Mauricio', 'Colmenero', '25/01/24 15:20', 'Leon', '$2a$10$eT/9KW0BhHt45owr2U6ZKOO4iGQDxOmFiMi1IOr8a.YDfJxWp95Pi'),
                                                                                                 ('21145756M', 'Johnny', 'Moore', '28/01/24 10:39', 'Cantabria', '$2a$10$eT/9KW0BhHt45owr2U6ZKOO4iGQDxOmFiMi1IOr8a.YDfJxWp95Pi'),
                                                                                                 ('23444321F', 'Somali', 'Tonacho', '26/01/24 14:52', 'Cantabria', '$2a$10$eT/9KW0BhHt45owr2U6ZKOO4iGQDxOmFiMi1IOr8a.YDfJxWp95Pi'),
                                                                                                 ('admin', 'Administrador', 'Pelaez', '25/01/24 10:00', 'Leon', 'contraUser');

-- --------------------------------------------------------
-- Table structure for `fotoperfils`
DROP TABLE IF EXISTS `fotoperfils`;
CREATE TABLE IF NOT EXISTS `fotoperfils` (
                                             `NIF_cliente` varchar(15) NOT NULL,
                                             `img` blob NOT NULL,
                                             FOREIGN KEY `FK_fotoperfil_cliente` (`NIF_cliente`) REFERENCES `clientes` (`NIF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for `peliculas`
DROP TABLE IF EXISTS `peliculas`;
CREATE TABLE IF NOT EXISTS `peliculas` (
                                           `ID` int(11) NOT NULL AUTO_INCREMENT,
                                           `titulo` varchar(255) NOT NULL DEFAULT 'Super Mario Bros.',
                                           `genero` varchar(255) NOT NULL,
                                           `fechaEstreno` varchar(255) NOT NULL,
                                           `anio` int(4) NOT NULL DEFAULT 1993,
                                           `urlposter` varchar(255) NOT NULL DEFAULT 'https://posters.movieposterdb.com/06_02/1993/0108255/l_87814_0108255_68813d17.jpg',
                                           PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for `posterPeliculas`
DROP TABLE IF EXISTS `posterPeliculas`;
CREATE TABLE IF NOT EXISTS `posterPeliculas` (
                                                 `codPelicula` int(11) NOT NULL,
                                                 `titulo` varchar(255) NOT NULL,
                                                 `img` blob,
                                                 CONSTRAINT `FK_codPelicula_posters` FOREIGN KEY (`codPelicula`) REFERENCES `peliculas` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Trigger for `peliculas`
DELIMITER //

CREATE TRIGGER before_insert_peliculas
    BEFORE INSERT ON peliculas
    FOR EACH ROW
BEGIN
    IF NEW.fechaEstreno IS NULL THEN
        SET NEW.fechaEstreno = NOW();
    END IF;
END //

CREATE TRIGGER CrearPoster
    AFTER INSERT ON peliculas
    FOR EACH ROW
BEGIN
    INSERT INTO posterPeliculas (codPelicula, titulo, img) VALUES (NEW.ID, NEW.titulo, null);
END //

DELIMITER ;

-- Insert data into `peliculas`
INSERT INTO `peliculas` (`ID`, `titulo`, `genero`, `fechaEstreno`, `anio`) VALUES
                                                                               (1, 'The Matrix', 'Sci-Fi', STR_TO_DATE('31/03/2025 00:00', '%d/%m/%Y %H:%i'), 1999),
                                                                               (2, 'Inception', 'Sci-Fi', STR_TO_DATE('16/07/2025 00:00', '%d/%m/%Y %H:%i'), 2010),
                                                                               (3, 'Interstellar', 'Sci-Fi', STR_TO_DATE('07/11/2025 00:00', '%d/%m/%Y %H:%i'), 2014),
                                                                               (4, 'The Dark Knight', 'Action', STR_TO_DATE('18/07/2025 00:00', '%d/%m/%Y %H:%i'), 2008),
                                                                               (5, 'Pulp Fiction', 'Crime', STR_TO_DATE('14/10/2025 00:00', '%d/%m/%Y %H:%i'), 1994);

-- --------------------------------------------------------
-- Table structure for `tickets`
DROP TABLE IF EXISTS `tickets`;
CREATE TABLE IF NOT EXISTS `tickets` (
                                         `ID` int(11) NOT NULL AUTO_INCREMENT,
                                         `NIF_cliente` varchar(15) NOT NULL,
                                         `codPelicula` int(11) NOT NULL,
                                         `fecha_peli` varchar(255) NOT NULL,
                                         PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- Foreign key constraints for `tickets`
ALTER TABLE `tickets`
    ADD CONSTRAINT `FK_cliente_tickets` FOREIGN KEY (`NIF_cliente`) REFERENCES `clientes` (`NIF`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `FK_codPelicula_tickets` FOREIGN KEY (`codPelicula`) REFERENCES `peliculas` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

-- Stored Procedure
DROP PROCEDURE IF EXISTS DeleteOldMovies;

DELIMITER //

CREATE PROCEDURE DeleteOldMovies()
BEGIN
    DELETE FROM peliculas
    WHERE STR_TO_DATE(fechaEstreno, '%Y-%m-%d %H:%i:%s') < NOW();
END //

DELIMITER ;

COMMIT;

-- Restore original character set and collation
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;