/*
SQLyog Enterprise - MySQL GUI v7.15 
MySQL - 5.0.27-community-nt : Database - lucera
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `skill_spellbooks` */

DROP TABLE IF EXISTS `skill_spellbooks`;

CREATE TABLE `skill_spellbooks` (
  `skill_id` int(11) NOT NULL default '-1',
  `item_id` int(11) NOT NULL default '-1',
  KEY `skill_id` (`skill_id`,`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `skill_spellbooks` */

LOCK TABLES `skill_spellbooks` WRITE;

insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (2,1512);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (10,3039);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (13,3940);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (15,1513);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (21,1377);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (22,3040);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (25,3038);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (33,3041);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (44,3432);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (45,1378);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (46,3042);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (49,3043);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (58,1096);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (61,1379);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (65,3044);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (67,3045);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (69,3046);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (70,1097);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (72,3047);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (77,1095);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (86,3048);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (91,1294);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (102,1380);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (103,3049);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (105,1381);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (115,1382);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (122,3050);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (123,3051);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (127,3052);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (129,1383);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (230,1384);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (262,3053);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (278,3054);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (279,3055);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (283,3056);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (289,4203);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (299,4915);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (301,4921);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (438,8877);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (448,8909);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (449,8890);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (454,8892);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (455,8893);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (456,8894);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (457,8895);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (458,8896);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (459,8897);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (460,8898);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1002,1518);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1003,1519);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1004,3103);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1005,1520);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1006,1521);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1007,1522);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1008,3104);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1009,1523);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1010,1524);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1011,1152);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1012,1053);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1013,1385);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1015,1050);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1016,1514);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1018,3072);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1020,3068);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1027,1054);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1028,3073);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1031,1386);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1032,3094);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1033,1387);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1034,3069);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1035,1388);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1036,3095);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1040,1058);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1042,3070);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1043,1389);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1044,1390);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1045,3096);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1047,4207);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1048,3097);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1049,3071);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1050,3098);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1056,3079);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1059,1391);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1062,1392);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1064,3064);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1068,1048);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1069,1394);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1071,3082);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1072,3080);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1073,1515);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1074,3074);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1075,1397);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1077,1398);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1078,1399);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1085,1401);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1086,3099);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1087,1402);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1090,1525);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1092,1526);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1095,1527);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1096,1528);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1097,1529);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1099,1530);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1100,1531);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1101,1532);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1102,1533);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1104,3105);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1105,1534);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1107,1535);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1108,3106);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1111,1403);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1126,1404);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1127,1405);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1128,1667);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1129,3057);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1139,3091);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1140,3092);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1141,3093);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1144,1406);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1145,1407);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1146,1408);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1147,1051);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1148,3065);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1151,1516);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1154,3058);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1155,3059);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1156,3060);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1157,1517);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1159,3066);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1160,1409);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1163,3061);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1164,1056);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1167,1410);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1168,1055);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1169,3062);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1170,3063);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1171,3075);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1172,1411);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1174,3083);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1175,1370);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1176,3089);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1178,1371);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1181,1052);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1182,1412);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1183,3084);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1184,1049);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1189,1413);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1191,1414);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1201,1415);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1204,1098);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1206,1099);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1208,1536);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1209,1537);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1210,3107);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1213,3108);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1217,3429);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1218,3430);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1219,3431);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1220,1372);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1222,1416);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1223,1417);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1224,1418);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1225,1668);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1226,1669);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1227,1670);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1228,1671);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1229,1856);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1230,3076);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1231,3081);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1232,3077);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1233,3078);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1234,3067);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1235,3085);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1236,3086);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1237,3087);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1238,3088);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1239,3090);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1240,3100);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1242,3101);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1243,3102);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1244,3115);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1245,3114);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1246,3109);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1247,3110);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1248,3111);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1249,3112);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1250,3113);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1251,3116);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1252,3117);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1253,3118);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1254,3941);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1256,3943);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1257,3944);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1258,4200);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1259,4201);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1260,4204);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1261,4205);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1262,4206);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1263,4208);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1264,4906);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1265,4907);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1266,4908);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1267,4909);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1268,4910);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1269,4911);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1271,4912);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1272,4913);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1273,4914);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1274,4916);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1275,4917);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1276,4918);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1277,4919);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1278,4920);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1279,4922);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1280,4923);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1281,4924);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1282,4925);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1283,4926);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1284,4927);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1285,4928);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1286,4929);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1287,4930);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1288,4931);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1289,4932);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1290,4933);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1291,4934);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1292,5013);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1293,5014);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1294,5015);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1295,5809);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1296,5810);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1298,5811);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1299,5812);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1300,5813);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1301,5814);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1303,5815);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1304,5816);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1305,6350);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1306,6351);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1307,6352);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1308,6395);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1309,6396);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1310,6397);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1311,6398);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1328,7638);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1329,7639);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1330,7640);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1331,7641);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1332,7642);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1333,7643);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1334,7644);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1335,7645);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1336,7646);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1337,7647);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1338,7648);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1339,7649);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1340,7650);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1341,7651);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1342,7652);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1343,7653);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1344,7654);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1345,7655);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1346,7656);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1347,7657);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1348,7658);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1349,7659);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1350,7660);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1351,7661);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1352,7662);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1353,7663);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1354,7664);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1355,7665);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1356,7666);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1357,7667);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1358,7668);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1359,7669);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1360,7670);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1361,7671);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1362,7672);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1363,7673);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1364,7674);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1365,7675);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1366,7676);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1367,7835);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1380,8380);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1381,8381);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1382,8382);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1383,8383);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1384,8384);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1385,8385);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1386,8386);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1387,8387);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1388,8388);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1389,8389);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1390,8390);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1391,8391);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1392,8392);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1393,8393);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1394,8394);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1395,8395);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1396,8396);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1397,8397);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1398,8398);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1399,8399);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1400,8400);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1401,8401);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1402,8402);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1403,8616);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1404,8617);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1406,8878);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1407,8879);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1408,8880);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1409,8881);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1410,8882);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1411,8883);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1412,8884);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1413,8885);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1414,8886);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1415,8887);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1416,8888);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1417,8891);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1418,8945);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1419,8899);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1420,8900);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1421,8901);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1422,8902);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1423,8903);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1424,8904);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1425,8905);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1426,8906);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1427,8907);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1428,8908);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1429,8889);
insert  into `skill_spellbooks`(`skill_id`,`item_id`) values (1430,8946);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;