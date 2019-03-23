
delete from au_t_menu where MENU_ID='100108';
commit;

insert into au_t_menu (MENU_ID, MENU_NAME, MENU_ACTION, MENU_PARENTID, MENU_DISABLED, MENU_SORT_ORDER, ICONCLS, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100108', '��������', 'caseinfo/init', '1002', '0', '3', null, '0', 'admin', sysdate, sysdate, 'admin', null, 'b');
commit;

---AU_T_FUNCTION

delete from AU_T_FUNCTION  t where t.FUNCTION_ID like'100108%';

commit;

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1001080100, '��ʼ��', 'caseinfo/init', '��ʼ��', '0', '��ʼ��', 'query', 'icon-search', 1, '1', null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1001080101, '��ѯ', 'caseinfo/getlist', '������ѯ', '1', '��ѯ', 'query', 'icon-search', 1, '2', 'Y', 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1001080201, '����', 'caseinfo/imports', '��������', '1', '����', 'openImport', 'icon-xls-imp', 3, '2', null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1001080301, '����', 'caseinfo/reload', '����', '1', '����', 'resetQuery', 'icon-clear', 2, '2', null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1001080401, 'ˢ��', 'caseinfo/refresh', 'ˢ��', '1', 'ˢ��', 'refresh', 'icon-reload', 4, '2', null, 'admin', sysdate,sysdate, 'admin', null, 'b');

commit;

----AU_T_PERMISSION

delete from AU_T_PERMISSION t where t.PERMISSION_ID like'100108%';
commit;

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (100108, '��������', 1001, 0, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10010801, '��ѯ', 100108, 1, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10010802, '����', 100108, 3, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10010803, 'ˢ��', 100108, 4, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10010804, '����', 100108, 2, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

commit;

-----AU_T_PERMISSION_FUNCTION 

delete from AU_T_PERMISSION_FUNCTION t where t.PERMISSION_ID like'100108%';
commit;
insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 100108, 1001080100, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10010801, 1001080101, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10010802, 1001080201, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10010803, 1001080301, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION_FUNCTION (PK, PERMISSION_ID, FUNCTION_ID, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (SQ_PERMISSION_FUNCTION.nextval, 10010804, 1001080401, 'admin', sysdate,sysdate, 'admin', null, 'b');

commit;

delete from AU_T_ROLE_PERMISSION t where t.PERMISSION_ID like'100108%';
commit;

--ȥ���ǹ���Ա��ɫ��ϵͳ����˵���Ȩ��
delete from AU_T_ROLE_PERMISSION t where t.PERMISSION_ID = '1001' and t.role_id in (1000,1001,1002,1003);
commit;



