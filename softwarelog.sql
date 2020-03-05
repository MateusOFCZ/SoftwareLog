-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 19-Nov-2019 às 12:24
-- Versão do servidor: 10.1.36-MariaDB
-- versão do PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `softwarelog`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `configuracoes`
--

CREATE TABLE `configuracoes` (
  `id` int(11) UNSIGNED NOT NULL,
  `versaorecente` varchar(5) DEFAULT '0.0.1',
  `obrigatorio` varchar(3) DEFAULT 'Não'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `configuracoes`
--

INSERT INTO `configuracoes` (`id`, `versaorecente`, `obrigatorio`) VALUES
(1, '0.0.1', 'Sim');

-- --------------------------------------------------------

--
-- Estrutura da tabela `gado`
--

CREATE TABLE `gado` (
  `id` int(11) UNSIGNED NOT NULL,
  `brincorastreado` varchar(50) NOT NULL,
  `controleinterno` varchar(50) NOT NULL,
  `nome` varchar(150) DEFAULT NULL,
  `raca` varchar(150) NOT NULL,
  `nascimento` varchar(10) NOT NULL,
  `peso` double UNSIGNED NOT NULL,
  `genero` varchar(50) NOT NULL,
  `iduser` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gado`
--

INSERT INTO `gado` (`id`, `brincorastreado`, `controleinterno`, `nome`, `raca`, `nascimento`, `peso`, `genero`, `iduser`) VALUES
(1, '230174', '657936', 'Alazão', 'Hereford', '05/03/2018', 789, 'Macho', 1),
(2, '938128', '836201', 'Happy', 'Jersey', '11/09/2018', 626, 'Fêmea', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `reports`
--

CREATE TABLE `reports` (
  `id` int(11) UNSIGNED NOT NULL,
  `iduser` int(11) DEFAULT NULL,
  `erro` varchar(500) NOT NULL,
  `solucionado` varchar(3) DEFAULT 'Não'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) UNSIGNED NOT NULL,
  `propriedade` varchar(250) NOT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(15) NOT NULL,
  `idchave` int(11) DEFAULT NULL,
  `versaoatual` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`id`, `propriedade`, `telefone`, `email`, `senha`, `idchave`, `versaoatual`) VALUES
(1, 'Fazenda Foyer', '(49)998361049', 'mateusdacruz357@gmail.com', '0123456789', NULL, '0.0.1');

-- --------------------------------------------------------

--
-- Estrutura da tabela `vacinalista`
--

CREATE TABLE `vacinalista` (
  `id` int(11) UNSIGNED NOT NULL,
  `nome` varchar(150) NOT NULL,
  `iduser` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `vacinalista`
--

INSERT INTO `vacinalista` (`id`, `nome`, `iduser`) VALUES
(1, 'Clostridioses', 1),
(2, 'Leptospirose', 1),
(3, 'Botulismo', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `vacinas`
--

CREATE TABLE `vacinas` (
  `id` int(11) UNSIGNED NOT NULL,
  `nomevacina` varchar(150) NOT NULL,
  `iduser` int(11) DEFAULT NULL,
  `idgado` int(11) DEFAULT NULL,
  `datavacinado` varchar(10) DEFAULT NULL,
  `vacinado` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `vacinas`
--

INSERT INTO `vacinas` (`id`, `nomevacina`, `iduser`, `idgado`, `datavacinado`, `vacinado`) VALUES
(5, 'Botulismo', 1, 2, '12/11/2019', 'Sim'),
(6, 'Leptospirose', 1, 1, '05/08/2019', 'Sim'),
(7, 'Clostridioses', 1, 1, '30/09/2019', 'Sim'),
(8, 'Botulismo', 1, 1, '13/11/2019', 'Sim');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `configuracoes`
--
ALTER TABLE `configuracoes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gado`
--
ALTER TABLE `gado`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `vacinalista`
--
ALTER TABLE `vacinalista`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vacinas`
--
ALTER TABLE `vacinas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `configuracoes`
--
ALTER TABLE `configuracoes`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `gado`
--
ALTER TABLE `gado`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `vacinalista`
--
ALTER TABLE `vacinalista`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `vacinas`
--
ALTER TABLE `vacinas`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
