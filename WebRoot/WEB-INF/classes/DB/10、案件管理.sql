
delete from au_t_menu where MENU_ID='100108';
commit;

insert into au_t_menu (MENU_ID, MENU_NAME, MENU_ACTION, MENU_PARENTID, MENU_DISABLED, MENU_SORT_ORDER, ICONCLS, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values ('100108', '案件管理', 'caseinfo/init', '1002', '0', '3', null, '0', 'admin', sysdate, sysdate, 'admin', null, 'b');
commit;

---AU_T_FUNCTION

delete from AU_T_FUNCTION  t where t.FUNCTION_ID like'100108%';

commit;

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1001080100, '初始化', 'caseinfo/init', '初始化', '0', '初始化', 'query', 'icon-search', 1, '1', null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1001080101, '查询', 'caseinfo/getlist', '案件查询', '1', '查询', 'query', 'icon-search', 1, '2', 'Y', 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1001080201, '导入', 'caseinfo/imports', '案件导入', '1', '导入', 'openImport', 'icon-xls-imp', 3, '2', null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1001080301, '重置', 'caseinfo/reload', '重置', '1', '重置', 'resetQuery', 'icon-clear', 2, '2', null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_FUNCTION (FUNCTION_ID, FUNCTIONNAME, URL, DESCPT, IS_TOOLBAR, TOOBAR_TITLE, JS_METHOD, ICON_CSS, TOOLBAR_ORDER, OPEN_METHOD, HASAUDATA, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (1001080401, '刷新', 'caseinfo/refresh', '刷新', '1', '刷新', 'refresh', 'icon-reload', 4, '2', null, 'admin', sysdate,sysdate, 'admin', null, 'b');

commit;

----AU_T_PERMISSION

delete from AU_T_PERMISSION t where t.PERMISSION_ID like'100108%';
commit;

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (100108, '案件管理', 1001, 0, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10010801, '查询', 100108, 1, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10010802, '导入', 100108, 3, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10010803, '刷新', 100108, 4, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

insert into AU_T_PERMISSION (PERMISSION_ID, AUTHORITY_NAME, PARENT_ID, SORT_ORDER, DOMAIN_NAME, CREATE_BY, CREATE_DT, UPDATE_DT, UPDATE_BY, DELETE_BY, ACT_BY_TYPE)
values (10010804, '重置', 100108, 2, null, 'admin', sysdate,sysdate, 'admin', null, 'b');

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

--去除非管理员角色的系统管理菜单的权限
delete from AU_T_ROLE_PERMISSION t where t.PERMISSION_ID = '1001' and t.role_id in (1000,1001,1002,1003);
commit;



