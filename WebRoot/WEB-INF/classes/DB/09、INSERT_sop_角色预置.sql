insert into AU_T_ROLE (ROLE_ID, ROLENAME, DESCRIPTION, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1000, '��ͨ��', '��Ҫ����ҵ������Ȳ���', '0', '1', sysdate,sysdate, '1', null, '0');

insert into AU_T_ROLE (ROLE_ID, ROLENAME, DESCRIPTION, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1001, '�ֹ��쵼', '��Ҫ�����Լ���λ��ҵ������', '0', '1', sysdate,sysdate, '1', null, '0');

insert into AU_T_ROLE (ROLE_ID, ROLENAME, DESCRIPTION, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1002, 'ֵ�೤', '��Ҫ�������ҵ�����뵥λ�����룬������������������ǲ', '0', '1', sysdate,sysdate, '1', null, '0');

insert into AU_T_ROLE (ROLE_ID, ROLENAME, DESCRIPTION, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1003, '������', '������ᱨ����������Э�飬����ʱ����', '0', '1', sysdate,sysdate, '1', null, '0');

insert into AU_T_ROLE (ROLE_ID, ROLENAME, DESCRIPTION, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1004, '������', '�ϳ���ҵ�������������', '0', '1', sysdate,sysdate, '1', null, '0');

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