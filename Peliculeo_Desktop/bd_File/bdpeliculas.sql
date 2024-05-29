-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3307
-- Tiempo de generación: 27-01-2024 a las 13:25:13
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdpeliculas`
--
CREATE DATABASE IF NOT EXISTS `bdpeliculas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bdpeliculas`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `NIF` varchar(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `fecha_Alta` varchar(255) NOT NULL,
  `provincia` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncar tablas antes de insertar `clientes`
--

TRUNCATE TABLE `clientes`;
--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`NIF`, `nombre`, `apellidos`, `fecha_Alta`, `provincia`) VALUES
('20554687A', 'pepeo', 'Apelldiso', '27/01/24 00:55', 'León'),
('20555468E', 'Pepote', 'Juliano', '27/01/24 10:24', 'León'),
('20888763W', 'Pepeo', 'Original Og', '26/01/24 18:42', 'Cantabria'),
('20932123F', 'dsfas', 'asdf', '25/01/24 15:20', 'León'),
('23444321F', 'asdfsd', 'asdfds', '26/01/24 14:52', 'Cantabria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fotoperfils`
--

DROP TABLE IF EXISTS `fotoperfils`;
CREATE TABLE `fotoperfils` (
  `NIF_cliente` varchar(100) NOT NULL,
  `uri_foto` varchar(255) NOT NULL DEFAULT 'src/GUI/imgs/fotoPerfil.png'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncar tablas antes de insertar `fotoperfils`
--

TRUNCATE TABLE `fotoperfils`;
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
CREATE TABLE `peliculas` (
  `codPelicula` int(11) NOT NULL,
  `titulo` varchar(255) NOT NULL DEFAULT 'Super Mario Bros.',
  `genero` varchar(255) NOT NULL,
  `fechaEstreno` varchar(255) NOT NULL DEFAULT current_timestamp(),
  `anio` int(4) NOT NULL DEFAULT 1993
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncar tablas antes de insertar `peliculas`
--

TRUNCATE TABLE `peliculas`;
--
-- Volcado de datos para la tabla `peliculas`
--

INSERT INTO `peliculas` (`codPelicula`, `titulo`, `genero`, `fechaEstreno`, `anio`) VALUES
(1, 'Super Mario Bros.', 'Aventura', '27/1/24 23:30', 1993),
(2, 'Retorno a Seoul', 'Drama', '30/1/24 10:30', 2022),
(13, 'Geranios', 'Acción', '30/1/24 0:51', 2015),
(16, 'Jumanji2', 'Drama', '30/1/24 11:45', 2000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tickets`
--

DROP TABLE IF EXISTS `tickets`;
CREATE TABLE `tickets` (
  `ID_ticket` int(11) NOT NULL,
  `NIF_cliente` varchar(11) NOT NULL,
  `codPelicula` int(11) NOT NULL,
  `fecha_peli` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncar tablas antes de insertar `tickets`
--

TRUNCATE TABLE `tickets`;
--
-- Volcado de datos para la tabla `tickets`
--

INSERT INTO `tickets` (`ID_ticket`, `NIF_cliente`, `codPelicula`, `fecha_peli`) VALUES
(5, '20932123F', 2, '30/1/24 10:30'),
(6, '20888763W', 2, '30/1/24 10:30'),
(8, '20888763W', 1, '27/1/24 23:30'),
(10, '20888763W', 1, '27/1/24 23:30');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`NIF`),
  ADD UNIQUE KEY `NIF` (`NIF`);

--
-- Indices de la tabla `fotoperfils`
--
ALTER TABLE `fotoperfils`
  ADD PRIMARY KEY (`NIF_cliente`);

--
-- Indices de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  ADD PRIMARY KEY (`codPelicula`);

--
-- Indices de la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`ID_ticket`),
  ADD KEY `FK_codPelicula_tickets` (`codPelicula`),
  ADD KEY `FK_cliente_tickets` (`NIF_cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  MODIFY `codPelicula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `tickets`
--
ALTER TABLE `tickets`
  MODIFY `ID_ticket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `FK_cliente_tickets` FOREIGN KEY (`NIF_cliente`) REFERENCES `clientes` (`NIF`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_codPelicula_tickets` FOREIGN KEY (`codPelicula`) REFERENCES `peliculas` (`codPelicula`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
