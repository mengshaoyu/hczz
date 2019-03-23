
delete from AU_T_USER;

commit;

insert into AU_T_USER (user_id, username, gender, age, login_account, login_pwd, id_no, mobile_phone, emial, cur_state, login_amount, dept_id, user_no, address, remark, police_type, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (1, 'admin', '1', 25, 'admin', '21218cca77804d2ba1922c33e0151105', '37082819891211595x', '15244218527', 'admin@163.com', '0', 0, 100, '666666', '无', null, null, 'admin', sysdate, sysdate, '1', null, '0');

insert into AU_T_USER (user_id, username, gender, age, login_account, login_pwd, id_no, mobile_phone, emial, cur_state, login_amount, dept_id, user_no, address, remark, police_type, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (2, '冯志刚', '1', null, '135430', '21218cca77804d2ba1922c33e0151105', '2', null, null, '0', 0, 14, '135430', '无', null, '1', '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_USER (user_id, username, gender, age, login_account, login_pwd, id_no, mobile_phone, emial, cur_state, login_amount, dept_id, user_no, address, remark, police_type, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (3, '高睿', '1', null, '135167', '21218cca77804d2ba1922c33e0151105', '3', null, null, '0', 0, 4, '135167', '无', null, '1', '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_USER (user_id, username, gender, age, login_account, login_pwd, id_no, mobile_phone, emial, cur_state, login_amount, dept_id, user_no, address, remark, police_type, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (4, '王空军', '1', null, '135419', '21218cca77804d2ba1922c33e0151105', '4', null, null, '0', 0, 3, '135419', '无', null, '1', '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_USER (user_id, username, gender, age, login_account, login_pwd, id_no, mobile_phone, emial, cur_state, login_amount, dept_id, user_no, address, remark, police_type, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (5, '张毅', '1', null, '135002', '21218cca77804d2ba1922c33e0151105', '5', null, null, '0', 0, 1, '135002', '无', null, '1', '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_USER (user_id, username, gender, age, login_account, login_pwd, id_no, mobile_phone, emial, cur_state, login_amount, dept_id, user_no, address, remark, police_type, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (6, '陈德杨', '1', null, '135417', '21218cca77804d2ba1922c33e0151105', '6', null, null, '0', 0, 14, '135417', '无', null, '1', '1', sysdate, sysdate, '1', null, 'b');

commit;
