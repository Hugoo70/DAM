-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: practicafinal
-- ------------------------------------------------------
-- Server version	8.0.40
Create database practicafinal;
use practicafinal;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cantidadproducto`
--

DROP TABLE IF EXISTS `cantidadproducto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cantidadproducto` (
  `Ticket_idTicket` int unsigned NOT NULL,
  `Producto_idProducto` int unsigned NOT NULL,
  `Cantidad` int DEFAULT NULL,
  PRIMARY KEY (`Ticket_idTicket`,`Producto_idProducto`),
  KEY `fk_Ticket_has_Producto_Producto1_idx` (`Producto_idProducto`),
  KEY `fk_Ticket_has_Producto_Ticket1_idx` (`Ticket_idTicket`),
  CONSTRAINT `fk_Ticket_has_Producto_Producto1` FOREIGN KEY (`Producto_idProducto`) REFERENCES `producto` (`idProducto`),
  CONSTRAINT `fk_Ticket_has_Producto_Ticket1` FOREIGN KEY (`Ticket_idTicket`) REFERENCES `ticket` (`idTicket`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cantidadproducto`
--

LOCK TABLES `cantidadproducto` WRITE;
/*!40000 ALTER TABLE `cantidadproducto` DISABLE KEYS */;
/*!40000 ALTER TABLE `cantidadproducto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `numerodecliente` int unsigned NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `Direccion` varchar(150) DEFAULT NULL,
  `Usuario_Alias` varchar(10) NOT NULL,
  PRIMARY KEY (`numerodecliente`),
  KEY `fk_Cliente_Usuario_idx` (`Usuario_Alias`),
  CONSTRAINT `fk_Cliente_Usuario` FOREIGN KEY (`Usuario_Alias`) REFERENCES `usuario` (`Alias`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Carlos Pérez','Av. Siempre Viva 123','usuario1'),(2,'Ana Gómez','Calle Falsa 456','usuario2'),(3,'Pedro Martínez','Paseo de la Reforma 789','usuario3'),(4,'Lucía Sánchez','Calle Mayor 101','usuario4'),(5,'Juan López','Calle 2, Colonia Centro','usuario5'),(6,'María Rodríguez','Av. Insurgentes 345','usuario6'),(7,'José Fernández','Calle El Sol 567','usuario7'),(8,'Laura García','Calle del Árbol 890','usuario8'),(9,'David Ruiz','Av. Libertador 234','usuario9'),(10,'Marta Díaz','Calle del Río 678','usuario10'),(11,'Jorge González','Calle de los Pinos 123','usuario11'),(12,'Isabel Martín','Avenida de la Luna 456','usuario12'),(13,'Antonio Hernández','Calle San José 789','usuario13'),(14,'Paula Pérez','Calle del Sol 101','usuario14'),(15,'Carlos Rodríguez','Paseo de la Reforma 234','usuario15'),(16,'Sofía López','Calle Nueva 567','usuario16'),(17,'Fernando González','Av. del Mar 890','usuario17'),(18,'Eva Fernández','Calle Alta 345','usuario18'),(19,'Ricardo Martínez','Calle Baja 678','usuario19'),(20,'Elena Sánchez','Av. Central 101','usuario20'),(21,'Pedro López','Calle Frontera 234','usuario21'),(22,'Raquel García','Calle de la Paz 567','usuario22'),(23,'Luis Fernández','Calle Marítima 890','usuario23'),(24,'Inés Rodríguez','Avenida del Sol 123','usuario24'),(25,'Óscar Martínez','Calle del Prado 456','usuario25'),(26,'Marcos Sánchez','Calle del Valle 789','usuario26'),(27,'Lola Pérez','Calle de los Álamos 101','usuario27'),(28,'Mario Hernández','Calle 15, Colonia Norte','usuario28'),(29,'Carmen García','Av. San Pedro 345','usuario29'),(30,'Miguel Fernández','Calle Mar 567','usuario30'),(31,'Patricia López','Calle de la Luna 890','usuario31'),(32,'José Sánchez','Paseo de la Montaña 234','usuario32'),(33,'Victoria Martínez','Calle Bajas 567','usuario33'),(34,'Antonio Ruiz','Av. del Norte 890','usuario34'),(35,'Alejandra Díaz','Calle Sol 345','usuario35'),(36,'Jaime González','Avenida Sol y Luna 678','usuario36'),(37,'Tania Rodríguez','Calle de la Playa 123','usuario37'),(38,'Santiago Hernández','Calle Alta 789','usuario38'),(39,'Lucía Martínez','Av. del Río 101','usuario39'),(40,'Raúl González','Calle del Mar 234','usuario40'),(41,'Sara López','Paseo Central 567','usuario41'),(42,'Javier Pérez','Av. San José 890','usuario42'),(43,'Lorena Sánchez','Calle del Sol 234','usuario43'),(44,'Esteban Ruiz','Calle de los Álamos 567','usuario44'),(45,'Alba Martínez','Avenida Hidalgo 890','usuario45'),(46,'Diego Fernández','Calle de los Pinos 345','usuario46'),(47,'Miriam Rodríguez','Calle 6, Colonia Centro','usuario47'),(48,'Manuel González','Av. Juan XXIII 678','usuario48'),(49,'Carlos Sánchez','Calle del Río 123','usuario49'),(50,'Alicia Pérez','Calle Reforma 456','usuario50'),(51,'Cristina Ruiz','Av. México 789','usuario51'),(52,'Adrián Hernández','Calle Sol y Mar 101','usuario52'),(53,'Laura González','Calle de la Estrella 234','usuario53'),(54,'Felipe Díaz','Calle Veracruz 567','usuario54'),(55,'Esther López','Avenida Los Angeles 890','usuario55'),(56,'Luis Hernández','Calle Mayor 345','usuario56'),(57,'Sandra Martínez','Paseo de la Costa 678','usuario57'),(58,'Iván Sánchez','Calle Luna 101','usuario58'),(59,'Patricia González','Calle del Lago 234','usuario59'),(60,'Raúl Pérez','Avenida Cuauhtémoc 567','usuario60'),(61,'María González','Calle Cero 890','usuario61'),(62,'Cristóbal Rodríguez','Av. Revolución 123','usuario62'),(63,'José Luis Ruiz','Calle de las Lomas 456','usuario63'),(64,'Alicia Martínez','Calle Olivo 789','usuario64'),(65,'Isabel Pérez','Paseo Central 101','usuario65'),(66,'Miguel Ángel González','Calle del Sol 234','usuario66'),(67,'Adrián Pérez','Avenida del Sur 567','usuario67'),(68,'Carolina Rodríguez','Calle Noche 890','usuario68'),(69,'Vicente Hernández','Calle Baja 123','usuario69'),(70,'Alfonso Sánchez','Calle Nueva 456','usuario70'),(71,'Beatriz Pérez','Av. 5 de Febrero 789','usuario71'),(72,'Simón Díaz','Calle California 101','usuario72'),(73,'Felipe Sánchez','Avenida Colón 234','usuario73'),(74,'Leticia González','Calle Cerezo 567','usuario74'),(75,'José Antonio Ruiz','Paseo Norte 890','usuario75'),(76,'Marina González','Calle Salto 234','usuario76'),(77,'Juan Carlos Rodríguez','Av. Hidalgo 567','usuario77'),(78,'Patricia Ruiz','Calle de los Árboles 890','usuario78'),(79,'Juan Martínez','Calle Oriente 123','usuario79'),(80,'Silvia González','Avenida de la Paz 456','usuario80'),(81,'Claudia Pérez','Calle 7, Colonia Sur 789','usuario81'),(82,'Francisco Sánchez','Calle Bonita 101','usuario82'),(83,'Marta González','Av. Santa Fe 234','usuario83'),(84,'José Francisco Díaz','Calle de la Estrella 567','usuario84'),(85,'Raquel Pérez','Calle del Sol 890','usuario85'),(86,'Luz González','Av. Pinos 123','usuario86'),(87,'Bernardo Martínez','Calle Cumbres 456','usuario87'),(88,'Gloria Sánchez','Paseo de los Lagos 789','usuario88'),(89,'Ernesto Pérez','Calle San Martín 101','usuario89'),(90,'Ana Ruiz','Calle 8, Colonia Centro 234','usuario90'),(91,'Carlos Díaz','Av. de los Heroes 567','usuario91'),(92,'Mercedes González','Calle del Pueblo 890','usuario92'),(93,'Martín Pérez','Calle Francisco 123','usuario93'),(94,'Ángel Rodríguez','Av. 4 Sur 456','usuario94'),(95,'Carlos Ruiz','Calle Villa 789','usuario95'),(96,'Margarita González','Calle Real 101','usuario96'),(97,'Antonio Pérez','Paseo del Sol 234','usuario97'),(98,'Luisa Martínez','Calle del Valle 567','usuario98'),(99,'Eduardo Sánchez','Av. 15 de Mayo 890','usuario99'),(100,'Cristina Rodríguez','Calle de la Montaña 123','usuario100'),(101,'Juliana Pérez','Calle 3, Colonia Oeste 456','usuario101'),(102,'Tomás González','Av. del Centro 789','usuario102'),(103,'Lydia Hernández','Calle Esperanza 101','usuario103'),(104,'Laura Sánchez','Calle Valle 234','usuario104'),(105,'David González','Calle Perla 567','usuario105'),(106,'Felicia Pérez','Av. del Sur 890','usuario106'),(107,'José Sánchez','Calle Colón 123','usuario107'),(108,'Eva Martínez','Calle Juan Pablo 456','usuario108'),(109,'Luis Pérez','Paseo los Ángeles 789','usuario109'),(110,'Estela González','Av. Sinaloa 101','usuario110'),(111,'Ricardo Sánchez','Calle 16, Colonia Este 234','usuario111'),(112,'Alfredo Martínez','Calle Pradera 567','usuario112'),(113,'Mónica Pérez','Av. Aragón 890','usuario113'),(114,'Antonio Díaz','Calle Lirio 123','usuario114'),(115,'Felipe Ruiz','Paseo de los Juncos 456','usuario115'),(116,'José Rodríguez','Calle San Pedro 789','usuario116'),(117,'Pedro González','Calle Bella 101','usuario117'),(118,'Ana Sánchez','Av. Santa Teresa 234','usuario118'),(119,'Raúl Pérez','Calle Los Olivos 567','usuario119'),(120,'María López','Calle Tercera 890','usuario120'),(121,'Teresa Rodríguez','Av. Cañada 123','usuario121'),(122,'Gabriela Pérez','Calle San Juan 456','usuario122'),(123,'Oscar Sánchez','Calle Río Grande 789','usuario123'),(124,'Raúl González','Calle Blanca 101','usuario124'),(125,'Gabriela Ruiz','Av. Zacatecas 234','usuario125'),(126,'Tomás Pérez','Calle 10, Colonia Este 567','usuario126'),(127,'Lidia González','Paseo de las Palmas 890','usuario127'),(128,'José Pérez','Calle 4, Colonia Norte 123','usuario128'),(129,'Carlos Martínez','Av. del Sol 456','usuario129'),(130,'Marta Sánchez','Calle Viaducto 789','usuario130'),(131,'Lucía González','Calle Monarca 101','usuario131'),(132,'Fernando Pérez','Av. Xalapa 234','usuario132'),(133,'Clara González','Calle Santa Ana 567','usuario133'),(134,'Adrián Pérez','Av. Norte 890','usuario134'),(135,'Felipe Rodríguez','Calle San Rafael 123','usuario135'),(136,'Esteban González','Calle Alta 456','usuario136'),(137,'Marina Pérez','Paseo de la Reforma 789','usuario137'),(138,'Cristina Sánchez','Calle 2, Colonia Central 101','usuario138'),(139,'Javier Pérez','Av. los Pinos 234','usuario139'),(140,'Isabel Rodríguez','Calle del Sol 567','usuario140'),(141,'José González','Av. Nuevo León 890','usuario141'),(142,'Carolina Pérez','Calle Barranca 123','usuario142'),(143,'Joaquín González','Av. Siete de Junio 456','usuario143'),(144,'Francisco Pérez','Calle Morera 789','usuario144'),(145,'Margarita Sánchez','Calle 2, Colonia Alta 101','usuario145'),(146,'Juan Pérez','Paseo de los Girasoles 234','usuario146'),(147,'Ana González','Calle de la Ciudad 567','usuario147'),(148,'Marcos Pérez','Av. Santa Fe 890','usuario148'),(149,'Antonio Sánchez','Calle de la Raza 123','usuario149'),(150,'Silvia Pérez','Calle del Sol 456','usuario150');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `codigoEmpleado` int unsigned NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `fechaIngreso` date NOT NULL,
  `puesto` set('Dependiente','Encargado','Cajero','Pistolero') NOT NULL,
  `Usuario_Alias` varchar(10) NOT NULL,
  PRIMARY KEY (`codigoEmpleado`),
  KEY `fk_Empleado_Usuario1_idx` (`Usuario_Alias`),
  CONSTRAINT `fk_Empleado_Usuario1` FOREIGN KEY (`Usuario_Alias`) REFERENCES `usuario` (`Alias`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,'Carlos Peña','2024-01-10','Dependiente','empleado1'),(2,'Ana Ruiz','2024-02-15','Cajero','empleado2'),(3,'Pedro Martínez','2024-03-20','Encargado','empleado3'),(4,'Lucía Gómez','2024-04-01','Dependiente','empleado4'),(5,'Juan López','2024-05-25','Cajero','empleado5'),(6,'María Sánchez','2024-06-10','Dependiente','empleado6'),(7,'José Fernández','2024-07-05','Pistolero','empleado7'),(8,'Laura García','2024-08-22','Encargado','empleado8'),(9,'David Ruiz','2024-09-12','Dependiente','empleado9'),(10,'Marta Díaz','2024-10-01','Cajero','empleado10'),(11,'Jorge González','2024-11-15','Encargado','empleado11'),(12,'Isabel Martín','2024-12-10','Dependiente','empleado12'),(13,'Antonio Hernández','2025-01-25','Cajero','empleado13'),(14,'Paula Pérez','2025-02-20','Dependiente','empleado14'),(15,'Carlos Rodríguez','2025-03-15','Pistolero','empleado15'),(16,'Sofía López','2025-04-30','Cajero','empleado16'),(17,'Fernando González','2025-05-10','Encargado','empleado17'),(18,'Eva Fernández','2025-06-25','Dependiente','empleado18'),(19,'Ricardo Martínez','2025-07-22','Cajero','empleado19'),(20,'Elena Sánchez','2025-08-30','Pistolero','empleado20'),(21,'Pedro López','2025-09-10','Dependiente','empleado21'),(22,'Raquel García','2025-10-05','Encargado','empleado22'),(23,'Luis Fernández','2025-11-12','Cajero','empleado23'),(24,'Inés Rodríguez','2025-12-15','Dependiente','empleado24'),(25,'Óscar Martínez','2026-01-10','Encargado','empleado25'),(26,'Marcos Sánchez','2026-02-22','Pistolero','empleado26'),(27,'Lola Pérez','2026-03-17','Cajero','empleado27'),(28,'Mario Hernández','2026-04-05','Dependiente','empleado28'),(29,'Carmen García','2026-05-25','Encargado','empleado29'),(30,'Miguel Fernández','2026-06-10','Dependiente','empleado30'),(31,'Patricia López','2026-07-01','Cajero','empleado31'),(32,'José Sánchez','2026-08-15','Encargado','empleado32'),(33,'Victoria Martínez','2026-09-30','Pistolero','empleado33'),(34,'Antonio Ruiz','2026-10-05','Dependiente','empleado34'),(35,'Alejandra Díaz','2026-11-22','Encargado','empleado35'),(36,'Jaime González','2026-12-01','Cajero','empleado36'),(37,'Tania Rodríguez','2027-01-10','Dependiente','empleado37'),(38,'Santiago Hernández','2027-02-25','Pistolero','empleado38'),(39,'Lucía Martínez','2027-03-15','Cajero','empleado39'),(40,'Raúl González','2027-04-01','Dependiente','empleado40'),(41,'Sara López','2027-05-20','Encargado','empleado41'),(42,'Javier Pérez','2027-06-25','Cajero','empleado42'),(43,'Cristina Sánchez','2027-07-15','Dependiente','empleado43'),(44,'Mónica Ruiz','2027-08-10','Encargado','empleado44'),(45,'Fernando González','2027-09-30','Pistolero','empleado45'),(46,'Eva Díaz','2027-10-25','Cajero','empleado46'),(47,'Tomás García','2027-11-12','Dependiente','empleado47'),(48,'Álvaro Hernández','2027-12-01','Encargado','empleado48'),(49,'Julia Pérez','2028-01-15','Cajero','empleado49'),(50,'Sonia López','2028-02-10','Dependiente','empleado50');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `idProducto` int unsigned NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `PrecioUnitario` float unsigned NOT NULL,
  `Stock` int unsigned NOT NULL,
  PRIMARY KEY (`idProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Coca-Cola',1.5,100),(2,'Pepsi',1.4,200),(3,'Fanta',1.6,150),(4,'Sprite',1.5,250),(5,'Red Bull',2.5,50),(6,'Nestlé Pureza',0.9,300),(7,'Agua Evian',2,100),(8,'Cerveza Corona',1.2,120),(9,'Cerveza Heineken',1.5,80),(10,'Vodka Absolut',25,30),(11,'Whisky Johnnie Walker',40,20),(12,'Tequila Don Julio',50,25),(13,'Ron Bacardi',18,60),(14,'Gatorade',1.8,180),(15,'Lipton Té Helado',1.3,200),(16,'Monster Energy',2,100),(17,'Hershey\'s Chocolate',2.5,250),(18,'Snickers',1,300),(19,'M&M\'s',1.2,200),(20,'KitKat',1.1,400),(21,'Twix',1.5,150),(22,'Chicles Orbit',0.5,500),(23,'Galletas Oreo',2,180),(24,'Pringles',3,90),(25,'Doritos',2.5,150),(26,'Cheetos',1.8,200),(27,'Lay\'s',1.5,250),(28,'Ruffles',1.6,200),(29,'Papas Fritas Sabritas',1.2,300),(30,'Salsas Heinz',3.5,100),(31,'Ketchup Heinz',2,150),(32,'Mayonesa Hellmann\'s',2.5,120),(33,'Mostaza French\'s',1.8,180),(34,'Aceite de Oliva Bertolli',5,90),(35,'Aceite de Canola',3.5,150),(36,'Vinagre de Manzana',2,200),(37,'Sal de Mar',1,400),(38,'Arroz Basmati',4,80),(39,'Arroz Blanco',2.5,300),(40,'Pasta Barilla',3.5,100),(41,'Macarrones',3,150),(42,'Spaghetti De Cecco',3.2,120),(43,'Harina de Trigo',1.5,250),(44,'Pan Bimbo',2,200),(45,'Pan de Molde Wonder',2.5,180),(46,'Leche Larga Vida',1.8,300),(47,'Leche Entera La La',1.5,250),(48,'Yogurt Griego',2,150),(49,'Mantequilla Lurpak',4,100),(50,'Margarina Flora',2.5,200),(51,'Queso Philadelphia',3.5,80),(52,'Queso Manchego',5,120),(53,'Queso Cheddar',3.2,150),(54,'Queso Mozzarella',3.5,130),(55,'Huevos San Juan',1.8,300),(56,'Pechuga de Pollo',6.5,50),(57,'Pechuga de Pavo',5,80),(58,'Salchichón',4.5,100),(59,'Chorizo Ibérico',6,70),(60,'Bacon',3.5,150),(61,'Filete de Res',8,60),(62,'Filete de Salmón',10,40),(63,'Atún en Lata',2,200),(64,'Sardinas en Lata',1.8,250),(65,'Caviar',30,20),(66,'Pistaches',3,200),(67,'Almendras',4,150),(68,'Nueces',3.5,180),(69,'Muesli',2.5,220),(70,'Cereal Kellogg\'s Cornflakes',3,250),(71,'Cereal Cheerios',3.2,180),(72,'Cereal Special K',3.5,150),(73,'Galletas Digestive',2.2,300),(74,'Galletas María',1.5,250),(75,'Galletas Ricas',1.8,200),(76,'Galletas de Chocolate',2.5,150),(77,'Manteca de Cerdo',3,100),(78,'Aceite de Coco',4.5,80),(79,'Vinagre Balsámico',3.5,100),(80,'Salsa de Soja Kikkoman',3,150),(81,'Pimientos de Padrón',5,50),(82,'Ajo',1,400),(83,'Cebolla',1.2,300),(84,'Tomate',2,250),(85,'Pepino',1.8,200),(86,'Lechuga',1.5,350),(87,'Perejil',0.8,450),(88,'Espárragos',3,120),(89,'Espinaca',2.5,150),(90,'Berenjena',2.2,180),(91,'Zanahorias',1,400),(92,'Judías Verdes',2.2,100),(93,'Brócoli',2.5,180),(94,'Coliflor',2,160),(95,'Champiñones',3,200),(96,'Patatas',1.8,300),(97,'Chirimoya',4,40),(98,'Fresas',3.5,120),(99,'Plátanos',2,180),(100,'Manzanas',2.5,200),(101,'Peras',2.2,150),(102,'Uvas',3,100),(103,'Kiwis',3,130),(104,'Melocotones',3.2,100),(105,'Cerezas',4.5,80),(106,'Sandía',5,60),(107,'Melón',4,70),(108,'Piña',3.5,90),(109,'Aguacates',3,200),(110,'Limones',1.5,300),(111,'Mandarinas',2,250),(112,'Papaya',4,50),(113,'Granada',3.5,120),(114,'Frambuesas',5,60),(115,'Mango',3.2,150),(116,'Naranjas',1.8,300),(117,'Coco',4,80),(118,'Frutas Congeladas',5,70),(119,'Té Verde',3,200),(120,'Café Illy',5,100),(121,'Café Nescafé',2.5,300),(122,'Chocolate Caliente Hershey\'s',2,250),(123,'Galletas Saladas',1.5,400),(124,'Pan Integral',2,150),(125,'Pan de Ajo',2.5,200),(126,'Pan de Molde',1.8,300),(127,'Pan de Pizza',3.5,100),(128,'Pasta de Pizza',1.8,150),(129,'Salsa Barbacoa',2,200),(130,'Salsa de Tomate',1.5,300),(131,'Salsa de Espaguetis',2,150),(132,'Pesto',3.5,120),(133,'Queso Ricotta',4,90),(134,'Queso Parmesano',5,80),(135,'Queso Suizo',4.5,100),(136,'Queso Brie',6,50),(137,'Leche Condensada',3,150),(138,'Leche Evaporada',2.5,200),(139,'Crema Agria',2,180),(140,'Crema de Leche',2.5,120),(141,'Manteca de Cerdo',2.2,150),(142,'Crema de Coco',3.5,100),(143,'Fideos Instantáneos',0.5,300),(144,'Puré de Papas',2,150),(145,'Sopa en Lata',1.5,200),(146,'Sopa de Pollo',2,150),(147,'Sopa de Verduras',1.8,250),(148,'Sopa de Tomate',1.5,180),(149,'Sopa de Lentejas',2.5,100),(150,'Minestrone',3,120),(151,'Lentejas Secas',2,180),(152,'Arroz Integral',3,120),(153,'Quinoa',5,80),(154,'Garbanzos',2.5,200),(155,'Frijoles Negros',1.8,300),(156,'Frijoles Blancos',2,150),(157,'Alubias Rojas',2.5,100),(158,'Maíz en Lata',2,200),(159,'Pechuga de Pavo',6,80);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `idTicket` int unsigned NOT NULL,
  `PrecioTotal` double unsigned DEFAULT NULL,
  `Empleado_codigoEmpleado` int unsigned NOT NULL,
  `Cliente_numerodecliente` int unsigned NOT NULL,
  PRIMARY KEY (`idTicket`),
  KEY `fk_Ticket_Empleado1_idx` (`Empleado_codigoEmpleado`),
  KEY `fk_Ticket_Cliente1_idx` (`Cliente_numerodecliente`),
  CONSTRAINT `fk_Ticket_Cliente1` FOREIGN KEY (`Cliente_numerodecliente`) REFERENCES `cliente` (`numerodecliente`),
  CONSTRAINT `fk_Ticket_Empleado1` FOREIGN KEY (`Empleado_codigoEmpleado`) REFERENCES `empleado` (`codigoEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `Alias` varchar(10) NOT NULL,
  `Clave` varchar(15) NOT NULL,
  `rol` enum('Admin','Empleado','Cliente') NOT NULL,
  `puntos` int unsigned DEFAULT NULL,
  PRIMARY KEY (`Alias`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('empleado1','claveempleado1','Empleado',1000),('empleado10','claveempleado10','Empleado',5500),('empleado11','claveempleado11','Empleado',6000),('empleado12','claveempleado12','Empleado',6500),('empleado13','claveempleado13','Empleado',7000),('empleado14','claveempleado14','Empleado',7500),('empleado15','claveempleado15','Empleado',8000),('empleado16','claveempleado16','Empleado',8500),('empleado17','claveempleado17','Empleado',9000),('empleado18','claveempleado18','Empleado',9500),('empleado19','claveempleado19','Empleado',10000),('empleado2','claveempleado2','Empleado',1500),('empleado20','claveempleado20','Empleado',10500),('empleado21','claveempleado21','Empleado',11000),('empleado22','claveempleado22','Empleado',11500),('empleado23','claveempleado23','Empleado',12000),('empleado24','claveempleado24','Empleado',12500),('empleado25','claveempleado25','Empleado',13000),('empleado26','claveempleado26','Empleado',13500),('empleado27','claveempleado27','Empleado',14000),('empleado28','claveempleado28','Empleado',14500),('empleado29','claveempleado29','Empleado',15000),('empleado3','claveempleado3','Empleado',2000),('empleado30','claveempleado30','Empleado',15500),('empleado31','claveempleado31','Empleado',16000),('empleado32','claveempleado32','Empleado',16500),('empleado33','claveempleado33','Empleado',17000),('empleado34','claveempleado34','Empleado',17500),('empleado35','claveempleado35','Empleado',18000),('empleado36','claveempleado36','Empleado',18500),('empleado37','claveempleado37','Empleado',19000),('empleado38','claveempleado38','Empleado',19500),('empleado39','claveempleado39','Empleado',20000),('empleado4','claveempleado4','Empleado',2500),('empleado40','claveempleado40','Empleado',20500),('empleado41','claveempleado41','Empleado',21000),('empleado42','claveempleado42','Empleado',21500),('empleado43','claveempleado43','Empleado',22000),('empleado44','claveempleado44','Empleado',22500),('empleado45','claveempleado45','Empleado',23000),('empleado46','claveempleado46','Empleado',23500),('empleado47','claveempleado47','Empleado',24000),('empleado48','claveempleado48','Empleado',24500),('empleado49','claveempleado49','Empleado',25000),('empleado5','claveempleado5','Empleado',3000),('empleado50','claveempleado50','Empleado',25500),('empleado6','claveempleado6','Empleado',3500),('empleado7','claveempleado7','Empleado',4000),('empleado8','claveempleado8','Empleado',4500),('empleado9','claveempleado9','Empleado',5000),('usuario1','clave12345','Admin',100),('usuario10','clave12354','Admin',800),('usuario100','clave12444','Admin',9800),('usuario101','clave12445','Empleado',9900),('usuario102','clave12446','Cliente',10000),('usuario103','clave12447','Admin',10100),('usuario104','clave12448','Empleado',10200),('usuario105','clave12449','Cliente',10300),('usuario106','clave12450','Admin',10400),('usuario107','clave12451','Empleado',10500),('usuario108','clave12452','Cliente',10600),('usuario109','clave12453','Admin',10700),('usuario11','clave12355','Empleado',900),('usuario110','clave12454','Empleado',10800),('usuario111','clave12455','Cliente',10900),('usuario112','clave12456','Admin',11000),('usuario113','clave12457','Empleado',11100),('usuario114','clave12458','Cliente',11200),('usuario115','clave12459','Admin',11300),('usuario116','clave12460','Empleado',11400),('usuario117','clave12461','Cliente',11500),('usuario118','clave12462','Admin',11600),('usuario119','clave12463','Empleado',11700),('usuario12','clave12356','Cliente',1000),('usuario120','clave12464','Cliente',11800),('usuario121','clave12465','Admin',11900),('usuario122','clave12466','Empleado',12000),('usuario123','clave12467','Cliente',12100),('usuario124','clave12468','Admin',12200),('usuario125','clave12469','Empleado',12300),('usuario126','clave12470','Cliente',12400),('usuario127','clave12471','Admin',12500),('usuario128','clave12472','Empleado',12600),('usuario129','clave12473','Cliente',12700),('usuario13','clave12357','Admin',1100),('usuario130','clave12474','Admin',12800),('usuario131','clave12475','Empleado',12900),('usuario132','clave12476','Cliente',13000),('usuario133','clave12477','Admin',13100),('usuario134','clave12478','Empleado',13200),('usuario135','clave12479','Cliente',13300),('usuario136','clave12480','Admin',13400),('usuario137','clave12481','Empleado',13500),('usuario138','clave12482','Cliente',13600),('usuario139','clave12483','Admin',13700),('usuario14','clave12358','Empleado',1200),('usuario140','clave12484','Empleado',13800),('usuario141','clave12485','Cliente',13900),('usuario142','clave12486','Admin',14000),('usuario143','clave12487','Empleado',14100),('usuario144','clave12488','Cliente',14200),('usuario145','clave12489','Admin',14300),('usuario146','clave12490','Empleado',14400),('usuario147','clave12491','Cliente',14500),('usuario148','clave12492','Admin',14600),('usuario149','clave12493','Empleado',14700),('usuario15','clave12359','Cliente',1300),('usuario150','clave12494','Cliente',14800),('usuario16','clave12360','Admin',1400),('usuario17','clave12361','Empleado',1500),('usuario18','clave12362','Cliente',1600),('usuario19','clave12363','Admin',1700),('usuario2','clave12346','Empleado',200),('usuario20','clave12364','Empleado',1800),('usuario21','clave12365','Cliente',1900),('usuario22','clave12366','Admin',2000),('usuario23','clave12367','Empleado',2100),('usuario24','clave12368','Cliente',2200),('usuario25','clave12369','Admin',2300),('usuario26','clave12370','Empleado',2400),('usuario27','clave12371','Cliente',2500),('usuario28','clave12372','Admin',2600),('usuario29','clave12373','Empleado',2700),('usuario3','clave12347','Cliente',150),('usuario30','clave12374','Cliente',2800),('usuario31','clave12375','Admin',2900),('usuario32','clave12376','Empleado',3000),('usuario33','clave12377','Cliente',3100),('usuario34','clave12378','Admin',3200),('usuario35','clave12379','Empleado',3300),('usuario36','clave12380','Cliente',3400),('usuario37','clave12381','Admin',3500),('usuario38','clave12382','Empleado',3600),('usuario39','clave12383','Cliente',3700),('usuario4','clave12348','Admin',250),('usuario40','clave12384','Admin',3800),('usuario41','clave12385','Empleado',3900),('usuario42','clave12386','Cliente',4000),('usuario43','clave12387','Admin',4100),('usuario44','clave12388','Empleado',4200),('usuario45','clave12389','Cliente',4300),('usuario46','clave12390','Admin',4400),('usuario47','clave12391','Empleado',4500),('usuario48','clave12392','Cliente',4600),('usuario49','clave12393','Admin',4700),('usuario5','clave12349','Empleado',300),('usuario50','clave12394','Empleado',4800),('usuario51','clave12395','Cliente',4900),('usuario52','clave12396','Admin',5000),('usuario53','clave12397','Empleado',5100),('usuario54','clave12398','Cliente',5200),('usuario55','clave12399','Admin',5300),('usuario56','clave12400','Empleado',5400),('usuario57','clave12401','Cliente',5500),('usuario58','clave12402','Admin',5600),('usuario59','clave12403','Empleado',5700),('usuario6','clave12350','Cliente',400),('usuario60','clave12404','Cliente',5800),('usuario61','clave12405','Admin',5900),('usuario62','clave12406','Empleado',6000),('usuario63','clave12407','Cliente',6100),('usuario64','clave12408','Admin',6200),('usuario65','clave12409','Empleado',6300),('usuario66','clave12410','Cliente',6400),('usuario67','clave12411','Admin',6500),('usuario68','clave12412','Empleado',6600),('usuario69','clave12413','Cliente',6700),('usuario7','clave12351','Admin',500),('usuario70','clave12414','Admin',6800),('usuario71','clave12415','Empleado',6900),('usuario72','clave12416','Cliente',7000),('usuario73','clave12417','Admin',7100),('usuario74','clave12418','Empleado',7200),('usuario75','clave12419','Cliente',7300),('usuario76','clave12420','Admin',7400),('usuario77','clave12421','Empleado',7500),('usuario78','clave12422','Cliente',7600),('usuario79','clave12423','Admin',7700),('usuario8','clave12352','Empleado',600),('usuario80','clave12424','Empleado',7800),('usuario81','clave12425','Cliente',7900),('usuario82','clave12426','Admin',8000),('usuario83','clave12427','Empleado',8100),('usuario84','clave12428','Cliente',8200),('usuario85','clave12429','Admin',8300),('usuario86','clave12430','Empleado',8400),('usuario87','clave12431','Cliente',8500),('usuario88','clave12432','Admin',8600),('usuario89','clave12433','Empleado',8700),('usuario9','clave12353','Cliente',700),('usuario90','clave12434','Cliente',8800),('usuario91','clave12435','Admin',8900),('usuario92','clave12436','Empleado',9000),('usuario93','clave12437','Cliente',9100),('usuario94','clave12438','Admin',9200),('usuario95','clave12439','Empleado',9300),('usuario96','clave12440','Cliente',9400),('usuario97','clave12441','Admin',9500),('usuario98','clave12442','Empleado',9600),('usuario99','clave12443','Cliente',9700);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-27 12:27:36