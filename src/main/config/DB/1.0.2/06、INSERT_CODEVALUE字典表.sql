--业务类型 code
delete from sy_t_code where type_id = 2001;

insert into sy_t_code (type_id,type_name,type_desc,edit_flag,domain_name,create_by,create_dt,update_by,update_dt,act_by_type)
values (2001,'案件类型','案件类型','1','0','1',sysdate,'1',sysdate,'b');

commit;

delete from sy_t_codevalue where type_id = 2001;

insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'行政','1',2001,'1',sysdate,'1',sysdate,'b');

insert into sy_t_codevalue (pk,value_desc,code_value,type_id,create_by,create_dt,update_by,update_dt,act_by_type)
values (SQ_CODE_VALUE.nextval,'刑事','2',2001,'1',sysdate,'1',sysdate,'b');

commit;