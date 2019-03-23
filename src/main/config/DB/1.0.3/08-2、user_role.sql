
--20171102 xuqiu 添加角色
insert into au_t_role (role_id,rolename,description,domain_name,create_by,create_dt,update_by,update_dt,act_by_type)
values (1005,'一级审批人','',0,1,sysdate,1,sysdate,0);
insert into au_t_role (role_id,rolename,description,domain_name,create_by,create_dt,update_by,update_dt,act_by_type)
values (1006,'二级审批人','',0,1,sysdate,1,sysdate,0);
commit;

--20171109 xuqiu 角色预置
delete from au_t_user_role where role_id in ('1005','1006');
insert into AU_T_USER_ROLE (rel_id, user_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_USER_ROLE.Nextval, (select user_id from au_t_user where user_no = '020038'), 1006, '1', sysdate, sysdate, '1', null, 'b');

insert into AU_T_USER_ROLE (rel_id, user_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_USER_ROLE.Nextval, (select user_id from au_t_user where user_no = '114748'), 1005, '1', sysdate, sysdate, '1', null, 'b');
insert into AU_T_USER_ROLE (rel_id, user_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_USER_ROLE.Nextval, (select user_id from au_t_user where user_no = '019951'), 1005, '1', sysdate, sysdate, '1', null, 'b');
insert into AU_T_USER_ROLE (rel_id, user_id, role_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values (SQ_USER_ROLE.Nextval, (select user_id from au_t_user where user_no = '113763'), 1005, '1', sysdate, sysdate, '1', null, 'b');

commit;