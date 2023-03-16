# 多线程实现百万级数据导出到excel

## **考虑前提：**

大数据量导出到文件，首先需要考虑的是内存溢出的场景：数据库读取数据到内存中、将数据写入到excel进行大量的IO操作。然后考虑到一个文件数据过大，用户打开慢，体验不好。针对这些问题的考虑，采用多线程的方式多个线程同时处理查询数据，一个线程生成一个excel，最后在合并数据返回，以达到提高效率的目的。

## **实现思路：**
1.使用easyExecl结合批量线程池执行,结合线程分批处理数据缩短查询数据库时间和导出数据时间,查询数据速度比较理想
测试接口:http://localhost:8080/exportEasyExcel?pageNum=0&pageSize=100000&limit=4000
2.使用easyExecl结合循环线程池执行,结合线程分批处理数据缩短查询数据库时间和导出数据时间,查询数据速度不理想
测试接口:http://localhost:8080/exportEasyExcelV1?pageNum=0&pageSize=60000&limit=2000
3.使用SXSSFWorkbook结合批量线程池执行,结合线程分批处理数据缩短查询数据库时间和导出数据时间,效果最优最理想
http://localhost:8080/exportEasyExcelV4?pageNum=0&pageSize=30000&limit=2000

模拟sql可以结合测试类本地造数据:
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `ceate_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `test1` varchar(255) DEFAULT NULL,
  `test2` varchar(255) DEFAULT NULL,
  `test3` varchar(255) DEFAULT NULL,
  `test4` varchar(255) DEFAULT NULL,
  `test5` varchar(255) DEFAULT NULL,
  `test6` varchar(255) DEFAULT NULL,
  `test7` varchar(255) DEFAULT NULL,
  `test8` varchar(255) DEFAULT NULL,
  `test9` varchar(255) DEFAULT NULL,
  `test10` varchar(255) DEFAULT NULL,
  `test11` varchar(255) DEFAULT NULL,
  `test12` varchar(255) DEFAULT NULL,
  `test13` varchar(255) DEFAULT NULL,
  `test14` varchar(255) DEFAULT NULL,
  `test15` varchar(255) DEFAULT NULL,
  `test16` varchar(255) DEFAULT NULL,
  `test17` varchar(255) DEFAULT NULL,
  `test18` varchar(255) DEFAULT NULL,
  `test19` varchar(255) DEFAULT NULL,
  `test20` varchar(255) DEFAULT NULL,
  `test21` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=600001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

