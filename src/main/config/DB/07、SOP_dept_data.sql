delete from AU_T_DEPT;

commit;

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1, '泗洪县公安局', '321324000000', 0, '江苏省泗洪县公安局', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), null, 0, '1', sysdate, sysdate, '1', null, 'B');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (2, '经济犯罪侦查大队', '321324020000', 1, '泗洪县公安局经济犯罪侦查大队', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 2, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (3, '治安警察大队', '321324030000', 1, '泗洪县公安局治安警察大队', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 3, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (4, '刑事警察大队', '321324050000', 1, '泗洪县公安局刑事警察大队', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 4, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (5, '出入境管理大队', '321324060000', 1, '泗洪县公安局出入境管理大队', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 5, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (6, '网络安全保卫大队', '321324110000', 1, '泗洪县公安局网络安全保卫大队', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 6, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (7, '信访科', '321324150000', 1, '泗洪县公安局信访科', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 7, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (8, '交通警察大队', '321324170000', 1, '泗洪县公安局交通警察大队', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 8, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (9, '法制大队', '321324180000', 1, '泗洪县公安局法制大队', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 9, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (10, '信息通信大队', '321324230000', 1, '泗洪县公安局信息通信大队', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 10, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (11, '中共泗洪县委维护稳定工作领导小组办公室', '321324290000', 1, '中共泗洪县委维护稳定工作领导小组办公室', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 11, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (12, '指挥中心', '321324330000', 1, '泗洪县公安局指挥中心', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 12, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (13, '情报信息科', '321324330100', 12, '泗洪县公安局指挥中心情报信息科', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 13, 'admin', sysdate, sysdate, 'admin', null, 'b');

insert into AU_T_DEPT (dept_id, dept_name, dept_no, parent_id, address, create_date, leaf, sort_no, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (14, '青阳派出所', '321324520000', 1, '泗洪县公安局青阳派出所', to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'), 0, 14, 'admin', sysdate, sysdate, 'admin', null, 'b');

commit;

