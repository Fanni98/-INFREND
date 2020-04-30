Ügyfél tábla
+-------------+--------------+------+-----+---------+----------------+
| Field       | Type         | Null | Key | Default | Extra          |
+-------------+--------------+------+-----+---------+----------------+
| id          | int          | NO   | PRI | NULL    | auto_increment |
| nev         | varchar(100) | YES  |     | NULL    |                |
| lakcim      | varchar(100) | YES  |     | NULL    |                |
| telefonszam | int          | YES  |     | NULL    |                |
| szigszam    | varchar(10)  | YES  |     | NULL    |                |
| statusz     | varchar(10)  | YES  |     | NULL    |                |
+-------------+--------------+------+-----+---------+----------------+
Dvd tábla
+----------------+--------------+------+-----+---------+-------+
| Field          | Type         | Null | Key | Default | Extra |
+----------------+--------------+------+-----+---------+-------+
| sorszam        | int          | NO   | PRI | NULL    |       |
| cim            | varchar(100) | YES  |     | NULL    |       |
| beszerzesdatum | varchar(20)  | YES  |     | NULL    |       |
| statusz        | varchar(20)  | YES  |     | NULL    |       |
+----------------+--------------+------+-----+---------+-------+
Kölcsönzés tábla
+----------+-------------+------+-----+---------+-------+
| Field    | Type        | Null | Key | Default | Extra |
+----------+-------------+------+-----+---------+-------+
| uid      | int         | YES  | MUL | NULL    |       |
| usorszam | int         | YES  | MUL | NULL    |       |
| mikortol | varchar(20) | YES  |     | NULL    |       |
+----------+-------------+------+-----+---------+-------+
