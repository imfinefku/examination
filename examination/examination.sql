/*
Navicat MySQL Data Transfer

Source Server         : mysql5.7
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : examination

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2022-10-08 10:11:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `authority_department`
-- ----------------------------
DROP TABLE IF EXISTS `authority_department`;
CREATE TABLE `authority_department` (
  `id` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `masterid` varchar(36) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_department
-- ----------------------------
INSERT INTO `authority_department` VALUES ('04b3ed49-5ed4-4a85-8ed3-62e9e547120f', '研发部', '0', '1520486a-1a55-4b73-a32e-94121711a64e', '2022-08-29 17:58:53');
INSERT INTO `authority_department` VALUES ('1520486a-1a55-4b73-a32e-94121711a64e', '集团总部', '100', '', '2022-08-25 14:56:33');
INSERT INTO `authority_department` VALUES ('15fcaf31-c4a3-457a-9ef3-3f55ced932d9', '研发一部', '100', '04b3ed49-5ed4-4a85-8ed3-62e9e547120f', '2022-08-29 17:59:41');
INSERT INTO `authority_department` VALUES ('3b0d3288-7f10-408c-8e05-a5bfd265a9c1', '财务部', '0', '1520486a-1a55-4b73-a32e-94121711a64e', '2022-08-25 14:59:55');
INSERT INTO `authority_department` VALUES ('6117ae08-095b-49b2-85a6-afd08d39c46e', '研发二部', '80', '04b3ed49-5ed4-4a85-8ed3-62e9e547120f', '2022-08-29 17:59:53');
INSERT INTO `authority_department` VALUES ('976b3fde-db63-4eb2-8dfb-904bbb0e871e', '人力资源部', '1', '1520486a-1a55-4b73-a32e-94121711a64e', '2022-08-25 15:05:16');

-- ----------------------------
-- Table structure for `authority_menu`
-- ----------------------------
DROP TABLE IF EXISTS `authority_menu`;
CREATE TABLE `authority_menu` (
  `id` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `hidden` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `masterid` varchar(36) DEFAULT NULL,
  `route` varchar(50) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_menu
-- ----------------------------
INSERT INTO `authority_menu` VALUES ('0479cfde-28d2-4c91-9c6d-5d085b79dc87', '试卷批改', 'el-icon-edit', '0', '30', '1d7bd3a8-d016-457b-adce-2306344ce1ed', '/home/examinationManage/testpaperCorrection', '2022-08-29 17:53:47');
INSERT INTO `authority_menu` VALUES ('1d7bd3a8-d016-457b-adce-2306344ce1ed', '考试管理', 'el-icon-tickets', '0', '70', '', '/home/examinationManage', '2022-08-29 17:45:13');
INSERT INTO `authority_menu` VALUES ('2ae71bc0-3ea2-487d-b872-9993cea9a092', '部门管理', 'el-icon-wind-power', '0', '95', '854ab22b-8ce5-4e95-bdd2-692b20d899fe', '/home/authority/departmentManage', '2022-08-23 13:47:30');
INSERT INTO `authority_menu` VALUES ('30e94a7f-90fa-43f7-a50a-f8698392f36e', '我的考试', 'el-icon-tickets', '0', '60', '', '/home/myExamination', '2022-08-29 17:46:17');
INSERT INTO `authority_menu` VALUES ('3773f52e-4164-4b97-aae5-ca5c7936d38a', '试卷管理', 'el-icon-document-checked', '0', '60', '1d7bd3a8-d016-457b-adce-2306344ce1ed', '/home/examinationManage/testpaperManage', '2022-08-29 17:47:43');
INSERT INTO `authority_menu` VALUES ('443b51b2-f15d-431c-81f8-8188bcda19f0', '试题管理', 'el-icon-document', '0', '70', '1d7bd3a8-d016-457b-adce-2306344ce1ed', '/home/examinationManage/questionManage', '2022-08-29 17:50:11');
INSERT INTO `authority_menu` VALUES ('62963b9a-ce69-4316-ae70-b338593f6522', '角色管理', 'el-icon-s-check', '0', '98', '854ab22b-8ce5-4e95-bdd2-692b20d899fe', '/home/authority/roleManage', '2022-08-15 16:41:28');
INSERT INTO `authority_menu` VALUES ('62fba0dd-9c58-48be-ae39-430b61a1cb2f', '科目管理', 'el-icon-s-data', '0', '200', '1d7bd3a8-d016-457b-adce-2306344ce1ed', '/home/examinationManage/subjectManage', '2022-08-30 10:07:28');
INSERT INTO `authority_menu` VALUES ('82ce0802-8f29-4cc8-bba7-98cf6c79f7c6', '我的成绩', 'el-icon-data-analysis', '0', '10', '30e94a7f-90fa-43f7-a50a-f8698392f36e', '/home/myExamination/myScore', '2022-08-29 17:57:10');
INSERT INTO `authority_menu` VALUES ('854ab22b-8ce5-4e95-bdd2-692b20d899fe', '权限管理', 'el-icon-postcard', '0', '100', '', '/home/authority', '2022-08-15 15:20:47');
INSERT INTO `authority_menu` VALUES ('85686289-6c87-4572-8ace-6b82877a6c79', '用户管理', 'el-icon-user', '0', '96', '854ab22b-8ce5-4e95-bdd2-692b20d899fe', '/home/authority/userManage', '2022-08-15 16:42:28');
INSERT INTO `authority_menu` VALUES ('8e72c059-0e85-4808-b16d-2cfb5b2a26aa', '考试管理', 'el-icon-notebook-2', '0', '50', '1d7bd3a8-d016-457b-adce-2306344ce1ed', '/home/examinationManage/examinationManage2', '2022-08-29 17:52:17');
INSERT INTO `authority_menu` VALUES ('a116f738-081c-4399-a5a8-5bafdcbfd24c', '在线考试', 'el-icon-monitor', '0', '60', '30e94a7f-90fa-43f7-a50a-f8698392f36e', '/home/myExamination/examOnline', '2022-08-29 17:56:09');
INSERT INTO `authority_menu` VALUES ('b6db4b4d-9019-4ace-899e-837654f4b262', '菜单管理', 'el-icon-menu', '0', '99', '854ab22b-8ce5-4e95-bdd2-692b20d899fe', '/home/authority/menuManage', '2022-08-15 16:40:16');
INSERT INTO `authority_menu` VALUES ('d9c7e7f1-e0e0-4b84-9ded-000dd53ad975', '管理台', 'el-icon-pie-chart', '0', '200', '', '/home/managerConsole', '2022-08-29 17:43:16');
INSERT INTO `authority_menu` VALUES ('ea2b59b9-2164-4284-b0a0-388fdf69427f', '权限管理', 'el-icon-postcard', '0', '97', '854ab22b-8ce5-4e95-bdd2-692b20d899fe', '/home/authority/authorityManage', '2022-08-15 16:41:59');

-- ----------------------------
-- Table structure for `authority_role`
-- ----------------------------
DROP TABLE IF EXISTS `authority_role`;
CREATE TABLE `authority_role` (
  `id` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_role
-- ----------------------------
INSERT INTO `authority_role` VALUES ('1663b611-7ddb-47f0-a81e-8ac181deac8c', '超级管理员', '拥有所有权限的管理员', '2022-08-15 17:22:48');

-- ----------------------------
-- Table structure for `authority_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `authority_role_menu`;
CREATE TABLE `authority_role_menu` (
  `id` varchar(36) NOT NULL,
  `roleid` varchar(36) DEFAULT NULL,
  `menuid` varchar(36) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_role_menu
-- ----------------------------
INSERT INTO `authority_role_menu` VALUES ('12d6272b-e131-486b-ac95-fa61126f7575', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '443b51b2-f15d-431c-81f8-8188bcda19f0', '0', '2022-08-30 16:10:35');
INSERT INTO `authority_role_menu` VALUES ('21723a27-95fc-4a0c-befc-b0bc58ee8814', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '62963b9a-ce69-4316-ae70-b338593f6522', '0', '2022-08-30 16:10:35');
INSERT INTO `authority_role_menu` VALUES ('425169f6-ac35-44ac-9a9a-9fc8965390d1', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '1d7bd3a8-d016-457b-adce-2306344ce1ed', '0', '2022-08-30 16:10:35');
INSERT INTO `authority_role_menu` VALUES ('5b2a14c0-1cec-4c77-9b49-a0d81d01e7a5', '1663b611-7ddb-47f0-a81e-8ac181deac8c', 'a116f738-081c-4399-a5a8-5bafdcbfd24c', '0', '2022-08-30 16:10:35');
INSERT INTO `authority_role_menu` VALUES ('6b750cf8-418e-4ead-a61a-6b0b35b6f434', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '3773f52e-4164-4b97-aae5-ca5c7936d38a', '0', '2022-08-30 16:10:35');
INSERT INTO `authority_role_menu` VALUES ('74536a24-8995-497b-be51-667482c10d7c', '1663b611-7ddb-47f0-a81e-8ac181deac8c', 'ea2b59b9-2164-4284-b0a0-388fdf69427f', '0', '2022-08-30 16:10:35');
INSERT INTO `authority_role_menu` VALUES ('77724a7d-14be-4461-ad0e-36d0e706c19d', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '854ab22b-8ce5-4e95-bdd2-692b20d899fe', '0', '2022-08-30 16:10:35');
INSERT INTO `authority_role_menu` VALUES ('7f215440-6c98-451c-ac9a-3e9bca5b0d4d', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '30e94a7f-90fa-43f7-a50a-f8698392f36e', '0', '2022-08-30 16:10:35');
INSERT INTO `authority_role_menu` VALUES ('837f0fdd-c8e2-4278-a645-67171e95237d', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '0479cfde-28d2-4c91-9c6d-5d085b79dc87', '0', '2022-08-30 16:10:35');
INSERT INTO `authority_role_menu` VALUES ('8ee2dd4e-f83f-490e-9cda-f64264b76799', '1663b611-7ddb-47f0-a81e-8ac181deac8c', 'b6db4b4d-9019-4ace-899e-837654f4b262', '0', '2022-08-30 16:10:35');
INSERT INTO `authority_role_menu` VALUES ('9b5c4a0f-0c1b-484a-97a9-19ae0de18fd3', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '62fba0dd-9c58-48be-ae39-430b61a1cb2f', '0', '2022-08-30 16:10:35');
INSERT INTO `authority_role_menu` VALUES ('a2b61d42-db1b-466b-ab17-3347f9a5d362', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '82ce0802-8f29-4cc8-bba7-98cf6c79f7c6', '0', '2022-08-30 16:10:35');
INSERT INTO `authority_role_menu` VALUES ('a4691ef2-4615-4d2e-841e-070260ad436e', '1663b611-7ddb-47f0-a81e-8ac181deac8c', 'd9c7e7f1-e0e0-4b84-9ded-000dd53ad975', '0', '2022-08-30 16:10:35');
INSERT INTO `authority_role_menu` VALUES ('bd608e3f-40a3-4697-b37c-73698588caa3', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '2ae71bc0-3ea2-487d-b872-9993cea9a092', '0', '2022-08-30 16:10:35');
INSERT INTO `authority_role_menu` VALUES ('e82eda35-a693-4e0c-b2a8-1b437cc327e5', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '85686289-6c87-4572-8ace-6b82877a6c79', '0', '2022-08-30 16:10:35');
INSERT INTO `authority_role_menu` VALUES ('f674ae28-d5da-4940-bada-86936307b883', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '8e72c059-0e85-4808-b16d-2cfb5b2a26aa', '0', '2022-08-30 16:10:35');

-- ----------------------------
-- Table structure for `authority_user`
-- ----------------------------
DROP TABLE IF EXISTS `authority_user`;
CREATE TABLE `authority_user` (
  `id` varchar(36) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `lastlogintime` datetime DEFAULT NULL,
  `departmentid` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_user
-- ----------------------------
INSERT INTO `authority_user` VALUES ('b2041da2-9373-42a5-9b83-6388fd3710a6', '超级管理员', 'admin', 'admin', '2022-08-17 15:01:33', '2022-10-04 22:10:11', '1520486a-1a55-4b73-a32e-94121711a64e');

-- ----------------------------
-- Table structure for `authority_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `authority_user_role`;
CREATE TABLE `authority_user_role` (
  `id` varchar(36) NOT NULL,
  `userid` varchar(36) DEFAULT NULL,
  `roleid` varchar(36) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_user_role
-- ----------------------------
INSERT INTO `authority_user_role` VALUES ('04ea4f16-3b78-4d5e-b700-972f7c67c7ee', 'b2041da2-9373-42a5-9b83-6388fd3710a6', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '2022-08-26 10:32:16');

-- ----------------------------
-- Table structure for `examination_examination`
-- ----------------------------
DROP TABLE IF EXISTS `examination_examination`;
CREATE TABLE `examination_examination` (
  `id` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `testpaper` varchar(36) DEFAULT NULL,
  `qualifiedscore` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of examination_examination
-- ----------------------------
INSERT INTO `examination_examination` VALUES ('e0e4975c-0c91-48b2-b10a-c16bee4e9cec', 'Java中级工程师面试题', '98ebbd55-9ec8-4a48-965a-65df12832ab9', '20', '0', '', '2022-10-01 21:34:12');

-- ----------------------------
-- Table structure for `examination_question`
-- ----------------------------
DROP TABLE IF EXISTS `examination_question`;
CREATE TABLE `examination_question` (
  `id` varchar(36) NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `subject` varchar(36) DEFAULT NULL,
  `a` varchar(200) DEFAULT NULL,
  `b` varchar(200) DEFAULT NULL,
  `c` varchar(200) DEFAULT NULL,
  `d` varchar(200) DEFAULT NULL,
  `imgurl` varchar(200) DEFAULT NULL,
  `analysis` varchar(1000) DEFAULT NULL,
  `answer` varchar(1000) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of examination_question
-- ----------------------------
INSERT INTO `examination_question` VALUES ('196e6073-da06-4497-bf5e-f7e5dd569eb9', '下列对于Java锁升级说法错误的是？', '单选题', '97651035-2622-46cc-a508-0032297f86b3', '锁一共有4种状态，级别从低到高依次是：无锁、偏向锁、轻量级锁和重量级锁。', '锁状态能升级也能降级。', '轻量级锁基本原理是采用CAS+自旋的方式避免线程阻塞和唤醒的开销。', '采用操作系统互斥量实现，当发现对象无锁时，会获取锁。如果获取锁失败或者对象本身就处于锁定状态，会进入阻塞状态，等待线程唤醒。', '', 'jdk1.5之前是synchronized锁是重量级锁，性能很差。经过研究发现，被锁住的资源在大多数形况下是不存在线程竞争的，因此jdk1.6之后对synchronized锁进行了优化，引入了偏向锁和轻量级锁。目前锁一共有4种状态，级别从低到高依次是：无锁、偏向锁、轻量级锁和重量级锁。锁状态只能升级不能降级。\n\n锁的四种状态：\n1.无锁：Mark Word存储01，当一个对象被创建之后，还没有线程进入，这个时候对象处于无锁状态，其Mark Word中的信息如上表所示。\n\n2.偏向锁：Mark Word存储01，当锁处于无锁状态时，有一个线程A访问同步块并获取锁时，会在对象头和栈帧中的锁记录记录线程ID，以后该线程在进入和退出同步块时不需要进行CAS操作来进行加锁和解锁，只需要简单的测试一下啊对象头中的线程ID和当前线程是否一致。\n\n3.轻量级锁：Mark Word存储00，在偏向锁的基础上，又有另外一个线程B进来，这时判断对象头中存储的线程A的ID和线程B不一致，就会使用CAS竞争锁，并且升级为轻量级锁，会在线程栈中创建一个锁记录，将Mark Word复制到锁记录中，然后线程尝试使用CAS将对象头的Mark Word替换成指向锁记录的指针，如果成功，则当前线程获得锁；失败，表示其他线程竞争锁，当前线程便尝试CAS来获取锁。\n\n4.重量级锁：Mark Word存储10，当线程没有获得轻量级锁时，线程会CAS自旋来获取锁，当一个线程自旋10次之后，仍然未获得锁，那么就会升级成为重量级锁。成为重量级锁之后，线程会进入阻塞队列，线程不再自旋获取锁，而是由CPU进行调度，线程串行执行。', 'B', '2022-09-28 20:42:00');
INSERT INTO `examination_question` VALUES ('215f521d-de74-4de3-ad52-1c95bd148551', '用explain分析sql语句性能时，如果使用了索引，会在哪个字段中显示？', '单选题', '97651035-2622-46cc-a508-0032297f86b3', 'possible_keys', 'ref', 'Extra', 'key', '', '', 'D', '2022-09-28 20:43:21');
INSERT INTO `examination_question` VALUES ('4c600f0e-eb5c-4285-801f-662ba5f4674d', 'SpringBoot默认的数据库连接池是什么？', '单选题', '97651035-2622-46cc-a508-0032297f86b3', 'druid', 'hikari', 'c3p0', 'tomcat-jdbc', '', '', 'B', '2022-09-28 20:44:03');
INSERT INTO `examination_question` VALUES ('50c9d3c9-47a9-4795-b6ba-d529567540ec', 'mq常见的用途是什么？', '单选题', '97651035-2622-46cc-a508-0032297f86b3', '异步', '解耦', '削峰', '以上都是', '', '无', 'D', '2022-09-28 20:42:43');
INSERT INTO `examination_question` VALUES ('8551930b-680d-4ee7-8de1-7b733e5a26e8', 'SpringCloud核心组件有哪些？', '简答题', '97651035-2622-46cc-a508-0032297f86b3', '', '', '', '', '', '', '1.Spring Cloud Eureka：Eureka分为Eureka Server和Eureka Client，每个微服务都是一个Eureka Client。各个服务启动时，Eureka Client都会将服务注册到Eureka Server并被Eureka Server通过心跳监控，并且Eureka Client还可以反过来从Eureka Server拉取注册表，从⽽知道其他服务的地址信息以便调用。\n\n2.Spring Cloud Zuul：如果前端、移动端要调⽤后端系统，统⼀从Zuul⽹关进⼊，由Zuul⽹关转发请求给对应的服务，在网关上可以做鉴权、日志等操作，保护业务服务。\n\n3.Spring Cloud Ribbon：主要功能是提供客户端的软件负载均衡算法。\n\n4.Spring Cloud Feign：基于Ribbon，根据注解和选择的机器，拼接请求URL地址，发起请求进行服务间调用。\n\n5.Spring Cloud Hystrix：发起请求是通过Hystrix的线程池来⾛的，不同的服务⾛不同的线程池，实现了不同服务调⽤的隔离，避免了服务雪崩的问题，并且提供服务降级、服务熔断、服务限流等功能。\n\n6.Spring Cloud Config：分布式统一配置管理，统一管理各个微服务的配置文件。', '2022-09-28 20:45:17');
INSERT INTO `examination_question` VALUES ('85e5b2ca-689b-4414-a263-a3c24c8afd89', '请简述一下jvm垃圾回收机制的工作流程？', '简答题', '97651035-2622-46cc-a508-0032297f86b3', '', '', '', '', '', '', '当新生代eden空间不足时，触发一次minor GC，将eden中存活的对象和使用中的survivor存活的对象复制到另一个suivivor中，同时把一些超年龄的放入老年代。若还不够，把一些活跃的对象从eden放入survivor中。若还不够，放入老年代。若老年代不够，触发一次majorGC/fullGC，若不够，抛出内存不足的异常。\n\nJVM调优的目的就是尽量减少major GC/full GC的次数。', '2022-09-28 20:44:55');
INSERT INTO `examination_question` VALUES ('b382a1f9-aac6-473b-801f-16530685891c', '关于数据库锁，描述正确的是？', '单选题', '97651035-2622-46cc-a508-0032297f86b3', '当一个事务在表上获取了共享锁，其他事务能阅读表里的数据。', '当一个事务在表上获取了共享锁，其他事务能更新表里的数据。', '当一个事务在表上获取了排他锁，其他事务能阅读表里的数据。', '当一个事务在表上获取了排他锁，其他事务能更新表里的数据。', '', '', 'A', '2022-09-28 20:44:32');

-- ----------------------------
-- Table structure for `examination_subject`
-- ----------------------------
DROP TABLE IF EXISTS `examination_subject`;
CREATE TABLE `examination_subject` (
  `id` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of examination_subject
-- ----------------------------
INSERT INTO `examination_subject` VALUES ('97651035-2622-46cc-a508-0032297f86b3', 'Java', '2022-08-30 17:08:39');

-- ----------------------------
-- Table structure for `examination_testpaper`
-- ----------------------------
DROP TABLE IF EXISTS `examination_testpaper`;
CREATE TABLE `examination_testpaper` (
  `id` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `subject` varchar(36) DEFAULT NULL,
  `xznum` int(11) DEFAULT NULL,
  `pdnum` int(11) DEFAULT NULL,
  `jdnum` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of examination_testpaper
-- ----------------------------
INSERT INTO `examination_testpaper` VALUES ('98ebbd55-9ec8-4a48-965a-65df12832ab9', 'Java工程师面试题一卷', '97651035-2622-46cc-a508-0032297f86b3', '5', '0', '2', '35', '90', '2022-10-01 21:33:02');

-- ----------------------------
-- Table structure for `examination_testpaper_question`
-- ----------------------------
DROP TABLE IF EXISTS `examination_testpaper_question`;
CREATE TABLE `examination_testpaper_question` (
  `id` varchar(36) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `testpaper` varchar(36) DEFAULT NULL,
  `question` varchar(36) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of examination_testpaper_question
-- ----------------------------
INSERT INTO `examination_testpaper_question` VALUES ('33b6112b-b0d2-40f7-be23-560df7ab5b6d', '单选题', '98ebbd55-9ec8-4a48-965a-65df12832ab9', '215f521d-de74-4de3-ad52-1c95bd148551', '2', '2022-10-02 21:10:28');
INSERT INTO `examination_testpaper_question` VALUES ('44c5323e-90d8-4c7c-93e0-2d706d51d4e4', '单选题', '98ebbd55-9ec8-4a48-965a-65df12832ab9', '4c600f0e-eb5c-4285-801f-662ba5f4674d', '3', '2022-10-02 21:10:28');
INSERT INTO `examination_testpaper_question` VALUES ('46fe089c-1002-41b1-a1e5-0d53c8dca60f', '单选题', '98ebbd55-9ec8-4a48-965a-65df12832ab9', 'b382a1f9-aac6-473b-801f-16530685891c', '4', '2022-10-02 21:10:28');
INSERT INTO `examination_testpaper_question` VALUES ('4cdd5bd0-22fb-4367-9430-f0261b15e095', '简答题', '98ebbd55-9ec8-4a48-965a-65df12832ab9', '85e5b2ca-689b-4414-a263-a3c24c8afd89', '5', '2022-10-02 21:10:28');
INSERT INTO `examination_testpaper_question` VALUES ('585b90df-919a-47c0-ba36-8f9e7eef4a05', '单选题', '98ebbd55-9ec8-4a48-965a-65df12832ab9', '50c9d3c9-47a9-4795-b6ba-d529567540ec', '1', '2022-10-02 21:10:28');
INSERT INTO `examination_testpaper_question` VALUES ('e65ea866-5f53-419e-9644-24b6bdcbe5e6', '简答题', '98ebbd55-9ec8-4a48-965a-65df12832ab9', '8551930b-680d-4ee7-8de1-7b733e5a26e8', '6', '2022-10-02 21:10:28');
INSERT INTO `examination_testpaper_question` VALUES ('fc078963-39d1-44cf-83a7-c13b13b4c09c', '单选题', '98ebbd55-9ec8-4a48-965a-65df12832ab9', '196e6073-da06-4497-bf5e-f7e5dd569eb9', '0', '2022-10-02 21:10:28');

-- ----------------------------
-- Table structure for `examination_user_join`
-- ----------------------------
DROP TABLE IF EXISTS `examination_user_join`;
CREATE TABLE `examination_user_join` (
  `id` varchar(36) NOT NULL,
  `userid` varchar(36) DEFAULT NULL,
  `examination` varchar(36) DEFAULT NULL,
  `testpaper` varchar(36) DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of examination_user_join
-- ----------------------------
INSERT INTO `examination_user_join` VALUES ('462a03e2-e6eb-4454-a9ad-562d0f9075e8', 'b2041da2-9373-42a5-9b83-6388fd3710a6', 'e0e4975c-0c91-48b2-b10a-c16bee4e9cec', '98ebbd55-9ec8-4a48-965a-65df12832ab9', '2022-10-04 21:23:19', '2022-10-04 23:03:19', '2', '27');
INSERT INTO `examination_user_join` VALUES ('7a4f925a-c872-40c9-b71e-189b680ffa42', 'b2041da2-9373-42a5-9b83-6388fd3710a6', 'e0e4975c-0c91-48b2-b10a-c16bee4e9cec', '98ebbd55-9ec8-4a48-965a-65df12832ab9', '2022-10-04 21:24:26', '2022-10-04 23:04:26', '1', '0');

-- ----------------------------
-- Table structure for `examination_user_join_info`
-- ----------------------------
DROP TABLE IF EXISTS `examination_user_join_info`;
CREATE TABLE `examination_user_join_info` (
  `id` varchar(36) NOT NULL,
  `masterid` varchar(36) DEFAULT NULL,
  `question` varchar(36) DEFAULT NULL,
  `reply` varchar(1000) DEFAULT NULL,
  `correctScore` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of examination_user_join_info
-- ----------------------------
INSERT INTO `examination_user_join_info` VALUES ('012af71e-42e2-4d67-9745-c5d2eed3a78d', '7a4f925a-c872-40c9-b71e-189b680ffa42', '196e6073-da06-4497-bf5e-f7e5dd569eb9', 'B', '3');
INSERT INTO `examination_user_join_info` VALUES ('1d8dc4b5-9407-4ee9-9950-96fe626a57a1', '7a4f925a-c872-40c9-b71e-189b680ffa42', '85e5b2ca-689b-4414-a263-a3c24c8afd89', '不会', '0');
INSERT INTO `examination_user_join_info` VALUES ('1e93ddec-1ad7-4b9b-bb38-7a1b5ea34bce', '7a4f925a-c872-40c9-b71e-189b680ffa42', 'b382a1f9-aac6-473b-801f-16530685891c', 'A', '3');
INSERT INTO `examination_user_join_info` VALUES ('43eab748-8fda-4c63-b89b-2799198001b3', '462a03e2-e6eb-4454-a9ad-562d0f9075e8', '196e6073-da06-4497-bf5e-f7e5dd569eb9', 'B', '3');
INSERT INTO `examination_user_join_info` VALUES ('8cf633cd-1c3a-406f-8ec8-d19877d008de', '7a4f925a-c872-40c9-b71e-189b680ffa42', '8551930b-680d-4ee7-8de1-7b733e5a26e8', '不会', '0');
INSERT INTO `examination_user_join_info` VALUES ('918ce3f6-dae1-4b5d-b3e4-12f546e3228c', '462a03e2-e6eb-4454-a9ad-562d0f9075e8', '50c9d3c9-47a9-4795-b6ba-d529567540ec', 'D', '3');
INSERT INTO `examination_user_join_info` VALUES ('a8df7f28-24f5-4414-953d-09b8746277ee', '462a03e2-e6eb-4454-a9ad-562d0f9075e8', '8551930b-680d-4ee7-8de1-7b733e5a26e8', '你猜', '6');
INSERT INTO `examination_user_join_info` VALUES ('b9a02f80-56ad-4e40-bb21-c273124d541e', '462a03e2-e6eb-4454-a9ad-562d0f9075e8', '215f521d-de74-4de3-ad52-1c95bd148551', 'D', '3');
INSERT INTO `examination_user_join_info` VALUES ('c27dfdfc-0d73-42af-967f-2948b5857102', '7a4f925a-c872-40c9-b71e-189b680ffa42', '4c600f0e-eb5c-4285-801f-662ba5f4674d', 'B', '3');
INSERT INTO `examination_user_join_info` VALUES ('cc1739e0-dcaf-47e9-8240-c232317b10d0', '462a03e2-e6eb-4454-a9ad-562d0f9075e8', '4c600f0e-eb5c-4285-801f-662ba5f4674d', 'B', '3');
INSERT INTO `examination_user_join_info` VALUES ('d31d5e60-fde1-4328-ad26-449f8faac672', '462a03e2-e6eb-4454-a9ad-562d0f9075e8', '85e5b2ca-689b-4414-a263-a3c24c8afd89', '这个问题好  哈哈哈哈', '6');
INSERT INTO `examination_user_join_info` VALUES ('d8d15a26-4bde-4cd3-aa7d-1cdc9ed76c8d', '7a4f925a-c872-40c9-b71e-189b680ffa42', '215f521d-de74-4de3-ad52-1c95bd148551', 'A', '0');
INSERT INTO `examination_user_join_info` VALUES ('e0448509-a37b-4552-b684-8f4e3482fc15', '462a03e2-e6eb-4454-a9ad-562d0f9075e8', 'b382a1f9-aac6-473b-801f-16530685891c', 'A', '3');
INSERT INTO `examination_user_join_info` VALUES ('f9c68de7-b4b4-4cca-a9e5-209c7505daca', '7a4f925a-c872-40c9-b71e-189b680ffa42', '50c9d3c9-47a9-4795-b6ba-d529567540ec', 'D', '3');
