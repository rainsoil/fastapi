-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 192.168.1.14    Database: fastapi
-- ------------------------------------------------------
-- Server version	8.0.33

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



DROP TABLE IF EXISTS `sys_data_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_data_set` (
                                `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户id',
                                `id` bigint NOT NULL COMMENT '主键id',
                                `create_time` datetime NOT NULL COMMENT '创建时间',
                                `create_user` bigint NOT NULL COMMENT '创建用户',
                                `update_user` bigint NOT NULL COMMENT '修改用户',
                                `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                `del_flag` varchar(3) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '逻辑删除(0:正常,1:删除)',
                                `table_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表名',
                                `value` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'value值的字段',
                                `label` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'label值的字段',
                                `code` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据集编码',
                                `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据集名称',
                                `data_scope` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据权限 0:共享,1:私有',
                                `last_sql` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '补充sql',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='数据集';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_data_set`
--

LOCK TABLES `sys_data_set` WRITE;
/*!40000 ALTER TABLE `sys_data_set` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_data_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict`
--

DROP TABLE IF EXISTS `sys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict` (
                            `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户id',
                            `id` bigint NOT NULL COMMENT '主键id',
                            `create_time` datetime NOT NULL COMMENT '创建时间',
                            `create_user` bigint NOT NULL COMMENT '创建用户',
                            `update_user` bigint NOT NULL COMMENT '修改用户',
                            `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                            `del_flag` varchar(3) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '逻辑删除(0:正常,1:删除)',
                            `type` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典类型',
                            `description` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典描述',
                            `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                            `built_in` varchar(3) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否系统内置(0:是, 1:不是)',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict`
--

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_item`
--

DROP TABLE IF EXISTS `sys_dict_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_item` (
                                 `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户id',
                                 `id` bigint NOT NULL COMMENT '主键id',
                                 `create_time` datetime NOT NULL COMMENT '创建时间',
                                 `create_user` bigint NOT NULL COMMENT '创建用户',
                                 `update_user` bigint NOT NULL COMMENT '修改用户',
                                 `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                 `del_flag` varchar(3) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '逻辑删除(0:正常,1:删除)',
                                 `dict_id` bigint DEFAULT NULL COMMENT '字典表id',
                                 `value` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典值',
                                 `label` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典标签',
                                 `type` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典类型',
                                 `description` int DEFAULT '0' COMMENT '字典描述',
                                 `sort` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '排序',
                                 `style` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '样式',
                                 `show` varchar(3) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '是否展示(0:展示,1:不展示)',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='字典项表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_item`
--

LOCK TABLES `sys_dict_item` WRITE;
/*!40000 ALTER TABLE `sys_dict_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dict_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
                            `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户id',
                            `id` bigint NOT NULL COMMENT '主键id',
                            `create_time` datetime NOT NULL COMMENT '创建时间',
                            `create_user` bigint NOT NULL COMMENT '创建用户',
                            `update_user` bigint NOT NULL COMMENT '修改用户',
                            `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                            `del_flag` varchar(3) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '逻辑删除(0:正常,1:删除)',
                            `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单名称',
                            `permission` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '系统权限',
                            `path` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '路径',
                            `parent_id` bigint DEFAULT NULL COMMENT '父菜单id',
                            `icon` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'icon',
                            `sort` int DEFAULT '1' COMMENT '排序',
                            `keep_alive` varchar(255) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '保持活跃(1:不,0:保持)',
                            `type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单类型(1:菜单,2:按钮)',
                            `blank` varchar(255) COLLATE utf8mb4_bin DEFAULT '1' COMMENT '是否外链(1:否,0:是)',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
                            `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户id',
                            `id` bigint NOT NULL COMMENT '主键id',
                            `create_time` datetime NOT NULL COMMENT '创建时间',
                            `create_user` bigint NOT NULL COMMENT '创建用户',
                            `update_user` bigint NOT NULL COMMENT '修改用户',
                            `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                            `del_flag` varchar(3) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '逻辑删除(0:正常,1:删除)',
                            `role_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名称',
                            `role_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色编码',
                            `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `role_code` (`del_flag`,`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT=' 系统角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('1',1,'2023-06-26 23:36:49',1,1,'2023-06-26 23:36:53','0','系统管理员','SUPER_ADMIN',NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
                                 `role_id` bigint DEFAULT NULL COMMENT '角色id',
                                 `menu_id` bigint DEFAULT NULL COMMENT '菜单id',
                                 `id` bigint NOT NULL COMMENT '主键id',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `role_menu` (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统角色菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
                            `tenant_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户id',
                            `id` bigint NOT NULL COMMENT '主键id',
                            `create_time` datetime NOT NULL COMMENT '创建时间',
                            `create_user` bigint NOT NULL COMMENT '创建用户',
                            `update_user` bigint NOT NULL COMMENT '修改用户',
                            `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                            `del_flag` varchar(3) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '逻辑删除(0:正常,1:删除)',
                            `user_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '登录用户名',
                            `phone` varchar(11) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
                            `password` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
                            `real_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '真实姓名',
                            `status` varchar(3) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '状态(0:正常,1:冻结)',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `user_name` (`del_flag`,`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('1',1,'2023-06-19 22:28:03',1,1,'2023-06-19 22:28:09','0','admin','18174741414','207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb','超级管理员','0');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
                                 `id` bigint NOT NULL COMMENT '主键id',
                                 `user_id` bigint NOT NULL COMMENT '用户id',
                                 `role_id` bigint NOT NULL COMMENT '角色id',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `user_id_role_id` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1,1);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-26 20:30:15
