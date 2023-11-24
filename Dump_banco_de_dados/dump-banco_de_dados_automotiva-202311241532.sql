-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: banco_de_dados_automotiva
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agendamento`
--

DROP TABLE IF EXISTS `agendamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agendamento` (
  `id` int NOT NULL,
  `dataHora` datetime NOT NULL,
  `idFuncionario` int NOT NULL,
  `idVeiculo` int NOT NULL,
  `idCliente` int NOT NULL,
  `Lembrete` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idFuncionario` (`idFuncionario`),
  KEY `idVeiculo` (`idVeiculo`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `agendamento_ibfk_1` FOREIGN KEY (`idFuncionario`) REFERENCES `funcionario` (`id`),
  CONSTRAINT `agendamento_ibfk_2` FOREIGN KEY (`idVeiculo`) REFERENCES `veiculo` (`id`),
  CONSTRAINT `agendamento_ibfk_3` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agendamento`
--

LOCK TABLES `agendamento` WRITE;
/*!40000 ALTER TABLE `agendamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `agendamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cidade` (
  `Id` int NOT NULL,
  `nome` varchar(40) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
INSERT INTO `cidade` VALUES (1,'Campo Mourão'),(2,'Campo Largo');
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` int NOT NULL,
  `idPessoa` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idPessoa` (`idPessoa`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`idPessoa`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `id` int NOT NULL,
  `nome` varchar(40) NOT NULL,
  `CNPJ` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'CJ Autopeças','45. 248. 785/0001-10'),(2,'Ferro velho Laudo','32.314.432/0001-12');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `endereco` (
  `id` int NOT NULL,
  `idCid` int NOT NULL,
  `rua` varchar(45) NOT NULL,
  `CEP` varchar(18) NOT NULL,
  `num_resid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idCid` (`idCid`),
  CONSTRAINT `endereco_FK` FOREIGN KEY (`idCid`) REFERENCES `cidade` (`Id`),
  CONSTRAINT `endereco_ibfk_1` FOREIGN KEY (`idCid`) REFERENCES `cidade` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,2,'Rua Miguel Giani','87.309-125',172),(2,1,'Rua São Miguel Josafat','26.423-432',312);
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcao`
--

DROP TABLE IF EXISTS `funcao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcao` (
  `id` int NOT NULL,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcao`
--

LOCK TABLES `funcao` WRITE;
/*!40000 ALTER TABLE `funcao` DISABLE KEYS */;
INSERT INTO `funcao` VALUES (1,'Mecânico'),(2,'Administrador(a)');
/*!40000 ALTER TABLE `funcao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionario` (
  `id` int NOT NULL,
  `idFuncao` int NOT NULL,
  `idPessoa` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idFuncao` (`idFuncao`),
  KEY `idPessoa` (`idPessoa`),
  CONSTRAINT `funcionario_ibfk_1` FOREIGN KEY (`idFuncao`) REFERENCES `funcao` (`id`),
  CONSTRAINT `funcionario_ibfk_2` FOREIGN KEY (`idPessoa`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (1,1,1),(2,2,2);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_servico`
--

DROP TABLE IF EXISTS `grupo_servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo_servico` (
  `id` int NOT NULL,
  `nome` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_servico`
--

LOCK TABLES `grupo_servico` WRITE;
/*!40000 ALTER TABLE `grupo_servico` DISABLE KEYS */;
INSERT INTO `grupo_servico` VALUES (1,'Óleo de motor');
/*!40000 ALTER TABLE `grupo_servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca`
--

DROP TABLE IF EXISTS `marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marca` (
  `id` int NOT NULL,
  `nome` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca`
--

LOCK TABLES `marca` WRITE;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
INSERT INTO `marca` VALUES (1,'Fiat'),(2,'Chevrolet');
/*!40000 ALTER TABLE `marca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orcamento`
--

DROP TABLE IF EXISTS `orcamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orcamento` (
  `id` int NOT NULL,
  `idOD` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idOD` (`idOD`),
  CONSTRAINT `orcamento_ibfk_1` FOREIGN KEY (`idOD`) REFERENCES `ordem_de_servico` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orcamento`
--

LOCK TABLES `orcamento` WRITE;
/*!40000 ALTER TABLE `orcamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `orcamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordem_de_servico`
--

DROP TABLE IF EXISTS `ordem_de_servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordem_de_servico` (
  `id` int NOT NULL,
  `idEmpresa` int NOT NULL,
  `idVeiculo` int NOT NULL,
  `idCliente` int NOT NULL,
  `idFuncionario` int NOT NULL,
  `observacao` varchar(500) DEFAULT NULL,
  `idGservico` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idEmpresa` (`idEmpresa`),
  KEY `idVeiculo` (`idVeiculo`),
  KEY `idCliente` (`idCliente`),
  KEY `idFuncionario` (`idFuncionario`),
  KEY `ordem_de_servico_FK` (`idGservico`),
  CONSTRAINT `ordem_de_servico_FK` FOREIGN KEY (`idGservico`) REFERENCES `grupo_servico` (`id`),
  CONSTRAINT `ordem_de_servico_ibfk_1` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`id`),
  CONSTRAINT `ordem_de_servico_ibfk_2` FOREIGN KEY (`idVeiculo`) REFERENCES `veiculo` (`id`),
  CONSTRAINT `ordem_de_servico_ibfk_3` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `ordem_de_servico_ibfk_5` FOREIGN KEY (`idFuncionario`) REFERENCES `funcionario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordem_de_servico`
--

LOCK TABLES `ordem_de_servico` WRITE;
/*!40000 ALTER TABLE `ordem_de_servico` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordem_de_servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `id` int NOT NULL,
  `idEndereco` int NOT NULL,
  `nome` varchar(20) NOT NULL,
  `sobrenome` varchar(20) NOT NULL,
  `genero` varchar(15) NOT NULL,
  `telefone` varchar(25) NOT NULL,
  `email` varchar(70) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idEndereco` (`idEndereco`),
  CONSTRAINT `pessoa_ibfk_1` FOREIGN KEY (`idEndereco`) REFERENCES `endereco` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,1,'Alan','Brito','Masc','99425-5475','alaan@gmail.com'),(2,2,'Beatriz','Moreira','Fem','(44)99745-5451','Beaaa@gmail.com');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtosservicos`
--

DROP TABLE IF EXISTS `produtosservicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produtosservicos` (
  `id` int NOT NULL,
  `idGrupo` int NOT NULL,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(500) NOT NULL,
  `preco` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idGrupo` (`idGrupo`),
  CONSTRAINT `produtosservicos_ibfk_1` FOREIGN KEY (`idGrupo`) REFERENCES `grupo_servico` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtosservicos`
--

LOCK TABLES `produtosservicos` WRITE;
/*!40000 ALTER TABLE `produtosservicos` DISABLE KEYS */;
INSERT INTO `produtosservicos` VALUES (1,1,'Oleo Sl 20w50 500ml - Lubrax','O kit 4 Óleo 20w50 Motor Lubrax Mineral é um conjunto de quatro embalagens de óleo lubrificante mineral, cada uma com a capacidade de 1 litro. Esse óleo é especialmente desenvolvido para motores a gasolina, etanol e GNV, e possui a viscosidade SAE 20W50, o que garante uma excelente proteção contra o desgaste e a oxidação do motor. Além disso, o óleo Lubrax Mineral é capaz de manter a limpeza interna do motor, prolongando a vida útil do mesmo.',32.14),(2,1,'Troca de óleo',' Troca de óleo de carro.',100.56);
/*!40000 ALTER TABLE `produtosservicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veiculo`
--

DROP TABLE IF EXISTS `veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `veiculo` (
  `id` int NOT NULL,
  `idmarca` int NOT NULL,
  `nome` varchar(45) NOT NULL,
  `placa` varchar(15) NOT NULL,
  `ano` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idMarca` (`idmarca`),
  CONSTRAINT `veiculo_ibfk_1` FOREIGN KEY (`idmarca`) REFERENCES `marca` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veiculo`
--

LOCK TABLES `veiculo` WRITE;
/*!40000 ALTER TABLE `veiculo` DISABLE KEYS */;
INSERT INTO `veiculo` VALUES (1,1,'Uno ','DSA-1Q31',2015),(2,2,'Onix 1.0 MT','FAD-3A12',2019);
/*!40000 ALTER TABLE `veiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'banco_de_dados_automotiva'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-24 15:32:50
