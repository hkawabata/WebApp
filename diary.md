## 2016/09/04 MySQL と連携

```bash
$ brew update
$ brew install mysql
$ mysql.server start
```

```sql
$ mysql -uroot
mysql> create database keijiban;
mysql> create table keijiban.tsubuyaki(id int unique, date text, user text, text text);
mysql> create user keijiban@localhost identified by "keijiban";
mysql> grant select, insert, update, delete on tsubuyaki to keijiban@localhost;
mysql> exit;
```

```
$ mysql -u keijiban -p
mysql> select * from keijiban.tsubuyaki order by id;

+------+------------------------------+--------+------------------------------------
| id   | date                         | user   | text                               
+------+------------------------------+--------+------------------------------------
|    0 | Sun Sep 04 17:44:19 JST 2016 | 太郎   | 1げと                              
|    1 | Sun Sep 04 17:44:22 JST 2016 | 二郎   | 1get                               
|    2 | Sun Sep 04 17:44:30 JST 2016 | 二郎   | 僅差で負けたか                     
|    3 | Sun Sep 04 17:45:03 JST 2016 | 三郎   | のんびり4ゲット                    
|    4 | Sun Sep 04 17:46:36 JST 2016 | 太郎   | MySQL 無事に連携できてるぽい。     
|    5 | Sun Sep 04 17:46:46 JST 2016 | 二郎   | よかったー。                       
|    6 | Sun Sep 04 17:47:06 JST 2016 | 太郎   | 順序が保たれるかだけ確認すべし     
|    7 | Sun Sep 04 17:47:14 JST 2016 | 太郎   | 今から一旦サーバ落とします         
|    8 | Sun Sep 04 17:48:26 JST 2016 | 三郎   | 復活。順番もちゃんと時間順だね。   
+------+------------------------------+--------+------------------------------------

```
