
--业务类型 codevalue
delete from sy_t_codevalue where type_id = 1006;
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (sq_code_value.nextval,'图侦','1024',1006,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (sq_code_value.nextval,'情报','1025',1006,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (sq_code_value.nextval,'技侦','1026',1006,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (sq_code_value.nextval,'网侦','1027',1006,'1',sysdate,'1',sysdate,'b');
insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (sq_code_value.nextval,'刑警','1028',1006,'1',sysdate,'1',sysdate,'b');

commit;

delete from sy_t_code where type_id='1010';
insert into sy_t_code (type_id, type_name, type_desc, edit_flag, domain_name, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values ('1010', '常用语', '常用语', '1', '0', '1', sysdate, sysdate, '1', '', 'b');
commit;
delete from sy_t_codevalue where type_id='1010';
insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values ('101001', '同意合成申请', '1', '1010', '0', sysdate, sysdate, '1', '', 'b');
insert into sy_t_codevalue (pk, value_desc, code_value, type_id, create_by, create_dt, update_dt, update_by, delete_by, act_by_type)
values ('101002', '退帧补充', '2', '1010', '0', sysdate, sysdate, '1', '', 'b');
commit;