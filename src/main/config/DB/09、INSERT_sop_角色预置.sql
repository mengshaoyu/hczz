insert into AU_T_ROLE (ROLE_ID, ROLENAME, DESCRIPTION, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1000, '普通民警', '主要进行业务申请等操作', '0', '1', sysdate,sysdate, '1', null, '0');

insert into AU_T_ROLE (ROLE_ID, ROLENAME, DESCRIPTION, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1001, '分管领导', '主要负责自己单位的业务审批', '0', '1', sysdate,sysdate, '1', null, '0');

insert into AU_T_ROLE (ROLE_ID, ROLENAME, DESCRIPTION, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002, '值班长', '主要负责接收业务申请单位的申请，并进行审批、任务派遣', '0', '1', sysdate,sysdate, '1', null, '0');

insert into AU_T_ROLE (ROLE_ID, ROLENAME, DESCRIPTION, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1003, '研判民警', '负责对提报的线索进行协查，并及时反馈', '0', '1', sysdate,sysdate, '1', null, '0');

insert into AU_T_ROLE (ROLE_ID, ROLENAME, DESCRIPTION, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1004, '审批人', '合成民警业务申请进行审批', '0', '1', sysdate,sysdate, '1', null, '0');

commit;


insert into AU_T_ROLE_DATAAUTH (ROLE_ID, AUTH_TYPE, SQL_STATMENT, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1000', '3', null, '1', to_date('19-09-2017 15:27:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('19-09-2017 15:27:25', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');

insert into AU_T_ROLE_DATAAUTH (ROLE_ID, AUTH_TYPE, SQL_STATMENT, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1001', '2', null, '1', to_date('19-09-2017 15:27:35', 'dd-mm-yyyy hh24:mi:ss'), to_date('19-09-2017 15:27:35', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');

insert into AU_T_ROLE_DATAAUTH (ROLE_ID, AUTH_TYPE, SQL_STATMENT, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1002', '1', null, '1', to_date('19-09-2017 15:27:40', 'dd-mm-yyyy hh24:mi:ss'), to_date('19-09-2017 15:27:40', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');

insert into AU_T_ROLE_DATAAUTH (ROLE_ID, AUTH_TYPE, SQL_STATMENT, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('1003', '1', null, '1', to_date('19-09-2017 15:27:44', 'dd-mm-yyyy hh24:mi:ss'), to_date('19-09-2017 15:27:44', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');

insert into AU_T_ROLE_DATAAUTH (ROLE_ID, AUTH_TYPE, SQL_STATMENT, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('2', '1', null, '1', to_date('19-09-2017 15:27:49', 'dd-mm-yyyy hh24:mi:ss'), to_date('19-09-2017 15:27:49', 'dd-mm-yyyy hh24:mi:ss'), '1', null, 'b');
commit;